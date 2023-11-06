package com.estate.service.impl;

import com.estate.constant.ErrorMessageConstants;
import com.estate.constant.SystemConstants;
import com.estate.converter.CustomerConveter;
import com.estate.dto.CustomerDTO;
import com.estate.dto.request.AssignmentCustomerRequest;
import com.estate.dto.request.CustomerSearchRequest;
import com.estate.dto.respone.CustomerSearchResponse;
import com.estate.dto.respone.PaginationResponse;
import com.estate.entity.CustomerEntity;
import com.estate.entity.CustomerEntity_;
import com.estate.enums.SearchOperationEnum;
import com.estate.exception.NotFoundException;
import com.estate.repository.CustomerRepository;
import com.estate.repository.UserRepository;
import com.estate.service.ICustomerService;
import com.estate.specifications.CustomerSpecification;
import com.estate.specifications.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    private final UserRepository userRepository;

    private final CustomerConveter customerConveter;

    @Override
    public CustomerDTO getCustomerById(Long id) {

        CustomerEntity customerEntity = customerRepository
                .findById(id).orElseThrow(() -> new NotFoundException(ErrorMessageConstants.CUSTOMER_NOT_FOUND));
        return customerConveter.convertToDTO(customerEntity);
    }

    @Override
    @Transactional
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {

        CustomerEntity customerEntity = customerConveter.convertToEntity(customerDTO);
        customerEntity.setStatus(SystemConstants.ACTIVE_STATUS);
        return customerConveter.convertToDTO(customerRepository.save(customerEntity));
    }

    @Override
    @Transactional
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {

        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.CUSTOMER_NOT_FOUND));

        customerEntity.setFullName(customerDTO.getFullName());
        customerEntity.setPhone(customerDTO.getPhone());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setCompany(customerDTO.getCompany());
        customerEntity.setDemand(customerDTO.getDemand());
        customerEntity.setNote(customerDTO.getNote());
        CustomerEntity _customerEntity = customerRepository.save(customerEntity);
        return customerConveter.convertToDTO(_customerEntity);

    }

    @Override
    @Transactional
    public void deleteCustomers(List<Long> ids) {
        ids.forEach(id -> {
            CustomerEntity customerEntity = customerRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.CUSTOMER_NOT_FOUND));
            customerEntity.setStatus(SystemConstants.INACTIVE_STATUS);
            customerRepository.save(customerEntity);

        });
    }

    @Override
    public PaginationResponse<CustomerSearchResponse> searchCustomers(CustomerSearchRequest request) {

        CustomerSpecification customerSpecification = new CustomerSpecification();

        Specification<CustomerEntity> specification = Specification
                .where(customerSpecification.byCommon(new SearchCriteria(CustomerEntity_.FULL_NAME, request.getFullName(), SearchOperationEnum.CONTAINING)))
                .and(customerSpecification.byCommon(new SearchCriteria(CustomerEntity_.EMAIL, request.getEmail(), SearchOperationEnum.CONTAINING)))
                .and(customerSpecification.byCommon(new SearchCriteria(CustomerEntity_.PHONE, request.getPhone(), SearchOperationEnum.CONTAINING)))
                .and(customerSpecification.byCommon(new SearchCriteria(CustomerEntity_.STATUS, SystemConstants.ACTIVE_STATUS, SearchOperationEnum.EQUAL)))
                .and(customerSpecification.byStaffId(request.getStaffId()))
                .and(customerSpecification.orderBy(request.getSortColumnName(), request.getSortDirection()));

        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getTotalPageItems());
        Page<CustomerEntity> page = customerRepository.findAll(specification, pageable);

        List<CustomerSearchResponse> responses = page
                .getContent()
                .stream()
                .map(customerConveter::convertToCustomerSearchResponse)
                .collect(Collectors.toList());

        PaginationResponse<CustomerSearchResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setPage(request.getPage());
        paginationResponse.setTotalPages(page.getTotalPages());
        paginationResponse.setTotalPageItems(request.getTotalPageItems());
        paginationResponse.setTotalItems((int) page.getTotalElements());
        paginationResponse.setListResult(responses);
        return paginationResponse;
    }

    @Override
    @Transactional
    public void assignCustomer(AssignmentCustomerRequest request) {

        CustomerEntity customerEntity = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.CUSTOMER_NOT_FOUND));

        List<Long> ids = request.getStaffIds();
        Long count = userRepository.countByIdIn(ids);
        if (count != ids.size()) {
            throw new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND);
        } else {
            customerEntity.getUsers().clear();
            customerEntity.getUsers().addAll(userRepository.findByIdIn(ids));
        }
    }
}
