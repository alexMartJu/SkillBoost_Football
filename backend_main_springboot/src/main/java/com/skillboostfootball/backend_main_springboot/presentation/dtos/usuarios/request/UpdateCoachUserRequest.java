package com.skillboostfootball.backend_main_springboot.presentation.dtos.usuarios.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCoachUserRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;
    
    private String bio;
    
    private String image;
    
    @Email(message = "El formato de email es inválido")
    private String email;
    
    private String password;
    
    @Positive(message = "La edad debe ser un número positivo")
    private Integer edad;
    
    private String especialidad;
    
    private String certificaciones;
}
