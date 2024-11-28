package com.polideportivo.backend_springboot.infra.spec;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;
import com.polideportivo.backend_springboot.domain.model.Entrenamiento;

public class EntrenamientoSpecification implements Specification<Entrenamiento> {

    private final String nombre;
    private final String dia;
    private final Integer duracionMin;
    private final Integer duracionMax;
    private final Integer maxPlazasMin;
    private final Integer maxPlazasMax;
    private final Integer precioMin;
    private final Integer precioMax;
    private final Long deporteId;

    public EntrenamientoSpecification(String nombre, String dia, Integer duracionMin, Integer duracionMax,
                                      Integer maxPlazasMin, Integer maxPlazasMax, Integer precioMin,
                                      Integer precioMax, Long deporteId) {
        this.nombre = nombre;
        this.dia = dia;
        this.duracionMin = duracionMin;
        this.duracionMax = duracionMax;
        this.maxPlazasMin = maxPlazasMin;
        this.maxPlazasMax = maxPlazasMax;
        this.precioMin = precioMin;
        this.precioMax = precioMax;
        this.deporteId = deporteId;
    }

    @Override
    public Predicate toPredicate(Root<Entrenamiento> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction(); // Inicia con una conjunción (AND)

        // Filtro por nombre con LIKE para búsqueda parcial, sin sensibilidad a mayúsculas/minúsculas
        if (nombre != null) {
            // Convertimos tanto el campo 'nombre' como el valor 'nombre' a minúsculas y los comparamos
            predicate = criteriaBuilder.and(predicate, 
                criteriaBuilder.like(criteriaBuilder.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%"));
        }
        if (dia != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("dia"), dia));
        }
        if (duracionMin != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("duracion"), duracionMin));
        }
        if (duracionMax != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("duracion"), duracionMax));
        }
        if (maxPlazasMin != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("maxPlazas"), maxPlazasMin));
        }
        if (maxPlazasMax != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("maxPlazas"), maxPlazasMax));
        }
        if (precioMin != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("precio"), precioMin));
        }
        if (precioMax != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("precio"), precioMax));
        }
        if (deporteId != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("deporte").get("id"), deporteId));
        }

        // Siempre filtrar donde 'deleted_at' sea NULL
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.isNull(root.get("deletedAt")));


        return predicate;
    }
}
