package com.skillboostfootball.backend_main_springboot.domain.entities.subtiposTecnificacion;

import com.skillboostfootball.backend_main_springboot.domain.entities.tecnificaciones.Tecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.entities.images.Image;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubtipoTecnificacion {
    private Long id;
    private String nombre;
    private String slug;
    private String descripcion;
    private Tecnificacion tecnificacion;
    private List<Image> images;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    
    // Métodos de domain
    public void update(String nombre, String descripcion, String slug) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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
