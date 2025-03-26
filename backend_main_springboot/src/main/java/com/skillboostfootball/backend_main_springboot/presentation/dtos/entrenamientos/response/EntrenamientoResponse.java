package com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.response;

import com.skillboostfootball.backend_main_springboot.domain.entities.images.Image;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.tecnificaciones.response.TecnificacionResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.subtiposTecnificacion.response.SubtipoTecnificacionResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenadores.response.EntrenadorResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.horariosPista.response.HorarioPistaResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EntrenamientoResponse {
    private Long id;
    private String nombre;
    private String slug;
    private String descripcion;
    private String nivel;
    private Integer edadMinima;
    private Integer edadMaxima;
    private TecnificacionResponse tecnificacion;
    private SubtipoTecnificacionResponse subtipoTecnificacion;
    private EntrenadorResponse entrenador;
    private HorarioPistaResponse horarioPista;
    private Integer maxPlazas;
    private String objetivos;
    private String equipamientoNecesario;
    private Integer duracionMinutos;
    private List<Image> images;
}
