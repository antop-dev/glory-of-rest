package org.antop.verbs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.http.MediaType.APPLICATION_XML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VerbsApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void openSlotListRequest() throws Exception {
        String doctor = "mjones";

        MockHttpServletRequestBuilder builder = get("/doctors/mjones/slots")
                .param("date", "20100104")
                .param("status", "open")
                .accept(APPLICATION_XML)
                .contentType(APPLICATION_XML);

        mockMvc
                .perform(builder)
                .andDo(print())
                .andExpect(xpath("//openSlotList/slot").nodeCount(2))
                .andExpect(xpath("//openSlotList/slot[1]/@id").string("1234"))
                .andExpect(xpath("//openSlotList/slot[1]/@start").string("1400"))
                .andExpect(xpath("//openSlotList/slot[1]/@end").string("1450"))
                .andExpect(xpath("//openSlotList/slot[1]/@doctor").string(doctor))
                .andExpect(xpath("//openSlotList/slot[2]/@id").string("5678"))
                .andExpect(xpath("//openSlotList/slot[2]/@start").string("1600"))
                .andExpect(xpath("//openSlotList/slot[2]/@end").string("1650"))
                .andExpect(xpath("//openSlotList/slot[2]/@doctor").string(doctor))
        ;
    }

    @Test
    public void appointmentRequest() throws Exception {
        int slotId = 1234;
        String patient = "jsmith";

        MockHttpServletRequestBuilder builder = post("/slots/" + slotId)
                .accept(APPLICATION_XML)
                .contentType(APPLICATION_XML)
                .content("<appointmentRequest>\n" +
                        "  <patient id=\"" + patient + "\"/>\n" +
                        "</appointmentRequest>");

        mockMvc
                .perform(builder)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/slots/" + slotId + "/appointment"))
                .andExpect(xpath("//appointment").exists())
                .andExpect(xpath("//appointment/slot").exists())
                .andExpect(xpath("//appointment/slot/@id").string("" + slotId))
                .andExpect(xpath("//appointment/patient/@id").string(patient))
        ;
    }

    @Test
    public void appointmentRequestFailure() throws Exception {
        int slotId = 9999;
        String patient = "jsmith";

        MockHttpServletRequestBuilder builder = post("/slots/" + slotId)
                .accept(APPLICATION_XML)
                .contentType(APPLICATION_XML)
                .content("<appointmentRequest>\n" +
                        "  <patient id=\"" + patient + "\"/>\n" +
                        "</appointmentRequest>");

        mockMvc
                .perform(builder)
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(xpath("//appointment").doesNotExist())
                .andExpect(xpath("//appointmentRequestFailure").exists())
                .andExpect(xpath("//appointmentRequestFailure/reason").string("Slot not available"))
        ;
    }

}
