package org.enrique.controller;

import org.enrique.model.Comentario;
import org.enrique.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller para la gestión de comentarios.
 * Endpoint principal: /api/comentarios
 */
@RestController
@RequestMapping("/api/comentarios") 
public class ComentarioController {

    private final ComentarioService comentarioService;

    @Autowired
    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    /**
     * Obtiene una lista de todos los comentarios (general).
     */
    @GetMapping
    public List<Comentario> getAllComentarios() {
        return comentarioService.findAllComentarios();
    }

    /**
     * Obtiene un comentario por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getComentarioById(@PathVariable Integer id) {
        return comentarioService.findComentarioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todos los comentarios para una tarea específica.
     * Endpoint: /api/comentarios/tarea/{idTarea}
     */
    @GetMapping("/tarea/{idTarea}")
    public List<Comentario> getComentariosByTarea(@PathVariable Integer idTarea) {
        return comentarioService.findComentariosByTareaId(idTarea);
    }

    /**
     * Crea un nuevo comentario.
     */
    @PostMapping
    public ResponseEntity<Comentario> createComentario(@RequestBody Comentario comentario) {
        Comentario savedComentario = comentarioService.saveComentario(comentario);
        return new ResponseEntity<>(savedComentario, HttpStatus.CREATED);
    }

    /**
     * Elimina un comentario por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Integer id) {
        try {
            comentarioService.deleteComentario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}