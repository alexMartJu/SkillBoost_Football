package com.skillboostfootball.backend_main_springboot.presentation.controllers.profiles;

import com.skillboostfootball.backend_main_springboot.application.useCases.profiles.GetProfileByNumeroEntrenadorUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.profiles.GetProfileByNumeroSocioUseCase;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.profiles.ProfileAssembler;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.profiles.response.ProfileResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProfileController {
    private final GetProfileByNumeroSocioUseCase getProfileByNumeroSocioUseCase;
    private final GetProfileByNumeroEntrenadorUseCase getProfileByNumeroEntrenadorUseCase;
    private final ProfileAssembler profileAssembler;
    
    //Listar perfil por numero de socio (todos los usuarios menos entrenadores y administradores)
    @GetMapping("/profiles/{numeroSocio}")
    public ProfileResponse getProfileByNumeroSocio(@PathVariable String numeroSocio) {
        var profile = getProfileByNumeroSocioUseCase.execute(numeroSocio);
        return profileAssembler.toProfileResponse(profile);
    }

    //Listar perfil por numeero de entrenador (solo entrenadores)
    @GetMapping("/profiles/entrenadores/{numeroEntrenador}")
    public ProfileResponse getProfileByNumeroEntrenador(@PathVariable String numeroEntrenador) {
        var profile = getProfileByNumeroEntrenadorUseCase.execute(numeroEntrenador);
        return profileAssembler.toProfileResponse(profile);
    }
}
