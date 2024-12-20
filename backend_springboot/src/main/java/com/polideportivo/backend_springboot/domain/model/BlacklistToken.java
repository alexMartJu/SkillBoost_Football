package com.polideportivo.backend_springboot.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blacklist_token")
public class BlacklistToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @ToString.Exclude
    private Usuario user;  // Relación con Usuario

    @Column(nullable = false)
    private String refreshToken;  // El refresh token que se ha revocado

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime revokeTime;  // Fecha en la que se revocó el token
}
