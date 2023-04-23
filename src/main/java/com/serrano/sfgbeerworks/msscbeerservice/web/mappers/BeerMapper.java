package com.serrano.sfgbeerworks.msscbeerservice.web.mappers;

import com.serrano.sfgbeerworks.msscbeerservice.domain.Beer;
import com.serrano.sfgbeerworks.msscbeerservice.web.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);
    BeerDto beerToBeerDtoWithInventory(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
