package guru.springframework.msscbrewery.services;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import guru.springframework.msscbrewery.domain.Customer;
import guru.springframework.msscbrewery.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	public Customer getCustomerById(UUID id) {
		return repository.findById(id).orElseThrow();
	}

	public UUID createCustomer(Customer customer) {
		Assert.isNull(customer.getId(), "Customer Id shall not be passed! It must be generated automatically.");

		repository.save(customer);

		return customer.getId();
	}

	public void updateCustomer(UUID id, Customer customer) {
		Assert.notNull(id, "CustomerId shall not be null!");

		Customer existingOne = repository.findById(id).orElseThrow();

		BeanUtils.copyProperties(customer, existingOne, "id", "createdDate", "version", "updatedDate");

		repository.save(existingOne);
	}

	public void removeCustomer(UUID id) {
		repository.deleteById(id);
	}

	public Page<Customer> listPaginated(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return repository.findAll(pageRequest);
	}

	public Page<Customer> findCustomerByNamePaginated(int page, int size, String name) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return repository.findByNameContains(name, pageRequest);
	}

}
