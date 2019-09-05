package org.antop.restful;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeApiTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void restful() throws Exception {
        mockMvc.perform(get("/api/employees"))
                .andDo(print())
                .andExpect(jsonPath("$._embedded").hasJsonPath())
                .andExpect(jsonPath("$._links").hasJsonPath())
                .andExpect(jsonPath("$.page").hasJsonPath())
                .andExpect(jsonPath("$._embedded.employees").isArray())
                .andExpect(jsonPath("$.page.totalElements", is(107)))
        ;
    }

}
