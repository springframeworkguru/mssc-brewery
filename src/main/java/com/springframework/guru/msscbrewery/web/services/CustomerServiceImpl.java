package com.springframework.guru.msscbrewery.web.services;

import com.springframework.guru.msscbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Override
    public CustomerDto getCustomerByID(UUID id) {
        return CustomerDto.builder()
                .customerID(id)
                .customerName("Frikkie").build();
    }
}
