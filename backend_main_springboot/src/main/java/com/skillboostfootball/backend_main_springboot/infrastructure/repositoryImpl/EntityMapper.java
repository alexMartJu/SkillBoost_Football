package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl;

import com.skillboostfootball.backend_main_springboot.domain.entities.entrenamientos.Entrenamiento;
import com.skillboostfootball.backend_main_springboot.domain.entities.horarios.Horario;
import com.skillboostfootball.backend_main_springboot.domain.entities.images.Image;
import com.skillboostfootball.backend_main_springboot.domain.entities.pistas.Pista;
import com.skillboostfootball.backend_main_springboot.domain.entities.subtiposTecnificacion.SubtipoTecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.entities.tecnificaciones.Tecnificacion;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.entrenamientos.EntrenamientoEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.horarios.HorarioEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.images.ImageEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.pistas.PistaEntity;
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

    public Pista toPista(PistaEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Pista.builder()
            .id(entity.getId())
            .nombre(entity.getNombre())
            .slug(entity.getSlug())
            .descripcion(entity.getDescripcion())
            .tipo(entity.getTipo())
            .dimensiones(entity.getDimensiones())
            .capacidad(entity.getCapacidad())
            .images(new ArrayList<>())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .deletedAt(entity.getDeletedAt())
            .build();
    }

    public PistaEntity toPistaEntity(Pista domain) {
        if (domain == null) {
            return null;
        }
        
        return PistaEntity.builder()
            .id(domain.getId())
            .nombre(domain.getNombre())
            .slug(domain.getSlug())
            .descripcion(domain.getDescripcion())
            .tipo(domain.getTipo())
            .dimensiones(domain.getDimensiones())
            .capacidad(domain.getCapacidad())
            .createdAt(domain.getCreatedAt())
            .updatedAt(domain.getUpdatedAt())
            .deletedAt(domain.getDeletedAt())
            .build();
    }

    public Entrenamiento toEntrenamiento(EntrenamientoEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Entrenamiento.builder()
            .id(entity.getId())
            .nombre(entity.getNombre())
            .slug(entity.getSlug())
            .descripcion(entity.getDescripcion())
            .nivel(entity.getNivel())
            .edadMinima(entity.getEdadMinima())
            .edadMaxima(entity.getEdadMaxima())
            .tecnificacion(entity.getTecnificacion() != null ? toTecnificacion(entity.getTecnificacion()) : null)
            .subtipoTecnificacion(entity.getSubtipoTecnificacion() != null ? toSubtipoTecnificacion(entity.getSubtipoTecnificacion()) : null)
            .entrenadorId(entity.getEntrenadorId())
            .pista(entity.getPista() != null ? toPista(entity.getPista()) : null)
            .horario(toHorario(entity.getHorario()))
            .maxPlazas(entity.getMaxPlazas())
            .objetivos(entity.getObjetivos())
            .equipamientoNecesario(entity.getEquipamientoNecesario())
            .duracionMinutos(entity.getDuracionMinutos())
            .status(entity.getStatus())
            .images(new ArrayList<>())    
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .deletedAt(entity.getDeletedAt())
            .build();
    }

    public EntrenamientoEntity toEntrenamientoEntity(Entrenamiento domain) {
        if (domain == null) {
            return null;
        }
        
        return EntrenamientoEntity.builder()
            .id(domain.getId())
            .nombre(domain.getNombre())
            .slug(domain.getSlug())
            .descripcion(domain.getDescripcion())
            .nivel(domain.getNivel())
            .edadMinima(domain.getEdadMinima())
            .edadMaxima(domain.getEdadMaxima())
            .tecnificacion(domain.getTecnificacion() != null ? toTecnificacionEntity(domain.getTecnificacion()) : null)
            .subtipoTecnificacion(domain.getSubtipoTecnificacion() != null ? toSubtipoTecnificacionEntity(domain.getSubtipoTecnificacion()) : null)
            .entrenadorId(domain.getEntrenadorId())
            .pista(domain.getPista() != null ? toPistaEntity(domain.getPista()) : null)
            .horario(toHorarioEntity(domain.getHorario()))
            .maxPlazas(domain.getMaxPlazas())
            .objetivos(domain.getObjetivos())
            .equipamientoNecesario(domain.getEquipamientoNecesario())
            .duracionMinutos(domain.getDuracionMinutos())
            .status(domain.getStatus())
            .createdAt(domain.getCreatedAt())
            .updatedAt(domain.getUpdatedAt())
            .deletedAt(domain.getDeletedAt())
            .build();
    }

    public Horario toHorario(HorarioEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Horario.builder()
            .id(entity.getId())
            .fechaInicio(entity.getFechaInicio())
            .fechaFin(entity.getFechaFin())
            .disponible(entity.getDisponible())
            .build();
    }

    public HorarioEntity toHorarioEntity(Horario domain) {
        if (domain == null) {
            return null;
        }
        
        return HorarioEntity.builder()
            .id(domain.getId())
            .fechaInicio(domain.getFechaInicio())
            .fechaFin(domain.getFechaFin())
            .disponible(domain.getDisponible())
            .build();
    }
}
