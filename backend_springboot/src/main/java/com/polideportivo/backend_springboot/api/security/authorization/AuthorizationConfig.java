package com.polideportivo.backend_springboot.api.security.authorization;

import com.polideportivo.backend_springboot.api.security.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationConfig {
    
    private final AuthUtils authUtils;
    
    public boolean isAuthenticated() {
        return authUtils.isAuthenticated();
    }
}
