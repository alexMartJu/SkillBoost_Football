package com.skillboostfootball.backend_main_springboot.presentation.dtos.subtiposTecnificacion.response;

import com.skillboostfootball.backend_main_springboot.domain.entities.images.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubtipoTecnificacionResponse {
    private Long id;
    private String nombre;
    private String slug;
    private String descripcion;
    private String tipoEntrenamiento;
    private String objetivos;
    private String beneficios;
    private String frecuenciaSugerida;
    private String metodoEvaluacion;
    private String tecnologiaUtilizada;
    private List<Image> images;
}
