package com.skillboostfootball.backend_main_springboot.presentation.assemblers.tecnificaciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.tecnificaciones.Tecnificacion;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.tecnificaciones.response.TecnificacionResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.tecnificaciones.response.TecnificacionWrapper;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.subtiposTecnificacion.SubtipoTecnificacionAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TecnificacionAssembler {
    private final SubtipoTecnificacionAssembler subtipoMapper;
    
    public TecnificacionResponse toResponse(Tecnificacion tecnificacion) {
        TecnificacionResponse response = new TecnificacionResponse();
        
        response.setId(tecnificacion.getId());
        response.setNombre(tecnificacion.getNombre());
        response.setSlug(tecnificacion.getSlug());
        response.setDescripcion(tecnificacion.getDescripcion());
        response.setImages(tecnificacion.getImages());
        
        if (tecnificacion.getSubtipos() != null) {
            response.setSubtipos(
                tecnificacion.getSubtipos().stream()
                    .map(subtipoMapper::toResponse)
                    .collect(Collectors.toList())
            );
        }
        
        return response;
    }
    
    public TecnificacionWrapper toWrapper(List<Tecnificacion> tecnificaciones) {
        List<TecnificacionResponse> responseList = tecnificaciones.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
                
        return TecnificacionWrapper.builder()
                .tecnificaciones(responseList)
                .build();
    }
}
