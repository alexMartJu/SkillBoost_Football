package com.skillboostfootball.backend_main_springboot.domain.entities.usuarios;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.entities.roles.Role;

@Getter
@Builder
public class Usuario {
    private Long id;
    private String email;
    private String password;
    private String tipoUsuario;
    private String status;
    private List<Role> roles;
    private Profile profile;
    private String refreshToken;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    
    //Metodos de dominio
    public void update(String email, String password) {
        this.email = email;
        if (password != null && !password.isEmpty()) {
            this.password = password;
        }
        this.updatedAt = LocalDateTime.now();
    }
    
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    
    public void addRole(Role role) {
        this.roles.add(role);
    }
    
    public void removeRole(Role role) {
        this.roles.removeIf(r -> r.getId().equals(role.getId()));
    }
    
    public boolean hasRole(String roleName) {
        return roles.stream().anyMatch(r -> r.getName().equals(roleName));
    }
    
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    
    public boolean isActive() {
        return "active".equals(this.status);
    }
    
    public void activate() {
        this.status = "active";
    }
    
    public void deactivate() {
        this.status = "inactive";
        this.deletedAt = LocalDateTime.now();
    }
}
