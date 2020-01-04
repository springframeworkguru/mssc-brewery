package guru.springframework.msscbrewery.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.msscbrewery.model.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, UUID> {

	Page<Customer> findByNameContains(String name, Pageable pageable);

}
