package com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.organizaciones;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "organizaciones")
public class OrganizacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 191, nullable = false, unique = true)
    private String nombre;

    @Column(name = "slug", length = 191, unique = true, nullable = false)
    private String slug;
    
    @Column(name = "tipo", length = 100)
    private String tipo;
    
    @Column(name = "vision", length = 500)
    private String vision;
    
    @Column(name = "logo")
    private String logo;
    
    @Column(name = "descripcion", length = 1000)
    private String descripcion;
    
    @Column(name = "mision", length = 500)
    private String mision;
    
    @Column(name = "impacto_social", length = 500)
    private String impactoSocial;
    
    @Column(name = "sitio_web")
    private String sitioWeb;
    
    @Column(name = "contacto_email")
    private String contactoEmail;
    
    @Column(name = "contacto_tlf")
    private String contactoTlf;
    
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "ciudad")
    private String ciudad;
    
    @Column(name = "pais")
    private String pais;
    
    @Column(name = "colaboracion_descripcion", length = 1000)
    private String colaboracionDescripcion;
    
    @Column(name = "fecha_inicio_colaboracion")
    private LocalDate fechaInicioColaboracion;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
