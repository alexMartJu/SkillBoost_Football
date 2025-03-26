package com.skillboostfootball.backend_main_springboot.presentation.dtos.organizaciones.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class OrganizacionResponse {

    private Long id;
    private String nombre;
    private String slug;
    private String tipo;
    private String vision;
    private String logo;
    private String descripcion;
    private String mision;
    private String impactoSocial;
    private String sitioWeb;
    private String contactoEmail;
    private String contactoTlf;
    private String direccion;
    private String ciudad;
    private String pais;
    private String colaboracionDescripcion;
    private LocalDate fechaInicioColaboracion;
    
}
