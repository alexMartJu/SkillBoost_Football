package com.skillboostfootball.backend_main_springboot.application.security.authorization;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {
    
    public @interface Public {
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("permitAll()")
        public @interface canRead {}
    }

    public @interface Protected {
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.isAuthenticated")
        public @interface canManage {}
    }
    
    public @interface Entrenador {
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.isEntrenador")
        public @interface canAccess {}
    }
    
    public @interface Admin {
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.isAdmin")
        public @interface canAccess {}
    }
    
    public @interface Tutor {
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.isTutor")
        public @interface canAccess {}
    }
    
    public @interface Jugador {
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.isJugador")
        public @interface canAccess {}
    }
    
    public @interface JugadorSocial {
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.isJugadorSocial")
        public @interface canAccess {}
    }
    
    public @interface JugadorClub {
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.isJugadorClub")
        public @interface canAccess {}
    }
    
    public @interface Entrenamientos {
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.canManageEntrenamiento(#slug)")
        public @interface canManage {}
    }
    
}
