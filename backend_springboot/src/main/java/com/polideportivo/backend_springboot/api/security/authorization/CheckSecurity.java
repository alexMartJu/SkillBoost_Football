package com.polideportivo.backend_springboot.api.security.authorization;

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

    public @interface Reservas {
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.isReservaOwner(#slugPista, #hora, #fecha)")
        public @interface canDelete {}
    }
}
