package com.skillboostfootball.backend_main_springboot.domain.entities.subtiposTecnificacion;

import com.skillboostfootball.backend_main_springboot.domain.entities.tecnificaciones.Tecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.entities.images.Image;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubtipoTecnificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String nombre;
    private String slug;
    private String descripcion;
    private Tecnificacion tecnificacion;
    private String tipoEntrenamiento;
    private String objetivos;
    private String beneficios;
    private String frecuenciaSugerida;
    private String metodoEvaluacion;
    private String tecnologiaUtilizada;
    private List<Image> images;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    
    // Métodos de domain
    public void update(String nombre, String descripcion, String slug, String tipoEntrenamiento, String objetivos, 
        String beneficios, String frecuenciaSugerida, String metodoEvaluacion, String tecnologiaUtilizada) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.slug = slug;
        this.tipoEntrenamiento = tipoEntrenamiento;
        this.objetivos = objetivos;
        this.beneficios = beneficios;
        this.frecuenciaSugerida = frecuenciaSugerida;
        this.metodoEvaluacion = metodoEvaluacion;
        this.tecnologiaUtilizada = tecnologiaUtilizada;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
    
    public boolean isDeleted() {
        return this.deletedAt != null;
    }
}
