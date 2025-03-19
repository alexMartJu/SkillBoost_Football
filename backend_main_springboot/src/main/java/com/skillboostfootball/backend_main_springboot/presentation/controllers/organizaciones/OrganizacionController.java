package com.skillboostfootball.backend_main_springboot.presentation.controllers.organizaciones;

import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.application.useCases.organizaciones.*;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.organizaciones.request.OrganizacionRequest;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.organizaciones.response.OrganizacionResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.organizaciones.response.OrganizacionWrapper;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.organizaciones.OrganizacionAssembler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class OrganizacionController {

    private final GetAllOrganizacionesUseCase getAllOrganizacionesUseCase;
    private final GetOrganizacionBySlugUseCase getOrganizacionBySlugUseCase;
    private final CreateOrganizacionUseCase createOrganizacionUseCase;
    private final OrganizacionAssembler assembler;
    
    //Listar todas las organizaciones
    @GetMapping("/organizaciones")
    @CheckSecurity.Public.canRead
    public OrganizacionWrapper getAllOrganizaciones() {
        var organizaciones = getAllOrganizacionesUseCase.execute();
        return assembler.toWrapper(organizaciones);
    }

    //Obtener una organización por slug
    @GetMapping("/organizaciones/{slug}")
    @CheckSecurity.Public.canRead
    public OrganizacionResponse getBySlug(@PathVariable String slug) {
        var organizacion = getOrganizacionBySlugUseCase.execute(slug);
        return assembler.toResponse(organizacion);
    }
    
    //Crear una organización (solo Admin)
    @PostMapping("/organizaciones")
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Admin.canAccess
    public OrganizacionResponse createOrganizacion(@Valid @RequestBody OrganizacionRequest request) {
        var organizacion = createOrganizacionUseCase.execute(request.getNombre(), request.getTipo(), request.getVision(), request.getLogo(),
            request.getDescripcion(), request.getMision(), request.getImpactoSocial(), request.getSitioWeb(), request.getContactoEmail(), request.getContactoTlf(),
            request.getDireccion(), request.getCiudad(), request.getPais(), request.getColaboracionDescripcion(), request.getFechaInicioColaboracion());
        return assembler.toResponse(organizacion);
    }
    
}
