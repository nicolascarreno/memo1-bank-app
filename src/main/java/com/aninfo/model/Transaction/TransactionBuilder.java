package com.aninfo.model.Transaction;

public class TransactionBuilder {

    public Transaction newDeposit(long cbu, double amount) {
        Deposit deposit = new Deposit();
        deposit.setDeposit();
        deposit.setTransaction(cbu, amount);

        return deposit;
    }

    public Transaction newWithdraw(long cbu, double amount) {
        Withdraw withdraw = new Withdraw();
        withdraw.setWithdraw();
        withdraw.setTransaction(cbu, amount);

        return withdraw;
    }
}
