package com.skillboostfootball.backend_main_springboot.presentation.dtos.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCoachRequest {
    @NotBlank(message = "Email es obligatorio")
    @Email(message = "Formato de email inválido")
    private String email;
    
    @NotBlank(message = "Contraseña es obligatoria")
    private String password;
    
    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;
    
    @NotBlank(message = "Apellidos son obligatorios")
    private String apellidos;
    
    @NotBlank(message = "Especialidad es obligatoria")
    private String especialidad;
    
    @NotNull(message = "Experiencia en años es obligatoria")
    @Min(value = 0, message = "Experiencia debe ser un número positivo")
    private Integer experienciaAnios;
    
    private String certificaciones;
}
