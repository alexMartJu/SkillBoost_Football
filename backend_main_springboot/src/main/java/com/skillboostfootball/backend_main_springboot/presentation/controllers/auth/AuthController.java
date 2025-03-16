package com.skillboostfootball.backend_main_springboot.presentation.controllers.auth;

import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.application.useCases.auth.*;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.usuarios.UsuarioAssembler;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.auth.request.*;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.auth.response.AuthResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.auth.response.TokenRefreshResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.usuarios.response.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class AuthController {
    private final RegisterStandardUserUseCase registerStandardUserUseCase;
    private final RegisterCoachUserUseCase registerCoachUserUseCase;
    private final RegisterClubPlayerUseCase registerClubPlayerUseCase;
    private final RegisterSocialHelpPlayerUseCase registerSocialHelpPlayerUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final LogoutUserUseCase logoutUserUseCase;
    private final UsuarioAssembler userAssembler;
    
    //Registro de usuario estandar (tutor o jugador)
    @PostMapping("/usuarios")
    public UserResponse registerStandardUser(@Valid @RequestBody RegisterStandardUserRequest request) {
        var user = registerStandardUserUseCase.execute(request.getEmail(), request.getPassword(),
            request.getNombre(),request.getApellidos(),request.getEdad());

        return userAssembler.toUserResponse(user);
    }
    
    //Registro de entrenador
    @PostMapping("/entrenadores/registro")
    @CheckSecurity.Admin.canAccess
    public UserResponse registerCoach(@Valid @RequestBody RegisterCoachRequest request) {
        var user = registerCoachUserUseCase.execute(request.getEmail(), request.getPassword(), request.getNombre(),
            request.getApellidos(), request.getEspecialidad(), request.getExperienciaAnios(), request.getCertificaciones()); 

        return userAssembler.toUserResponse(user);
    }
    
    //Registro de jugador de club
    @PostMapping("/jugadores-club/registro")
    @CheckSecurity.Admin.canAccess
    public UserResponse registerClubPlayer(@Valid @RequestBody RegisterClubPlayerRequest request) {
        var user = registerClubPlayerUseCase.execute(request.getEmail(), request.getPassword(), request.getNombre(),
            request.getApellidos(), request.getEdad(), request.getPosicionPreferida(), request.getClubOrigen());
        
        return userAssembler.toUserResponse(user);
    }

    //Registro de jugador de ayuda social
    @PostMapping("/jugadores-sociales/registro")
    @CheckSecurity.Admin.canAccess
    public UserResponse registerSocialHelpPlayer(@Valid @RequestBody RegisterSocialHelpPlayerRequest request) {
        var user = registerSocialHelpPlayerUseCase.execute(request.getEmail(), request.getPassword(), request.getNombre(),
            request.getApellidos(), request.getEdad(), request.getOrganizacionOrigen()
        );
        return userAssembler.toUserResponse(user);
    }
    
    //Inicio de sesión
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        var authResult = authenticateUserUseCase.execute(request.getEmail(), request.getPassword());
        
        //Obtener el usuario asociado al email para incluirlo en la respuesta
        var user = userAssembler.findUserByEmail(request.getEmail());
        return userAssembler.toAuthResponse(user, authResult.accessToken(), authResult.refreshToken());
    }
    
    //Refrescar token
    @PostMapping("/refresh")
    public TokenRefreshResponse refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        var refreshResult = refreshTokenUseCase.execute(request.getRefreshToken());
        
        return TokenRefreshResponse.builder()
            .accessToken(refreshResult.accessToken())
            .build();
    }
    
    //Cerrar sesión
    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> logout(@Valid @RequestBody RefreshTokenRequest request) {
        logoutUserUseCase.execute(request.getRefreshToken());
        return ResponseEntity.noContent().build();
    }
}
