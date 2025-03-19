package com.skillboostfootball.backend_main_springboot.application.useCases.organizaciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.organizaciones.Organizacion;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.organizaciones.OrganizacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.organizaciones.OrganizacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetOrganizacionBySlugUseCase {

    private final OrganizacionRepository organizacionRepository;
    
    @Transactional(readOnly = true)
    public Organizacion execute(String slug) {

        return organizacionRepository.findBySlug(slug).orElseThrow(OrganizacionNotFoundException::new);
    }

}