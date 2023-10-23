package com.aninfo.service;

import com.aninfo.exceptions.AccountDoesNotExistException;
import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
//import com.aninfo.model.Transaction.Deposit;
import com.aninfo.model.Transaction.Transaction;
import com.aninfo.repository.AccountRepository;
//import com.aninfo.repository.TransactionRepository;
import com.aninfo.repository.TransactionRepositoryHelper;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private TransactionRepositoryHelper transactionRepositoryHelper;

    public AccountService(){
        this.transactionRepositoryHelper = new TransactionRepositoryHelper();
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long cbu) {
        return accountRepository.findById(cbu);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void deleteById(Long cbu) {
        accountRepository.deleteById(cbu);
    }

    public Optional<Transaction> getTransaction(long id){ return Optional.ofNullable(transactionRepository.findTransactionById(id)); }

    public void deleteTransactionById(Long id) { transactionRepository.deleteById(id); }

    public Optional<Collection<Transaction>> getTransactionsFromAccount(Long cbu) {
        if (!accountRepository.existsById(cbu)) {
            throw new AccountDoesNotExistException("Account does not exist");
        }

        return Optional.ofNullable(transactionRepositoryHelper.transactionsByAccount(transactionRepository.findAll(), cbu));
    }

    @Transactional
    public Account withdraw(Long cbu, Double sum) {
        Account account = accountRepository.findAccountByCbu(cbu);

        if (account.getBalance() < sum) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - sum);
        accountRepository.save(account);

        Transaction withdraw = transactionRepositoryHelper.withdraw(cbu, sum);
        transactionRepository.save(withdraw);

        return account;
    }

    @Transactional
    public Account deposit(Long cbu, Double sum) {

        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }

        if (sum >= 2000) {
            double extra = (sum*10)/100;
            if (extra <= 500) {
                sum = sum + extra;
            }
            else {
                sum = sum + 500;
            }
        }

        Account account = accountRepository.findAccountByCbu(cbu);
        account.setBalance(account.getBalance() + sum);
        accountRepository.save(account);

        Transaction deposit = transactionRepositoryHelper.deposit(cbu, sum);
        transactionRepository.save(deposit);

        return account;
    }

}
