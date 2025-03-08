package com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.profiles.ProfileEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.roles.RoleEntity;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "tipo_usuario")
    private String tipoUsuario;
    
    @Column(name = "status", columnDefinition = "varchar(50) default 'active'")
    private String status;
    
    @Column(name = "refresh_token", columnDefinition = "TEXT")
    private String refreshToken;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private ProfileEntity profile;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    //Método helper para mantener la relación bidireccional
    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
        if (profile != null && (profile.getUsuario() == null || !profile.getUsuario().equals(this))) {
            profile.setUsuario(this);
        }
    }
}
