package org.antop.account

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class BankApiApplicationTests {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun getAccounts() {
        mockMvc.perform(get("/accounts"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$._embedded.accountList").isArray)
                .andExpect(jsonPath("\$._embedded.accountList.size()").value(2))
                .andExpect(jsonPath("\$._embedded.accountList[0]._links").isNotEmpty)
        ;
    }

    @Test
    fun getAccount() {
        val accountId = 1;

        mockMvc.perform(get("/accounts/$accountId"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$.id").value(accountId))
                .andExpect(jsonPath("\$.balance").value(1000))
                .andExpect(jsonPath("\$._links.deposit.href").isNotEmpty)
                .andExpect(jsonPath("\$._links.withdraw.href").isNotEmpty)
                .andExpect(jsonPath("\$._links.transfer.href").isNotEmpty)
                .andExpect(jsonPath("\$._links.close.href").isNotEmpty)
        ;
    }

}
