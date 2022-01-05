package guru.springframework.msscbrewery.repository;

import guru.springframework.msscbrewery.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerReposotory extends JpaRepository<Beer, Integer> {
}
