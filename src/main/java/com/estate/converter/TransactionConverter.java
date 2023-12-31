package com.estate.converter;

import com.estate.dto.TransactionDTO;
import com.estate.entity.TransactionEntity;
import com.estate.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionConverter {

    private final ModelMapper modelMapper;

    public TransactionDTO convertToDTO(TransactionEntity transactionEntity) {
        TransactionDTO transactionDTO = modelMapper.map(transactionEntity, TransactionDTO.class);
        transactionDTO.setCreatedBy(DateUtils.convertFullDateToString(transactionEntity.getCreatedDate()));
        return transactionDTO;
    }
}
