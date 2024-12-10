package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.model.Profile;
import com.polideportivo.backend_springboot.domain.model.Usuario;
import com.polideportivo.backend_springboot.domain.repository.ProfileRepository;
import com.polideportivo.backend_springboot.domain.exception.ProfileNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    
    private final String DEFAULT_IMAGE_URL = "https://static.productionready.io/images/smiley-cyrus.jpg";


    private final ProfileRepository profileRepository;

    @Transactional(readOnly = true)
    public Profile getByNumeroSocio(String numeroSocio) {
        return profileRepository.findByNumeroSocio(numeroSocio).orElseThrow(ProfileNotFoundException::new);
    }

    @Transactional
    public Profile save(Profile profile) {

        profile.setBio(profile.getBio());
        profile.setEdad(profile.getEdad());
        profile.setImage(profile.getImage());
        
        return profileRepository.save(profile);
    }

    public Profile createNewProfile(Usuario user, String nombre, String apellidos) {

        // Generar un número de socio único
        String numeroSocio = generateUniqueNumeroSocio();

        return Profile.builder()
                .user(user)
                .numeroSocio(numeroSocio)
                .nombre(nombre)
                .apellidos(apellidos)
                .bio(null)
                .edad(null)
                .image(DEFAULT_IMAGE_URL)
                .build();
    }

    private String generateUniqueNumeroSocio() {
        String numeroSocio;
        do {
            // Genera un número aleatorio de 8 dígitos
            int randomNum = (int) (Math.random() * 1_000_000_00); 
            numeroSocio = String.format("%08d", randomNum); // Formato de 8 dígitos
        } while (isNumeroSocioTaken(numeroSocio)); // Verifica si ya existe en la base de datos
        return numeroSocio;
    }
    
    private boolean isNumeroSocioTaken(String numeroSocio) {
        // Consulta al repositorio para verificar si el número de socio ya existe
        return profileRepository.findByNumeroSocio(numeroSocio).isPresent();
    }


}
