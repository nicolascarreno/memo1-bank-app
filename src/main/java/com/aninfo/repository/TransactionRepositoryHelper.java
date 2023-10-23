package com.aninfo.repository;

//import com.aninfo.model.Transaction.Deposit;
//import com.aninfo.model.Transaction.Withdraw;
import com.aninfo.model.Transaction.Transaction;
import com.aninfo.model.Transaction.TransactionBuilder;
        import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.ArrayList;
        import java.util.List;

@RepositoryRestResource
public class TransactionRepositoryHelper {

    public TransactionRepositoryHelper(){}


    public List<Transaction> transactionsByAccount(List<Transaction> transactions, Long cbu){
        List<Transaction> accountTransactions = new ArrayList<Transaction>();

        for (Transaction transaction: transactions) {
            if (transaction.getCbu() == cbu) {
                accountTransactions.add(transaction);
            }
        }

        return accountTransactions;
    }

    public Transaction deposit(long cbu, double amount){
        return new TransactionBuilder().newDeposit(cbu, amount);
    }

    public Transaction withdraw(long cbu, double amount){
        return new TransactionBuilder().newWithdraw(cbu, amount);
    }

}
