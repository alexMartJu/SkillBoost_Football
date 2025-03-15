package com.skillboostfootball.backend_main_springboot.presentation.controllers.inscripciones;

import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.application.useCases.inscripciones.GetAllInscripcionesByEntrenadorUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.inscripciones.GetInscripcionesByEntrenamientoUseCase;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.inscripciones.InscripcionAssembler;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.inscripciones.response.InscripcionWrapper;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/entrenador")
public class InscripcionController {
    
    private final GetInscripcionesByEntrenamientoUseCase getInscripcionesByEntrenamientoUseCase;
    private final GetAllInscripcionesByEntrenadorUseCase getAllInscripcionesByEntrenadorUseCase;
    private final InscripcionAssembler inscripcionAssembler;
    
    //Listar todas las inscripciones de los entrenamientos del entrenador
    @GetMapping("/inscripciones")
    @CheckSecurity.Entrenador.canAccess
    public InscripcionWrapper getAllInscripciones() {
        var inscripciones = getAllInscripcionesByEntrenadorUseCase.execute();
        return inscripcionAssembler.toWrapper(inscripciones);
    }
    
    //Listar inscripciones de un entrenamiento específico
    @GetMapping("/entrenamientos/{slug}/inscripciones")
    @CheckSecurity.Entrenador.canAccess
    public InscripcionWrapper getInscripcionesByEntrenamiento(@PathVariable String slug) {
        var inscripciones = getInscripcionesByEntrenamientoUseCase.execute(slug);
        return inscripcionAssembler.toWrapper(inscripciones);
    }
}
