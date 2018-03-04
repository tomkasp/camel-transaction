package com.tomkasp.cameltransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
public class TransactionController {

    @Autowired
    AccountService accountService;

    @GetMapping(value = "/create")
    public String getAccount(@RequestParam("number") String number) {
        return accountService.createAccount(number).toString();
    }

    @GetMapping(value = "/update")
    public String updateAccount(@RequestParam("id") Long id, @RequestParam("newnumber") String newNumber) {
        return accountService.updateAccount(id, newNumber).toString();
    }
}
