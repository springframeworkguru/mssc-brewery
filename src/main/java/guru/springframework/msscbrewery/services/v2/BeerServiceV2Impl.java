package guru.springframework.msscbrewery.services.v2;

import java.util.UUID;

import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import org.springframework.stereotype.Service;

@Service
public class BeerServiceV2Impl implements BeerServiceV2 {
    @Override
    public BeerDtoV2 getBeerById(final UUID beerId) {
        return null;
    }

    @Override
    public BeerDtoV2 saveNewBeer(final BeerDtoV2 beerDto) {
        return null;
    }

    @Override
    public void updateBeer(final UUID beerId, final BeerDtoV2 beerDto) {

    }

    @Override
    public void deleteById(final UUID beerId) {

    }
}
