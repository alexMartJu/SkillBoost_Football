package com.skillboostfootball.backend_main_springboot.infrastructure.spec;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.entrenamientos.EntrenamientoEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.horarios.HorarioEntity;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntrenamientoSpecification {
    
    public static Specification<EntrenamientoEntity> withFilters(
            String nombre,
            String nivel,
            Integer edadMinima,
            Integer edadMaxima,
            Integer maxPlazasMin,
            Integer maxPlazasMax,
            String tecnificacionNombre,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin) {
        
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            //Filtrar entrenamientos no eliminados y activos
            predicates.add(criteriaBuilder.isNull(root.get("deletedAt")));
            predicates.add(criteriaBuilder.equal(root.get("status"), "active"));
            
            //Aplicar filtros de nivel, duración, edad, máxima plazas y tecnificación
            if (nivel != null && !nivel.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("nivel"), nivel));
            }
            
            //Filtro por nombre con LIKE para búsqueda parcial, sin sensibilidad a mayúsculas/minúsculas
            if (nombre != null && !nombre.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("nombre")), 
                    "%" + nombre.toLowerCase() + "%"
                ));
            }
            
            if (edadMinima != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("edadMinima"), edadMinima));
            }
            
            if (edadMaxima != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("edadMaxima"), edadMaxima));
            }
            
            if (maxPlazasMin != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("maxPlazas"), maxPlazasMin));
            }
            
            if (maxPlazasMax != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("maxPlazas"), maxPlazasMax));
            }
            
            if (tecnificacionNombre != null && !tecnificacionNombre.isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("tecnificacion").get("nombre")), 
                    "%" + tecnificacionNombre.toLowerCase() + "%"));
            }

            if (fechaInicio != null || fechaFin != null) {
                Join<EntrenamientoEntity, HorarioEntity> horarioJoin = 
                    root.join("horario", JoinType.LEFT);
                
                if (fechaInicio != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        horarioJoin.get("fechaInicio"), fechaInicio));
                }

                if (fechaFin != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(
                    horarioJoin.get("fechaFin"), fechaFin));
                }
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
