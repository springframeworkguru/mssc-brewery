package com.springframework.guru.msscbrewery.web.services;

import com.springframework.guru.msscbrewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerServiceImpl implements BeerService{
    @Override
    public BeerDto getBeerById(UUID id) {
        //Master changes
        return BeerDto.builder().id(id).
                beerName("Galaxy Beer")
                .beerStyle("Pale Ale")
                .build();
    }

    public BeerDto saveNewBeer(BeerDto newBeer)
    {
        return BeerDto.builder().id(UUID.randomUUID()).build();
    }

    public void updateBeer(UUID id, BeerDto updateDto)
    {

    }

    @Override
    public void deleteBeer(UUID customerId) {

    }
}
