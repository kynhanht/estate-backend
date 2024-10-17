package com.estate.converter;

import com.estate.dto.CustomerDTO;
import com.estate.dto.respone.CustomerSearchResponse;
import com.estate.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerConveter {

    private final ModelMapper modelMapper;

    public CustomerDTO convertToDTO(CustomerEntity entity) {

        return modelMapper.map(entity, CustomerDTO.class);
    }

    public CustomerEntity convertToEntity(CustomerDTO dto) {

        return modelMapper.map(dto, CustomerEntity.class);
    }

    public CustomerSearchResponse convertToCustomerSearchResponse(CustomerEntity entity) {
        return modelMapper.map(entity, CustomerSearchResponse.class);
    }

}
