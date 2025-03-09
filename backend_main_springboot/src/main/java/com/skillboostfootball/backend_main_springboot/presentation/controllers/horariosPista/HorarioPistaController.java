package com.skillboostfootball.backend_main_springboot.presentation.controllers.horariosPista;

import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.application.useCases.horariosPista.GetHorariosOcupadosByPistaAndFechaUseCase;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.horariosPista.response.HorariosOcupadosWrapper;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.horariosPista.HorarioOcupadoAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HorarioPistaController {
    
    private final GetHorariosOcupadosByPistaAndFechaUseCase getHorariosOcupadosUseCase;
    private final HorarioOcupadoAssembler horarioAssembler;
    
    //Listar horarios ocupados de una pista en una fecha
    @GetMapping("/pistas/{pistaSlug}/horarios-ocupados")
    @CheckSecurity.Public.canRead
    public HorariosOcupadosWrapper getHorariosOcupados(@PathVariable String pistaSlug,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        
        var horarios = getHorariosOcupadosUseCase.execute(pistaSlug, fecha);
        return horarioAssembler.toWrapper(horarios);

    }

}
