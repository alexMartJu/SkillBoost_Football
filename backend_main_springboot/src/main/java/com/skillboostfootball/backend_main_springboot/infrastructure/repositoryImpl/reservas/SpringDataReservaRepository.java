package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.reservas;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.reservas.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringDataReservaRepository extends JpaRepository<ReservaEntity, Long> {
    Optional<ReservaEntity> findByProfileIdAndEntrenamientoIdAndDeletedAtIsNull(Long profileId, Long entrenamientoId);
    
    List<ReservaEntity> findByProfileIdAndDeletedAtIsNull(Long profileId);
    
    @Query("SELECT r FROM ReservaEntity r WHERE r.profile.id = ?1 AND r.entrenamiento.status = ?2 AND r.deletedAt IS NULL")
    List<ReservaEntity> findByProfileIdAndEntrenamientoStatus(Long profileId, String status);
    
    @Query("SELECT COUNT(r) FROM ReservaEntity r WHERE r.entrenamiento.id = ?1 AND r.deletedAt IS NULL")
    int countByEntrenamientoId(Long entrenamientoId);

    @Modifying
    @Query("DELETE FROM ReservaEntity r WHERE r.profile.id = ?1 AND r.entrenamiento.id = ?2")
    void deleteByProfileIdAndEntrenamientoId(Long profileId, Long entrenamientoId);
    
    @Modifying
    @Query("DELETE FROM ReservaEntity r WHERE r.entrenamiento.id = ?1")
    void deleteByEntrenamientoId(Long entrenamientoId);

    int countByProfileIdAndDeletedAtIsNull(Long profileId);
}
