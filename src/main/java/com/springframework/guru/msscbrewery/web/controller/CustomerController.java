package com.springframework.guru.msscbrewery.web.controller;

import com.springframework.guru.msscbrewery.web.model.BeerDto;
import com.springframework.guru.msscbrewery.web.model.CustomerDto;
import com.springframework.guru.msscbrewery.web.services.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto custDto)
    {
        CustomerDto savedDto = customerService.saveCustomer(custDto);

        HttpHeaders header = new HttpHeaders();
        header.add("Location","api/v1/customer/" + savedDto.getCustomerID().toString());

        return new ResponseEntity(header,HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    ResponseEntity<CustomerDto> upDateCustomer(@PathVariable UUID customerId, @RequestBody BeerDto updateDto){
        customerService.updateCustomer(customerId, updateDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{customerId}")
    void deleteCustomer(@PathVariable UUID customerId, @RequestBody BeerDto updateDto){
        customerService.deleteCustomer(customerId);
    }



}
