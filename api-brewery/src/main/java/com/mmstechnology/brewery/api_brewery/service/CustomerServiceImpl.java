package com.mmstechnology.brewery.api_brewery.service;

import com.mmstechnology.brewery.api_brewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Optional<CustomerDto> getCustomerById(UUID customerId) {

        return Optional.of(CustomerDto.builder().id(customerId).name("John Doe").build());
    }
}
