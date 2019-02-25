package com.tomkasp.cameltransaction;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    private final AccountRepositoryJpaImpl accountRepositoryJpa;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final PlatformTransactionManager platformTransactionManager;

    private final TransactionTemplate transactionTemplate;

    private final AsyncAccountService asyncAccountService;

    public AccountService(AccountRepository accountRepository, AccountRepositoryJpaImpl accountRepositoryJpa, ApplicationEventPublisher applicationEventPublisher, PlatformTransactionManager platformTransactionManager, TransactionTemplate transactionTemplate, AsyncAccountService asyncAccountService) {
        this.accountRepository = accountRepository;
        this.accountRepositoryJpa = accountRepositoryJpa;
        this.applicationEventPublisher = applicationEventPublisher;
        this.platformTransactionManager = platformTransactionManager;
        this.transactionTemplate = transactionTemplate;
        this.asyncAccountService = asyncAccountService;
        transactionTemplate = new TransactionTemplate(platformTransactionManager);
    }


    public void createAccount(String number) {
        transactionTemplate.execute(transactionStatus -> {
           return accountRepository.save(new Account(number));
        });
//        accountRepository.save(new Account(number));
        asyncAccountService.asynTest();
//        applicationEventPublisher.publishEvent(new CreatedEvent(number));
    }

    public void updateAccount(Long id, String newNumber) {
        final Account one = accountRepository.getOne(id);
        one.updateNumber(newNumber);
        final TransactionStatus transactionStatus = TransactionAspectSupport.currentTransactionStatus();
        accountRepositoryJpa.updateAccount(one);
        applicationEventPublisher.publishEvent(new UpdatedEvent(newNumber));
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    @Async
    public void eventListener(CreatedEvent createdEvent) {
        final List<Account> all = accountRepository.findAll();
    }


    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    @Async
    public void eventListener(UpdatedEvent createdEvent) {
        final List<Account> all = accountRepository.findAll();
    }

}

//PlatformTransactionManager -> JpaTransactionManager

//2018-03-03 17:11:10.785DEBUG 8812---[nio-8080-exec-3]o.h.e.t.internal.TransactionImpl:begin
//2018-03-03 17:11:10.788TRACE 8812---[nio-8080-exec-3]o.s.t.i.TransactionInterceptor:Getting transaction for[com.tomkasp.cameltransaction.AccountRepositoryJpaImpl.updateAccount]
//2018-03-03 17:11:10.919TRACE 8812---[nio-8080-exec-3]o.s.t.i.TransactionInterceptor:Completing transaction for[com.tomkasp.cameltransaction.AccountRepositoryJpaImpl.updateAccount]
//2018-03-03 17:11:10.920DEBUG 8812---[nio-8080-exec-3]o.h.e.t.internal.TransactionImpl:committing