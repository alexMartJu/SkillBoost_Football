package com.polideportivo.backend_springboot.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deportes")
public class Deporte {
       
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 191, nullable = false)
    private String nombre;

    @Column(name = "slug", length = 191, unique = true, nullable = false)
    private String slug;

    @Column(name = "image", nullable = true)
    private String image;

    @ManyToMany
    @JoinTable(
        name = "deporte_pista",
        joinColumns = @JoinColumn(name = "deporte_id"),
        inverseJoinColumns = @JoinColumn(name = "pista_id")
    )
    @ToString.Exclude
    private List<Pista> pistas;
}
