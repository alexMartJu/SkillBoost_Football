package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.usuarios;

import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.repositories.usuarios.UsuarioRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.usuarios.UsuarioEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository {
    private final SpringDataUsuarioRepository repository;
    private final EntityMapper mapper;
    
    @Override
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id).map(mapper::toUsuario);
    }
    
    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toUsuario);
    }
    
    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
    
    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = mapper.toUsuarioEntity(usuario);
        entity = repository.save(entity);
        return mapper.toUsuario(entity);
    }
    
    @Override
    public void delete(Usuario usuario) {
        repository.deleteById(usuario.getId());
    }
}
