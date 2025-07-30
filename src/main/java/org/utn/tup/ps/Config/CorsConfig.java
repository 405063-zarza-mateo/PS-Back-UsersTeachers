package org.utn.tup.ps.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class CorsConfig {

    // Para Spring MVC
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        // Agregado http://localhost:80 para el contenedor
                        .allowedOrigins(
                                "http://localhost:4200",    // Angular dev
                                "https://localhost:4200",   // Angular dev HTTPS
                                "http://localhost",         // Frontend en puerto 80
                                "http://localhost:80",      // Frontend en puerto 80 (explícito)
                                "http://ps-front",          // Nombre del contenedor frontend
                                "http://ps-front:80"        // Contenedor frontend con puerto
                                , "https://elgalponcitoateneo.com.ar/"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .exposedHeaders("Authorization")
                        .allowCredentials(true);
            }
        };
    }

    // Para Spring Security
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Mismos orígenes que arriba
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:4200",    // Angular dev
                "https://localhost:4200",   // Angular dev HTTPS
                "http://localhost",         // Frontend en puerto 80
                "http://localhost:80",      // Frontend en puerto 80 (explícito)
                "http://ps-front",          // Nombre del contenedor frontend
                "http://ps-front:80"        // Contenedor frontend con puerto
                , "https://elgalponcitoateneo.com.ar/"

        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}