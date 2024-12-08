package com.polideportivo.backend_springboot.api.controller;

import com.polideportivo.backend_springboot.api.assembler.UsuarioAssembler;
import com.polideportivo.backend_springboot.api.model.usuario.UsuarioResponse;
import com.polideportivo.backend_springboot.api.model.usuario.UsuarioUpdate;
import com.polideportivo.backend_springboot.api.security.authorization.CheckSecurity;
import com.polideportivo.backend_springboot.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsuarioController {
    
    private final UsuarioService userService;
    private final UsuarioAssembler userAssembler;

    //Obtener el usuario actual logueado
    @GetMapping("/user")
    @CheckSecurity.Protected.canManage
    public UsuarioResponse getCurrentUser() {
        var user = userService.getCurrentUser();
        return userAssembler.toResponse(user);
    }

    //Actualizar el usuario actual logueado
    @PutMapping("/user")
    @CheckSecurity.Protected.canManage
    public UsuarioResponse updateCurrentUser(@RequestBody UsuarioUpdate userUpdate) {
        var currentUser = userService.getCurrentUser();
        userAssembler.copyToEntity(userUpdate, currentUser);
        return userAssembler.toResponse(userService.saveUpdate(currentUser, currentUser.getProfile()));
    }
}