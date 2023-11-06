package com.estate.enums;

public enum TransactionEnum {

    TRANSACTION_1("Quá trình CSKH"),
    TRANSACTION_2("Dẫn khách đi xem"),
    TRANSACTION_3("Ký Hợp đồng với khách");

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
