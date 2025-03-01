package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl;

import com.skillboostfootball.backend_main_springboot.domain.entities.images.Image;
import com.skillboostfootball.backend_main_springboot.domain.entities.subtiposTecnificacion.SubtipoTecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.entities.tecnificaciones.Tecnificacion;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.images.ImageEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.subtiposTecnificacion.SubtipoTecnificacionEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.tecnificaciones.TecnificacionEntity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EntityMapper {
    public SubtipoTecnificacion toSubtipoTecnificacion(SubtipoTecnificacionEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return SubtipoTecnificacion.builder()
            .id(entity.getId())
            .nombre(entity.getNombre())
            .slug(entity.getSlug())
            .descripcion(entity.getDescripcion())
            .tecnificacion(toTecnificacion(entity.getTecnificacion()))
            .images(new ArrayList<>())    // Se cargarán si es necesario por un caso de uso
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .deletedAt(entity.getDeletedAt())
            .build();
    }

    public SubtipoTecnificacionEntity toSubtipoTecnificacionEntity(SubtipoTecnificacion domain) {
        if (domain == null) {
            return null;
        }
        
        return SubtipoTecnificacionEntity.builder()
            .id(domain.getId())
            .nombre(domain.getNombre())
            .slug(domain.getSlug())
            .descripcion(domain.getDescripcion())
            .tecnificacion(domain.getTecnificacion() != null ? 
                toTecnificacionEntity(domain.getTecnificacion()) : null)
            .createdAt(domain.getCreatedAt())
            .updatedAt(domain.getUpdatedAt())
            .deletedAt(domain.getDeletedAt())
            .build();
    }

    public Tecnificacion toTecnificacion(TecnificacionEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Tecnificacion.builder()
            .id(entity.getId())
            .nombre(entity.getNombre())
            .slug(entity.getSlug())
            .descripcion(entity.getDescripcion())
            .subtipos(new ArrayList<>())  // Se cargarán si es necesario por un caso de uso
            .images(new ArrayList<>())    // Se cargarán si es necesario por un caso de uso
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .deletedAt(entity.getDeletedAt())
            .build();
    }
    
    public TecnificacionEntity toTecnificacionEntity(Tecnificacion domain) {
        if (domain == null) {
            return null;
        }
        
        return TecnificacionEntity.builder()
            .id(domain.getId())
            .nombre(domain.getNombre())
            .slug(domain.getSlug())
            .descripcion(domain.getDescripcion())
            .createdAt(domain.getCreatedAt())
            .updatedAt(domain.getUpdatedAt())
            .deletedAt(domain.getDeletedAt())
            .build();
    }

    public Image toImage(ImageEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Image.builder()
                .id(entity.getId())
                .imageUrl(entity.getImageUrl())
                .imageableId(entity.getImageableId())
                .imageableType(entity.getImageableType())
                .build();
    }

    public ImageEntity toImageEntity(Image domain) {
        if (domain == null) {
            return null;
        }
        
        return ImageEntity.builder()
                .id(domain.getId())
                .imageUrl(domain.getImageUrl())
                .imageableId(domain.getImageableId())
                .imageableType(domain.getImageableType())
                .build();
    }
}
