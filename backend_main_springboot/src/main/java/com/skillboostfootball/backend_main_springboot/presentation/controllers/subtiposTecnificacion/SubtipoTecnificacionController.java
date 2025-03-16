package com.skillboostfootball.backend_main_springboot.presentation.controllers.subtiposTecnificacion;

import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.application.useCases.subtiposTecnificacion.*;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.subtiposTecnificacion.request.SubtipoTecnificacionRequest;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.subtiposTecnificacion.response.SubtipoTecnificacionResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.subtiposTecnificacion.response.SubtipoTecnificacionWrapper;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.subtiposTecnificacion.SubtipoTecnificacionAssembler;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class SubtipoTecnificacionController {

    private final GetAllSubtipoTecnificacionesUseCase getAllSubtiposUseCase;
    private final GetSubtipoTecnificacionBySlugUseCase getSubtipoBySlugUseCase;
    private final CreateSubtipoTecnificacionUseCase createSubtipoUseCase;
    private final UpdateSubtipoTecnificacionUseCase updateSubtipoUseCase;
    private final DeleteSubtipoTecnificacionUseCase deleteSubtipoUseCase;
    private final GetSubtiposByTecnificacionSlugUseCase getSubtiposByTecnificacionSlugUseCase;
    private final GetAllSubtipoTecnificacionesScrollUseCase getAllSubtiposScrollUseCase;
    private final SubtipoTecnificacionAssembler assembler;
    
    //Listar todos los subtipos de tecnificaciones
    @GetMapping("/subtipo-tecnificaciones")
    @CheckSecurity.Public.canRead
    public SubtipoTecnificacionWrapper getAllSubtipos() {
        var subtipos = getAllSubtiposUseCase.execute();
        return assembler.toWrapper(subtipos);
    }

    //Listar un subtipo de tecnificación por slug
    @GetMapping("/subtipo-tecnificaciones/{slug}")
    @CheckSecurity.Public.canRead
    public SubtipoTecnificacionResponse getBySlug(@PathVariable String slug) {
        var subtipo = getSubtipoBySlugUseCase.execute(slug);
        return assembler.toResponse(subtipo);
    }
    
    //Crear un subtipo de tecnificación
    @PostMapping("/tecnificaciones/{tecnificacionSlug}/subtipos")
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Admin.canAccess
    public SubtipoTecnificacionResponse createSubtipo(@PathVariable String tecnificacionSlug, @Valid @RequestBody SubtipoTecnificacionRequest request) {
        var subtipo = createSubtipoUseCase.execute(tecnificacionSlug, request.getNombre(), request.getDescripcion(), request.getTipoEntrenamiento(),
            request.getObjetivos(), request.getBeneficios(), request.getFrecuenciaSugerida(), request.getMetodoEvaluacion(), request.getTecnologiaUtilizada());
        return assembler.toResponse(subtipo);
    }
    
    //Actualizar un subtipo de tecnificación
    @PutMapping("/subtipo-tecnificaciones/{slug}")
    @CheckSecurity.Admin.canAccess
    public SubtipoTecnificacionResponse updateSubtipo(@PathVariable String slug, @Valid @RequestBody SubtipoTecnificacionRequest request) {
        var subtipo = updateSubtipoUseCase.execute(slug, request.getNombre(), request.getDescripcion(), request.getTipoEntrenamiento(),
            request.getObjetivos(), request.getBeneficios(), request.getFrecuenciaSugerida(), request.getMetodoEvaluacion(), request.getTecnologiaUtilizada());
        return assembler.toResponse(subtipo);
    }
    
    //Eliminar un subtipo de tecnificación (soft delete)
    @DeleteMapping("/subtipo-tecnificaciones/{slug}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Admin.canAccess
    public ResponseEntity<Void> deleteSubtipo(@PathVariable String slug) {
        deleteSubtipoUseCase.execute(slug);
        return ResponseEntity.noContent().build();
    }

    //Obtener subtipos por slug de tecnificación
    @GetMapping("/tecnificaciones/{tecnificacionSlug}/subtipos")
    @CheckSecurity.Public.canRead
    public SubtipoTecnificacionWrapper getSubtiposByTecnificacionSlug(@PathVariable String tecnificacionSlug) {
        var subtipos = getSubtiposByTecnificacionSlugUseCase.execute(tecnificacionSlug);
        return assembler.toWrapper(subtipos);
    }
    
    //Obtener subtipos con paginación para scroll infinito
    @GetMapping("/subtipo-tecnificaciones-scroll")
    @CheckSecurity.Public.canRead
    public SubtipoTecnificacionWrapper getAllSubtiposScroll(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "4") int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        var subtipos = getAllSubtiposScrollUseCase.execute(pageable);
        return assembler.toWrapper(subtipos);
    }
}