package com.polideportivo.backend_springboot.api.model.entrenador;

import com.polideportivo.backend_springboot.api.model.deporte.DeporteResponse;
import com.polideportivo.backend_springboot.api.model.sala.SalaResponse;
import com.polideportivo.backend_springboot.domain.model.Image;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EntrenadorResponse {
    
    private String nombre;
    private String apellidos;
    private String email;
    private String numeroEntrenador;
    private DeporteResponse deporte;
    private List<Image> images;
    private SalaResponse sala;
}
