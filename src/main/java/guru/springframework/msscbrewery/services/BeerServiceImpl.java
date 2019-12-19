package guru.springframework.msscbrewery.services;

import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import guru.springframework.msscbrewery.repository.BeerRepository;
import guru.springframework.msscbrewery.web.model.Beer;
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
	public BeerDto getBeerById(UUID id) {
		Assert.notNull(id, "Beer Id shall not be null!");

		Beer beer = repository.findById(id).orElseThrow();

		BeerDto dto = new BeerDto();
		BeanUtils.copyProperties(beer, dto);

		return dto;
	}

	@Override
	public UUID saveNewBeer(BeerDto dto) {
		Beer beer = new Beer();
		BeanUtils.copyProperties(dto, beer);

		Beer newBeer = repository.save(beer);
		return newBeer.getId();
	}

	@Override
	public void updateExistingBeer(UUID id, BeerDto dto) {
		Assert.notNull(id, "Beer Id shall not be null!");

		Beer beer = repository.findById(id).orElseThrow();
		BeanUtils.copyProperties(dto, beer, "id");

		repository.save(beer);
	}

	@Override
	public void deleteBeer(UUID id) {
		Assert.notNull(id, "Beer Id shall not be null!");

		repository.deleteById(id);
	}

}
