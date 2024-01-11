package com.estate.controller;

import com.estate.dto.CustomerDTO;
import com.estate.dto.request.AssignmentCustomerRequest;
import com.estate.dto.request.CustomerSearchRequest;
import com.estate.dto.respone.CustomerSearchResponse;
import com.estate.service.ICustomerService;
import com.estate.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;

    private final IUserService userService;



    @PostMapping("/search")
    public ResponseEntity<Page<CustomerSearchResponse>> searchCustomers(@RequestBody CustomerSearchRequest request, @PageableDefault(size = 4 ,sort = "fullName", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok(customerService.searchCustomers(request, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(customerService.findCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDTO customerDTO) {

        return ResponseEntity.ok(customerService.updateCustomer(id, customerDTO));
    }


    @DeleteMapping
    public ResponseEntity<Void> removeCustomers(@RequestBody List<Long> ids) {

        if (ids != null && !ids.isEmpty()) {
            customerService.deleteCustomers(ids);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/assignment-customer")
    public ResponseEntity<Void> assignCustomer(@RequestBody AssignmentCustomerRequest request) {
        customerService.assignCustomer(request);
        return ResponseEntity.ok().build();
    }

}
