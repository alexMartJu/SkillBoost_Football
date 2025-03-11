package com.skillboostfootball.backend_main_springboot.domain.repositories.pagos;

import com.skillboostfootball.backend_main_springboot.domain.entities.pagos.Pago;
import java.util.List;
import java.util.Optional;

public interface PagoRepository {
    Pago save(Pago pago);
    Optional<Pago> findById(Long id);
    List<Pago> findByProfileSuscripcionId(Long profileSuscripcionId);
}
