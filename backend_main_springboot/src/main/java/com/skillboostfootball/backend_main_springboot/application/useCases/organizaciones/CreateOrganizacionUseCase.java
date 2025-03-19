package com.skillboostfootball.backend_main_springboot.application.useCases.organizaciones;

import com.skillboostfootball.backend_main_springboot.application.applicationServices.SlugService;
import com.skillboostfootball.backend_main_springboot.domain.entities.organizaciones.Organizacion;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.organizaciones.OrganizacionNameTakenException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.organizaciones.OrganizacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateOrganizacionUseCase {

    private final OrganizacionRepository organizacionRepository;
    private final SlugService slugService;
    
    @Transactional
    public Organizacion execute(String nombre, String tipo, String vision, String logo, String descripcion, String mision, String impactoSocial, 
        String sitioWeb, String contactoEmail, String contactoTlf, String direccion, String ciudad, String pais, String colaboracionDescripcion, 
        LocalDate fechaInicioColaboracion) {

        if (organizacionRepository.existsByNombre(nombre)) {
            throw new OrganizacionNameTakenException();
        }
        
        String slug = slugService.generateSlug(nombre);
        Organizacion organizacion = Organizacion.builder()
            .nombre(nombre)
            .slug(slug)
            .tipo(tipo)
            .vision(vision)
            .logo(logo)
            .descripcion(descripcion)
            .mision(mision)
            .impactoSocial(impactoSocial)
            .sitioWeb(sitioWeb)
            .contactoEmail(contactoEmail)
            .contactoTlf(contactoTlf)
            .direccion(direccion)
            .ciudad(ciudad)
            .pais(pais)
            .colaboracionDescripcion(colaboracionDescripcion)
            .fechaInicioColaboracion(fechaInicioColaboracion)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        return organizacionRepository.save(organizacion);

    }
}
