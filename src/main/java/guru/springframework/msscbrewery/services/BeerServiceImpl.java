package guru.springframework.msscbrewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.msscbrewery.web.model.BeerDto;

@Service
public class BeerServiceImpl implements BeerService {
    
	@Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder()
        		.id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle("Pale Ale")
                .build();
    }

	@Override
	public BeerDto saveNewBeer(BeerDto beer) {
		beer.setId(UUID.randomUUID());
		return beer;
	}

	@Override
	public BeerDto updateExistingBeer(UUID beerId, BeerDto beer) {
		BeerDto oldBeer = this.getBeerById(beerId);
		
		oldBeer.setBeerName(beer.getBeerName());
		oldBeer.setBeerStyle(beer.getBeerStyle());
		oldBeer.setUpc(beer.getUpc());
		//TODO update beer in database
		
		return oldBeer;
	}

	@Override
	public BeerDto deleteBeer(UUID beerId) {
		BeerDto deletedBeer = this.getBeerById(beerId);
		//TODO delete beer from database
		return deletedBeer;
	}
	
}
