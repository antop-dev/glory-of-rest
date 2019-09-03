package org.antop.account

import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class AccountService {

    companion object {
        val accounts = mutableListOf(
                Account(1, BigDecimal(1000)),
                Account(2, BigDecimal.ZERO)
        )
    }

    fun getAccounts() = accounts

    fun getAccount(accountId: Int) = accounts.find { it.id == accountId }

}