package com.skillboostfootball.backend_main_springboot.presentation.controllers.logros;

import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.application.useCases.logros.GetLogrosUsuarioUseCase;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.logros.response.LogroWrapper;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.logros.LogroAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LogroController {
    private final GetLogrosUsuarioUseCase getLogrosUsuarioUseCase;
    private final LogroAssembler assembler;
    
    //Obtener logros del usuario
    @GetMapping("/logros")
    @CheckSecurity.Jugadores.canAccess
    public LogroWrapper getLogrosUsuario() {
        var logrosInfo = getLogrosUsuarioUseCase.execute();
        return assembler.toWrapper(logrosInfo);
    }
}
