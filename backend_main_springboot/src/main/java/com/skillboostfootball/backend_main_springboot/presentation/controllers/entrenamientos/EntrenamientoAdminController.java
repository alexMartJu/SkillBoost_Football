package com.skillboostfootball.backend_main_springboot.presentation.controllers.entrenamientos;

import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos.ApproveEntrenamientoUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos.DenyEntrenamientoUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos.GetPendingEntrenamientosUseCase;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.response.EntrenamientoResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.response.EntrenamientoWrapper;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.entrenamientos.EntrenamientoAssembler;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class EntrenamientoAdminController {

    private final GetPendingEntrenamientosUseCase getPendingEntrenamientosUseCase;
    private final ApproveEntrenamientoUseCase approveEntrenamientoUseCase;
    private final DenyEntrenamientoUseCase denyEntrenamientoUseCase;
    private final EntrenamientoAssembler assembler;

    //Listar todos los entrenamientos pendientes (solo admins)
    @GetMapping("/admin/entrenamientos/pending")
    @CheckSecurity.Admin.canAccess
    public EntrenamientoWrapper getPendingEntrenamientos() {
        var entrenamientos = getPendingEntrenamientosUseCase.execute();
        var responses = entrenamientos.stream().map(assembler::toResponse).collect(Collectors.toList());
        return EntrenamientoWrapper.builder()
                .entrenamientos(responses)
                .build();
    }

    //Aprobar un entrenamiento (solo admins)
    @PostMapping("/admin/entrenamientos/{slug}/approve")
    @CheckSecurity.Admin.canAccess
    public EntrenamientoResponse approveEntrenamiento(@PathVariable String slug) {
        var entrenamiento = approveEntrenamientoUseCase.execute(slug);
        return assembler.toResponse(entrenamiento);
    }

    //Rechazar un entrenamiento (solo admins)
    @PostMapping("/admin/entrenamientos/{slug}/deny")
    @CheckSecurity.Admin.canAccess
    public EntrenamientoResponse denyEntrenamiento(@PathVariable String slug) {
        var entrenamiento = denyEntrenamientoUseCase.execute(slug);
        return assembler.toResponse(entrenamiento);
    }

}
