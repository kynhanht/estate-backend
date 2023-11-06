package com.estate.controller;

import com.estate.dto.TransactionDTO;
import com.estate.dto.request.TransactionRequest;
import com.estate.dto.respone.TransactionResponse;
import com.estate.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionService transactionService;

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByCustomerId(@PathVariable("id") Long customerId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.getTransactionsByCustomerId(customerId));
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(transactionRequest));
    }

}
