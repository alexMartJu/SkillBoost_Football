package com.skillboostfootball.backend_main_springboot.application.security.authorization;

import com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos.GetEntrenamientoBySlugUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.usuarios.GetCurrentUserUseCase;
import com.skillboostfootball.backend_main_springboot.application.security.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationConfig {
    
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final GetEntrenamientoBySlugUseCase getEntrenamientoBySlugUseCase;
    private final AuthUtils authUtils;
    
    public boolean isAuthenticated() {
        return authUtils.isAuthenticated();
    }
    
    public boolean isEntrenador() {
        Authentication authentication = authUtils.getAuthentication();
        return authentication != null && (
            authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ENTRENADOR")));
    }
    
    public boolean isAdmin() {
        Authentication authentication = authUtils.getAuthentication();
        return authentication != null && 
            authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
    
    public boolean isTutor() {
        Authentication authentication = authUtils.getAuthentication();
        return authentication != null && (
            authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_TUTOR")));
    }
    
    public boolean isJugador() {
        Authentication authentication = authUtils.getAuthentication();
        return authentication != null && (
            authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_JUGADOR")));
    }
    
    public boolean isJugadorSocial() {
        Authentication authentication = authUtils.getAuthentication();
        return authentication != null && (
            authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_JUGADOR_SOCIAL")));
    }
    
    public boolean isJugadorClub() {
        Authentication authentication = authUtils.getAuthentication();
        return authentication != null && (
            authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_JUGADOR_CLUB")));
    }

    public boolean areJugadores() {
        Authentication authentication = authUtils.getAuthentication();
        return authentication != null && (
            authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_JUGADOR")) ||
            authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_JUGADOR_CLUB")) ||
            authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_JUGADOR_SOCIAL")) ||
            authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_TUTOR"))
        );
    }
    
    public boolean canManageEntrenamiento(String slug) {
        if (!isAuthenticated()) {
            return false;
        }
        
        if (isAdmin()) {
            return true;
        }
        
        var currentUser = getCurrentUserUseCase.execute();
        var entrenamiento = getEntrenamientoBySlugUseCase.execute(slug);
        
        if (isEntrenador()) {
            return entrenamiento.getEntrenadorId().equals(currentUser.getId());
        }
        
        return false;
    }
}
