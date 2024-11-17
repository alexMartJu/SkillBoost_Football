package com.polideportivo.backend_springboot.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "deportes")
public class Deporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deportes_id")
    private Long id;

    @Column(name = "nombre", length = 191)
    private String nombre;

    @Column(name = "slug", length = 191, unique = true)
    private String slug;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
        name = "deportes_pistas",
        joinColumns = @JoinColumn(name = "deportes_id"),
        inverseJoinColumns = @JoinColumn(name = "pistas_id")
    )
    @JsonManagedReference
    private Set<Pista> pistas = new HashSet<>();

    @OneToMany(mappedBy = "deporte", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Clase> clases = new HashSet<>();

    // Constructor vacío
    public Deporte() {}

    // Constructor con parámetros
    public Deporte(String nombre, String slug) {
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

    public Set<Pista> getPistas() {
        return pistas;
    }

    public void setPistas(Set<Pista> pistas) {
        this.pistas = pistas;
    }

    public Set<Clase> getClases() {
        return clases;
    }

    public void setClases(Set<Clase> clases) {
        this.clases = clases;
    }

    @Override
    public String toString() {
        return "Deporte{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }
}
