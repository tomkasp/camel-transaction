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
    public void getAccount(@RequestParam("number") String number) {
         accountService.createAccount(number);
    }

    @GetMapping(value = "/update")
    public void updateAccount(@RequestParam("id") Long id, @RequestParam("newnumber") String newNumber) {
         accountService.updateAccount(id, newNumber);
    }
}
