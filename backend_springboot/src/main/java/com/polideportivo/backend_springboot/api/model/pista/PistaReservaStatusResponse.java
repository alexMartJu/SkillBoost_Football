package com.polideportivo.backend_springboot.api.model.pista;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PistaReservaStatusResponse {
    
    private String slug;

    @JsonProperty("isReservedPista")
    private boolean isReservedPista;

    private String fecha;
    private String hora;
}
