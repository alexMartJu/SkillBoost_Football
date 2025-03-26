package com.skillboostfootball.backend_main_springboot.presentation.assemblers.organizaciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.organizaciones.Organizacion;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.organizaciones.response.OrganizacionResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.organizaciones.response.OrganizacionWrapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrganizacionAssembler {
    
    public OrganizacionResponse toResponse(Organizacion organizacion) {

        OrganizacionResponse response = new OrganizacionResponse();
        response.setId(organizacion.getId());
        response.setNombre(organizacion.getNombre());
        response.setSlug(organizacion.getSlug());
        response.setTipo(organizacion.getTipo());
        response.setVision(organizacion.getVision());
        response.setLogo(organizacion.getLogo());
        response.setDescripcion(organizacion.getDescripcion());
        response.setMision(organizacion.getMision());
        response.setImpactoSocial(organizacion.getImpactoSocial());
        response.setSitioWeb(organizacion.getSitioWeb());
        response.setContactoEmail(organizacion.getContactoEmail());
        response.setContactoTlf(organizacion.getContactoTlf());
        response.setDireccion(organizacion.getDireccion());
        response.setCiudad(organizacion.getCiudad());
        response.setPais(organizacion.getPais());
        response.setColaboracionDescripcion(organizacion.getColaboracionDescripcion());
        response.setFechaInicioColaboracion(organizacion.getFechaInicioColaboracion());
        
        return response;
    }
    
    public OrganizacionWrapper toWrapper(List<Organizacion> organizaciones) {
        List<OrganizacionResponse> responseList = organizaciones.stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
                
        return OrganizacionWrapper.builder()
            .organizaciones(responseList)
            .build();
    }
}
