package org.antop.resources.endpoint;

import org.antop.resources.exception.NotFoundOperationException;
import org.antop.resources.exception.SlotNotAvailableException;
import org.antop.resources.model.Appointment;
import org.antop.resources.model.Slot;
import org.antop.resources.service.DoctorService;
import org.antop.resources.service.SlotService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class AppointmentEndpoint {
    private final DoctorService doctorService;
    private final SlotService slotService;

    public AppointmentEndpoint(DoctorService doctorService, SlotService slotService) {
        this.doctorService = doctorService;
        this.slotService = slotService;
    }

    @PostMapping(path = "/doctors/{id}",
            consumes = {MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object doctors(@PathVariable("id") String id, @RequestBody String payload) throws IOException, SAXException, ParserConfigurationException, InvocationTargetException, IllegalAccessException {
        Element root = parseXml(payload);
        String methodName = root.getNodeName(); // root node name = method name
        Method method = findMethod(doctorService.getClass(), methodName);

        if (Objects.nonNull(method)) {
            LocalDate date = LocalDate.parse(root.getAttribute("date"), DateTimeFormatter.ISO_LOCAL_DATE);

            List<Slot> slots = (List<Slot>) method.invoke(doctorService, id, date);
            return OpenSlotListResponse.of(slots);
        }
        throw new NotFoundOperationException(methodName);
    }

    @PostMapping(path = "/slots/{id}",
            consumes = {MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object slots(@PathVariable("id") int id, @RequestBody String payload) throws IOException, SAXException, ParserConfigurationException, InvocationTargetException, IllegalAccessException {
        Element root = parseXml(payload);
        String methodName = root.getNodeName(); // root node name = method name
        Method method = findMethod(slotService.getClass(), methodName);

        if (Objects.nonNull(method)) {
            String patient = root.getElementsByTagName("patient").item(0).getAttributes().getNamedItem("id").getNodeValue();
            Optional<Appointment> optional = (Optional<Appointment>) method.invoke(slotService, id, patient);
            return optional.orElseThrow(SlotNotAvailableException::new);
        }
        throw new NotFoundOperationException(methodName);
    }

    private Element parseXml(String xml) throws ParserConfigurationException, IOException, SAXException {
        try (Reader reader = new StringReader(xml)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // https://find-sec-bugs.github.io/bugs.htm#XXE_DOCUMENT
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            InputSource is = new InputSource(reader);
            Document document = factory.newDocumentBuilder().parse(is);
            return document.getDocumentElement();
        }
    }

    private Method findMethod(Class<?> cl, String methodName) {
        return Arrays.stream(cl.getMethods())
                .filter(it -> it.getName().equals(methodName))
                .findFirst()
                .orElse(null);
    }

}
