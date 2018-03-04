package com.tomkasp.cameltransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.EntityManager;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public class AccountRepositoryJpaImpl extends SimpleJpaRepository<Account, Long> {

    EntityManager em;

    @Autowired
    public AccountRepositoryJpaImpl(EntityManager em) {
        super(Account.class, em);
        this.em = em;
    }

    public Account updateAccount(Account account) {
        final TransactionStatus transactionStatus = TransactionAspectSupport.currentTransactionStatus();
        return super.save(account);
    }
}
