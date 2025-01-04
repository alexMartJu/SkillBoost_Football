package com.polideportivo.backend_springboot.api.assembler;

import com.polideportivo.backend_springboot.api.model.pista.PistaWrapper;
import com.polideportivo.backend_springboot.api.model.reserva.ReservaResponse;
import com.polideportivo.backend_springboot.api.model.pista.PistaReservaStatusResponse;
import com.polideportivo.backend_springboot.api.model.pista.PistaReservadaResponse;
import com.polideportivo.backend_springboot.api.model.pista.PistaResponse;
import com.polideportivo.backend_springboot.domain.model.Pista;
import com.polideportivo.backend_springboot.domain.model.Reserva;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PistaAssembler {
    
    private final ModelMapper modelMapper;

    public PistaResponse toResponse(Pista pista) {
        System.out.println("Convirtiendo pista: " + pista); 
        var response = modelMapper.map(pista, PistaResponse.class);

        return response;
    }

    public PistaWrapper toCollectionModel(List<Pista> pistas) {

        var content = pistas.stream()
                .map(this::toResponse)
                .toList();

        return buildResponse(content);
    }

    private PistaWrapper buildResponse(List<PistaResponse> pistas) {
        return PistaWrapper.builder()
                .pistas(pistas)
                .build();
    }

    public PistaReservadaResponse toPistaReservadaResponse(Pista pista) {
        // Mapeo básico de la información de la pista
        PistaReservadaResponse response = modelMapper.map(pista, PistaReservadaResponse.class);

        // Añade las reservas asociadas a la pista
        List<ReservaResponse> reservas = pista.getReservas().stream()
                .map(this::toReservaResponse)
                .collect(Collectors.toList());

        response.setReservas(reservas);
        return response;
    }

    // Convierte una lista de entidades Pista en una lista de DTOs PistaReservadaResponse
    public List<PistaReservadaResponse> toPistaReservadaResponseList(List<Pista> pistas) {
        return pistas.stream()
                .map(this::toPistaReservadaResponse)
                .collect(Collectors.toList());
    }

    // Convierte una entidad Reserva en un DTO ReservaResponse
    private ReservaResponse toReservaResponse(Reserva reserva) {
        ReservaResponse response = new ReservaResponse();
        response.setPistaNombre(reserva.getPista().getNombre());
        response.setHora(reserva.getHorario().getHora());
        response.setFecha(reserva.getFecha().toString());
        return response;
    }

    public PistaReservaStatusResponse toPistaReservaStatusResponse(Reserva reserva) {
        PistaReservaStatusResponse response = new PistaReservaStatusResponse();
        response.setSlug(reserva.getPista().getSlug());  // Obtener el slug de la pista
        response.setReservedPista(true); // Siempre será true ya que está reservado
        return response;
    }
}
