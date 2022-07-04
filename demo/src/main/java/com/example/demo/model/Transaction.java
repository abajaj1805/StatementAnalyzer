package com.example.demo.model;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

    Date trxDate;
    BigDecimal trxAmount;
    String trxCategory;

    public Transaction(Date trxDate, BigDecimal trxAmount, String trxCategory) {
        this.trxDate = trxDate;
        this.trxAmount = trxAmount;
        this.trxCategory = trxCategory;
    }

    public Date getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(Date trxDate) {
        this.trxDate = trxDate;
    }

    public BigDecimal getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(BigDecimal trxAmount) {
        this.trxAmount = trxAmount;
    }

    public String getTrxCategory() {
        return trxCategory;
    }

    public void setTrxCategory(String trxCategory) {
        this.trxCategory = trxCategory;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trxDate=" + trxDate +
                ", trxAmount=" + trxAmount +
                ", trxCategory='" + trxCategory + '\'' +
                '}';
    }
}
