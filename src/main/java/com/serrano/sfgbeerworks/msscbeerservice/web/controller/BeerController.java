package com.serrano.sfgbeerworks.msscbeerservice.web.controller;

import com.serrano.sfgbeerworks.msscbeerservice.services.BeerService;
import com.serrano.sfgbeerworks.msscbeerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
        return ResponseEntity.ok(beerService.getById(beerId));
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Validated @RequestBody BeerDto beerDto) {
        return new ResponseEntity(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId,@Validated @RequestBody BeerDto beerDto) {
        return new ResponseEntity(beerService.updateBeer(beerDto), HttpStatus.NO_CONTENT);
    }
}
