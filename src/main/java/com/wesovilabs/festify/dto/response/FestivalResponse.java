package com.wesovilabs.festify.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record FestivalResponse(Long id, String title, String about, String city,
                               LocalDate from, LocalDate to,
                               @JsonProperty("price_from")
                               double priceFrom,
                               @JsonProperty("price_to")
                               double priceTo) {
}
