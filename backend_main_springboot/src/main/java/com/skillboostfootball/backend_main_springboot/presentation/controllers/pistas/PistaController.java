package com.skillboostfootball.backend_main_springboot.presentation.controllers.pistas;

import com.skillboostfootball.backend_main_springboot.application.useCases.pistas.*;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.pistas.request.PistaRequest;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.pistas.response.PistaResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.pistas.response.PistaWrapper;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.pistas.PistaAssembler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PistaController {
    
    private final GetAllPistasUseCase getAllPistasUseCase;
    private final GetPistaBySlugUseCase getPistaBySlugUseCase;
    private final CreatePistaUseCase createPistaUseCase;
    private final UpdatePistaUseCase updatePistaUseCase;
    private final DeletePistaUseCase deletePistaUseCase;
    private final PistaAssembler assembler;
    
    //Listar todas las pistas
    @GetMapping("/pistas")
    public PistaWrapper getAllPistas() {
        var pistas = getAllPistasUseCase.execute();
        return assembler.toWrapper(pistas);
    }

    //Listar una pista
    @GetMapping("/pistas/{slug}")
    public PistaResponse getBySlug(@PathVariable String slug) {
        var pista = getPistaBySlugUseCase.execute(slug);
        return assembler.toResponse(pista);
    }
    
    //Crear una pista
    @PostMapping("/pistas")
    @ResponseStatus(HttpStatus.CREATED)
    public PistaResponse createPista(@Valid @RequestBody PistaRequest request) {
        var pista = createPistaUseCase.execute(
            request.getNombre(), 
            request.getDescripcion(), 
            request.getTipo(), 
            request.getDimensiones(), 
            request.getCapacidad()
        );
        return assembler.toResponse(pista);
    }
    
    //Actualizar una pista
    @PutMapping("/pistas/{slug}")
    public PistaResponse updatePista(@PathVariable String slug, @Valid @RequestBody PistaRequest request) {
        var pista = updatePistaUseCase.execute(
            slug, 
            request.getNombre(), 
            request.getDescripcion(), 
            request.getTipo(), 
            request.getDimensiones(), 
            request.getCapacidad()
        );
        return assembler.toResponse(pista);
    }
    
    //Eliminar una pista
    @DeleteMapping("/pistas/{slug}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletePista(@PathVariable String slug) {
        deletePistaUseCase.execute(slug);
        return ResponseEntity.noContent().build();
    }
}
