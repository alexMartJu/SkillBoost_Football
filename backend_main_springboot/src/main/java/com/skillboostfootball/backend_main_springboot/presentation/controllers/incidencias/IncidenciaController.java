package com.skillboostfootball.backend_main_springboot.presentation.controllers.incidencias;

import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.application.useCases.incidencias.CreateIncidenciaUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.usuarios.GetCurrentUserUseCase;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.incidencias.request.IncidenciaRequest;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.incidencias.response.IncidenciaResponse;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.incidencias.IncidenciaAssembler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class IncidenciaController {
    private final CreateIncidenciaUseCase createIncidenciaUseCase;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final IncidenciaAssembler assembler;
    
    //Crear una incidencia (cualquier usuario autenticado)
    @PostMapping("/incidencias")
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Protected.canManage
    public IncidenciaResponse createIncidencia(@Valid @RequestBody IncidenciaRequest request) {
        var currentUser = getCurrentUserUseCase.execute();
        var incidencia = createIncidenciaUseCase.execute(
            currentUser.getId(),
            request.getTipo(),
            request.getDescripcion(),
            request.getMetodoPago(),
            request.getReferenciaExterna()
        );
        return assembler.toResponse(incidencia);
    }
}
