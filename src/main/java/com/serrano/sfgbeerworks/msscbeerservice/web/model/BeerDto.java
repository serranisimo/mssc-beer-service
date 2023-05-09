package com.serrano.sfgbeerworks.msscbeerservice.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto implements Serializable {

    static final long serialVersionUID = -4528799043253432436L;
    @Null // this property should only be set from inside the application
    private UUID id;
    @Null
    private Integer version;
    @JsonFormat(pattern = "yyy-MM-dd'T'HH:mm:ssZ",shape = JsonFormat.Shape.STRING)
    @Null
    private OffsetDateTime createdDate;
    @JsonFormat(pattern = "yyy-MM-dd'T'HH:mm:ssZ",shape = JsonFormat.Shape.STRING)
    @Null
    private OffsetDateTime lastModufiedDate;
    @NotBlank
    @Size(min = 2, max = 100)
    private String beerName;
    @NotNull
    private BeerStyleEnum beerStyle;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Positive
    @NotNull
    private String upc;
    @Positive
    @NotNull
    private BigDecimal price;
    @Positive
    private Integer quantityOnHand;
}
