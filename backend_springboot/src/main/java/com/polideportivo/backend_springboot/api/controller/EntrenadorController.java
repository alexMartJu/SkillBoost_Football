package com.polideportivo.backend_springboot.api.controller;

import com.polideportivo.backend_springboot.api.assembler.EntrenadorAssembler;
import com.polideportivo.backend_springboot.api.model.entrenador.EntrenadorWrapper;
import com.polideportivo.backend_springboot.api.security.authorization.CheckSecurity;
import com.polideportivo.backend_springboot.domain.service.EntrenadorService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EntrenadorController {
    
    private final EntrenadorService entrenadorService;
    private final EntrenadorAssembler entrenadorAssembler;

    // Obtener todos los entrenadores
    @GetMapping("/entrenadores")
    @CheckSecurity.Public.canRead
    @Transactional
    public EntrenadorWrapper getAllEntrenadores() {

        var entrenadores = entrenadorService.getAllEntrenadores();
        return entrenadorAssembler.toCollectionModel(entrenadores);
    }
}
