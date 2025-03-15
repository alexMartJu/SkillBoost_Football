package com.skillboostfootball.backend_main_springboot.presentation.assemblers.inscripciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.evaluaciones.EvaluacionRendimiento;
import com.skillboostfootball.backend_main_springboot.domain.entities.reservas.Reserva;
import com.skillboostfootball.backend_main_springboot.domain.repositories.evaluaciones.EvaluacionRendimientoRepository;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.entrenamientos.EntrenamientoAssembler;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.evaluaciones.EvaluacionRendimientoAssembler;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.profiles.ProfileAssembler;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.inscripciones.response.InscripcionResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.inscripciones.response.InscripcionWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InscripcionAssembler {
    
    private final ProfileAssembler profileAssembler;
    private final EntrenamientoAssembler entrenamientoAssembler;
    private final EvaluacionRendimientoAssembler evaluacionAssembler;
    private final EvaluacionRendimientoRepository evaluacionRepository;
    
    public InscripcionResponse toResponse(Reserva reserva) {
        InscripcionResponse response = new InscripcionResponse();
        
        response.setId(reserva.getId());
        response.setProfile(profileAssembler.toResponse(reserva.getProfile()));
        response.setEntrenamiento(entrenamientoAssembler.toResponse(reserva.getEntrenamiento()));
        response.setFecha(reserva.getFecha());
        
        //Buscar si existe una evaluación para esta inscripción
        Optional<EvaluacionRendimiento> evaluacion = evaluacionRepository.findByProfileIdAndEntrenamientoId(reserva.getProfile().getId(), reserva.getEntrenamiento().getId());
        evaluacion.ifPresent(eval -> response.setEvaluacion(evaluacionAssembler.toResponse(eval)));
        
        return response;
    }
    
    public InscripcionWrapper toWrapper(List<Reserva> reservas) {
        List<InscripcionResponse> responseList = reservas.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
                
        return InscripcionWrapper.builder()
                .inscripciones(responseList)
                .build();
    }
    
}
