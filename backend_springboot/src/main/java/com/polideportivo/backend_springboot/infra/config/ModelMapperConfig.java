package com.polideportivo.backend_springboot.infra.config;

import com.polideportivo.backend_springboot.api.model.usuario.UsuarioUpdate;
import com.polideportivo.backend_springboot.domain.model.Usuario;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        
        // Configuración específica del ModelMapper para mapear UserUpdate -> User
        var updateUserTypeMap = modelMapper.createTypeMap(UsuarioUpdate.class, Usuario.class);

        // Mapeo personalizado para la propiedad "bio".
        // Toma el valor de "UserUpdate.getBio()" y lo asigna a "User.getProfile().setBio()".
        updateUserTypeMap.<String>addMapping(UsuarioUpdate::getBio, (destination, value) -> destination.getProfile().setBio(value));
        // Mapeo personalizado para la propiedad "image".
        // Toma el valor de "UserUpdate.getImage()" y lo asigna a "User.getProfile().setImage()".
        updateUserTypeMap.<String>addMapping(UsuarioUpdate::getImage, (destination, value) -> destination.getProfile().setImage(value));
        // Mapeo personalizado para la propiedad "edad".
        // Toma el valor de "UserUpdate.getEdad()" y lo asigna a "User.getProfile().setEdad()".
        updateUserTypeMap.<Integer>addMapping(UsuarioUpdate::getEdad, (destination, value) -> destination.getProfile().setEdad(value));

        return modelMapper;
    }
}
