package com.skillboostfootball.backend_main_springboot.domain.entities.organizaciones;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class Organizacion {

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    
    //Métodos de dominio
    public void update(String nombre, String tipo, String vision, String logo, String descripcion, String mision, String impactoSocial, 
        String sitioWeb, String contactoEmail, String contactoTlf, String direccion, String ciudad, String pais, String colaboracionDescripcion, 
        LocalDate fechaInicioColaboracion, String slug) {

        this.nombre = nombre;
        this.tipo = tipo;
        this.vision = vision;
        this.logo = logo;
        this.descripcion = descripcion;
        this.mision = mision;
        this.impactoSocial = impactoSocial;
        this.sitioWeb = sitioWeb;
        this.contactoEmail = contactoEmail;
        this.contactoTlf = contactoTlf;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.pais = pais;
        this.colaboracionDescripcion = colaboracionDescripcion;
        this.fechaInicioColaboracion = fechaInicioColaboracion;
        this.slug = slug;
        this.updatedAt = LocalDateTime.now();
    }
    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
    public boolean isDeleted() {
        return this.deletedAt != null;
    }
}
