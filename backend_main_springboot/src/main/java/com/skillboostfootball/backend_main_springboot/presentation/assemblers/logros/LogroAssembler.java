package com.skillboostfootball.backend_main_springboot.presentation.assemblers.logros;

import com.skillboostfootball.backend_main_springboot.presentation.dtos.logros.response.LogroResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.logros.response.LogroWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LogroAssembler {
    
    public LogroResponse toResponse(Map<String, Object> logroInfo) {

        LogroResponse response = new LogroResponse();
        
        response.setId((Long) logroInfo.get("id"));
        response.setNombre((String) logroInfo.get("nombre"));
        response.setDescripcion((String) logroInfo.get("descripcion"));
        response.setRequisitoEntrenamientos((Integer) logroInfo.get("requisitoEntrenamientos"));
        response.setNivelDificultad((String) logroInfo.get("nivelDificultad"));
        response.setRecompensa((String) logroInfo.get("recompensa"));
        response.setProgreso((Integer) logroInfo.get("progreso"));
        response.setCompletado((Boolean) logroInfo.get("completado"));
        response.setFechaLogro((java.time.LocalDateTime) logroInfo.get("fechaLogro"));
        return response;

    }
    
    public LogroWrapper toWrapper(List<Map<String, Object>> logrosInfo) {
        List<LogroResponse> responseList = logrosInfo.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
                
        return LogroWrapper.builder()
                .logros(responseList)
                .build();
    }
}
