package com.skillboostfootball.backend_main_springboot.presentation.dtos.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterSocialHelpPlayerRequest {
    @NotBlank(message = "Email es obligatorio")
    @Email(message = "Formato de email inválido")
    private String email;
    
    @NotBlank(message = "Contraseña es obligatoria")
    private String password;
    
    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;
    
    @NotBlank(message = "Apellidos son obligatorios")
    private String apellidos;
    
    @NotNull(message = "Edad es obligatoria")
    @Min(value = 1, message = "Edad debe ser mayor a 0")
    private Integer edad;
    
    @NotBlank(message = "Organización de origen es obligatoria")
    private String organizacionOrigen;
}
