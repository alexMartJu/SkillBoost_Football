package com.skillboostfootball.backend_main_springboot.application.useCases.auth;

import com.skillboostfootball.backend_main_springboot.application.applicationServices.NumeroSocioGenerator;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.entities.roles.Role;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.roles.RoleNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.usuarios.EmailTakenException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.usuarios.UsuarioRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.roles.RoleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class RegisterSocialHelpPlayerUseCase {
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final NumeroSocioGenerator numeroSocioGenerator;
    
    private static final String DEFAULT_IMAGE = "https://static.productionready.io/images/default-user.png";
    
    @Transactional
    public Usuario execute(String email, String password, String nombre, String apellidos, Integer edad, String organizacionOrigen) {

        if (usuarioRepository.existsByEmail(email)) {
            throw new EmailTakenException();
        }   

        boolean esMenor = edad < 18;
        Role role = roleRepository.findByName("jugador_social").orElseThrow(RoleNotFoundException::new);
        
        //Crear el usuario
        Usuario usuario = Usuario.builder()
            .email(email)
            .password(passwordEncoder.encode(password))
            .tipoUsuario("jugador_social")
            .status("active")
            .roles(new ArrayList<>())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        usuario.addRole(role);

        // Crear el perfil
        Profile profile = Profile.builder()
            .numeroSocio(numeroSocioGenerator.generate())
            .nombre(nombre)
            .apellidos(apellidos)
            .bio(null)
            .image(DEFAULT_IMAGE)
            .edad(edad)
            .esMenor(esMenor)
            .organizacionOrigen(organizacionOrigen)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        usuario.setProfile(profile);
        usuario = usuarioRepository.save(usuario);
        
        return usuario;
        
    }
}
