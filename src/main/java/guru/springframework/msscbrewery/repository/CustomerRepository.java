package guru.springframework.msscbrewery.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.msscbrewery.domain.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, UUID> {

}
