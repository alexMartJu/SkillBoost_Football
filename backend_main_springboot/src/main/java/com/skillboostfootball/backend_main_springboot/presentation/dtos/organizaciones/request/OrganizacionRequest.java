package com.skillboostfootball.backend_main_springboot.presentation.dtos.organizaciones.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class OrganizacionRequest {
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;
    
    @NotBlank(message = "El tipo es obligatorio")
    private String tipo;
    
    private String vision;
    private String logo;
    
    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    private String descripcion;
    
    private String mision;
    private String impactoSocial;
    private String sitioWeb;
    
    @Email(message = "El formato de email es inválido")
    private String contactoEmail;
    
    private String contactoTlf;
    private String direccion;
    private String ciudad;
    private String pais;
    
    @Size(max = 1000, message = "La descripción de colaboración no puede exceder los 1000 caracteres")
    private String colaboracionDescripcion;
    
    private LocalDate fechaInicioColaboracion;
    
}
