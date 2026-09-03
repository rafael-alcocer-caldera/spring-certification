package rafael.alcocer.caldera.spring.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import rafael.alcocer.caldera.spring.repository.AccountRepository;

@Component
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;

    public TransferServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    // SOLUTION
    /*
    public TransferServiceImpl(@Qualifier("jdbcAccountRepository") AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    */
}
