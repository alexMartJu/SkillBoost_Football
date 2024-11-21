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
    private Long id;

    @Column(name = "nombre", length = 191, nullable = false)
    private String nombre;

    @Column(name = "slug", length = 191, unique = true, nullable = false)
    private String slug;

    @Column(name = "image", nullable = false)
    private String image;

    @JsonManagedReference
    @OneToMany(mappedBy = "deporte", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Entrenamiento> entrenamientos = new HashSet<>();

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
        name = "deporte_pista",
        joinColumns = @JoinColumn(name = "deporte_id"),
        inverseJoinColumns = @JoinColumn(name = "pista_id")
    )
    private Set<Pista> pistas = new HashSet<>();

    // Getters, setters y constructor vacío
    public Deporte() {}

    // Constructor con parámetros
    public Deporte(String nombre, String slug, String image) {
        this.nombre = nombre;
        this.slug = slug;
        this.image = image;
    }

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

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }

    public Set<Entrenamiento> getEntrenamientos() {
        return entrenamientos;
    }

    public void setEntrenamientos(Set<Entrenamiento> entrenamientos) {
        this.entrenamientos = entrenamientos;
    }

    public Set<Pista> getPistas() {
        return pistas;
    }

    public void setPistas(Set<Pista> pistas) {
        this.pistas = pistas;
    }

    // Método toString para depuración
    @Override
    public String toString() {
        return "Deporte{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", slug='" + slug + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
