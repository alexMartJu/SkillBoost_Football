package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.permissions;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.permissions.PermissionEntity;
import java.util.Optional;

public interface SpringDataPermissionRepository extends JpaRepository<PermissionEntity, Long> {
    Optional<PermissionEntity> findByName(String name);
}
