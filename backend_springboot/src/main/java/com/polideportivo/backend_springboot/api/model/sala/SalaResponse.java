package com.polideportivo.backend_springboot.api.model.sala;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaResponse {
    
    private String nombre;
    private String tamaño;
    private String ubicacion;
}
