package com.mmstechnology.brewery.api_brewery.service;

import com.mmstechnology.brewery.api_brewery.web.model.CustomerDto;

import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    Optional<CustomerDto> getCustomerById(UUID customerId);

    Optional<CustomerDto> saveNewCustomer(CustomerDto customerDto);

    Optional<CustomerDto> updateCustomer(UUID customerId, CustomerDto customerDto);

    void deleteCustomer(UUID customerId);
}
