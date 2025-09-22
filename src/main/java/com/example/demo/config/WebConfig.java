package com.example.demo.config;

import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;

@Configuration
<<<<<<< HEAD
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:5173",
                        "http://localhost:8085"
                )
=======
public class WebConfig implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {

    @Override
    public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8085", "http://localhost:5174")
>>>>>>> 4e7d5b834dde51ffd0472257dc19d381d7113975
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
<<<<<<< HEAD
=======

    @PreDestroy
    public void cleanup() {
        try {
            Class<?> clazz = Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread");
            clazz.getMethod("checkedShutdown").invoke(null);
        } catch (ClassNotFoundException e) {
            // class not available in newer MySQL connector → ignore
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
>>>>>>> 4e7d5b834dde51ffd0472257dc19d381d7113975
}


