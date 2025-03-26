package com.skillboostfootball.backend_main_springboot.presentation.controllers.profiles;

import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.application.useCases.profiles.GetAllTutorsUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.profiles.GetAllClubPlayerUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.profiles.GetAllPlayersUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.profiles.GetAllSocialHelpPlayerUseCase;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.profiles.ProfileAssembler;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.profiles.response.ProfileWrapper;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class UserTypesProfileController {

    private final GetAllTutorsUseCase getAllTutorsUseCase;
    private final GetAllClubPlayerUseCase getAllClubPlayerUseCase;
    private final GetAllPlayersUseCase getAllPlayersUseCase;
    private final GetAllSocialHelpPlayerUseCase getAllSocialHelpPlayerUseCase;
    private final ProfileAssembler profileAssembler;
    
    //Listar todos los usuarios de tipo tutor
    @GetMapping("/profiles/tutores")
    @CheckSecurity.Admin.canAccess
    public ProfileWrapper getAllTutors() {
        var profiles = getAllTutorsUseCase.execute();
        return profileAssembler.toWrapper(profiles);
    }
    
    //Listar todos los usuarios de tipo jugador club
    @GetMapping("/profiles/jugadores-club")
    @CheckSecurity.Admin.canAccess
    public ProfileWrapper getAllClubPlayer() {
        var profiles = getAllClubPlayerUseCase.execute();
        return profileAssembler.toWrapper(profiles);
    }
    
    //Listar todos los usuarios de tipo jugador
    @GetMapping("/profiles/jugadores")
    @CheckSecurity.Admin.canAccess
    public ProfileWrapper getAllPlayers() {
        var profiles = getAllPlayersUseCase.execute();
        return profileAssembler.toWrapper(profiles);
    }
    
    //Listar todos los usuarios de tipo jugador social
    @GetMapping("/profiles/jugadores-sociales")
    @CheckSecurity.Admin.canAccess
    public ProfileWrapper getAllSocialHelpPlayer() {
        var profiles = getAllSocialHelpPlayerUseCase.execute();
        return profileAssembler.toWrapper(profiles);
    }
    
}
