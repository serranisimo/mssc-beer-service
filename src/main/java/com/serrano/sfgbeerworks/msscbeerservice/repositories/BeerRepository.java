package com.serrano.sfgbeerworks.msscbeerservice.repositories;

import com.serrano.sfgbeerworks.msscbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {}
