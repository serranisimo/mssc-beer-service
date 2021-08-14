package com.serrano.sfgbeerworks.msscbeerservice.web.mappers;

import com.serrano.sfgbeerworks.msscbeerservice.domain.Beer;
import com.serrano.sfgbeerworks.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {
    BeerDto BEerToBeerDto(Beer beer);

    Beer BeerDtoToBeer(BeerDto beerDto);
}
