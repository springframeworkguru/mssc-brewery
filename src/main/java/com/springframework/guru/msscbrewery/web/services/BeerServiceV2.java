package com.springframework.guru.msscbrewery.web.services;

import com.springframework.guru.msscbrewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerServiceV2 {
    //Master changes
    BeerDto getBeerById(UUID id);

    BeerDto saveNewBeer(BeerDto custDto);

    void updateBeer(UUID beerId, BeerDto updateDto);

    void deleteBeer(UUID customerId);
}
