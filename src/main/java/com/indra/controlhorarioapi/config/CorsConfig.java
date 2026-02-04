package com.indra.controlhorarioapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile({"dev", "test"}) // Solo se activa en perfiles dev o test
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        // Permite cualquier front levantado en localhost con cualquier puerto
                        .allowedOriginPatterns("http://localhost:[*]")
                        .allowedMethods("*")   // GET, POST, PUT, DELETE...
                        .allowedHeaders("*")   // cualquier header
                        .allowCredentials(true); // si usan cookies o auth headers
            }
        };
    }
}
