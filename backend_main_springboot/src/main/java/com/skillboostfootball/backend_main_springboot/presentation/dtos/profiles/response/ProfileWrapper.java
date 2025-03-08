package com.skillboostfootball.backend_main_springboot.presentation.dtos.profiles.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class ProfileWrapper {
    
    @JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
    private List<ProfileResponse> profiles;
    
}