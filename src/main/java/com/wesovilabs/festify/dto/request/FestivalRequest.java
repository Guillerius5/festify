package com.wesovilabs.festify.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "Datos de festival a crear o modificar")
public record FestivalRequest(

        @NotBlank(message = "El titulo del festival es obligatorio")
        @Size(min=3, max = 20, message = "La longitud debe ser mayor que 3 y menor que 20 caracteres")
        String title,

        String about,

        @NotNull
        LocalDate from,

        @NotNull
        LocalDate to,

        @NotBlank(message = "La ciudad es un campo obligatorio")
        String city,

        @JsonProperty("price_from")
        @NotNull
        @PositiveOrZero(message = "El precio debe ser un valor positivo")
        Double minPrice,

        @JsonProperty("price_to")
        @NotNull
        @PositiveOrZero(message = "El precio debe ser un valor positivo")
        Double maxPrice

) {
}