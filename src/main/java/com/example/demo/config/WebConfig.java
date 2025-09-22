package com.example.demo.config;

import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:5173",
                        "http://localhost:8085"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    // Prevent MySQL AbandonedConnectionCleanupThread memory leak
    @PreDestroy
    public void cleanup() {
        try {
            Class<?> clazz = Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread");
            clazz.getMethod("checkedShutdown").invoke(null);
            System.out.println("MySQL cleanup thread shut down successfully.");
        } catch (ClassNotFoundException e) {
            // MySQL driver does not have the cleanup class (newer versions) → ignore
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
