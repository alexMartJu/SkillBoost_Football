package com.polideportivo.backend_springboot.api.model.pista;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PistaResponse {
    private Long id;
    private String nombre;
    private String slug;
}
