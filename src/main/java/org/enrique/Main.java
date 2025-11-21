package org.enrique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación TaskFlow API.
 * La anotación @SpringBootApplication es crucial para que
 * Spring Boot configure automáticamente el servidor web (Tomcat)
 * y escanee los componentes de la aplicación.
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}