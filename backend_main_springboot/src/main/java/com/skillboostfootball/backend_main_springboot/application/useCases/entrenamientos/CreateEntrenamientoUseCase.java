package com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos;


import com.skillboostfootball.backend_main_springboot.application.applicationServices.SlugService;
import com.skillboostfootball.backend_main_springboot.application.useCases.usuarios.GetCurrentUserUseCase;
import com.skillboostfootball.backend_main_springboot.domain.entities.entrenamientos.Entrenamiento;
import com.skillboostfootball.backend_main_springboot.domain.entities.horariosPista.HorarioPista;
import com.skillboostfootball.backend_main_springboot.domain.entities.pistas.Pista;
import com.skillboostfootball.backend_main_springboot.domain.entities.subtiposTecnificacion.SubtipoTecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.entities.tecnificaciones.Tecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.horariosPista.HorarioOverlapException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.pistas.PistaNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.subtiposTecnificacion.SubtipoTecnificacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.tecnificaciones.TecnificacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.entrenamientos.EntrenamientoRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.horariosPista.HorarioPistaRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.pistas.PistaRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.subtiposTecnificacion.SubtipoTecnificacionRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.tecnificaciones.TecnificacionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateEntrenamientoUseCase {
    
    private final EntrenamientoRepository entrenamientoRepository;
    private final PistaRepository pistaRepository;
    private final TecnificacionRepository tecnificacionRepository;
    private final SubtipoTecnificacionRepository subtipoTecnificacionRepository;
    private final HorarioPistaRepository horarioPistaRepository;
    private final SlugService slugService;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    
    @Transactional
    public Entrenamiento execute(String nombre, String descripcion, String nivel, Integer edadMinima, Integer edadMaxima, String tecnificacionSlug,
            String subtipoTecnificacionSlug, String pistaSlug, LocalDateTime fechaInicio, LocalDateTime fechaFin, Integer maxPlazas,
            String objetivos, String equipamientoNecesario, Integer duracionMinutos) {
                
        Usuario usuarioActual = getCurrentUserUseCase.execute();
        
        //Verificar que sea un entrenador
        if (!"entrenador".equals(usuarioActual.getTipoUsuario())) {
            throw new RuntimeException("Solo los entrenadores pueden crear entrenamientos");
        }
        
        //Obtener el ID del perfil del entrenador
        Long entrenadorId = usuarioActual.getProfile().getId();
        
        log.info("Creando entrenamiento: {}, pista: {}, fechas: {} - {}", nombre, pistaSlug, fechaInicio, fechaFin);
        
        //Validar datos básicos
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha de fin");
        }
        
        //Buscar pista usando findBySlug
        Pista pista = pistaRepository.findBySlug(pistaSlug).orElseThrow(PistaNotFoundException::new);
            
        //Buscar tecnificación y subtipo
        Tecnificacion tecnificacion = null;
        SubtipoTecnificacion subtipoTecnificacion = null;
        
        if (tecnificacionSlug != null && !tecnificacionSlug.isEmpty()) {
            tecnificacion = tecnificacionRepository.findBySlug(tecnificacionSlug).orElseThrow(TecnificacionNotFoundException::new);
                
            if (subtipoTecnificacionSlug != null && !subtipoTecnificacionSlug.isEmpty()) {
                subtipoTecnificacion = subtipoTecnificacionRepository.findBySlug(subtipoTecnificacionSlug).orElseThrow(SubtipoTecnificacionNotFoundException::new);
            }
        }
        
        //Verificar si hay horarios que se solapan en la misma pista
        List<HorarioPista> horariosSolapados = horarioPistaRepository.findOverlappingHorarios(
            pista.getId(), fechaInicio, fechaFin);
        
        if (!horariosSolapados.isEmpty()) {
            log.warn("Se encontraron {} horarios solapados", horariosSolapados.size());
            throw new HorarioOverlapException();
        }
        
        //Generar un slug
        String slug = slugService.generateSlug(nombre);
        
        //Crear nuevo horario para la pista
        HorarioPista horarioPista = HorarioPista.builder()
            .pista(pista)
            .fechaInicio(fechaInicio)
            .fechaFin(fechaFin)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        
        //Crear el entrenamiento
        Entrenamiento entrenamiento = Entrenamiento.builder()
            .nombre(nombre)
            .slug(slug)
            .descripcion(descripcion)
            .nivel(nivel)
            .edadMinima(edadMinima)
            .edadMaxima(edadMaxima)
            .tecnificacion(tecnificacion)
            .subtipoTecnificacion(subtipoTecnificacion)
            .entrenadorId(entrenadorId)
            .maxPlazas(maxPlazas)
            .objetivos(objetivos)
            .equipamientoNecesario(equipamientoNecesario)
            .duracionMinutos(duracionMinutos)
            .status("pending")
            .images(new ArrayList<>())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        
        //Guardar el entrenamiento
        entrenamiento = entrenamientoRepository.save(entrenamiento);
        //Asignar el entrenamiento al horario y guardar
        horarioPista.asignarEntrenamiento(entrenamiento);
        horarioPista = horarioPistaRepository.save(horarioPista);
        
        entrenamiento = entrenamientoRepository.findBySlug(entrenamiento.getSlug()).orElseThrow();
            
        return entrenamiento;

    }

}