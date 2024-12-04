package com.polideportivo.backend_springboot.api.model.deporte;

import com.polideportivo.backend_springboot.api.model.pista.PistaResponse;
import com.polideportivo.backend_springboot.domain.model.Image;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeporteResponse {
    
    private Long id;
    private String nombre;
    private String slug;
    private List<PistaResponse> pistas;  // Lista de objetos PistaResponse
    private List<Image> images;
}
