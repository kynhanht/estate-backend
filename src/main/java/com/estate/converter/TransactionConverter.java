package com.estate.converter;

import com.estate.dto.TransactionDTO;
import com.estate.entity.TransactionEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionConverter {

    private final ModelMapper modelMapper;

    public TransactionDTO convertToDTO(TransactionEntity entity) {
        TransactionDTO dto = modelMapper.map(entity, TransactionDTO.class);
        if(entity.getUser() != null ){
            dto.setStaffName(entity.getUser().getFullName());
        }
        return dto;
    }
}
