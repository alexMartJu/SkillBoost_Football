package com.polideportivo.backend_springboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "entrenamientos")
public class Entrenamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "slug", unique = true, nullable = false)
    private String slug;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    @Column(name = "max_plazas", nullable = false)
    private Integer maxPlazas;

    @Column(name = "precio", nullable = false)
    private Integer precio;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deporte_id", nullable = false)
    private Deporte deporte;

    // Constructor vacío
    public Entrenamiento() {}

    // Constructor con parámetros
    public Entrenamiento(String nombre, String slug, String descripcion, Integer duracion, Integer maxPlazas, Integer precio, Deporte deporte) {
        this.nombre = nombre;
        this.slug = slug;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.maxPlazas = maxPlazas;
        this.precio = precio;
        this.deporte = deporte;
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
        return "Entrenamiento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", slug='" + slug + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", duracion=" + duracion +
                ", maxPlazas=" + maxPlazas +
                ", precio=" + precio +
                ", deporte=" + deporte +  // Asumiendo que quieres mostrar el nombre del deporte
                '}';
    }
}
