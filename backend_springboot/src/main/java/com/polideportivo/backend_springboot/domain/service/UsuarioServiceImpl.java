package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.model.Usuario;
import com.polideportivo.backend_springboot.domain.model.Profile;
import com.polideportivo.backend_springboot.domain.repository.ProfileRepository;
import com.polideportivo.backend_springboot.domain.repository.UsuarioRepository;

import jakarta.persistence.EntityManager;

import com.polideportivo.backend_springboot.domain.exception.EmailNotFoundException;
import com.polideportivo.backend_springboot.domain.exception.EmailTakenException;
import com.polideportivo.backend_springboot.domain.exception.NumeroSocioTakenException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository userRepository;
    private final EntityManager entityManager;
    private final ProfileService profileService;
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;

    @Transactional(readOnly = true)
    public Usuario getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(EmailNotFoundException::new);
    }

    @Transactional
    public Usuario save(Usuario user, Profile profile) {
        entityManager.detach(user);

        checkUserAvailable(user, profile);

        if (user.getId() == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setProfile(profile);
        }

        user = userRepository.save(user);
        profileService.save(profile);
        return user;
    }

    private void checkUserAvailable(Usuario user, Profile profile) {
        if (numeroSocioTaken(profile)) {
            throw new NumeroSocioTakenException();
        }

        if (emailTaken(user)) {
            throw new EmailTakenException();
        }
    }

    private boolean numeroSocioTaken(Profile profile) {
        var existingProfile = profileRepository.findByNumeroSocio(profile.getNumeroSocio());
        return existingProfile.isPresent() && !existingProfile.get().equals(profile);
    }

    private boolean emailTaken(Usuario user) {
        var existingUser = userRepository.findByEmail(user.getEmail());
        return existingUser.isPresent() && !existingUser.get().equals(user);
    }
}
