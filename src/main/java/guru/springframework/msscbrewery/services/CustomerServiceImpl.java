package guru.springframework.msscbrewery.services;

import java.util.UUID;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(final UUID customerId) {
        return CustomerDto.builder().id(customerId).name("Betty Sue").build();
    }

    @Override
    public CustomerDto createCustomer(final CustomerDto customerDto) {
        return CustomerDto.builder().name("new customer").id(UUID.randomUUID()).build();
    }

    @Override
    public CustomerDto updateCustomer(final UUID customerId, final CustomerDto customerDto) {
        return CustomerDto.builder().id(customerId).name(customerDto.getName()).build();
    }

    @Override
    public void deleteCustomerById(final UUID customerId) {
        log.debug("--> Deleting customer: " + customerId.toString());
    }
}
