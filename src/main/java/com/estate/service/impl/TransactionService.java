package com.estate.service.impl;

import com.estate.constant.ErrorMessageConstants;
import com.estate.converter.TransactionConverter;
import com.estate.dto.TransactionDTO;
import com.estate.dto.request.TransactionRequest;
import com.estate.dto.respone.TransactionResponse;
import com.estate.entity.CustomerEntity;
import com.estate.entity.TransactionEntity;
import com.estate.entity.UserEntity;
import com.estate.enums.TransactionEnum;
import com.estate.exception.NotFoundException;
import com.estate.repository.CustomerRepository;
import com.estate.repository.TransactionRepository;
import com.estate.repository.UserRepository;
import com.estate.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;

    private final CustomerRepository customerRepository;

    private final UserRepository userRepository;

    private final TransactionConverter transactionConverter;

    @Override
    public List<TransactionResponse> getTransactionsByCustomerId(Long customerId) {

        List<TransactionEntity> transactionEntities = transactionRepository.findByCustomer_Id(customerId);
        List<TransactionResponse> responses = new ArrayList<>();

        Arrays.stream(TransactionEnum.values())
                .forEach(transactionEnum -> {
                    TransactionResponse response = new TransactionResponse();
                    response.setCode(transactionEnum.toString());
                    response.setValue(transactionEnum.getTransactionValue());
                    responses.add(response);

                });

        responses.forEach(response -> {
            String code = response.getCode();
            transactionEntities.stream()
                    .filter(transaction -> code.equals(transaction.getCode()))
                    .forEach(transaction -> {
                        TransactionDTO transactionDTO = transactionConverter.convertToDTO(transaction);
                        response.getTransactions().add(transactionDTO);
                    });

        });

        return responses;

    }

    @Override
    public TransactionDTO createTransaction(TransactionRequest transactionRequest) {

        CustomerEntity customerEntity = customerRepository.findById(transactionRequest.getCustomerId())
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.CUSTOMER_NOT_FOUND));
        UserEntity userEntity = userRepository.findById(transactionRequest.getStaffId())
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND));
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setCode(transactionRequest.getCode());
        transactionEntity.setNote(transactionRequest.getNote());
        transactionEntity.setAppointmentDate(transactionRequest.getAppointmentDate());
        transactionEntity.setCustomer(customerEntity);
        transactionEntity.setUser(userEntity);
        return transactionConverter.convertToDTO(transactionRepository.save(transactionEntity));
    }
}
