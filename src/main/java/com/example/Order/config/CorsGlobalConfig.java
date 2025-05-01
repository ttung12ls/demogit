package com.example.Order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsGlobalConfig {
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(List.of("http://localhost:3000")); // Cho phép React frontend
        corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // Các method được phép
        corsConfig.setAllowedHeaders(List.of("*")); // Cho phép tất cả headers
        corsConfig.setAllowCredentials(true); // Cho phép gửi cookie, token

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
