package com.skillboostfootball.backend_main_springboot.presentation.controllers.pagos;

import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.application.useCases.suscripciones.CreateBuySuscripcionUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.suscripciones.GetSuscripcionActivaUseCase;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.pagos.PagoAssembler;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.suscripciones.SuscripcionAssembler;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.pagos.request.PagoRequest;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.pagos.response.PagoResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.suscripciones.response.SuscripcionResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PagoController {
    
    private final CreateBuySuscripcionUseCase crearComprarSuscripcionUseCase;
    private final GetSuscripcionActivaUseCase getSuscripcionActivaUseCase;
    private final PagoAssembler pagoAssembler;
    private final SuscripcionAssembler suscripcionAssembler;
    
    //Guardar pago y suscripción teniendo en cuenta la renovación
    @PostMapping("/pagos/suscripciones")
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Protected.canManage
    public PagoResponse procesarPagoSuscripcion(@Valid @RequestBody PagoRequest request) {
        var pago = crearComprarSuscripcionUseCase.execute(request.getSuscripcionSlug(), request.getMonto(),
            request.getMetodoPago(), request.getReferenciaExterna());
        return pagoAssembler.toResponse(pago);
    }
    
    //Obtener suscripción activa del usuario actual
    @GetMapping("/mi-suscripcion")
    @CheckSecurity.Protected.canManage
    public SuscripcionResponse getSuscripcionActiva() {
        var suscripcion = getSuscripcionActivaUseCase.execute();
        return suscripcionAssembler.toResponse(suscripcion);
    }

}
