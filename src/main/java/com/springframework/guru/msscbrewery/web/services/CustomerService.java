package com.springframework.guru.msscbrewery.web.services;

import com.springframework.guru.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerByID(UUID id);
}
