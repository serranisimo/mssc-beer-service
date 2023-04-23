package com.serrano.sfgbeerworks.msscbeerservice.services.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getOnhandInventory() {

        //todo evolve to use UPC
//        Integer qoh = beerInventoryService.getOnhandInventory(UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb"));
//
//        System.out.println(qoh);

    }

}