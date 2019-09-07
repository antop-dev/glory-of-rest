package org.antop.pox.endpoint;

import org.antop.pox.exception.NotFoundOperationException;
import org.antop.pox.model.Slot;
import org.antop.pox.service.DoctorService;
import org.antop.pox.service.SlotService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

@RestController
public class AppointmentEndpoint {
    private final DoctorService doctorService;
    private final SlotService slotService;

    public AppointmentEndpoint(DoctorService doctorService, SlotService slotService) {
        this.doctorService = doctorService;
        this.slotService = slotService;
    }

    @PostMapping(path = "/appointmentService",
            consumes = {MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object endpoint(@RequestBody String payload) throws Exception {
        String nodeName = getNodeName(payload);

        if (nodeName.equals("openSlotRequest")) {
            try (StringReader reader = new StringReader(payload)) {
                Unmarshaller unmarshaller = JAXBContext.newInstance(OpenSlotRequest.class).createUnmarshaller();
                OpenSlotRequest request = (OpenSlotRequest) unmarshaller.unmarshal(reader);

                List<Slot> slots = doctorService.openSlotList(request);
                return OpenSlotListResponse.of(slots);
            }
        }

        if (nodeName.equals("appointmentRequest")) {
            try (StringReader reader = new StringReader(payload)) {
                Unmarshaller unmarshaller = JAXBContext.newInstance(AppointmentRequest.class).createUnmarshaller();
                AppointmentRequest request = (AppointmentRequest) unmarshaller.unmarshal(reader);
                return slotService.appointment(request);
            } catch (Exception e) {
                return new AppointmentRequestFailure(e.getMessage());
            }
        }

        throw new NotFoundOperationException(nodeName);
    }

    // XML의 루트 태그명을 가져온다
    private String getNodeName(String xml) throws ParserConfigurationException, IOException, SAXException {
        try (Reader reader = new StringReader(xml)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // https://find-sec-bugs.github.io/bugs.htm#XXE_DOCUMENT
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            InputSource is = new InputSource(new StringReader(xml));
            Document document = factory.newDocumentBuilder().parse(is);
            Element root = document.getDocumentElement();

            return root.getNodeName();
        }

    }

}
