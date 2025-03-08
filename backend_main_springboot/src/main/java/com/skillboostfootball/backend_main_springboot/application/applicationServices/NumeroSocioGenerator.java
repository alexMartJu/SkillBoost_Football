package com.skillboostfootball.backend_main_springboot.application.applicationServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.skillboostfootball.backend_main_springboot.domain.repositories.profiles.ProfileRepository;

@Service
@RequiredArgsConstructor
public class NumeroSocioGenerator {
    
    private final ProfileRepository profileRepository;
    
    public String generate() {
        String numeroSocio;
        do {
            // Genera un número aleatorio de 8 dígitos
            int randomNum = (int) (Math.random() * 100000000);
            numeroSocio = String.format("%08d", randomNum);
        } while (isNumeroSocioTaken(numeroSocio));
        
        return numeroSocio;
    }
    
    private boolean isNumeroSocioTaken(String numeroSocio) {
        return profileRepository.findByNumeroSocio(numeroSocio).isPresent();
    }
}
