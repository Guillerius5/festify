package com.wesovilabs.festify.web.controller;

import com.wesovilabs.festify.dto.request.FestivalRequest;
import com.wesovilabs.festify.dto.response.FestivalResponse;
import com.wesovilabs.festify.service.FestivalService;
import com.wesovilabs.festify.service.FestivalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Tag(name = "Festivales", description = "Gestión de festivales")
@CrossOrigin(origins = "*")
@RestController
public class FestivalController {

    final private FestivalService festivalService;

    @Autowired
    public FestivalController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    @Operation(
            summary = "Lista de festivales",
            description = "Devuelve la lista de los festivales"
    )
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = FestivalResponse.class))
            )
    )
    @GetMapping("/festivals")
    @ResponseStatus(HttpStatus.OK)
    public List<FestivalResponse> listFestivals(@RequestParam(defaultValue = "title") String sortBy,
                                                @RequestParam(defaultValue = "asc") String dir){
        Set<String> allowed = Set.of("name","city","from","price");
        if (!allowed.contains(sortBy)) {
            sortBy = "title";
        }
        String property = switch (sortBy) {
            case "title"  -> "title";
            case "city"  -> "city";
            case "from"  -> "from";
            case "price"  -> "priceMin";
            default -> "title";
        };
        return this.festivalService.listFestivals(property,dir);
    }


    @Operation(
            summary = "Devuelve un festival",
            description = "Devuelve el detalle de un festival"
    )
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = FestivalResponse.class)))
    @ApiResponse(responseCode = "404", description = "Festival no encontrado con el Id proporcionado",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @GetMapping("/festivals/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FestivalResponse getFestival(@PathVariable Long id){
        return this.festivalService.getFestival(id);
    }
    @Operation(summary = "Elimina un festival")
    @ApiResponse(responseCode = "204", description = "Eliminado")
    @ApiResponse(responseCode = "404", description = "Festival no encontrado con el Id proporcionado",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @DeleteMapping("/festivals/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFestival(@PathVariable Long id){
        this.festivalService.deleteFestival(id);
    }
    @Operation(summary = "Crea un festival")
    @ApiResponse(responseCode = "201", description = "Creado",
            content = @Content(schema = @Schema(implementation = FestivalResponse.class)))
    @ApiResponse(responseCode = "400", description = "Error de validación de datos",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @PostMapping("/festivals")
    @ResponseStatus(HttpStatus.CREATED)
    public FestivalResponse createFestival(@Valid @RequestBody FestivalRequest request){
        return this.festivalService.createFestival(request);
    }
    @Operation(summary = "Actualiza un festival")
    @ApiResponse(responseCode = "200", description = "Actualizado",
            content = @Content(schema = @Schema(implementation = FestivalResponse.class)))
    @ApiResponse(responseCode = "400", description = "Error de validación de datos",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "404", description = "Festival no encontrado con el Id proporcionado",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @PutMapping("/festivals/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FestivalResponse updateFestival(@PathVariable Long id, @Valid @RequestBody FestivalRequest request){
        return this.festivalService.updateFestival(id,request);
    }
}
