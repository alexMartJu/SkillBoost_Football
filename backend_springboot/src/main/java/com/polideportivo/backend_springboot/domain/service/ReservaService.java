package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.api.model.reserva.ReservaRequest;
import com.polideportivo.backend_springboot.domain.model.Reserva;

public interface ReservaService {
    
    Reserva createReserva(ReservaRequest request);

    Reserva findReserva(String slugPista, String hora, String fecha);
}
