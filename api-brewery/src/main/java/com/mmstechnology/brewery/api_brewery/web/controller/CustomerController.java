package com.mmstechnology.brewery.api_brewery.web.controller;

import com.mmstechnology.brewery.api_brewery.exception.CustomerNotFound;
import com.mmstechnology.brewery.api_brewery.service.CustomerService;
import com.mmstechnology.brewery.api_brewery.web.model.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable UUID customerId) {
        return customerService.getCustomerById(customerId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomerNotFound("Customer not found with id: " + customerId));
    }
}
