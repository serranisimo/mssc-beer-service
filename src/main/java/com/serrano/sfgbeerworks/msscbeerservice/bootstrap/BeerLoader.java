package com.serrano.sfgbeerworks.msscbeerservice.bootstrap;

import com.serrano.sfgbeerworks.msscbeerservice.domain.Beer;
import com.serrano.sfgbeerworks.msscbeerservice.repositories.BeerRepository;
import com.serrano.sfgbeerworks.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//@Component
public class BeerLoader implements CommandLineRunner {

    BeerRepository beerRepository;

    @Autowired
    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeer();
    }

    private void loadBeer() {
        if (beerRepository.count() == 3){
            beerRepository.save(Beer.builder()
                    .beerName("Serrano Braeu")
                    .beerStyle("IPA")
                    .quantityToBrew(250)
                    .minOnHand(120)
                    .upc("1234567890l")
                    .price(new BigDecimal("12.95"))
                    .build());
            beerRepository.save(Beer.builder()
                    .beerName("Mansanita Braeu")
                    .beerStyle(BeerStyleEnum.SAISON.name())
                    .quantityToBrew(125)
                    .minOnHand(75)
                    .upc("1234567891l")
                    .price(new BigDecimal("14.95"))
                    .build());
        }

        System.out.println("--------------------------------------------------------------------");
        System.out.println(beerRepository.count());
        System.out.println("--------------------------------------------------------------------");
//        System.out.println(beerRepository.findAll().iterator().next().getId());
    }




}
