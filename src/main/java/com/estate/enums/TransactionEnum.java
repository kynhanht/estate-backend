package com.estate.enums;

public enum TransactionEnum {

    TRANSACTION_1("Customer Service Progress"),
    TRANSACTION_2("Take Guest To See"),
    TRANSACTION_3("Contract");

    private final String transactionValue;

    TransactionEnum(String transactionValue) {
        this.transactionValue = transactionValue;
    }

    public String getTransactionValue() {
        return transactionValue;
    }

    public static String findTransactionValue(String name) {
        for (TransactionEnum transaction : TransactionEnum.values()) {
            if (transaction.name().equals(name)) {
                return transaction.getTransactionValue();
            }
        }
        return "";
    }
}
