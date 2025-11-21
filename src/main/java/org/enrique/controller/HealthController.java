package org.enrique.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador simple para verificar el estado de la API.
 * Su existencia es crucial para que Spring Boot inicie el servidor web (Tomcat)
 * y mantenga el proceso corriendo dentro del contenedor Docker.
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    /**
     * Endpoint GET /health.
     * @return Un mensaje de estado OK.
     */
    @GetMapping
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("TaskFlow API is running successfully.");
    }
}   