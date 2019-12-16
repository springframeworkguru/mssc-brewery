package guru.springframework.msscbrewery.services;

import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import guru.springframework.msscbrewery.domain.Beer;
import guru.springframework.msscbrewery.repository.BeerRepository;
import guru.springframework.msscbrewery.web.model.BeerDto;

@Service
public class BeerServiceImpl implements BeerService {

	@Autowired
	private BeerRepository repository;

	@Override
	public Collection<Beer> list() {
		return (Collection<Beer>) repository.findAll();
	}

	@Override
	public BeerDto getBeerById(UUID beerId) {
		Assert.notNull(beerId, "Beer Id shall not be null!");

		Beer beer = repository.findById(beerId).orElseThrow();

		return BeerDto.builder()
				.beerName(beer.getBeerName())
				.beerStyle(beer.getBeerStyle())
				.upc(beer.getUpc())
				.id(beer.getId())
				.build();
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beer) {
		Beer saved = repository.save(Beer.builder()
				.beerName(beer.getBeerName())
				.beerStyle(beer.getBeerStyle())
				.upc(beer.getUpc())
				.build());

		beer.setId(saved.getId());
		return beer;
	}

	@Override
	public void updateExistingBeer(UUID beerId, BeerDto beer) {
		Assert.notNull(beerId, "Beer Id shall not be null!");

		Beer oldBeer = repository.findById(beerId).orElseThrow();

		oldBeer.setBeerName(beer.getBeerName());
		oldBeer.setBeerStyle(beer.getBeerStyle());
		oldBeer.setUpc(beer.getUpc());

		repository.save(oldBeer);
	}

	@Override
	public void deleteBeer(UUID beerId) {
		Assert.notNull(beerId, "Beer Id shall not be null!");

		repository.deleteById(beerId);
	}

}
