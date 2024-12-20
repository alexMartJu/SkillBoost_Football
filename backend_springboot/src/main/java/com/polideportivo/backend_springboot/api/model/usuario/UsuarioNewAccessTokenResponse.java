package com.polideportivo.backend_springboot.api.model.usuario;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioNewAccessTokenResponse {
    private String token;
}
