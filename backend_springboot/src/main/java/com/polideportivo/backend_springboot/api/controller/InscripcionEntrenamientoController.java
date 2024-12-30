package com.polideportivo.backend_springboot.api.controller;

import com.polideportivo.backend_springboot.domain.model.InscripcionEntrenamiento;
import com.polideportivo.backend_springboot.domain.service.InscripcionEntrenamientoService;
import com.polideportivo.backend_springboot.api.model.inscripcionEntrenamiento.InscripcionEntrenamientoResponse;
import com.polideportivo.backend_springboot.api.security.authorization.CheckSecurity;
import com.polideportivo.backend_springboot.api.assembler.InscripcionEntrenamientoAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InscripcionEntrenamientoController {
    
    private final InscripcionEntrenamientoService inscripcionEntrenamientoService;
    private final InscripcionEntrenamientoAssembler inscripcionEntrenamientoAssembler;

    // Endpoint para inscribirse
    @PostMapping("/entrenamientos/{slug}/inscripcion")
    @CheckSecurity.Protected.canManage
    public ResponseEntity<InscripcionEntrenamientoResponse> inscribir(@PathVariable String slug) {
        // Llamamos al servicio para inscribir
        InscripcionEntrenamiento inscripcion = inscripcionEntrenamientoService.inscribir(slug);

        // Usamos el Assembler para convertir la inscripción en un DTO de respuesta
        InscripcionEntrenamientoResponse response = inscripcionEntrenamientoAssembler.toResponse(inscripcion);

        return ResponseEntity.ok(response);
    }

    // Endpoint para desinscribirse
    @DeleteMapping("/entrenamientos/{slug}/inscripcion")
    @CheckSecurity.Protected.canManage
    public ResponseEntity<InscripcionEntrenamientoResponse> desinscribir(@PathVariable String slug) {
        // Llamamos al servicio para desinscribir
        String numeroSocio = inscripcionEntrenamientoService.desinscribir(slug);

        // Usamos el Assembler para crear una respuesta de desinscripción
        InscripcionEntrenamientoResponse response = inscripcionEntrenamientoAssembler.toDesinscripcionResponse(slug, numeroSocio);

        return ResponseEntity.ok(response);
    }
}
