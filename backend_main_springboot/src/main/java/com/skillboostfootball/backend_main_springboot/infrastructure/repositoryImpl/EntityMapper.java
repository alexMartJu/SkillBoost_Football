package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl;

import com.skillboostfootball.backend_main_springboot.domain.entities.blacklistToken.BlacklistToken;
import com.skillboostfootball.backend_main_springboot.domain.entities.entrenamientos.Entrenamiento;
import com.skillboostfootball.backend_main_springboot.domain.entities.horarios.Horario;
import com.skillboostfootball.backend_main_springboot.domain.entities.images.Image;
import com.skillboostfootball.backend_main_springboot.domain.entities.permissions.Permission;
import com.skillboostfootball.backend_main_springboot.domain.entities.pistas.Pista;
import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.entities.roles.Role;
import com.skillboostfootball.backend_main_springboot.domain.entities.subtiposTecnificacion.SubtipoTecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.entities.suscripciones.Suscripcion;
import com.skillboostfootball.backend_main_springboot.domain.entities.tecnificaciones.Tecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.blacklistToken.BlacklistTokenEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.entrenamientos.EntrenamientoEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.horarios.HorarioEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.images.ImageEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.permissions.PermissionEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.pistas.PistaEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.profiles.ProfileEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.roles.RoleEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.subtiposTecnificacion.SubtipoTecnificacionEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.suscripciones.SuscripcionEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.tecnificaciones.TecnificacionEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.usuarios.UsuarioEntity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

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

    public Suscripcion toSuscripcion(SuscripcionEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Suscripcion.builder()
            .id(entity.getId())
            .nombre(entity.getNombre())
            .slug(entity.getSlug())
            .precio(entity.getPrecio())
            .entrenamientosSemanales(entity.getEntrenamientosSemanales())
            .incluyeGraficas(entity.getIncluyeGraficas())
            .incluyeEvaluacion(entity.getIncluyeEvaluacion())
            .ventaja1(entity.getVentaja1())
            .ventaja2(entity.getVentaja2())
            .ventaja3(entity.getVentaja3())
            .ventaja4(entity.getVentaja4())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .build();
    }

    public SuscripcionEntity toSuscripcionEntity(Suscripcion domain) {
        if (domain == null) {
            return null;
        }
        
        return SuscripcionEntity.builder()
            .id(domain.getId())
            .nombre(domain.getNombre())
            .slug(domain.getSlug())
            .precio(domain.getPrecio())
            .entrenamientosSemanales(domain.getEntrenamientosSemanales())
            .incluyeGraficas(domain.getIncluyeGraficas())
            .incluyeEvaluacion(domain.getIncluyeEvaluacion())
            .ventaja1(domain.getVentaja1())
            .ventaja2(domain.getVentaja2())
            .ventaja3(domain.getVentaja3())
            .ventaja4(domain.getVentaja4())
            .createdAt(domain.getCreatedAt())
            .updatedAt(domain.getUpdatedAt())
            .build();
    }

    public Usuario toUsuario(UsuarioEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Usuario.builder()
            .id(entity.getId())
            .email(entity.getEmail())
            .password(entity.getPassword())
            .tipoUsuario(entity.getTipoUsuario())
            .status(entity.getStatus())
            .profile(toProfile(entity.getProfile()))
            .roles(entity.getRoles() != null ? 
                entity.getRoles().stream().map(this::toRole).collect(Collectors.toList()) : 
                new ArrayList<>())
            .refreshToken(entity.getRefreshToken())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .deletedAt(entity.getDeletedAt())
            .build();
    }

    public UsuarioEntity toUsuarioEntity(Usuario domain) {
        if (domain == null) {
            return null;
        }
        
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
            .id(domain.getId())
            .email(domain.getEmail())
            .password(domain.getPassword())
            .tipoUsuario(domain.getTipoUsuario())
            .status(domain.getStatus())
            .roles(domain.getRoles() != null ? 
                domain.getRoles().stream().map(this::toRoleEntity).collect(Collectors.toList()) : 
                new ArrayList<>())
            .refreshToken(domain.getRefreshToken())
            .createdAt(domain.getCreatedAt())
            .updatedAt(domain.getUpdatedAt())
            .deletedAt(domain.getDeletedAt())
            .build();

        // Configurar el profile de forma segura
        if (domain.getProfile() != null) {
            ProfileEntity profileEntity = toProfileEntity(domain.getProfile());
            profileEntity.setUsuario(usuarioEntity); // Establecer la referencia al usuario
            usuarioEntity.setProfile(profileEntity); // Establecer la referencia al perfil
        }
        
        return usuarioEntity;
    }
    
    public Profile toProfile(ProfileEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Profile.builder()
            .id(entity.getId())
            .numeroSocio(entity.getNumeroSocio())
            .nombre(entity.getNombre())
            .apellidos(entity.getApellidos())
            .bio(entity.getBio())
            .image(entity.getImage())
            .edad(entity.getEdad())
            .esMenor(entity.getEsMenor())
            .posicionPreferida(entity.getPosicionPreferida())
            .clubOrigen(entity.getClubOrigen())
            .numeroEntrenador(entity.getNumeroEntrenador())
            .especialidad(entity.getEspecialidad())
            .experienciaAnios(entity.getExperienciaAnios())
            .certificaciones(entity.getCertificaciones())
            .organizacionOrigen(entity.getOrganizacionOrigen())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .deletedAt(entity.getDeletedAt())
            .build();
    }
    
    public ProfileEntity toProfileEntity(Profile domain) {
        if (domain == null) {
            return null;
        }
        
        ProfileEntity entity = ProfileEntity.builder()
            .id(domain.getId())
            .numeroSocio(domain.getNumeroSocio())
            .nombre(domain.getNombre())
            .apellidos(domain.getApellidos())
            .bio(domain.getBio())
            .image(domain.getImage())
            .edad(domain.getEdad())
            .esMenor(domain.getEsMenor())
            .posicionPreferida(domain.getPosicionPreferida())
            .clubOrigen(domain.getClubOrigen())
            .numeroEntrenador(domain.getNumeroEntrenador())
            .especialidad(domain.getEspecialidad())
            .experienciaAnios(domain.getExperienciaAnios())
            .certificaciones(domain.getCertificaciones())
            .organizacionOrigen(domain.getOrganizacionOrigen())
            .createdAt(domain.getCreatedAt())
            .updatedAt(domain.getUpdatedAt())
            .deletedAt(domain.getDeletedAt())
            .build();

        return entity;
    }
    
    public Role toRole(RoleEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Role.builder()
            .id(entity.getId())
            .name(entity.getName())
            .slug(entity.getSlug())
            .description(entity.getDescription())
            .permissions(entity.getPermissions() != null ? 
                entity.getPermissions().stream().map(this::toPermission).collect(Collectors.toList()) : 
                new ArrayList<>())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .build();
    }
    
    public RoleEntity toRoleEntity(Role domain) {
        if (domain == null) {
            return null;
        }
        
        return RoleEntity.builder()
            .id(domain.getId())
            .name(domain.getName())
            .slug(domain.getSlug())
            .description(domain.getDescription())
            .permissions(domain.getPermissions() != null ? 
                domain.getPermissions().stream().map(this::toPermissionEntity).collect(Collectors.toList()) : 
                new ArrayList<>())
            .createdAt(domain.getCreatedAt())
            .updatedAt(domain.getUpdatedAt())
            .build();
    }
    
    public Permission toPermission(PermissionEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return Permission.builder()
            .id(entity.getId())
            .name(entity.getName())
            .slug(entity.getSlug())
            .description(entity.getDescription())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .build();
    }
    
    public PermissionEntity toPermissionEntity(Permission domain) {
        if (domain == null) {
            return null;
        }
        
        return PermissionEntity.builder()
            .id(domain.getId())
            .name(domain.getName())
            .slug(domain.getSlug())
            .description(domain.getDescription())
            .createdAt(domain.getCreatedAt())
            .updatedAt(domain.getUpdatedAt())
            .build();
    }
    
    // BlacklistToken mapping
    public BlacklistToken toBlacklistToken(BlacklistTokenEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return BlacklistToken.builder()
            .id(entity.getId())
            .usuarioId(entity.getUsuarioId())
            .refreshToken(entity.getRefreshToken())
            .revokeTime(entity.getRevokeTime())
            .build();
    }
    
    public BlacklistTokenEntity toBlacklistTokenEntity(BlacklistToken domain) {
        if (domain == null) {
            return null;
        }
        
        return BlacklistTokenEntity.builder()
            .id(domain.getId())
            .usuarioId(domain.getUsuarioId())
            .refreshToken(domain.getRefreshToken())
            .revokeTime(domain.getRevokeTime())
            .build();
    }
}
