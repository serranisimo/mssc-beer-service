package com.serrano.sfgbeerworks.msscbeerservice.services;

import com.serrano.sfgbeerworks.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID beerId);

    BeerDto updateBeer(BeerDto beerDto);

    BeerDto saveNewBeer(BeerDto beerDto);
}
