package com.skillboostfootball.backend_main_springboot.presentation.controllers.entrenadores;

import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.application.useCases.entrenadores.DeleteCoachUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.entrenadores.GetActiveCoachesUseCase;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.entrenadores.EntrenadorAssembler;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenadores.response.EntrenadorWrapper;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EntrenadorController {

    private final GetActiveCoachesUseCase getActiveCoachesUseCase;
    private final DeleteCoachUseCase deleteCoachUseCase;
    private final EntrenadorAssembler assembler;
    
    //Listar todos los entrenadores activos
    @GetMapping("/entrenadores")
    @CheckSecurity.Public.canRead
    public EntrenadorWrapper getAllActiveCoaches() {
        var entrenadores = getActiveCoachesUseCase.execute();
        return assembler.toWrapper(entrenadores);
    }
    
    //Eliminar un entrenador (solo para administradores)
    @DeleteMapping("/entrenadores/{numeroEntrenador}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Admin.canAccess
    public ResponseEntity<Void> deleteCoach(@PathVariable String numeroEntrenador) {
        deleteCoachUseCase.execute(numeroEntrenador);
        return ResponseEntity.noContent().build();
    }
}
