package com.skillboostfootball.backend_main_springboot.application.useCases.organizaciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.organizaciones.Organizacion;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.organizaciones.OrganizacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.organizaciones.OrganizacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllOrganizacionesUseCase {

    private final OrganizacionRepository organizacionRepository;
    
    @Transactional(readOnly = true)
    public List<Organizacion> execute() {

        List<Organizacion> organizaciones = organizacionRepository.findAllActive();
        
        if (organizaciones.isEmpty()) {
            throw new OrganizacionNotFoundException();
        }
        return organizaciones;
        
    }
}
