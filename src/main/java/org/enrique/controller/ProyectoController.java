package org.enrique.controller;

import org.enrique.model.Proyecto;
import org.enrique.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller para la gestión de proyectos.
 * Utiliza ProyectoService para la lógica de negocio.
 */
@RestController
@RequestMapping("/api/proyectos") 
public class ProyectoController {

    private final ProyectoService proyectoService;

    @Autowired
    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    /**
     * Obtiene una lista de todos los proyectos.
     * GET /api/proyectos
     */
    @GetMapping
    public List<Proyecto> getAllProyectos() {
        return proyectoService.findAllProyectos();
    }

    /**
     * Obtiene un proyecto por su ID.
     * GET /api/proyectos/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> getProyectoById(@PathVariable Integer id) {
        return proyectoService.findProyectoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo proyecto.
     * POST /api/proyectos
     */
    @PostMapping
    public ResponseEntity<Proyecto> createProyecto(@RequestBody Proyecto proyecto) {
        Proyecto savedProyecto = proyectoService.saveProyecto(proyecto);
        return new ResponseEntity<>(savedProyecto, HttpStatus.CREATED);
    }

    /**
     * Actualiza un proyecto existente por su ID.
     * PUT /api/proyectos/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> updateProyecto(@PathVariable Integer id, @RequestBody Proyecto proyectoDetails) {
        try {
            Proyecto updatedProyecto = proyectoService.updateProyecto(id, proyectoDetails);
            return ResponseEntity.ok(updatedProyecto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un proyecto por su ID.
     * DELETE /api/proyectos/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer id) {
        try {
            proyectoService.deleteProyecto(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}