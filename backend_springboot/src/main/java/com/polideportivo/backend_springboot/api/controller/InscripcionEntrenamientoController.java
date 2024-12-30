package com.polideportivo.backend_springboot.api.controller;

import com.polideportivo.backend_springboot.domain.model.Entrenamiento;
import com.polideportivo.backend_springboot.domain.model.InscripcionEntrenamiento;
import com.polideportivo.backend_springboot.domain.service.InscripcionEntrenamientoService;
import com.polideportivo.backend_springboot.api.model.entrenamiento.EntrenamientoWrapper;
import com.polideportivo.backend_springboot.api.model.inscripcionEntrenamiento.InscripcionEntrenamientoResponse;
import com.polideportivo.backend_springboot.api.security.authorization.CheckSecurity;
import com.polideportivo.backend_springboot.api.assembler.EntrenamientoAssembler;
import com.polideportivo.backend_springboot.api.assembler.InscripcionEntrenamientoAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InscripcionEntrenamientoController {
    
    private final InscripcionEntrenamientoService inscripcionEntrenamientoService;
    private final InscripcionEntrenamientoAssembler inscripcionEntrenamientoAssembler;
    private final EntrenamientoAssembler entrenamientoAssembler;

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

    // Endpoint para obtener los entrenamientos a los que el usuario está inscrito
    @GetMapping("/profileEntrenamientos")
    @CheckSecurity.Protected.canManage
    public EntrenamientoWrapper obtenerEntrenamientosInscritos() {
        // Llamamos al servicio para obtener los entrenamientos
        List<Entrenamiento> entrenamientos = inscripcionEntrenamientoService.obtenerEntrenamientosInscritos();
        
        // Convertimos los entrenamientos a DTOs de respuesta
        return entrenamientoAssembler.toCollectionModel(entrenamientos);
    }
}
