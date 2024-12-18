package com.polideportivo.backend_springboot.api.model.usuario;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdate {
    
    @Email
    @Nullable
    private String email;

    @Nullable
    private String bio;

    @Nullable
    private Integer edad;

    @Nullable
    private String image;

    @Nullable
    private String password;
}
