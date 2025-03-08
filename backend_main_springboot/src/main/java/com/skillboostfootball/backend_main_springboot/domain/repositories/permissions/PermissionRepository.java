package com.skillboostfootball.backend_main_springboot.domain.repositories.permissions;

import java.util.Optional;

import com.skillboostfootball.backend_main_springboot.domain.entities.permissions.Permission;

import java.util.List;

public interface PermissionRepository {

    Optional<Permission> findById(Long id);
    Optional<Permission> findByName(String name);
    List<Permission> findAll();
    Permission save(Permission permission);
    
}
