package com.aninfo.model.Transaction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    protected long id;
    protected long cbu;
    protected double amount;
    protected String tipo;


    public void setTransaction(long cbu, double amount) {
        this.cbu = cbu;
        this.amount = amount;
    }

    public long getId() { return this.id; }

    public long getCbu() { return this.cbu; }

    public double getAmount() { return this.amount; }

    public String getType() { return this.tipo; }
}
