package com.springframework.guru.msscbrewery.web.services;

import com.springframework.guru.msscbrewery.web.model.BeerDto;
import com.springframework.guru.msscbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Override
    public CustomerDto getCustomerByID(UUID id) {
        //Master changes
        return CustomerDto.builder()
                .customerID(id)
                .customerName("Frikkie").build();
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto custDto) {
        return CustomerDto.builder().customerID(UUID.randomUUID()).build();
    }

    @Override
    public void updateCustomer(UUID beerId, BeerDto updateDto) {

    }

    @Override
    public void deleteCustomer(UUID customerId) {

    }
}
