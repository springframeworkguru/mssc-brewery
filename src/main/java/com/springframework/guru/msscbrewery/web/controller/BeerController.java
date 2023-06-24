package com.springframework.guru.msscbrewery.web.controller;

import com.springframework.guru.msscbrewery.web.model.BeerDto;
import com.springframework.guru.msscbrewery.web.services.BeerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    ResponseEntity<BeerDto> getBeer(@PathVariable UUID beerId)
    {
       return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }
}
