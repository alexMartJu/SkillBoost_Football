package com.polideportivo.backend_springboot.api.model.entrenamiento;

import com.polideportivo.backend_springboot.api.model.deporte.DeporteResponse;
import com.polideportivo.backend_springboot.api.model.entrenador.EntrenadorInfoResponse;
import com.polideportivo.backend_springboot.api.model.horario.HorarioResponse;
import com.polideportivo.backend_springboot.api.model.pistaPrivada.PistaPrivadaResponse;
import com.polideportivo.backend_springboot.domain.model.Image;

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
    private String dia;
    private Integer duracion;
    private Integer maxPlazas;
    private Integer precio;
    private String status;
    private PistaPrivadaResponse pistaPrivada;
    private EntrenadorInfoResponse entrenador;
    private HorarioResponse horario;

    private DeporteResponse deporte;
    private List<Image> images;
}
