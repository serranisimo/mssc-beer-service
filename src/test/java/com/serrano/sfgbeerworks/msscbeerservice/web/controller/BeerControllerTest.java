package com.serrano.sfgbeerworks.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serrano.sfgbeerworks.msscbeerservice.services.order.BeerService;
import com.serrano.sfgbeerworks.msscbeerservice.web.model.BeerDto;
import com.serrano.sfgbeerworks.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "dev.springframework.guru", uriPort = 80)
@ExtendWith(RestDocumentationExtension.class)
class BeerControllerTest {

    private static final String PATH = "/api/v1/beer";

    BeerDto validBeer;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerService beerService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        validBeer = BeerDto.builder()
                .beerName("Test Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .upc("0081364232")
                .price(BigDecimal.valueOf(4.33d))
                .build();
    }

    @Test
    void getBeerById() throws Exception {
        when(beerService.getById(Mockito.any(UUID.class), Mockito.anyBoolean())).thenReturn(this.validBeer);
        String beerId = UUID.randomUUID().toString();
        mockMvc.perform(
                get(PATH + "/" + "{beerId}", beerId)
                        .param("iscold", "yes") // restdocs - controller doesnt take any, it's just an example
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk()) // test
                .andDo( // documentation
                        document(
                                "v1/beer-get",
                                pathParameters(
                                        parameterWithName("beerId").description("UUID of desired beer to get")
                                ),
                                requestParameters(
                                        parameterWithName("iscold").description("is beer cold query paremeter")
                                ),
                                responseFields(
                                        fieldWithPath("id").description("id of beer"),
                                        fieldWithPath("version").description("version of beer"),
                                        fieldWithPath("createdDate").description("date of creation"),
                                        fieldWithPath("lastModufiedDate").description("las date modified"),
                                        fieldWithPath("beerName").description("name of beer"),
                                        fieldWithPath("beerStyle").description("beer style"),
                                        fieldWithPath("quantityOnHand").description("Quantity available"),
                                        fieldWithPath("upc").description("universal product code"),
                                        fieldWithPath("price").description("price of beer")
                                )
                        )
                );
    }

    @Test
    void saveNewBeer() throws Exception {
        when(beerService.saveNewBeer(Mockito.any())).thenReturn(this.validBeer);
        BeerDto beerDto = validBeer;
        String beerDtoToJson = objectMapper.writeValueAsString(beerDto);

        CostraintFields fields = new CostraintFields(BeerDto.class);

        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoToJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/beer-post", // by adding a suffix a different folder is generated (in order to avoid overwriting)
                    requestFields( // fields not provided by the user at creation: id, version, createDate, lastModufiedDate and price
                            fields.withPath("id").ignored(),
                            fields.withPath("version").ignored(),
                            fields.withPath("createdDate").ignored(),
                            fields.withPath("lastModufiedDate").ignored(),
                            fields.withPath("beerName").description("name of beer"),
                            fields.withPath("beerStyle").description("beer style"),
                            fields.withPath("quantityOnHand").description("Quantity available"),
                            fields.withPath("upc").description("universal product code"),
                            fields.withPath("price").ignored()
//                            fieldWithPath("id").ignored(),
//                            fieldWithPath("version").ignored(),
//                            fieldWithPath("createdDate").ignored(),
//                            fieldWithPath("lastModufiedDate").ignored(),
//                            fieldWithPath("beerName").description("name of beer"),
//                            fieldWithPath("beerStyle").description("beer style"),
//                            fieldWithPath("quantityOnHand").description("Quantity available"),
//                            fieldWithPath("upc").description("universal product code"),
//                            fieldWithPath("price").ignored()
                    )
                ));
    }

    @Test
    void updateBeerById() throws Exception {
        when(beerService.updateBeer(Mockito.any())).thenReturn(this.validBeer);
        BeerDto beerDto = validBeer;
        String beerDtoToJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put(PATH + "/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoToJson))
                .andExpect(status().isNoContent());
    }

    private static class CostraintFields {
        private final ConstraintDescriptions constraintDescriptions;

        public CostraintFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
}