package com.aninfo.model.Transaction;

import javax.persistence.Entity;

@Entity
public class Deposit extends Transaction{

    public Deposit(){}

    public void setDeposit() {
        this.tipo = "Deposit";
    }
}
