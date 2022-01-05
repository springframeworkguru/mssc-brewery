package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.domain.Beer;
import guru.springframework.msscbrewery.repository.BeerReposotory;
import guru.springframework.msscbrewery.web.mappers.BeerMapper;
import guru.springframework.msscbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    private final BeerReposotory beerReposotory;
    private final BeerMapper beerMapper;

    public BeerServiceImpl(BeerReposotory beerReposotory, BeerMapper beerMapper) {
        this.beerReposotory = beerReposotory;
        this.beerMapper = beerMapper;
    }

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle("Pale Ale")
                .build();
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        Beer savedBeer = beerReposotory.save(beerMapper.beerDtoToBeer(beerDto));
        return beerMapper.beerToBeerDto(savedBeer);
//        return BeerDto.builder()
//                .id(UUID.randomUUID())
//                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        //todo impl - would add a real impl to update beer
    }

    @Override
    public void deleteById(UUID beerId) {
        log.debug("Deleting a beer...");
    }
}
