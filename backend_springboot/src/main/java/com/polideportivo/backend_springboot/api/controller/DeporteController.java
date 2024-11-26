package com.polideportivo.backend_springboot.api.controller;

import com.polideportivo.backend_springboot.api.assembler.DeporteAssembler;
import com.polideportivo.backend_springboot.api.assembler.PistaAssembler;
import com.polideportivo.backend_springboot.api.model.deporte.DeporteResponse;
import com.polideportivo.backend_springboot.api.model.deporte.DeporteWrapper;
import com.polideportivo.backend_springboot.api.model.pista.PistaWrapper;
import com.polideportivo.backend_springboot.domain.service.DeporteService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DeporteController {

    private final DeporteService deporteService;
    private final DeporteAssembler deporteAssembler;
    private final PistaAssembler pistaAssembler;
    
    // Obtener todos los deportes
    @GetMapping("/deportes")
    @Transactional
    public DeporteWrapper getAllDeportes() {

        var deportes = deporteService.getAllDeportes();
        return deporteAssembler.toCollectionModel(deportes);
    }

    // Obtener un deporte por su slug
    @GetMapping("/deportes/{slug}")
    public DeporteResponse getBySlug(@PathVariable String slug) {
        var deporte = deporteService.getBySlug(slug);
        return deporteAssembler.toResponse(deporte);
    }

    // Obtener las pistas de un deporte
    @GetMapping("/deportes/{slug}/pistas")
    public PistaWrapper getPistasByDeporte(@PathVariable String slug) {
        var deporte = deporteService.getBySlug(slug); // Busca por slug
        var pistas = deporte.getPistas();
        System.out.println(pistas);

        return pistaAssembler.toCollectionModel(pistas);
    }
}
