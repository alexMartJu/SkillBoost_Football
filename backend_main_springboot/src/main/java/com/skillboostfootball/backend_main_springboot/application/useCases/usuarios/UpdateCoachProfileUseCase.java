package com.skillboostfootball.backend_main_springboot.application.useCases.usuarios;

import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.usuarios.EmailTakenException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.usuarios.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateCoachProfileUseCase {
    private final UsuarioRepository usuarioRepository;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final PasswordEncoder passwordEncoder;
    
    @Transactional
    public Profile execute(String nombre, String apellidos, String bio, String image, String email, String password, Integer edad, 
            String especialidad, String certificaciones) {

        Usuario usuario = getCurrentUserUseCase.execute();
        
        //Verificar si el usuario es entrenador
        if (!usuario.hasRole("entrenador")) {
            throw new RuntimeException("Solo los entrenadores pueden usar este endpoint");
        }
        
        //Actualizar email y password si se proporcionan
        if (email != null && !email.isEmpty() && !email.equals(usuario.getEmail())) {
            // Verificar que el email no esté en uso por otro usuario
            if (usuarioRepository.existsByEmail(email) && !email.equals(usuario.getEmail())) {
                throw new EmailTakenException();
            }
            String encodedPassword = password != null && !password.isEmpty() ? passwordEncoder.encode(password) : usuario.getPassword();
            usuario.update(email, encodedPassword);
        } else if (password != null && !password.isEmpty()) {
            usuario.update(usuario.getEmail(), passwordEncoder.encode(password));
        }
        
        //Actualizar perfil
        Profile profile = usuario.getProfile();
        profile.update(nombre, apellidos, bio, edad);
        
        //Actualizar campos adicionales
        if (image != null && !image.isEmpty()) {
            profile.updateImage(image);
        }
        
        //Actualizar información específica de entrenador
        profile.updateCoachInfo(especialidad, profile.getExperienciaAnios(), certificaciones);
        //Guardar usuario y perfil
        usuario = usuarioRepository.save(usuario);
        return usuario.getProfile();
        
    }
}
