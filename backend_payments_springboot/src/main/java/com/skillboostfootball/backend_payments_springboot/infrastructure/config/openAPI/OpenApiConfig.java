package com.skillboostfootball.backend_payments_springboot.infrastructure.config.openAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("SkillBoost Football Payment API")
                .version("3.0.0")
                .description("Payment processing API for SkillBoost Football platform"));
    }
}
