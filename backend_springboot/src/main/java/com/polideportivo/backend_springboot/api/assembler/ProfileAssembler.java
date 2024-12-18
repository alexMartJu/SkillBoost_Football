package com.polideportivo.backend_springboot.api.assembler;

import com.polideportivo.backend_springboot.api.model.profile.ProfileResponse;
import com.polideportivo.backend_springboot.domain.model.Profile;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileAssembler {
    
    private final ModelMapper modelMapper;

    public ProfileResponse toResponse(Profile profile) {
        return modelMapper.map(profile, ProfileResponse.class);
    }

    public ProfileResponse toResponse(Profile current, Profile profile) {
        var response = toResponse(profile);

        return response;
    }
}
