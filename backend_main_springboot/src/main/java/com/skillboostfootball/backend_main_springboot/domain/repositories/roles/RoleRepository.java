package com.skillboostfootball.backend_main_springboot.domain.repositories.roles;

import java.util.Optional;

import com.skillboostfootball.backend_main_springboot.domain.entities.roles.Role;

import java.util.List;

public interface RoleRepository {
    Optional<Role> findById(Long id);
    Optional<Role> findByName(String name);
    List<Role> findAll();
    Role save(Role role);
}
