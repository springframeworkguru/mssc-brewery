package guru.springframework.msscbrewery.controller;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import guru.springframework.msscbrewery.model.Customer;
import guru.springframework.msscbrewery.services.CustomerService;

@RequestMapping("/${sfg.brewery.api.customer-path}")
@RestController
public class CustomerController {

	@Autowired
	private ApiProperties properties;

	@Autowired
	private CustomerService service;

	@GetMapping
	public Page<Customer> findPaginated(
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = "${spring.data.web.pageable.default-page-size}") int size,
			@RequestParam(name = "name", required = false) String name) {

		if (StringUtils.hasText(name)) {
			return service.findCustomerByNamePaginated(page, size, name);
		}

		return service.listPaginated(page, size);
	}

	@GetMapping("/{id}")
	public Customer get(@PathVariable("id") UUID id) {
		return service.getCustomerById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> save(@Valid @RequestBody Customer customer,
			UriComponentsBuilder builder) {
		UUID newId = service.createCustomer(customer);

		URI uri = builder.path("{customerPath}/{id}").build(false)
				.expand(properties.getCustomerPath(), newId)
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") UUID id, @Valid @RequestBody Customer customer) {
		service.updateCustomer(id, customer);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") UUID id) {
		service.removeCustomer(id);
	}

}
