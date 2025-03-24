package com.skillboostfootball.backend_main_springboot.presentation.controllers.entrenamientos;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.entrenamientos.EntrenamientoAssembler;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.request.CreateEntrenamientoRequest;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.response.EntrenamientoResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.response.EntrenamientoWrapper;

import jakarta.validation.Valid;

import com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos.CreateEntrenamientoUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos.GetEntrenamientosByCoachAndStatusUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class EntrenamientoCoachController {
    
    private final GetEntrenamientosByCoachAndStatusUseCase getEntrenamientosByEntrenadorAndStatusUseCase;
    private final CreateEntrenamientoUseCase createEntrenamientoUseCase;
    private final EntrenamientoAssembler assembler;

    //Listar entrenamientos pendientes del entrenador logueado
    @GetMapping("/entrenador/entrenamientos/pending")
    @CheckSecurity.Entrenador.canAccess
    public EntrenamientoWrapper getPendingEntrenamientos() {
        var entrenamientos = getEntrenamientosByEntrenadorAndStatusUseCase.execute(List.of("pending"));
        return assembler.toWrapper(entrenamientos);
    }

    //Listar entrenamientos aprobados (activos o completados) del entrenador logueado
    @GetMapping("/entrenador/entrenamientos/approved")
    @CheckSecurity.Entrenador.canAccess
    public EntrenamientoWrapper getApprovedEntrenamientos() {
        var entrenamientos = getEntrenamientosByEntrenadorAndStatusUseCase.execute(List.of("active", "completed", "expired"));
        return assembler.toWrapper(entrenamientos);
    }

    //Listar entrenamientos denegados del entrenador logueado
    @GetMapping("/entrenador/entrenamientos/denied")
    @CheckSecurity.Entrenador.canAccess
    public EntrenamientoWrapper getDeniedEntrenamientos() {
        var entrenamientos = getEntrenamientosByEntrenadorAndStatusUseCase.execute(List.of("denied"));
        return assembler.toWrapper(entrenamientos);
    }

    //Crear un nuevo entrenamiento viendo la disponibilidad de horarios y pista (solo entrenadores)
    @PostMapping("/entrenador/entrenamientos/create")
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Entrenador.canAccess
    public EntrenamientoResponse createEntrenamiento(@Valid @RequestBody CreateEntrenamientoRequest request) {
        
        var entrenamiento = createEntrenamientoUseCase.execute(request.getNombre(), request.getDescripcion(), request.getNivel(),
            request.getEdadMinima(), request.getEdadMaxima(), request.getTecnificacionSlug(), request.getSubtipoTecnificacionSlug(),
            request.getPistaSlug(), request.getFechaInicio(), request.getFechaFin(), request.getMaxPlazas(), request.getObjetivos(),
            request.getEquipamientoNecesario(), request.getDuracionMinutos());
        
        return assembler.toResponse(entrenamiento);
        
    }

}
