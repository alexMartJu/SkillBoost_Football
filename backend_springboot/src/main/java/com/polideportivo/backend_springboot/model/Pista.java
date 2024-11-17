package com.polideportivo.backend_springboot.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "pistas")
public class Pista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pistas_id")
    private Long id;

    @Column(name = "nombre", length = 191)
    private String nombre;

    @Column(name = "slug", length = 191, unique = true)
    private String slug;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
        name = "deportes_pistas",
        joinColumns = @JoinColumn(name = "pistas_id"),
        inverseJoinColumns = @JoinColumn(name = "deportes_id")
    )
    @JsonBackReference
    private Set<Deporte> deportes = new HashSet<>();

    // Constructor vacío
    public Pista() {}

    // Constructor con parámetros
    public Pista(String nombre, String slug) {
        this.nombre = nombre;
        this.slug = slug;
    }

    // Getters y Setters
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

    @Override
    public String toString() {
        return "Pista{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }
}
