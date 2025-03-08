package com.skillboostfootball.backend_main_springboot.domain.entities.roles;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import com.skillboostfootball.backend_main_springboot.domain.entities.permissions.Permission;

@Getter
@Builder
public class Role {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private List<Permission> permissions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    //Metodos de dominio
    public void update(String name, String description) {
        this.name = name;
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void addPermission(Permission permission) {
        this.permissions.add(permission);
    }
    
    public void removePermission(Permission permission) {
        this.permissions.removeIf(p -> p.getId().equals(permission.getId()));
    }
    
    public boolean hasPermission(String permissionName) {
        return permissions.stream().anyMatch(p -> p.getName().equals(permissionName));
    }
}
