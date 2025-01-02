package com.polideportivo.backend_springboot.api.controller;

import com.polideportivo.backend_springboot.api.assembler.ReservaAssembler;
import com.polideportivo.backend_springboot.api.model.reserva.ReservaRequest;
import com.polideportivo.backend_springboot.api.model.reserva.ReservaResponse;
import com.polideportivo.backend_springboot.api.security.authorization.CheckSecurity;
import com.polideportivo.backend_springboot.domain.service.ReservaService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReservaController {
    
    private final ReservaService reservaService;
    private final ReservaAssembler reservaAssembler;

    //Endpoint para crear reservas
    @PostMapping("/reservaPista")
    @CheckSecurity.Protected.canManage
    public ReservaResponse createReserva(@RequestBody ReservaRequest request) {
        var reserva = reservaService.createReserva(request);
        return reservaAssembler.toResponse(reserva);
    }

    ////Endpoint para cancelar reservas
    @DeleteMapping("/reservaPista")
    @CheckSecurity.Reservas.canDelete
    public ResponseEntity<String> cancelReserva(
            @RequestParam String slugPista, 
            @RequestParam String hora, 
            @RequestParam String fecha) {
        reservaService.cancelReserva(slugPista, hora, fecha);
        return ResponseEntity.ok("La reserva ha sido cancelada exitosamente.");
    }
}
