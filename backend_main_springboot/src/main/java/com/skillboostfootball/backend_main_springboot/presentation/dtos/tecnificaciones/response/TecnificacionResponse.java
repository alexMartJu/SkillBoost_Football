package com.skillboostfootball.backend_main_springboot.presentation.dtos.tecnificaciones.response;

import com.skillboostfootball.backend_main_springboot.presentation.dtos.subtiposTecnificacion.response.SubtipoTecnificacionResponse;
import com.skillboostfootball.backend_main_springboot.domain.entities.images.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TecnificacionResponse {
    private Long id;
    private String nombre;
    private String slug;
    private String descripcion;
    private List<SubtipoTecnificacionResponse> subtipos;
    private List<Image> images;
}
