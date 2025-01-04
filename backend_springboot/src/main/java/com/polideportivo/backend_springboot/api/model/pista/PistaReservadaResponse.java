package com.polideportivo.backend_springboot.api.model.pista;

import com.polideportivo.backend_springboot.api.model.reserva.ReservaResponse;
import com.polideportivo.backend_springboot.domain.model.Image;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PistaReservadaResponse {
    
    private Long id;
    private String nombre;
    private String slug;
    private List<Image> images;
    private List<ReservaResponse> reservas;
}
