package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.profileSuscripciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.profileSuscripciones.ProfileSuscripcion;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profileSuscripciones.ProfileSuscripcionRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.profileSuscripciones.ProfileSuscripcionEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProfileSuscripcionRepositoryImpl implements ProfileSuscripcionRepository {
    
    private final SpringDataProfileSuscripcionRepository repository;
    private final EntityMapper mapper;
    
    @Override
    public List<ProfileSuscripcion> findByProfileId(Long profileId) {
        return repository.findByProfileId(profileId)
                .stream()
                .map(mapper::toProfileSuscripcion)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<ProfileSuscripcion> findActiveByProfileId(Long profileId) {
        return repository.findActiveByProfileId(profileId, LocalDate.now()).map(mapper::toProfileSuscripcion);
    }
    
    @Override
    public ProfileSuscripcion save(ProfileSuscripcion profileSuscripcion) {
        ProfileSuscripcionEntity entity = mapper.toProfileSuscripcionEntity(profileSuscripcion);
        entity = repository.save(entity);
        return mapper.toProfileSuscripcion(entity);
    }
    
    @Override
    public Optional<ProfileSuscripcion> findById(Long id) {
        return repository.findById(id).map(mapper::toProfileSuscripcion);
    }

}
