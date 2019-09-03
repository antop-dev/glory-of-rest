package org.antop.account

import org.springframework.hateoas.Resource
import org.springframework.hateoas.Resources
import org.springframework.hateoas.mvc.ControllerLinkBuilder
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/accounts"])
class AccountController(val accountService: AccountService) {

    @GetMapping
    fun getAccount(): Resources<Resource<Account>> {
        val accounts = accountService.getAccounts()
        val resources = accounts.map { addLink(it) }

        val link = linkTo(AccountController::class.java).withSelfRel()
        return Resources(resources, link)
    }

    @GetMapping("/{accountId}")
    fun getAccount(@PathVariable accountId: Int): Resource<Account>? {
        val account = accountService.getAccount(accountId)
        return account?.let { addLink(it) }
    }

    private fun addLink(account: Account) = account.let {
        val r = Resource(it)
        r.add(linkTo(ControllerLinkBuilder.methodOn(AccountController::class.java).getAccount(it.id)).withSelfRel())
        r.add(linkTo(ControllerLinkBuilder.methodOn(AccountController::class.java).deposit(it.id)).withRel("deposit"))
        r.add(linkTo(ControllerLinkBuilder.methodOn(AccountController::class.java).withdraw(it.id)).withRel("withdraw"))
        r.add(linkTo(ControllerLinkBuilder.methodOn(AccountController::class.java).transfer(it.id)).withRel("transfer"))
        r.add(linkTo(ControllerLinkBuilder.methodOn(AccountController::class.java).close(it.id)).withRel("close"))
        r
    }

    @GetMapping("/{accountId}/deposit")
    fun deposit(@PathVariable accountId: Int) = accountService.getAccount(accountId)

    @PostMapping("/{accountId}/withdraw")
    fun withdraw(@PathVariable accountId: Int) = accountService.getAccount(accountId)

    @PostMapping("/{accountId}/transfer")
    fun transfer(@PathVariable accountId: Int) = accountService.getAccount(accountId)

    @PostMapping("/{accountId}/close")
    fun close(@PathVariable accountId: Int) = accountService.getAccount(accountId)

}