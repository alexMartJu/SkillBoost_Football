package com.skillboostfootball.backend_main_springboot.presentation.controllers.subtiposTecnificacion;

import com.skillboostfootball.backend_main_springboot.application.useCases.subtiposTecnificacion.*;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.subtiposTecnificacion.request.SubtipoTecnificacionRequest;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.subtiposTecnificacion.response.SubtipoTecnificacionResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.subtiposTecnificacion.response.SubtipoTecnificacionWrapper;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.subtiposTecnificacion.SubtipoTecnificacionAssembler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SubtipoTecnificacionController {

    private final GetAllSubtipoTecnificacionesUseCase getAllSubtiposUseCase;
    private final GetSubtipoTecnificacionBySlugUseCase getSubtipoBySlugUseCase;
    private final CreateSubtipoTecnificacionUseCase createSubtipoUseCase;
    private final UpdateSubtipoTecnificacionUseCase updateSubtipoUseCase;
    private final DeleteSubtipoTecnificacionUseCase deleteSubtipoUseCase;
    private final SubtipoTecnificacionAssembler assembler;
    
    // Listar todos los subtipos de tecnificaciones
    @GetMapping("/subtipo-tecnificaciones")
    public SubtipoTecnificacionWrapper getAllSubtipos() {
        var subtipos = getAllSubtiposUseCase.execute();
        return assembler.toWrapper(subtipos);
    }

    // Listar un subtipo de tecnificación por slug
    @GetMapping("/subtipo-tecnificaciones/{slug}")
    public SubtipoTecnificacionResponse getBySlug(@PathVariable String slug) {
        var subtipo = getSubtipoBySlugUseCase.execute(slug);
        return assembler.toResponse(subtipo);
    }
    
    //Crear un subtipo de tecnificación
    @PostMapping("/tecnificaciones/{tecnificacionSlug}/subtipos")
    @ResponseStatus(HttpStatus.CREATED)
    public SubtipoTecnificacionResponse createSubtipo(@PathVariable String tecnificacionSlug, @Valid @RequestBody SubtipoTecnificacionRequest request) {
        var subtipo = createSubtipoUseCase.execute(tecnificacionSlug, request.getNombre(), request.getDescripcion());
        return assembler.toResponse(subtipo);
    }
    
    //Actualizar un subtipo de tecnificación
    @PutMapping("/subtipo-tecnificaciones/{slug}")
    public SubtipoTecnificacionResponse updateSubtipo(@PathVariable String slug, @Valid @RequestBody SubtipoTecnificacionRequest request) {
        var subtipo = updateSubtipoUseCase.execute(slug, request.getNombre(), request.getDescripcion());
        return assembler.toResponse(subtipo);
    }
    
    //Eliminar un subtipo de tecnificación (soft delete)
    @DeleteMapping("/subtipo-tecnificaciones/{slug}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteSubtipo(@PathVariable String slug) {
        deleteSubtipoUseCase.execute(slug);
        return ResponseEntity.noContent().build();
    }
}