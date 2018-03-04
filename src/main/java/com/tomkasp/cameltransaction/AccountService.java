package com.tomkasp.cameltransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;

/**
 * @author Tomasz Kasprzycki
 */
@Service
@Transactional
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountRepositoryJpaImpl accountRepositoryJpa;

    public Account createAccount(String number) {
        return accountRepository.save(new Account(number));
    }

    public Account updateAccount(Long id, String newNumber) {
        final Account one = accountRepository.getOne(id);
        one.updateNumber(newNumber);
        return accountRepositoryJpa.updateAccount(one);
    }

}


//2018-03-03 17:11:10.785DEBUG 8812---[nio-8080-exec-3]o.h.e.t.internal.TransactionImpl:begin
//2018-03-03 17:11:10.788TRACE 8812---[nio-8080-exec-3]o.s.t.i.TransactionInterceptor:Getting transaction for[com.tomkasp.cameltransaction.AccountRepositoryJpaImpl.updateAccount]
//2018-03-03 17:11:10.919TRACE 8812---[nio-8080-exec-3]o.s.t.i.TransactionInterceptor:Completing transaction for[com.tomkasp.cameltransaction.AccountRepositoryJpaImpl.updateAccount]
//2018-03-03 17:11:10.920DEBUG 8812---[nio-8080-exec-3]o.h.e.t.internal.TransactionImpl:committing