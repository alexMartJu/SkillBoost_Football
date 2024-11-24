package com.polideportivo.backend_springboot.api.controller;

import com.polideportivo.backend_springboot.api.assembler.DeporteAssembler;
import com.polideportivo.backend_springboot.api.model.deporte.DeporteWrapper;
import com.polideportivo.backend_springboot.domain.service.DeporteService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DeporteController {

    private final DeporteService deporteService;
    private final DeporteAssembler deporteAssembler;
    
    // Obtener todos los deportes
    @GetMapping("/deportes")
    @Transactional
    public DeporteWrapper getAllDeportes() {

        var deportes = deporteService.getAllDeportes();
        return deporteAssembler.toCollectionModel(deportes);
    }
}
