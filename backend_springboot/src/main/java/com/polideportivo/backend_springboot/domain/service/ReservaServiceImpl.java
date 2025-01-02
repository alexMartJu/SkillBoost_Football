package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.api.model.reserva.ReservaRequest;
import com.polideportivo.backend_springboot.domain.exception.HorarioNotFoundException;
import com.polideportivo.backend_springboot.domain.exception.PistaNotFoundException;
import com.polideportivo.backend_springboot.domain.exception.ProfileNotFoundException;
import com.polideportivo.backend_springboot.domain.exception.ReservaAlreadyExistsException;
import com.polideportivo.backend_springboot.domain.exception.ReservaNotFoundException;
import com.polideportivo.backend_springboot.domain.model.Horario;
import com.polideportivo.backend_springboot.domain.model.Pista;
import com.polideportivo.backend_springboot.domain.model.Profile;
import com.polideportivo.backend_springboot.domain.model.Reserva;
import com.polideportivo.backend_springboot.domain.repository.HorarioRepository;
import com.polideportivo.backend_springboot.domain.repository.PistaRepository;
import com.polideportivo.backend_springboot.domain.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {
    
    private final ReservaRepository reservaRepository;
    private final PistaRepository pistaRepository;
    private final HorarioRepository horarioRepository;
    private final UsuarioService usuarioService;

    @Override
    public Reserva createReserva(ReservaRequest request) {

        LocalDate fecha = LocalDate.parse(request.getFecha());
        
        // Obtener el perfil del usuario logueado
        Profile profile = usuarioService.getCurrentUser().getProfile();
        if (profile == null) {
            throw new ProfileNotFoundException();
        }

        // Obtener la pista por slug
        Pista pista = pistaRepository.findBySlugAndDeletedAtIsNull(request.getSlugPista())
                .orElseThrow(PistaNotFoundException::new);

        // Buscar horario por hora
        Horario horario = horarioRepository.findByHora(request.getHora())
        .orElseThrow(HorarioNotFoundException::new);

        // Validar si ya existe una reserva para la pista, el horario y el día
        boolean exists = reservaRepository.existsByPistaIdAndHorarioIdAndFecha(pista.getId(), horario.getId(), fecha);
        if (exists) {
            throw new ReservaAlreadyExistsException("La pista ya está reservada en ese horario y día.");
        }

        // Crear la nueva reserva
        Reserva reserva = new Reserva();
        reserva.setProfile(profile);
        reserva.setPista(pista);
        reserva.setHorario(horario);
        reserva.setFecha(fecha);

        // Guardar la reserva en la base de datos
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva findReserva(String slugPista, String hora, String fecha) {
        // Convertir fecha en formato LocalDate
        LocalDate fechaLocal = LocalDate.parse(fecha);

        // Obtener la Pista por su slug
        Pista pista = pistaRepository.findBySlugAndDeletedAtIsNull(slugPista)
                .orElseThrow(PistaNotFoundException::new);

        // Obtener el Horario por la hora
        Horario horario = horarioRepository.findByHora(hora)
                .orElseThrow(HorarioNotFoundException::new);

        // Buscar la reserva utilizando los IDs de la Pista y el Horario
        return reservaRepository.findByPistaIdAndHorarioIdAndFecha(pista.getId(), horario.getId(), fechaLocal)
                .orElseThrow(ReservaNotFoundException::new);
    }
}
