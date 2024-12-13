package com.polideportivo.backend_springboot.api.security;

import com.polideportivo.backend_springboot.api.assembler.UsuarioAssembler;
import com.polideportivo.backend_springboot.api.model.usuario.UsuarioAuthenticate;
import com.polideportivo.backend_springboot.api.model.usuario.UsuarioRegister;
import com.polideportivo.backend_springboot.api.model.usuario.UsuarioResponse;
import com.polideportivo.backend_springboot.domain.service.ProfileService;
import com.polideportivo.backend_springboot.domain.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    
    private final UsuarioService userService;
    private final AuthService authService;
    private final UsuarioAssembler userAssembler;
    private final ProfileService profileService;

    // Register un nuevo usuario
    @PostMapping("/users")
    public ResponseEntity<UsuarioResponse> register(@Valid @RequestBody UsuarioRegister register) {
        var user = userAssembler.toEntity(register);
        var profile = profileService.createNewProfile(user, register.getNombre(), register.getApellidos());

        return ResponseEntity.ok(authService.register(userService.save(user, profile)));
    }

    // Login de un usuario
    @PostMapping("/users/login")
    public ResponseEntity<Object> authenticate(@Valid @RequestBody UsuarioAuthenticate authenticate) {
        return ResponseEntity.ok(authService.authenticate(authenticate));
    }
}