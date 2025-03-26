package com.skillboostfootball.backend_main_springboot.domain.entities.tecnificaciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.subtiposTecnificacion.SubtipoTecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.entities.images.Image;
import java.time.LocalDateTime;
import java.util.List;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Tecnificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String nombre;
    private String slug;
    private String descripcion;
    private List<SubtipoTecnificacion> subtipos;
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
