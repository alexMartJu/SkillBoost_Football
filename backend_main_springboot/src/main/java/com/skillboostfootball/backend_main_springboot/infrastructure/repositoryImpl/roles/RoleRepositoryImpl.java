package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.roles;

import com.skillboostfootball.backend_main_springboot.domain.entities.roles.Role;
import com.skillboostfootball.backend_main_springboot.domain.repositories.roles.RoleRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.roles.RoleEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {
    private final SpringDataRoleRepository repository;
    private final EntityMapper mapper;
    
    @Override
    public Optional<Role> findById(Long id) {
        return repository.findById(id).map(mapper::toRole);
    }
    
    @Override
    public Optional<Role> findByName(String name) {
        return repository.findByName(name).map(mapper::toRole);
    }
    
    @Override
    public List<Role> findAll() {
        return repository.findAll().stream()
            .map(mapper::toRole)
            .collect(Collectors.toList());
    }
    
    @Override
    public Role save(Role role) {
        RoleEntity entity = mapper.toRoleEntity(role);
        entity = repository.save(entity);
        return mapper.toRole(entity);
    }
}
