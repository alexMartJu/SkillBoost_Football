package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.organizaciones;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.organizaciones.OrganizacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SpringDataOrganizacionRepository extends JpaRepository<OrganizacionEntity, Long> {
    List<OrganizacionEntity> findByDeletedAtIsNull();
    Optional<OrganizacionEntity> findBySlugAndDeletedAtIsNull(String slug);
    boolean existsByNombreAndDeletedAtIsNull(String nombre);
}
