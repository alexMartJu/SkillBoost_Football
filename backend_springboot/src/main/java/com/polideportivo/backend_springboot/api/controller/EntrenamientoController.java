package com.polideportivo.backend_springboot.api.controller;

import com.polideportivo.backend_springboot.api.assembler.EntrenamientoAssembler;
import com.polideportivo.backend_springboot.api.model.entrenamiento.EntrenamientoResponse;
import com.polideportivo.backend_springboot.api.model.entrenamiento.EntrenamientoWrapper;
import com.polideportivo.backend_springboot.api.model.entrenamiento.EntrenamientoDataResponse;
import com.polideportivo.backend_springboot.domain.service.EntrenamientoService;
import com.polideportivo.backend_springboot.infra.spec.EntrenamientoSpecification;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EntrenamientoController {
    private final EntrenamientoService entrenamientoService;
    private final EntrenamientoAssembler entrenamientoAssembler;
    
    // Obtener todos los entrenamientos con filtros
    @GetMapping("/entrenamientos")
    public EntrenamientoWrapper getAllEntrenamientos(
            EntrenamientoSpecification filter, // Especificación generada automáticamente
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "4") int limit) {

        System.out.println("Filtros recibidos: " + filter);
        Pageable pageable = PageRequest.of(offset, limit);
        var entrenamientos = entrenamientoService.getAllEntrenamientos(filter, pageable).getContent();
        return entrenamientoAssembler.toCollectionModel(entrenamientos);
    }

    // Obtener un entrenamiento por su slug
    @GetMapping("/entrenamientos/{slug}")
    public EntrenamientoResponse getEntrenamientoBySlug(@PathVariable String slug) {
        var entrenamiento = entrenamientoService.getEntrenamientoBySlug(slug);
        return entrenamientoAssembler.toResponse(entrenamiento);
    }

    //Obtener los datos de los entrenamientos para ponerlos en algunos filtros.
    @GetMapping("/entrenamientos/data")
    public EntrenamientoDataResponse getEntrenamientoData() {
        // Obtenemos los datos desde el servicio
        var data = entrenamientoService.getEntrenamientoData();

        // Usamos el Assembler para convertir los datos a un DTO de respuesta
        return entrenamientoAssembler.toDataResponse(
            data.getTotalEntrenamientos(),
            data.getPrecioMinimo(),
            data.getPrecioMaximo(),
            data.getDuracionMinima(),
            data.getDuracionMaxima(),
            data.getPlazasMinimas(),
            data.getPlazasMaximas()
        );
    }
}
