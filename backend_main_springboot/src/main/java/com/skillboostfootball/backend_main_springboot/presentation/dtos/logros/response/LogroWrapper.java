package com.skillboostfootball.backend_main_springboot.presentation.dtos.logros.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class LogroWrapper {
    
    @JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
    private List<LogroResponse> logros;
}
