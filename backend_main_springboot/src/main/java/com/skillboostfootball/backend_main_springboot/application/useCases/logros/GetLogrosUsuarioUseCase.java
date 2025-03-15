package com.skillboostfootball.backend_main_springboot.application.useCases.logros;

import com.skillboostfootball.backend_main_springboot.domain.entities.logros.Logro;
import com.skillboostfootball.backend_main_springboot.domain.entities.logros.ProfileLogro;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.repositories.logros.LogroRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.logros.ProfileLogroRepository;
import com.skillboostfootball.backend_main_springboot.application.useCases.usuarios.GetCurrentUserUseCase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GetLogrosUsuarioUseCase {
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final LogroRepository logroRepository;
    private final ProfileLogroRepository profileLogroRepository;
    
    @Transactional(readOnly = true)
    public List<Map<String, Object>> execute() {

        Usuario currentUser = getCurrentUserUseCase.execute();
        List<Logro> todosLosLogros = logroRepository.findAll();
        List<ProfileLogro> logrosUsuario = profileLogroRepository.findByProfileId(currentUser.getProfile().getId());
        
        //Crear un mapa para acceder rápidamente a los logros del usuario por ID logro
        Map<Long, ProfileLogro> mapaLogrosUsuario = new HashMap<>();
        for (ProfileLogro profileLogro : logrosUsuario) {
            mapaLogrosUsuario.put(profileLogro.getLogroId(), profileLogro);
        }
        
        List<Map<String, Object>> resultado = new ArrayList<>();
        
        for (Logro logro : todosLosLogros) {
            
            Map<String, Object> logroInfo = new HashMap<>();
            logroInfo.put("id", logro.getId());
            logroInfo.put("nombre", logro.getNombre());
            logroInfo.put("descripcion", logro.getDescripcion());
            logroInfo.put("requisitoEntrenamientos", logro.getRequisitoEntrenamientos());
            logroInfo.put("nivelDificultad", logro.getNivelDificultad());
            logroInfo.put("recompensa", logro.getRecompensa());
            
            //Añadir información del progreso del usuario
            ProfileLogro profileLogro = mapaLogrosUsuario.get(logro.getId());
            if (profileLogro != null) {
                logroInfo.put("progreso", profileLogro.getProgresoActual());
                logroInfo.put("completado", profileLogro.getNotificado());
                logroInfo.put("fechaLogro", profileLogro.getFechaLogro());
            } else {
                logroInfo.put("progreso", 0);
                logroInfo.put("completado", false);
                logroInfo.put("fechaLogro", null);
            }
            resultado.add(logroInfo);

        }
        return resultado;

    }
}
