package com.skillboostfootball.backend_main_springboot.presentation.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {
    
    private String status;
    private String message;
    private Map<String, Object> errors;
    
}
