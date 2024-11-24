package com.polideportivo.backend_springboot.api.controller;

import com.polideportivo.backend_springboot.api.assembler.PistaAssembler;
import com.polideportivo.backend_springboot.api.model.pista.PistaWrapper;
import com.polideportivo.backend_springboot.domain.service.PistaService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PistaController {
    
    private final PistaService pistaService;
    private final PistaAssembler pistaAssembler;

    // Obtener todos las pistas
    @GetMapping("/pistas")
    @Transactional
    public PistaWrapper getAllDeportes() {

        var pistas = pistaService.getAllPistas();
        return pistaAssembler.toCollectionModel(pistas);
    }
}
