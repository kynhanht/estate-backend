package com.estate.controller;

import com.estate.dto.TransactionDTO;
import com.estate.dto.request.TransactionRequest;
import com.estate.dto.respone.TransactionResponse;
import com.estate.service.ITransactionService;
import com.estate.utils.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionService transactionService;

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByCustomerId(@PathVariable("id") Long customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.getTransactionsByCustomerId(customerId));
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(transactionRequest));
    }

    @GetMapping("/types")
    public ResponseEntity<Map<String, String>> getTransactionTypes() {
        return ResponseEntity.status(HttpStatus.OK).body(SystemUtils.getTransactions());
    }

}
