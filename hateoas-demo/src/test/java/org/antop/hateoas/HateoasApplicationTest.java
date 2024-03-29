package org.antop.hateoas;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HateoasApplicationTest {
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
                .andDo(MockMvcResultHandlers.print())
                .andExpect(xpath("//openSlotList/slot").nodeCount(2))

                .andExpect(xpath("//openSlotList/slot[1]/@id").string("1234"))
                .andExpect(xpath("//openSlotList/slot[1]/@start").string("1400"))
                .andExpect(xpath("//openSlotList/slot[1]/@end").string("1450"))
                .andExpect(xpath("//openSlotList/slot[1]/@doctor").string(doctor))
                .andExpect(xpath("//openSlotList/slot[1]/link/@href").string("/slots/1234"))

                .andExpect(xpath("//openSlotList/slot[2]/@id").string("5678"))
                .andExpect(xpath("//openSlotList/slot[2]/@start").string("1600"))
                .andExpect(xpath("//openSlotList/slot[2]/@end").string("1650"))
                .andExpect(xpath("//openSlotList/slot[2]/@doctor").string(doctor))
                .andExpect(xpath("//openSlotList/slot[2]/link/@href").string("/slots/5678"))
        ;
    }

    @Test
    public void appointmentRequest() throws Exception {
        int slotId = 1234;
        String patient = "jsmith";

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/slots/" + slotId)
                .accept(APPLICATION_XML)
                .contentType(APPLICATION_XML)
                .content("<appointmentRequest>\n" +
                        "  <patient id=\"" + patient + "\"/>\n" +
                        "</appointmentRequest>");

        mockMvc
                .perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(xpath("//appointment").exists())
                .andExpect(xpath("//appointment/slot").exists())
                .andExpect(xpath("//appointment/slot/@id").string("" + slotId))
                .andExpect(xpath("//appointment/patient/@id").string(patient))

                .andExpect(xpath("//appointment/link").nodeCount(6))
        ;
    }

}
