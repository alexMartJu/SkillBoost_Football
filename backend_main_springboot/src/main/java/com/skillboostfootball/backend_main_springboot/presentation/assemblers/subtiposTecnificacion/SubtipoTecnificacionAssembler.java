package com.skillboostfootball.backend_main_springboot.presentation.assemblers.subtiposTecnificacion;

import com.skillboostfootball.backend_main_springboot.domain.entities.subtiposTecnificacion.SubtipoTecnificacion;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.subtiposTecnificacion.response.SubtipoTecnificacionResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.subtiposTecnificacion.response.SubtipoTecnificacionWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SubtipoTecnificacionAssembler {
    
    public SubtipoTecnificacionResponse toResponse(SubtipoTecnificacion subtipo) {
        SubtipoTecnificacionResponse response = new SubtipoTecnificacionResponse();
        
        response.setId(subtipo.getId());
        response.setNombre(subtipo.getNombre());
        response.setSlug(subtipo.getSlug());
        response.setDescripcion(subtipo.getDescripcion());
        response.setTipoEntrenamiento(subtipo.getTipoEntrenamiento());
        response.setObjetivos(subtipo.getObjetivos());
        response.setBeneficios(subtipo.getBeneficios());
        response.setFrecuenciaSugerida(subtipo.getFrecuenciaSugerida());
        response.setMetodoEvaluacion(subtipo.getMetodoEvaluacion());
        response.setTecnologiaUtilizada(subtipo.getTecnologiaUtilizada());
        response.setImages(subtipo.getImages());
        
        return response;
    }
    
    public SubtipoTecnificacionWrapper toWrapper(List<SubtipoTecnificacion> subtipos) {
        List<SubtipoTecnificacionResponse> responseList = subtipos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
                
        return SubtipoTecnificacionWrapper.builder()
                .subtipos(responseList)
                .build();
    }
}
