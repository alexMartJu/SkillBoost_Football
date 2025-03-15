package com.skillboostfootball.backend_main_springboot.application.useCases.logros;

import com.skillboostfootball.backend_main_springboot.domain.entities.logros.Logro;
import com.skillboostfootball.backend_main_springboot.domain.entities.logros.ProfileLogro;
import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.repositories.logros.LogroRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.logros.ProfileLogroRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.reservas.ReservaRepository;
import com.skillboostfootball.backend_main_springboot.application.applicationServices.NotificationService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VerifyLogrosUsuarioUseCase {
    
    private static final Logger logger = LoggerFactory.getLogger(VerifyLogrosUsuarioUseCase.class);
    private final LogroRepository logroRepository;
    private final ProfileLogroRepository profileLogroRepository;
    private final ReservaRepository reservaRepository;
    private final NotificationService notificationService;
    
    @Transactional
    public void execute(Usuario usuario) {
        
        Profile profile = usuario.getProfile();
        //Obtener el número total de reservas del usuario
        int totalReservas = reservaRepository.countByProfileId(profile.getId());
        //Obtener todos los logros ordenados por requisito
        List<Logro> logros = logroRepository.findLogrosOrdenadosPorRequisito();
        
        for (Logro logro : logros) {

            //Verificar si el usuario ya tiene este logro registrado
            Optional<ProfileLogro> profileLogroOpt = profileLogroRepository.findByProfileIdAndLogroId(
                profile.getId(), logro.getId());
            
            if (profileLogroOpt.isPresent()) {

                ProfileLogro profileLogro = profileLogroOpt.get();
                
                //Si ya está notificado, no hacer nada con este logro
                if (profileLogro.getNotificado()) {
                    continue;
                }
                profileLogro.actualizarProgreso(totalReservas);
                
                //Si alcanzó el requisito, marcar como notificado y enviar notificación
                if (totalReservas >= logro.getRequisitoEntrenamientos() && !profileLogro.getNotificado()) {
                    enviarNotificacionLogro(usuario, logro);
                    profileLogro.marcarComoNotificado();
                }
                profileLogroRepository.save(profileLogro);

            } else {
                //Crear nuevo registro de progreso
                ProfileLogro nuevoProfileLogro = ProfileLogro.builder()
                    .profileId(profile.getId())
                    .logroId(logro.getId())
                    .progresoActual(totalReservas)
                    .notificado(totalReservas >= logro.getRequisitoEntrenamientos())
                    .fechaLogro(totalReservas >= logro.getRequisitoEntrenamientos() ? LocalDateTime.now() : null)
                    .build();
                
                profileLogroRepository.save(nuevoProfileLogro);
                
                //Si alcanzó el requisito, enviar notificación
                if (totalReservas >= logro.getRequisitoEntrenamientos()) {
                    enviarNotificacionLogro(usuario, logro);
                }

            }
        }
    }
    
    private void enviarNotificacionLogro(Usuario usuario, Logro logro) {
        notificationService.sendAchievementNotification(usuario, logro)
            .subscribe(
                null,
                error -> logger.error("Error al enviar notificación de logro: {}", error.getMessage(), error)
            );

    }

}
