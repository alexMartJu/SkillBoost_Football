package com.skillboostfootball.backend_main_springboot.presentation.controllers.reservas;

import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.application.useCases.reservas.*;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.reservas.response.ReservaResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.reservas.response.ReservaWrapper;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.reservas.ReservaAssembler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class ReservaController {
    private final InscribirseEntrenamientoUseCase inscribirseEntrenamientoUseCase;
    private final DesinscribirseEntrenamientoUseCase desinscribirseEntrenamientoUseCase;
    private final GetReservasUsuarioUseCase getReservasUsuarioUseCase;
    private final GetReservasUsuarioActiveUseCase getReservasUsuarioActivasUseCase;
    private final GetReservasUsuarioExpiredUseCase getReservasUsuarioExpidasUseCase;
    private final ReservaAssembler assembler;
    
    //Inscripción a un entrenamiento
    @PostMapping("/entrenamientos/{slug}/inscribirse")
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Protected.canManage
    public ReservaResponse inscribirseEntrenamiento(@PathVariable String slug) {
        var reserva = inscribirseEntrenamientoUseCase.execute(slug);
        return assembler.toResponse(reserva);
    }
    
    //Desinscripción de un entrenamiento
    @DeleteMapping("/entrenamientos/{slug}/desinscribirse")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Protected.canManage
    public ResponseEntity<Void> desinscribirseEntrenamiento(@PathVariable String slug) {
        desinscribirseEntrenamientoUseCase.execute(slug);
        return ResponseEntity.noContent().build();
    }
    
    //Obtener todas las reservas del usuario
    @GetMapping("/reservas")
    @CheckSecurity.Protected.canManage
    public ReservaWrapper getReservasUsuario() {
        var reservas = getReservasUsuarioUseCase.execute();
        return assembler.toWrapper(reservas);
    }
    
    //Obtener todas las reservas activas del usuario
    @GetMapping("/reservas/activas")
    @CheckSecurity.Protected.canManage
    public ReservaWrapper getReservasUsuarioActivas() {
        var reservas = getReservasUsuarioActivasUseCase.execute();
        return assembler.toWrapper(reservas);
    }
    
    //Obtener todas las reservas expiradas del usuario
    @GetMapping("/reservas/expiradas")
    @CheckSecurity.Protected.canManage
    public ReservaWrapper getReservasUsuarioExpiradas() {
        var reservas = getReservasUsuarioExpidasUseCase.execute();
        return assembler.toWrapper(reservas);
    }
}
