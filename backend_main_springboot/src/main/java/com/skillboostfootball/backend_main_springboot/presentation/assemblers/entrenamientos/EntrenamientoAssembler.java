package com.skillboostfootball.backend_main_springboot.presentation.assemblers.entrenamientos;


import com.skillboostfootball.backend_main_springboot.domain.entities.entrenamientos.Entrenamiento;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.response.EntrenamientoResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.response.EntrenamientoWrapper;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.tecnificaciones.TecnificacionAssembler;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.subtiposTecnificacion.SubtipoTecnificacionAssembler;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.horariosPista.HorarioPistaAssembler;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EntrenamientoAssembler {
    private final TecnificacionAssembler tecnificacionAssembler;
    private final SubtipoTecnificacionAssembler subtipoAssembler;
    private final HorarioPistaAssembler horarioPistaAssembler;
    
    public EntrenamientoResponse toResponse(Entrenamiento entrenamiento) {
        EntrenamientoResponse response = new EntrenamientoResponse();
        
        response.setId(entrenamiento.getId());
        response.setNombre(entrenamiento.getNombre());
        response.setSlug(entrenamiento.getSlug());
        response.setDescripcion(entrenamiento.getDescripcion());
        response.setNivel(entrenamiento.getNivel());
        response.setEdadMinima(entrenamiento.getEdadMinima());
        response.setEdadMaxima(entrenamiento.getEdadMaxima());
        
        if (entrenamiento.getTecnificacion() != null) {
            response.setTecnificacion(tecnificacionAssembler.toResponse(entrenamiento.getTecnificacion()));
        }
        
        if (entrenamiento.getSubtipoTecnificacion() != null) {
            response.setSubtipoTecnificacion(subtipoAssembler.toResponse(entrenamiento.getSubtipoTecnificacion()));
        }
        
        response.setEntrenadorId(entrenamiento.getEntrenadorId());
        
        if (entrenamiento.getHorarioPista() != null) {
            response.setHorarioPista(horarioPistaAssembler.toResponse(entrenamiento.getHorarioPista()));
        }
        response.setMaxPlazas(entrenamiento.getMaxPlazas());
        response.setObjetivos(entrenamiento.getObjetivos());
        response.setEquipamientoNecesario(entrenamiento.getEquipamientoNecesario());
        response.setDuracionMinutos(entrenamiento.getDuracionMinutos());
        response.setImages(entrenamiento.getImages());
        
        return response;
    }
    
    public EntrenamientoWrapper toWrapper(Page<Entrenamiento> entrenamientoPage) {
        List<EntrenamientoResponse> responseList = entrenamientoPage.getContent().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
                
        return EntrenamientoWrapper.builder()
                .entrenamientos(responseList)
                .build();
    }
}
