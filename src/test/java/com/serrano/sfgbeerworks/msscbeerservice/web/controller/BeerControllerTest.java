package com.serrano.sfgbeerworks.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serrano.sfgbeerworks.msscbeerservice.web.model.BeerDto;
import com.serrano.sfgbeerworks.msscbeerservice.web.model.BeerStyleEnum;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    private static final String PATH = "/api/v1/beer";

    BeerDto validBeer;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        validBeer = BeerDto.builder()
                .id(UUID.randomUUID()).beerName("Test Beer").beerStyle(BeerStyleEnum.ALE).upc(81364232l).build();
    }

    @Test
    void getBeerById() throws Exception {
        String beerId = UUID.randomUUID().toString();
        mockMvc.perform(get(PATH + "/" + beerId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = validBeer;
        String beerDtoToJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoToJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto beerDto = validBeer;
        String beerDtoToJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put(PATH + "/" + beerDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoToJson))
                .andExpect(status().isAccepted());
    }
}