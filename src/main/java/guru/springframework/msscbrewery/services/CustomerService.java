package guru.springframework.msscbrewery.services;


import guru.springframework.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomer(UUID id);
    CustomerDto save(CustomerDto customerDto);
    void updateCustomer(UUID id, CustomerDto customerDto);
    void deleteCustomer(UUID customerId);
}
