package guru.springframework.msscbrewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.msscbrewery.web.model.Customer;

@Service
public class CustomerService {

	public Customer getCustomerById(UUID id) {
		return Customer.builder()
				.id(UUID.randomUUID())
				.name("Pernilongo do Agreste")
				.build();
	}

	public Customer createCustomer(Customer customer) {
		customer.setId(UUID.randomUUID());
		return customer;
	}

	public Customer updateCustomer(UUID id, Customer customer) {
		Customer oldCustomer = this.getCustomerById(id);
		
		oldCustomer.setId(id);
		oldCustomer.setName(customer.getName());
		//TODO update customer in database
		
		return oldCustomer;
	}

	public Customer removeCustomer(UUID id) {
		//TODO delete customer from database
		return null;
	}

}
