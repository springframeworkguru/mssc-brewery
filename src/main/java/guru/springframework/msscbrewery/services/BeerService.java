package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);

	BeerDto saveNewBeer(BeerDto beer);

	BeerDto updateExistingBeer(UUID beerId, BeerDto beer);

	BeerDto deleteBeer(UUID beerId);
}
