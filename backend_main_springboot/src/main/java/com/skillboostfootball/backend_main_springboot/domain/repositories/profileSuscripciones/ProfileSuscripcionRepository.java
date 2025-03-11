package com.skillboostfootball.backend_main_springboot.domain.repositories.profileSuscripciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.profileSuscripciones.ProfileSuscripcion;

import java.util.List;
import java.util.Optional;

public interface ProfileSuscripcionRepository {
    List<ProfileSuscripcion> findByProfileId(Long profileId);
    Optional<ProfileSuscripcion> findActiveByProfileId(Long profileId);
    ProfileSuscripcion save(ProfileSuscripcion profileSuscripcion);
    Optional<ProfileSuscripcion> findById(Long id);
}
