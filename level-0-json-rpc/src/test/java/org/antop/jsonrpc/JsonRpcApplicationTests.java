package org.antop.jsonrpc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.antop.jsonrpc.model.Patient;
import org.antop.jsonrpc.model.Slot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JsonRpcApplicationTests {
    private final Faker faker = new Faker();
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void openSlotRequest() throws Exception {
        // request
        MockHttpServletRequestBuilder servletRequestBuilder = post("/appointmentService")
                .content(jsonRpcRequest("openSlotRequest", openSlotRequestParameter()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_XML);
        // action & print
        mockMvc.perform(servletRequestBuilder).andDo(MockMvcResultHandlers.print())
                // verify
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result.length()").value(2))
                .andExpect(jsonPath("$.result[0].start").value("1400"))
                .andExpect(jsonPath("$.result[1].end").value("1650"))
        ;
    }

    @Test
    public void appointmentRequest() throws Exception {
        // request
        MockHttpServletRequestBuilder servletRequestBuilder = post("/appointmentService")
                .content(jsonRpcRequest("appointmentRequest", appointmentRequestParameter()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_XML);
        // action & print
        mockMvc.perform(servletRequestBuilder).andDo(MockMvcResultHandlers.print())
                // verify
                .andExpect(jsonPath("$.result").exists())
                .andExpect(jsonPath("$.result.slot.start").value("1400"))
                .andExpect(jsonPath("$.result.slot.end").value("1450"))
                .andExpect(jsonPath("$.result.slot.doctor").value("mjones"))
                .andExpect(jsonPath("$.result.patient.id").value("jsmith"))
        ;
    }

    private String jsonRpcRequest(String method, Object params) throws JsonProcessingException {
        JsonRpcRequest request = JsonRpcRequest.of(faker.number().randomDigitNotZero(), method, params);
        return objectMapper.writeValueAsString(request);
    }

    private Map<String, Object> openSlotRequestParameter() {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("date", LocalDate.now());
        parameter.put("doctor", "jsmith");
        return parameter;
    }

    private Map<String, Object> appointmentRequestParameter() {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("slot", Slot.of(LocalTime.of(14, 0), LocalTime.of(14,50), "mjones"));
        parameter.put("patient", Patient.of("jsmith"));
        return parameter;
    }


}
