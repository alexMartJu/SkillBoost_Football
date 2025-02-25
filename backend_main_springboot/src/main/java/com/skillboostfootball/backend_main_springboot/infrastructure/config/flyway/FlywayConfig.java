package com.skillboostfootball.backend_main_springboot.infrastructure.config.flyway;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {
    
    @Bean
    public FlywayMigrationStrategy repairFlyway() {
        return flyway -> {
            // Repair Flyway before migration
            flyway.repair();
            // Then migrate
            flyway.migrate();
        };
    }
}
