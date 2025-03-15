package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.logros;

import com.skillboostfootball.backend_main_springboot.domain.entities.logros.ProfileLogro;
import com.skillboostfootball.backend_main_springboot.domain.repositories.logros.ProfileLogroRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.logros.ProfileLogroEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.logros.ProfileLogroId;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProfileLogroRepositoryImpl implements ProfileLogroRepository {
    private final SpringDataProfileLogroRepository repository;
    private final EntityMapper mapper;
    
    @Override
    public List<ProfileLogro> findByProfileId(Long profileId) {
        return repository.findById_ProfileId(profileId)
            .stream()
            .map(mapper::toProfileLogro)
            .collect(Collectors.toList());
    }
    
    @Override
    public Optional<ProfileLogro> findByProfileIdAndLogroId(Long profileId, Long logroId) {
        ProfileLogroId id = new ProfileLogroId(profileId, logroId);
        return repository.findById(id).map(mapper::toProfileLogro);
    }
    
    @Override
    public ProfileLogro save(ProfileLogro profileLogro) {
        ProfileLogroEntity entity = mapper.toProfileLogroEntity(profileLogro);
        entity = repository.save(entity);
        return mapper.toProfileLogro(entity);
    }
    
    
    @Override
    public List<ProfileLogro> findByProfileIdAndNotNotificado(Long profileId) {
        return repository.findById_ProfileIdAndNotificadoFalse(profileId)
            .stream()
            .map(mapper::toProfileLogro)
            .collect(Collectors.toList());
    }
    }
