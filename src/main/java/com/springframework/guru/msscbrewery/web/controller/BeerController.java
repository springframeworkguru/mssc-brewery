package com.springframework.guru.msscbrewery.web.controller;

import com.springframework.guru.msscbrewery.web.model.BeerDto;
import com.springframework.guru.msscbrewery.web.services.BeerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    ResponseEntity<BeerDto> saveBeer(BeerDto custDto)
    {
        BeerDto savedDto = beerService.saveNewBeer(custDto);

        HttpHeaders header = new HttpHeaders();
        header.add("Location", "api/v1/beer/" + savedDto.getId().toString());

        return new ResponseEntity(header,HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    ResponseEntity<BeerDto> handlePut(@PathVariable UUID beerId, BeerDto updateDto){
        beerService.updateBeer(beerId, updateDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{customerId}")
    void deleteCustomer(@PathVariable UUID customerId, @RequestBody BeerDto updateDto){
        beerService.deleteBeer(customerId);
    }

}
