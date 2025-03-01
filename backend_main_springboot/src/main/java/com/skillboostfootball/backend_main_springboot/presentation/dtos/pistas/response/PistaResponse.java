package com.skillboostfootball.backend_main_springboot.presentation.dtos.pistas.response;

import com.skillboostfootball.backend_main_springboot.domain.entities.images.Image;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PistaResponse {

    private Long id;
    private String nombre;
    private String slug;
    private String descripcion;
    private String tipo;
    private String dimensiones;
    private Integer capacidad;
    private List<Image> images;
    
}
