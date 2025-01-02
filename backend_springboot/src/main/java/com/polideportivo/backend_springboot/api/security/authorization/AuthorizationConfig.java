package com.polideportivo.backend_springboot.api.security.authorization;

import com.polideportivo.backend_springboot.api.security.AuthUtils;
import com.polideportivo.backend_springboot.domain.model.Reserva;
import com.polideportivo.backend_springboot.domain.service.ReservaService;
import com.polideportivo.backend_springboot.domain.service.UsuarioService;
import com.polideportivo.backend_springboot.domain.model.Profile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationConfig {
    
    private final AuthUtils authUtils;
    private final UsuarioService usuarioService;
    private final ReservaService reservaService;
    
    public boolean isAuthenticated() {
        return authUtils.isAuthenticated();
    }

    public boolean isReservaOwner(String slugPista, String hora, String fecha) {
        if (!isAuthenticated()) {
            return false;
        }

        // Buscar la reserva en el servicio
        Reserva reserva = reservaService.findReserva(slugPista, hora, fecha);

        // Verificar si la reserva pertenece al usuario autenticado
        return authenticatedUserEquals(reserva.getProfile());
    }

    private boolean authenticatedUserEquals(Profile user) {
        // Comparación directa del usuario autenticado con el usuario de la reserva
        return usuarioService.getCurrentUser().getProfile().equals(user);
    }
}
