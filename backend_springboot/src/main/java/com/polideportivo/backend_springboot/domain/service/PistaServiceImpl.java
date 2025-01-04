package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.api.assembler.PistaAssembler;
import com.polideportivo.backend_springboot.api.model.pista.PistaReservaStatusResponse;
import com.polideportivo.backend_springboot.domain.exception.PistaNotFoundException;
import com.polideportivo.backend_springboot.domain.exception.ProfileNotFoundException;
import com.polideportivo.backend_springboot.domain.model.Pista;
import com.polideportivo.backend_springboot.domain.model.Reserva;
import com.polideportivo.backend_springboot.domain.repository.PistaRepository;
import com.polideportivo.backend_springboot.domain.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PistaServiceImpl implements PistaService {
    private final PistaRepository repository;
    private final ImageService imageService;
    private final UsuarioService usuarioService;
    private final ReservaRepository reservaRepository;
    private final PistaAssembler pistaAssembler;

    @Transactional(readOnly = true)
    public List<Pista> getAllPistas() {
        // Obtiene todas las pistas
        List<Pista> pistas = repository.findByDeletedAtIsNull();
        // Asigna imágenes a cada pista
        pistas.forEach(pista -> pista.setImages(
            imageService.getImagesForEntity("App\\Models\\Pista", pista.getId())
        ));
        return pistas;
    }

    @Transactional(readOnly = true)
    public Pista getBySlug(String slug) {
        // Busca la pista por slug
        Pista pista = repository.findBySlugAndDeletedAtIsNull(slug).orElseThrow(PistaNotFoundException::new);
        // Asigna imágenes a la pista
        pista.setImages(imageService.getImagesForEntity("App\\Models\\Pista", pista.getId()));
        return pista;
    }

    @Transactional(readOnly = true)
    public Page<Pista> getAllScrollPistas(Pageable pageable) {
        // Obtiene todas las pistas
        Page<Pista> pistas = repository.findByDeletedAtIsNull(pageable);
        // Asigna imágenes a cada pista
        pistas.forEach(pista -> pista.setImages(
            imageService.getImagesForEntity("App\\Models\\Pista", pista.getId())
        ));
        return pistas;
    }

    @Transactional(readOnly = true)
    public List<Pista> getPistasReservadasCurrentUser() {
        // Obtener el perfil del usuario logueado
        var currentUserProfile = usuarioService.getCurrentUser().getProfile();
        if (currentUserProfile == null) {
            throw new ProfileNotFoundException();
        }

        // Buscar todas las reservas asociadas al usuario
        List<Reserva> reservas = reservaRepository.findByProfileId(currentUserProfile.getId());

        // Obtener las pistas asociadas a las reservas (sin duplicados)
        List<Pista> pistas = reservas.stream()
                .map(Reserva::getPista) // Extraer la pista de cada reserva
                .distinct() // Eliminar duplicados
                .collect(Collectors.toList());

        // Cargar las imágenes para cada pista
        pistas.forEach(pista -> pista.setImages(
            imageService.getImagesForEntity("App\\Models\\Pista", pista.getId())
        ));

        // Asignar las reservas correspondientes a cada pista
        pistas.forEach(pista -> {
            List<Reserva> reservasDePista = reservas.stream()
                    .filter(reserva -> reserva.getPista().getId().equals(pista.getId()))
                    .collect(Collectors.toList());
            pista.setReservas(reservasDePista); // Relación cargada dinámicamente
        });

        return pistas;
    }

    public List<PistaReservaStatusResponse> getProfilePistasStatus() {

        // Obtener el perfil del usuario logueado
        var currentUserProfile = usuarioService.getCurrentUser().getProfile();
        if (currentUserProfile == null) {
            throw new ProfileNotFoundException();
        }

        // Obtener todas las reservas del usuario
        List<Reserva> reservas = reservaRepository.findByProfileId(currentUserProfile.getId());

        // Usar el assembler para convertir las reservas a la respuesta
        return reservas.stream()
                .map(pistaAssembler::toPistaReservaStatusResponse) // Usamos el assembler aquí
                .collect(Collectors.toList());
    }
}
