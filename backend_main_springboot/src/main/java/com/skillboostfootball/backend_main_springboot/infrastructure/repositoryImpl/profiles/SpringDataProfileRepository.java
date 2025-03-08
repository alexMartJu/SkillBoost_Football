package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.profiles.ProfileEntity;
import java.util.List;
import java.util.Optional;

public interface SpringDataProfileRepository extends JpaRepository<ProfileEntity, Long> {

    Optional<ProfileEntity> findByNumeroSocio(String numeroSocio);
    Optional<ProfileEntity> findByNumeroEntrenador(String numeroEntrenador);
    List<ProfileEntity> findByEsMenor(Boolean esMenor);
    List<ProfileEntity> findByClubOrigenIsNotNull();
    List<ProfileEntity> findByOrganizacionOrigenIsNotNull();
    
}
