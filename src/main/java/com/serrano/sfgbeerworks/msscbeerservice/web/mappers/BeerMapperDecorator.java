package com.serrano.sfgbeerworks.msscbeerservice.web.mappers;

import com.serrano.sfgbeerworks.msscbeerservice.domain.Beer;
import com.serrano.sfgbeerworks.msscbeerservice.services.inventory.BeerInventoryService;
import com.serrano.sfgbeerworks.msscbeerservice.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerMapperDecorator implements BeerMapper{

    private BeerInventoryService beerInventoryService;

    private BeerMapper beerMapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setBeerMapper(BeerMapper beerMapper) {
        this.beerMapper = beerMapper;
    }

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
        var beerDto = this.beerMapper.beerToBeerDto(beer);
        return beerDto;
    }

    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer) {
        var beerDto = this.beerMapper.beerToBeerDto(beer);
        beerDto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
        return beerDto;
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        var beer = this.beerMapper.beerDtoToBeer(beerDto);
        return null;
    }
}
