package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.roles.RoleEntity;
import java.util.Optional;

public interface SpringDataRoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);
}
