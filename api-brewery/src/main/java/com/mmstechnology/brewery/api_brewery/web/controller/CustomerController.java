package com.mmstechnology.brewery.api_brewery.web.controller;

import com.mmstechnology.brewery.api_brewery.exception.CustomerNotFound;
import com.mmstechnology.brewery.api_brewery.service.CustomerService;
import com.mmstechnology.brewery.api_brewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") UUID customerId) {
        return customerService.getCustomerById(customerId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomerNotFound("Customer not found with id: " + customerId));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> saveNewCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto customerDtoCreated = customerService.saveNewCustomer(customerDto)
                .orElseThrow(() -> new CustomerNotFound("Customer can't be created with id: " + customerDto.getId()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + customerDtoCreated.getId().toString());
        return new ResponseEntity<>(customerDtoCreated, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDto customerDto) {
        CustomerDto customerDtoUpdated = customerService.updateCustomer(customerId, customerDto)
                .orElseThrow(() -> new CustomerNotFound("Customer not found with id: " + customerId));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + customerDtoUpdated.getId().toString());
        return new ResponseEntity<>(customerDtoUpdated, headers, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("customerId") UUID customerId) {
        log.debug("In controller - Deleting customer with id: " + customerId);
        customerService.deleteCustomer(customerId);
    }
}