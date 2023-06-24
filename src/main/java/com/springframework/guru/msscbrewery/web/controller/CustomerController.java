package com.springframework.guru.msscbrewery.web.controller;

import com.springframework.guru.msscbrewery.web.model.CustomerDto;
import com.springframework.guru.msscbrewery.web.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("api/v1/customer")
@RestController
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID customerId)
    {
        return new ResponseEntity<>(customerService.getCustomerByID(customerId), HttpStatus.OK);
    }
}
