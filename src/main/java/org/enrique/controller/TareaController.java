package org.enrique.controller;

import org.enrique.model.Tarea;
import org.enrique.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller para la gestión de tareas.
 * Endpoint: /api/tareas
 */
@RestController
@RequestMapping("/api/tareas") 
public class TareaController {

    private final TareaService tareaService;

    @Autowired
    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    /**
     * Obtiene una lista de todas las tareas.
     */
    @GetMapping
    public List<Tarea> getAllTareas() {
        return tareaService.findAllTareas();
    }

    /**
     * Obtiene una tarea por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getTareaById(@PathVariable Integer id) {
        return tareaService.findTareaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea una nueva tarea.
     */
    @PostMapping
    public ResponseEntity<Tarea> createTarea(@RequestBody Tarea tarea) {
        Tarea savedTarea = tareaService.saveTarea(tarea);
        return new ResponseEntity<>(savedTarea, HttpStatus.CREATED);
    }

    /**
     * Actualiza una tarea existente por su ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> updateTarea(@PathVariable Integer id, @RequestBody Tarea tareaDetails) {
        try {
            Tarea updatedTarea = tareaService.updateTarea(id, tareaDetails);
            return ResponseEntity.ok(updatedTarea);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina una tarea por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarea(@PathVariable Integer id) {
        try {
            tareaService.deleteTarea(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}