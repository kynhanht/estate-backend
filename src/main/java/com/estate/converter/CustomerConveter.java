package com.estate.converter;

import com.estate.dto.CustomerDTO;
import com.estate.dto.respone.CustomerSearchResponse;
import com.estate.entity.CustomerEntity;
import com.estate.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerConveter {

    private final ModelMapper modelMapper;

    public CustomerDTO convertToDTO(CustomerEntity customerEntity) {

        return modelMapper.map(customerEntity, CustomerDTO.class);
    }

    public CustomerEntity convertToEntity(CustomerDTO customerDTO) {

        return modelMapper.map(customerDTO, CustomerEntity.class);
    }

    public CustomerSearchResponse convertToCustomerSearchResponse(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerSearchResponse.class);
    }

}
