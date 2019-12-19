package guru.springframework.msscbrewery.services;

import java.util.Collection;
import java.util.UUID;

import guru.springframework.msscbrewery.web.model.Beer;
import guru.springframework.msscbrewery.web.model.BeerDto;

public interface BeerService {

	BeerDto getBeerById(UUID beerId);

	UUID saveNewBeer(BeerDto beer);

	void updateExistingBeer(UUID beerId, BeerDto beer);

	void deleteBeer(UUID beerId);

	Collection<Beer> list();

}
