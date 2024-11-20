package com.polideportivo.backend_springboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pistas")
public class Pista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 191, nullable = false)
    private String nombre;

    @Column(name = "slug", length = 191, unique = true, nullable = false)
    private String slug;

    // Relación ManyToMany con configuraciones específicas
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
        name = "deporte_pista",  // Tabla intermedia
        joinColumns = @JoinColumn(name = "pista_id"),  // Columna en la tabla intermedia que referencia a Pista
        inverseJoinColumns = @JoinColumn(name = "deporte_id")  // Columna en la tabla intermedia que referencia a Deporte
    )
    @JsonBackReference  // Evita la serialización recursiva
    private Set<Deporte> deportes = new HashSet<>();

    // Constructor vacío
    public Pista() {}

    // Constructor con parámetros
    public Pista(String nombre, String slug) {
        this.nombre = nombre;
        this.slug = slug;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Set<Deporte> getDeportes() {
        return deportes;
    }

    public void setDeportes(Set<Deporte> deportes) {
        this.deportes = deportes;
    }

    // Método toString para depuración
    @Override
    public String toString() {
        return "Pista{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }
}
