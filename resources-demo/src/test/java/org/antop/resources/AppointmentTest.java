package org.antop.resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void notFoundOperation() throws Exception {
        mockMvc.perform(
                post("/doctors/antop")
                        .accept(MediaType.APPLICATION_XML)
                        .contentType(MediaType.APPLICATION_XML)
                        .content("<someOperation date=\"2019-08-01\"/>")
        )
                .andDo(print())
                .andExpect(xpath("//error/@message").string("Not found operation someOperation"))
        ;
    }

    @Test
    public void openSlotListRequest() throws Exception {
        String doctor = "mjones";

        mockMvc.perform(
                post("/doctors/" + doctor)
                        .accept(MediaType.APPLICATION_XML)
                        .contentType(MediaType.APPLICATION_XML)
                        .content("<openSlotListRequest date=\"2019-08-01\"/>")
        )
                .andDo(print())
                .andExpect(xpath("count(//openSlotList/slot)").number(9.0))
                .andExpect(xpath("//openSlotList/slot[1]/@start").string("0900"))
                .andExpect(xpath("//openSlotList/slot[1]/@end").string("0940"))
                .andExpect(xpath("//openSlotList/slot[1]/@doctor").string(doctor))
        ;
    }

    @Test
    public void appointmentRequest() throws Exception {
        mockMvc.perform(
                post("/slots/2")
                        .accept(MediaType.APPLICATION_XML)
                        .contentType(MediaType.APPLICATION_XML)
                        .content("<appointmentRequest>\n" +
                                "    <patient id=\"jsmith\"/>\n" +
                                "</appointmentRequest>")
        )
                .andDo(print())
                .andExpect(xpath("//appointment/slot/@id").string("2"))
                .andExpect(xpath("//appointment/slot/@start").string("1100"))
                .andExpect(xpath("//appointment/slot/@end").string("1140"))
                .andExpect(xpath("//appointment/patient/@id").string("jsmith"))
        ;
    }

    @Test
    public void appointmentNonSlot() throws Exception {
        mockMvc.perform(
                post("/slots/99999")
                        .accept(MediaType.APPLICATION_XML)
                        .contentType(MediaType.APPLICATION_XML)
                        .content("<appointmentRequest>\n" +
                                "    <patient id=\"jsmith\"/>\n" +
                                "</appointmentRequest>")
        )
                .andDo(print())
                .andExpect(xpath("//error/@message").string("Slot not available"))
        ;
    }

    @Test
    public void appointmentError() throws Exception {
        mockMvc.perform(
                post("/slots/12")
                        .accept(MediaType.APPLICATION_XML)
                        .contentType(MediaType.APPLICATION_XML)
                        .content("<appointmentRequest>\n" +
                                "    <patient id=\"jsmith\"/>\n" +
                                "</appointmentRequest>")
        )
                .andDo(print())
                .andExpect(xpath("//error/@message").string("Slot not available"))
        ;
    }
}