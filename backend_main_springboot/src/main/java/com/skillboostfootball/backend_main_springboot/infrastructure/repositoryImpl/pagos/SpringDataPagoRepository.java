package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.pagos;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.pagos.PagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpringDataPagoRepository extends JpaRepository<PagoEntity, Long> {
    
    List<PagoEntity> findByProfileSuscripcionId(Long profileSuscripcionId);

}
