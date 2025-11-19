package org.enrique.controller;

import org.enrique.model.Fichaje;
import org.enrique.service.FichajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller para la gestión de registros de fichaje.
 * Endpoint: /api/fichajes
 */
@RestController
@RequestMapping("/api/fichajes") 
public class FichajeController {

    private final FichajeService fichajeService;

    @Autowired
    public FichajeController(FichajeService fichajeService) {
        this.fichajeService = fichajeService;
    }

    /**
     * Obtiene una lista de todos los registros de fichaje.
     */
    @GetMapping
    public List<Fichaje> getAllFichajes() {
        return fichajeService.findAllFichajes();
    }

    /**
     * Obtiene un registro de fichaje por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Fichaje> getFichajeById(@PathVariable Integer id) {
        return fichajeService.findFichajeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todos los registros de fichaje para un usuario específico.
     * Endpoint: /api/fichajes/usuario/{idUsuario}
     */
    @GetMapping("/usuario/{idUsuario}")
    public List<Fichaje> getFichajesByUsuario(@PathVariable Integer idUsuario) {
        return fichajeService.findFichajesByUsuarioId(idUsuario);
    }

    /**
     * Crea un nuevo registro de fichaje (Entrada o Salida).
     */
    @PostMapping
    public ResponseEntity<Fichaje> createFichaje(@RequestBody Fichaje fichaje) {
        Fichaje savedFichaje = fichajeService.saveFichaje(fichaje);
        return new ResponseEntity<>(savedFichaje, HttpStatus.CREATED);
    }

    /**
     * Elimina un registro de fichaje por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFichaje(@PathVariable Integer id) {
        try {
            fichajeService.deleteFichaje(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}