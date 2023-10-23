package com.aninfo.model.Transaction;

import javax.persistence.Entity;

@Entity
public class Withdraw extends Transaction{

    public Withdraw(){}

    public void setWithdraw() {
        this.tipo = "Withdraw";
    }
}

