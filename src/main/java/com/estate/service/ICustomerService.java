package com.estate.service;

import com.estate.dto.CustomerDTO;
import com.estate.dto.request.AssignmentCustomerRequest;
import com.estate.dto.request.CustomerSearchRequest;
import com.estate.dto.respone.CustomerSearchResponse;
import com.estate.dto.respone.PaginationResponse;

import java.util.List;

public interface ICustomerService {

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomers(List<Long> ids);

    PaginationResponse<CustomerSearchResponse> searchCustomers(CustomerSearchRequest request);

    void assignCustomer(AssignmentCustomerRequest request);
}
