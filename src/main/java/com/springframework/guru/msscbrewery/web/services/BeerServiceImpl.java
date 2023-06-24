package com.springframework.guru.msscbrewery.web.services;

import com.springframework.guru.msscbrewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerServiceImpl implements BeerService{
    @Override
    public BeerDto getBeerById(UUID id) {
        return BeerDto.builder().id(id).
                beerName("Galaxy Beer")
                .beerStyle("Pale Ale")
                .build();
    }
}
