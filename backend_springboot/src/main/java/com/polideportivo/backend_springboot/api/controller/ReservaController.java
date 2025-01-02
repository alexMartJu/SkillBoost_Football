package com.polideportivo.backend_springboot.api.controller;

import com.polideportivo.backend_springboot.api.assembler.ReservaAssembler;
import com.polideportivo.backend_springboot.api.model.reserva.ReservaRequest;
import com.polideportivo.backend_springboot.api.model.reserva.ReservaResponse;
import com.polideportivo.backend_springboot.api.security.authorization.CheckSecurity;
import com.polideportivo.backend_springboot.domain.service.ReservaService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReservaController {
    
    private final ReservaService reservaService;
    private final ReservaAssembler reservaAssembler;

    @PostMapping("/reservaPista")
    @CheckSecurity.Protected.canManage
    public ReservaResponse createReserva(@RequestBody ReservaRequest request) {
        var reserva = reservaService.createReserva(request);
        return reservaAssembler.toResponse(reserva);
    }
}
