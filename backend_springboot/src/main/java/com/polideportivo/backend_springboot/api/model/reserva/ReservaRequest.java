package com.polideportivo.backend_springboot.api.model.reserva;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaRequest {
    
    private String slugPista; 
    private String hora;
    private String fecha;

}
