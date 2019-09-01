package org.antop.pox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.http.MediaType.APPLICATION_XML;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentTest  {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void openSlotListRequest() throws Exception {
        String doctor = "mjones";

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/appointmentService")
                .accept(APPLICATION_XML)
                .contentType(APPLICATION_XML)
                .content("<openSlotRequest date=\"2010-01-04\" doctor=\"" + doctor + "\"/>");

        mockMvc
                .perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(xpath("count(//openSlotList/slot)").number(2.0))
                .andExpect(xpath("//openSlotList/slot[1]/@start").string("1400"))
                .andExpect(xpath("//openSlotList/slot[1]/@end").string("1450"))
                .andExpect(xpath("//openSlotList/slot[1]/@doctor").string(doctor))
                .andExpect(xpath("//openSlotList/slot[2]/@start").string("1600"))
                .andExpect(xpath("//openSlotList/slot[2]/@end").string("1650"))
                .andExpect(xpath("//openSlotList/slot[2]/@doctor").string(doctor))
        ;
    }

    @Test
    public void appointmentRequest() throws Exception {
        String doctor = "mjones";
        String start = "1400";
        String end = "1450";
        String patient = "jsmith";

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/appointmentService")
                .accept(APPLICATION_XML)
                .contentType(APPLICATION_XML)
                .content("<appointmentRequest>\n" +
                        "  <slot doctor=\"" + doctor + "\" start=\"" + start + "\" end=\"" + end + "\"/>\n" +
                        "  <patient id=\"" + patient + "\"/>\n" +
                        "</appointmentRequest>");

        mockMvc
                .perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(xpath("//appointment").exists())
                .andExpect(xpath("//appointment/slot").exists())
                .andExpect(xpath("//appointment/slot/@doctor").string(doctor))
                .andExpect(xpath("//appointment/slot/@start").string(start))
                .andExpect(xpath("//appointment/slot/@end").string(end))
                .andExpect(xpath("//appointment/patient").exists())
                .andExpect(xpath("//appointment/patient/@id").string(patient))
        ;
    }

    @Test
    public void appointmentRequestFailure() throws Exception {
        String doctor = "antop"; // not available
        String start = "1400";
        String end = "1450";
        String patient = "jsmith";

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/appointmentService")
                .accept(APPLICATION_XML)
                .contentType(APPLICATION_XML)
                .content("<appointmentRequest>\n" +
                        "  <slot doctor=\"" + doctor + "\" start=\"" + start + "\" end=\"" + end + "\"/>\n" +
                        "  <patient id=\"" + patient + "\"/>\n" +
                        "</appointmentRequest>");

        mockMvc
                .perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(xpath("//appointment").doesNotExist())
                .andExpect(xpath("//appointmentRequestFailure").exists())
                .andExpect(xpath("//appointmentRequestFailure/reason").string("Slot not available"))
        ;
    }

}
