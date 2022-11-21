package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getCustomer(UUID customerId) {
        return CustomerDto.builder()
                .id(customerId)
                .name("Moustafa")
                .build();
    }
}
