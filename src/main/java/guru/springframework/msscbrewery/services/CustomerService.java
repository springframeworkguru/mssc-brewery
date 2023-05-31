package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.customerDto;

import java.util.UUID;

public interface CustomerService {
    customerDto getCustomerByID(UUID CustomerId);
}
