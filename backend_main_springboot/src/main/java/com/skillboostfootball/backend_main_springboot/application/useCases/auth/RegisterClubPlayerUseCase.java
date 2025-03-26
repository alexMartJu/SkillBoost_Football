package com.skillboostfootball.backend_main_springboot.application.useCases.auth;

import com.skillboostfootball.backend_main_springboot.application.applicationServices.NumeroSocioGenerator;
import com.skillboostfootball.backend_main_springboot.application.applicationServices.SuscripcionAssignmentService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class RegisterClubPlayerUseCase {

    private static final Logger logger = LoggerFactory.getLogger(RegisterClubPlayerUseCase.class);

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final NumeroSocioGenerator numeroSocioGenerator;
    private final SuscripcionAssignmentService suscripcionAssignmentService;
    
    private static final String DEFAULT_IMAGE = "https://static.productionready.io/images/smiley-cyrus.jpg";
    
    @Transactional
    public Usuario execute(String email, String password, String nombre, String apellidos, Integer edad, String posicionPreferida, String clubOrigen) {
        //Verificar que el email no esté en uso
        if (usuarioRepository.existsByEmail(email)) {
            throw new EmailTakenException();
        }
        
        //Determinar si es menor
        boolean esMenor = edad < 18;
        //Obtener el rol correspondiente
        Role role = roleRepository.findByName("jugador_club").orElseThrow(RoleNotFoundException::new);
        
        //Crear el usuario
        Usuario usuario = Usuario.builder()
            .email(email)
            .password(passwordEncoder.encode(password))
            .tipoUsuario("jugador_club")
            .status("active")
            .roles(new ArrayList<>())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        usuario.addRole(role);

        //Crear el perfil
        Profile profile = Profile.builder()
            .numeroSocio(numeroSocioGenerator.generate())
            .nombre(nombre)
            .apellidos(apellidos)
            .bio(null)
            .image(DEFAULT_IMAGE)
            .edad(edad)
            .esMenor(esMenor)
            .posicionPreferida(posicionPreferida)
            .clubOrigen(clubOrigen)
            .entrenamientosDisponibles(0)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        usuario.setProfile(profile);
        usuario = usuarioRepository.save(usuario);
        logger.info("Usuario jugador de club registrado con ID: {}", usuario.getId());

        //Asignar suscripción Elite Profesional
        suscripcionAssignmentService.asignarSuscripcionJugadorClub(usuario.getProfile());
        
        return usuario;
    }
}
