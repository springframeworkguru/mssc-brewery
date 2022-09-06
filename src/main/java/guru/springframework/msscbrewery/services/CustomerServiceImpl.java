import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	public Customer getCustomerById(UUID id) {
		// TODO Auto-generated method stub
		return Customer.builder().id(UUID.randomUUID()).name("yash Bhardwaj").build();
	}

}
