package com.skillboostfootball.backend_main_springboot.domain.entities.pistas;

import com.skillboostfootball.backend_main_springboot.domain.entities.images.Image;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Pista {
    
    private Long id;
    private String nombre;
    private String slug;
    private String descripcion;
    private String tipo;
    private String dimensiones;
    private Integer capacidad;
    private List<Image> images;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    
    // Métodos de domain
    public void update(String nombre, String descripcion, String slug, String tipo, String dimensiones, Integer capacidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.slug = slug;
        this.tipo = tipo;
        this.dimensiones = dimensiones;
        this.capacidad = capacidad;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
    
    public boolean isDeleted() {
        return this.deletedAt != null;
    }
}
