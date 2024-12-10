package com.polideportivo.backend_springboot.api.model.usuario;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.polideportivo.backend_springboot.api.model.BaseResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonTypeName("usuario")
public class UsuarioResponse extends BaseResponse {
    
    private String email;

    private String nombre;
    private String bio;
    private String numeroSocio;
    private String image;
    private String apellidos;
    private Integer edad;

    private String token;
}
