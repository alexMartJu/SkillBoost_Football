package com.polideportivo.backend_springboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "clases")
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 191)
    private String nombre;

    @Column(name = "slug", length = 191, unique = true)
    private String slug;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "duracion")
    private Integer duracion;

    @Column(name = "max_plazas")
    private Integer maxPlazas;

    @Column(name = "precio")
    private Integer precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deportes_id", nullable = false)
    @JsonBackReference
    private Deporte deporte;

    // Constructor vacío
    public Clase() {}

    // Constructor con parámetros
    public Clase(String nombre, String slug, String descripcion, Integer duracion, Integer maxPlazas, Integer precio) {
        this.nombre = nombre;
        this.slug = slug;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.maxPlazas = maxPlazas;
        this.precio = precio;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Integer getMaxPlazas() {
        return maxPlazas;
    }

    public void setMaxPlazas(Integer maxPlazas) {
        this.maxPlazas = maxPlazas;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    // Método toString
    @Override
    public String toString() {
        return "Clase{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", slug='" + slug + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", duracion=" + duracion +
                ", maxPlazas=" + maxPlazas +
                ", precio=" + precio +
                ", deporte=" + deporte +
                '}';
    }
}
