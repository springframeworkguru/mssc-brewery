package com.springframework.guru.msscbrewery.web.services;

import com.springframework.guru.msscbrewery.web.model.BeerDto;
import com.springframework.guru.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    //Master changes
    CustomerDto getCustomerByID(UUID id);

    CustomerDto saveCustomer(CustomerDto custDto);

    void updateCustomer(UUID beerId, BeerDto updateDto);

    void deleteCustomer(UUID customerId);
}
