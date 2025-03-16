package com.skillboostfootball.backend_main_springboot.presentation.controllers.usuarios;

import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.application.useCases.usuarios.*;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.usuarios.UsuarioAssembler;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.usuarios.request.*;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.usuarios.response.UserResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class UsuarioController {
    private final UpdateProfileUseCase updateProfileUseCase;
    private final UpdateCoachProfileUseCase updateCoachProfileUseCase;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final UsuarioAssembler userAssembler;
    
    //Obtener el usuario actual
    @GetMapping("/user")
    @CheckSecurity.Protected.canManage
    public UserResponse getCurrentUser() {
        var user = getCurrentUserUseCase.execute();
        return userAssembler.toUserResponse(user);
    }
    
    //Actualizar usuario estándar
    @PutMapping("/user")
    @CheckSecurity.Protected.canManage
    public UserResponse updateUser(@Valid @RequestBody UpdateUserRequest request) {
        updateProfileUseCase.execute(request.getNombre(), request.getApellidos(), request.getBio(), request.getImage(),
            request.getEmail(), request.getPassword(), request.getEdad(), request.getPosicionPreferida());
        
        //Obtenemos el usuario actualizado para devolverlo completo
        var updatedUser = getCurrentUserUseCase.execute();
        return userAssembler.toUserResponse(updatedUser);
    }
    
    //Endpoint específico para actualizar usuario entrenador
    @PutMapping("/user/coach")
    @CheckSecurity.Entrenador.canAccess
    public UserResponse updateCoachUser(@Valid @RequestBody UpdateCoachUserRequest request) {
        updateCoachProfileUseCase.execute(request.getNombre(), request.getApellidos(), request.getBio(), request.getImage(),
            request.getEmail(), request.getPassword(), request.getEdad(), request.getEspecialidad(), request.getCertificaciones());
        
        var updatedUser = getCurrentUserUseCase.execute();
        return userAssembler.toUserResponse(updatedUser);
    }
}
