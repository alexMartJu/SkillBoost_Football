package com.polideportivo.backend_springboot.api.controller;

import com.polideportivo.backend_springboot.api.assembler.ProfileAssembler;
import com.polideportivo.backend_springboot.api.model.profile.ProfileResponse;
import com.polideportivo.backend_springboot.api.security.AuthUtils;
import com.polideportivo.backend_springboot.api.security.authorization.CheckSecurity;
import com.polideportivo.backend_springboot.domain.service.ProfileService;
import com.polideportivo.backend_springboot.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProfileController {
    
    private final UsuarioService userService;
    private final ProfileService profileService;
    private final ProfileAssembler profileAssembler;
    private final AuthUtils authUtils;

    //Ver perfil a traves del numero de socio
    @GetMapping("/profiles/{numeroSocio}")
    @CheckSecurity.Public.canRead
    public ProfileResponse getByNumeroSocio(@PathVariable String numeroSocio, WebRequest request) {
        if (authUtils.isAuthenticated()) {
            var currentUser = userService.getCurrentUser().getProfile();
            return profileAssembler.toResponse(currentUser, profileService.getByNumeroSocio(numeroSocio));
        }

        return profileAssembler.toResponse(profileService.getByNumeroSocio(numeroSocio));
    }
}
