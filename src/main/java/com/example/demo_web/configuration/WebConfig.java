package com.example.demo_web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for defining CORS settings in the application.
 *
 * <p>This class enables Cross-Origin Resource Sharing (CORS) for specific
 * endpoints, allowing communication between the frontend and backend
 * applications hosted on different origins.</p>
 */
@Configuration
public class WebConfig {

    /**
     * Defines a {@link WebMvcConfigurer} bean to customize CORS mappings.
     *
     * @return a {@link WebMvcConfigurer} instance with customized CORS settings.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            /**
             * Configures CORS mappings for the application.
             *
             * <p>Specifies allowed origins, HTTP methods, and URL patterns for
             * which cross-origin requests are permitted.</p>
             *
             * @param registry the {@link CorsRegistry} used to register CORS mappings.
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/contract/**")
                        .allowedOrigins("http://localhost:4200") // Replace with your frontend URL
                        .allowedMethods("GET", "POST", "DELETE");
            }
        };
    }
}
