package com.polideportivo.backend_springboot.api.model.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioAuthenticate {
    
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
