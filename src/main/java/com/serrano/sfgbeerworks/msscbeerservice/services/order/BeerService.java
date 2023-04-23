package com.serrano.sfgbeerworks.msscbeerservice.services.order;

import com.serrano.sfgbeerworks.msscbeerservice.web.model.BeerDto;
import com.serrano.sfgbeerworks.msscbeerservice.web.model.BeerPagedList;
import com.serrano.sfgbeerworks.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);
    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto updateBeer(BeerDto beerDto);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto getByUpc(String upc);
}
