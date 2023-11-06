package com.estate.service;

import com.estate.dto.TransactionDTO;
import com.estate.dto.request.TransactionRequest;
import com.estate.dto.respone.TransactionResponse;

import java.util.List;

public interface ITransactionService {

    List<TransactionResponse> getTransactionsByCustomerId(Long customerId);

    TransactionDTO createTransaction(TransactionRequest transactionRequest);
}
