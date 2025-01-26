package com.mmstechnology.brewery.api_brewery.service;

import com.mmstechnology.brewery.api_brewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Optional<CustomerDto> getCustomerById(UUID customerId) {

        return Optional.of(CustomerDto.builder().id(customerId).name("John Doe").build());
    }

    @Override
    public Optional<CustomerDto> saveNewCustomer(CustomerDto customerDto) {
        customerDto.setId(UUID.randomUUID());

        return Optional.of(customerDto);

    }

    @Override
    public Optional<CustomerDto> updateCustomer(UUID customerId, CustomerDto customerDto) {
        customerDto.setId(customerId);
        return Optional.of(customerDto);
    }

    @Override
    public void deleteCustomer(UUID customerId) {

        log.debug("Deleting customer with id: " + customerId);
    }
}
