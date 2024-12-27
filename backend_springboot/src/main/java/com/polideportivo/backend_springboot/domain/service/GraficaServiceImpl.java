package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.model.Grafica;
import com.polideportivo.backend_springboot.domain.model.Usuario;
import com.polideportivo.backend_springboot.domain.repository.GraficaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GraficaServiceImpl implements GraficaService {
    
    private final GraficaRepository graficaRepository;
    private final UsuarioService userService;

    @Override
    public List<Grafica> getGraficasByAuthenticatedUserAndAnio(Integer año) {
        // Obtenemos el usuario autenticado
        Usuario currentUser = userService.getCurrentUser();

        // Obtenemos su número de socio desde el perfil
        String numeroSocio = currentUser.getProfile().getNumeroSocio();

        // Filtramos las gráficas por su número de socio y el año
        return graficaRepository.findByProfile_NumeroSocioAndAño(numeroSocio, año);
    }
}
