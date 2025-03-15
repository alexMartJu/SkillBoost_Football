package com.skillboostfootball.backend_main_springboot.presentation.controllers.evaluaciones;

import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.application.useCases.evaluaciones.CreateEvaluacionRendimientoUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.evaluaciones.GetEvaluacionByProfileAndEntrenamientoUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.evaluaciones.UpdateEvaluacionRendimientoUseCase;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.evaluaciones.EvaluacionRendimientoAssembler;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.evaluaciones.request.EvaluacionRendimientoRequest;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.evaluaciones.response.EvaluacionRendimientoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/entrenador")
public class EvaluacionRendimientoController {
    
    private final CreateEvaluacionRendimientoUseCase createEvaluacionRendimientoUseCase;
    private final UpdateEvaluacionRendimientoUseCase updateEvaluacionRendimientoUseCase;
    private final GetEvaluacionByProfileAndEntrenamientoUseCase getEvaluacionByProfileAndEntrenamientoUseCase;
    private final EvaluacionRendimientoAssembler evaluacionAssembler;
    
    //Crear una evaluación de rendimiento para un usuario en un entrenamiento
    @PostMapping("/profiles/{numeroSocio}/entrenamientos/{slug}/evaluaciones")
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Entrenador.canAccess
    public EvaluacionRendimientoResponse createEvaluacion(@PathVariable String numeroSocio, @PathVariable String slug,
        @Valid @RequestBody EvaluacionRendimientoRequest request) {
        
        var evaluacion = createEvaluacionRendimientoUseCase.execute(numeroSocio, slug, request.getPuntuacion(), request.getComentarios(), request.getAreasMejora());
        return evaluacionAssembler.toResponse(evaluacion);
    }
    
    //Actualizar una evaluación de rendimiento existente
    @PutMapping("/profiles/{numeroSocio}/entrenamientos/{slug}/evaluaciones")
    @CheckSecurity.Entrenador.canAccess
    public EvaluacionRendimientoResponse updateEvaluacion(@PathVariable String numeroSocio, @PathVariable String slug,
        @Valid @RequestBody EvaluacionRendimientoRequest request) {
        
        var evaluacion = updateEvaluacionRendimientoUseCase.execute(numeroSocio, slug, request.getPuntuacion(),
            request.getComentarios(), request.getAreasMejora());
        return evaluacionAssembler.toResponse(evaluacion);
    }
    
    //Obtener una evaluación de rendimiento específica
    @GetMapping("/profiles/{numeroSocio}/entrenamientos/{slug}/evaluaciones")
    @CheckSecurity.Entrenador.canAccess
    public EvaluacionRendimientoResponse getEvaluacion(@PathVariable String numeroSocio, @PathVariable String slug) {
        var evaluacion = getEvaluacionByProfileAndEntrenamientoUseCase.execute(numeroSocio, slug);
        return evaluacionAssembler.toResponse(evaluacion);
    }
}
