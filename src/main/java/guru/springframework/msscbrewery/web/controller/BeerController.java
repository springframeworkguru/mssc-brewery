package guru.springframework.msscbrewery.web.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;

@RequestMapping("/api/v1/beers")
@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId){

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody BeerDto beer) {
    	BeerDto saved = beerService.saveNewBeer(beer);
    	
    	URI location = ServletUriComponentsBuilder.fromCurrentRequest()
    			.path("/{id}").build(saved.getId());
    	
    	return ResponseEntity.created(location).build();
    }

}
