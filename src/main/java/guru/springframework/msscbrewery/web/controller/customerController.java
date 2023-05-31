package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.customerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
public class customerController {
    private final CustomerService customerService;


    public customerController(CustomerService customerService){
        this.customerService=customerService;
    }



    @GetMapping("/{CustomerId}")
    public ResponseEntity<customerDto> getcustomer(@PathVariable("CustomerId") UUID CustomerId){
        
        return new ResponseEntity<>(customerService.getCustomerByID(CustomerId), HttpStatus.OK);
    }
}

