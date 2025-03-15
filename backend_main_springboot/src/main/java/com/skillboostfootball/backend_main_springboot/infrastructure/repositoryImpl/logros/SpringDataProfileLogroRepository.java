package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.logros;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.logros.ProfileLogroEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.logros.ProfileLogroId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataProfileLogroRepository extends JpaRepository<ProfileLogroEntity, ProfileLogroId> {
    List<ProfileLogroEntity> findById_ProfileId(Long profileId);
    List<ProfileLogroEntity> findById_ProfileIdAndNotificadoFalse(Long profileId);
}
