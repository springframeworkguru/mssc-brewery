package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.customerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service

public class CustomerServiceImpl implements CustomerService {
    @Override
    public customerDto getCustomerByID(UUID CustomerId) {
        return customerDto.builder()
                .id(UUID.randomUUID())
                .name("Ankit Choudhary")
                .build() ;
    }
}
