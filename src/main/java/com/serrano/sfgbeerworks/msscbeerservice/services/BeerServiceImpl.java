package com.serrano.sfgbeerworks.msscbeerservice.services;

import com.serrano.sfgbeerworks.msscbeerservice.domain.Beer;
import com.serrano.sfgbeerworks.msscbeerservice.repositories.BeerRepository;
import com.serrano.sfgbeerworks.msscbeerservice.web.mappers.BeerMapper;
import com.serrano.sfgbeerworks.msscbeerservice.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    public BeerServiceImpl(BeerRepository beerRepository, BeerMapper beerMapper) {
        this.beerRepository = beerRepository;
        this.beerMapper = beerMapper;
    }

    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.BeerToBeerDto(
                beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
        );
    }

    @Override
    public BeerDto updateBeer(BeerDto beerDto) {
        Beer beer = beerRepository.findById(
                beerDto.getId()
        ).orElseThrow(NotFoundException::new);
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().toString());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());
        return beerMapper.BeerToBeerDto(beerRepository.save(beer));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.BeerToBeerDto(
                beerRepository.save(beerMapper.BeerDtoToBeer(beerDto))
        );
    }
}
