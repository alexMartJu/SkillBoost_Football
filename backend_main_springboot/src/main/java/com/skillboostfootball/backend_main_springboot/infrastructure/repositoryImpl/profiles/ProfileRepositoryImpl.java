package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.profiles;

import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profiles.ProfileRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.profiles.ProfileEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProfileRepositoryImpl implements ProfileRepository {
    private final SpringDataProfileRepository repository;
    private final EntityMapper mapper;
    
    @Override
    public Optional<Profile> findById(Long id) {
        return repository.findById(id).map(mapper::toProfile);
    }
    
    @Override
    public Optional<Profile> findByNumeroSocio(String numeroSocio) {
        return repository.findByNumeroSocio(numeroSocio).map(mapper::toProfile);
    }

    @Override
    public Optional<Profile> findByNumeroEntrenador(String numeroEntrenador) {
        return repository.findByNumeroEntrenador(numeroEntrenador).map(mapper::toProfile);
    }
    
    @Override
    public List<Profile> findByEsMenor(Boolean esMenor) {
        return repository.findByEsMenor(esMenor).stream()
            .map(mapper::toProfile)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Profile> findByClubOrigenIsNotNull() {
        return repository.findByClubOrigenIsNotNull().stream()
            .map(mapper::toProfile)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Profile> findByOrganizacionOrigenIsNotNull() {
        return repository.findByOrganizacionOrigenIsNotNull().stream()
            .map(mapper::toProfile)
            .collect(Collectors.toList());
    }
    
    @Override
    public Profile save(Profile profile) {
        ProfileEntity entity = mapper.toProfileEntity(profile);
        entity = repository.save(entity);
        return mapper.toProfile(entity);
    }
}
