package com.skillboostfootball.backend_main_springboot.application.security;

import com.skillboostfootball.backend_main_springboot.domain.repositories.usuarios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UsuarioRepository usuarioRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));
        
        //Si el usuario está inactivo (borrado), no permitimos la autenticación
        if (!"active".equals(usuario.getStatus())) {
            throw new UsernameNotFoundException("Usuario inactivo: " + email);
        }
        
        //Convertir roles a authorities
        var authorities = new ArrayList<SimpleGrantedAuthority>();
        
        //Añadir el tipo de usuario como un rol
        authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getTipoUsuario().toUpperCase()));
        
        //Añadir roles del usuario
        if (usuario.getRoles() != null) {
            authorities.addAll(
                usuario.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()))
                    .collect(Collectors.toList())
            );
            
            //Añadir permisos de los roles
            authorities.addAll(
                usuario.getRoles().stream()
                    .flatMap(role -> role.getPermissions().stream())
                    .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                    .collect(Collectors.toList())
            );
        }
        
        return new User(
            usuario.getEmail(),
            usuario.getPassword(),
            authorities
        );
    }
}
