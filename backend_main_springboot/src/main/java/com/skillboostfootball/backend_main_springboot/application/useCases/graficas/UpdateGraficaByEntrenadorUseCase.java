package com.skillboostfootball.backend_main_springboot.application.useCases.graficas;

import com.skillboostfootball.backend_main_springboot.application.useCases.usuarios.GetCurrentUserUseCase;
import com.skillboostfootball.backend_main_springboot.domain.entities.graficas.Grafica;
import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.profiles.ProfileNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.graficas.GraficaRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profiles.ProfileRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profileSuscripciones.ProfileSuscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateGraficaByEntrenadorUseCase {

    private final GraficaRepository graficaRepository;
    private final ProfileRepository profileRepository;
    private final ProfileSuscripcionRepository profileSuscripcionRepository;
    private final GetCurrentUserUseCase getCurrentUserUseCase;

    @Transactional
    public List<Grafica> execute(String numeroSocio, List<GraficaUpdateData> graficasData) {
        
        Usuario currentUser = getCurrentUserUseCase.execute();
        //Buscar el perfil por número de socio
        Profile profile = profileRepository.findByNumeroSocio(numeroSocio).orElseThrow((ProfileNotFoundException::new));
        Long profileId = profile.getId();
        
        //Verificar que el usuario tiene una suscripción que permite gráficas
        boolean tieneGraficas = profileSuscripcionRepository.findActiveWithSuscripcionByProfileId(profileId)
            .map(ps -> ps.getSuscripcion() != null && ps.getSuscripcion().getIncluyeGraficas())
            .orElse(false);
        
        if (!tieneGraficas) {
            throw new IllegalStateException("El usuario no tiene una suscripción que incluya gráficas de rendimiento");
        }
        
        //Obtener el mes y año actual
        YearMonth now = YearMonth.now();
        int mesActual = now.getMonthValue();
        int añoActual = now.getYear();
        
        List<Grafica> graficasActualizadas = new ArrayList<>();
        for (GraficaUpdateData data : graficasData) {

            //Buscar si ya existe una gráfica para esta sección en el mes y año actual
            Grafica grafica = graficaRepository.findByProfileIdAndSeccionAndMesAndAño(
                    profileId, data.seccion(), mesActual, añoActual)
                    .orElse(null);
            
            if (grafica == null) {

                //Crear nueva gráfica
                grafica = Grafica.builder()
                        .profileId(profileId)
                        .seccion(data.seccion())
                        .nivel(data.nivel())
                        .mes(mesActual)
                        .año(añoActual)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
            } else {

                //Actualizar gráfica existente
                grafica = Grafica.builder()
                        .id(grafica.getId())
                        .profileId(profileId)
                        .seccion(data.seccion())
                        .nivel(data.nivel())
                        .mes(mesActual)
                        .año(añoActual)
                        .createdAt(grafica.getCreatedAt())
                        .updatedAt(LocalDateTime.now())
                        .build();
            }
            graficasActualizadas.add(graficaRepository.save(grafica));
        }
        return graficasActualizadas;
        
    }
    
    public record GraficaUpdateData(String seccion, Integer nivel) {}
}
