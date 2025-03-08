package com.skillboostfootball.backend_main_springboot.domain.repositories.profiles;

import java.util.Optional;

import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;

import java.util.List;

public interface ProfileRepository {

    Optional<Profile> findById(Long id);
    Optional<Profile> findByNumeroSocio(String numeroSocio);
    Optional<Profile> findByNumeroEntrenador(String numeroEntrenador);
    List<Profile> findByEsMenor(Boolean esMenor);
    List<Profile> findByClubOrigenIsNotNull();
    List<Profile> findByOrganizacionOrigenIsNotNull();
    Profile save(Profile profile);
    
}
