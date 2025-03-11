package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.profileSuscripciones;


import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.profileSuscripciones.ProfileSuscripcionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SpringDataProfileSuscripcionRepository extends JpaRepository<ProfileSuscripcionEntity, Long> {
    
    List<ProfileSuscripcionEntity> findByProfileId(Long profileId);
    
    @Query("SELECT ps FROM ProfileSuscripcionEntity ps WHERE ps.profileId = :profileId " +
           "AND ps.status = 'active' AND (ps.fechaFin >= :hoy OR ps.fechaFin IS NULL)")
    Optional<ProfileSuscripcionEntity> findActiveByProfileId(@Param("profileId") Long profileId, @Param("hoy") LocalDate hoy);

}
