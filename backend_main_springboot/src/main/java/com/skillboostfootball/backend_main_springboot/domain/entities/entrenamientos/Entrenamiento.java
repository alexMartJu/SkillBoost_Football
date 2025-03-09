package com.skillboostfootball.backend_main_springboot.domain.entities.entrenamientos;

import com.skillboostfootball.backend_main_springboot.domain.entities.tecnificaciones.Tecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.entities.subtiposTecnificacion.SubtipoTecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.entities.pistas.Pista;
import com.skillboostfootball.backend_main_springboot.domain.entities.horariosPista.HorarioPista;
import com.skillboostfootball.backend_main_springboot.domain.entities.images.Image;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class Entrenamiento {
    
    private Long id;
    private String nombre;
    private String slug;
    private String descripcion;
    private String nivel;
    private Integer edadMinima;
    private Integer edadMaxima;
    private Tecnificacion tecnificacion;
    private SubtipoTecnificacion subtipoTecnificacion;
    private Long entrenadorId;
    private HorarioPista horarioPista;
    private Integer maxPlazas;
    private String objetivos;
    private String equipamientoNecesario;
    private Integer duracionMinutos;
    private String status;
    private List<Image> images;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
