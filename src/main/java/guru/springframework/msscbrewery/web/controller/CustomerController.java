package guru.springframework.msscbrewery.web.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.Customer;

@RequestMapping("/${sfg.brewery.api.customer-path}")
@RestController
public class CustomerController {
	
	@Autowired
	private ApiProperties properties;
	
	@Autowired
	private CustomerService service;
	
	@GetMapping("/{id}")
	public Customer get(@PathVariable("id") UUID id) {
		return service.getCustomerById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> save(@RequestBody Customer customer,
			UriComponentsBuilder builder) {
		Customer saved = service.createCustomer(customer);
		
		URI uri = builder.path("{customerPath}/{id}").build(false)
				.expand(properties.getCustomerPath(), saved.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") UUID id, @RequestBody Customer customer) {
		service.updateCustomer(id, customer);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") UUID id) {
		service.removeCustomer(id);
	}

}
