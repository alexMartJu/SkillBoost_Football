package com.skillboostfootball.backend_main_springboot.presentation.controllers.tecnificaciones;


import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.application.useCases.tecnificaciones.*;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.tecnificaciones.request.TecnificacionRequest;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.tecnificaciones.response.TecnificacionResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.tecnificaciones.response.TecnificacionWrapper;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.tecnificaciones.TecnificacionAssembler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class TecnificacionController {
    private final GetAllTecnificacionesUseCase getAllTecnificacionesUseCase;
    private final GetTecnificacionBySlugUseCase getTecnificacionBySlugUseCase;
    private final CreateTecnificacionUseCase createTecnificacionUseCase;
    private final UpdateTecnificacionUseCase updateTecnificacionUseCase;
    private final DeleteTecnificacionUseCase deleteTecnificacionUseCase;
    private final TecnificacionAssembler assembler;
    
    //Listar todas las tecnificaciones
    @GetMapping("/tecnificaciones")
    @CheckSecurity.Public.canRead
    public TecnificacionWrapper getAllTecnificaciones() {
        var tecnificaciones = getAllTecnificacionesUseCase.execute();
        return assembler.toWrapper(tecnificaciones);
    }

    //Listar uan tecnificación por slug
    @GetMapping("/tecnificaciones/{slug}")
    @CheckSecurity.Public.canRead
    public TecnificacionResponse getBySlug(@PathVariable String slug) {
        var tecnificacion = getTecnificacionBySlugUseCase.execute(slug);
        return assembler.toResponse(tecnificacion);
    }
    
    //Crear una tecnificación
    @PostMapping("/tecnificaciones")
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Admin.canAccess
    public TecnificacionResponse createTecnificacion(@Valid @RequestBody TecnificacionRequest request) {
        var tecnificacion = createTecnificacionUseCase.execute(request.getNombre(), request.getDescripcion());
        return assembler.toResponse(tecnificacion);
    }
    
    //Actualizar una tecnificación
    @PutMapping("/tecnificaciones/{slug}")
    @CheckSecurity.Admin.canAccess
    public TecnificacionResponse updateTecnificacion(@PathVariable String slug, @Valid @RequestBody TecnificacionRequest request) {
        var tecnificacion = updateTecnificacionUseCase.execute(slug, request.getNombre(), request.getDescripcion());
        return assembler.toResponse(tecnificacion);
    }
    
    //Eliminar una tecnificación (soft delete)
    @DeleteMapping("/tecnificaciones/{slug}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Admin.canAccess
    public ResponseEntity<Void> deleteTecnificacion(@PathVariable String slug) {
        deleteTecnificacionUseCase.execute(slug);
        return ResponseEntity.noContent().build();
    }
}
