package org.enrique.service;

import org.enrique.model.Comentario;
import org.enrique.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime; // Necesario para la comprobación de fecha
import java.util.List;
import java.util.Optional;

/**
 * Capa de servicio para la gestión de la entidad Comentario.
 * Contiene la lógica de negocio central.
 */
@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    @Autowired
    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    /**
     * Encuentra todos los comentarios.
     */
    public List<Comentario> findAllComentarios() {
        return comentarioRepository.findAll();
    }

    /**
     * Encuentra un comentario por su ID.
     */
    public Optional<Comentario> findComentarioById(Integer id) {
        return comentarioRepository.findById(id);
    }

    /**
     * Encuentra todos los comentarios asociados a una tarea.
     */
    public List<Comentario> findComentariosByTareaId(Integer idTarea) {
        return comentarioRepository.findByTareaIdTarea(idTarea);
    }

    /**
     * Guarda un nuevo comentario.
     */
    @Transactional
    public Comentario saveComentario(Comentario comentario) {
        // Corrección: Usar getFechaHora() para comprobar la fecha
        if (comentario.getFechaHora() == null) {
            comentario.setFechaHora(LocalDateTime.now());
        }
        return comentarioRepository.save(comentario);
    }

    /**
     * Elimina un comentario por su ID.
     */
    @Transactional
    public void deleteComentario(Integer id) {
        if (!comentarioRepository.existsById(id)) {
            throw new RuntimeException("Comentario no encontrado con ID: " + id);
        }
        comentarioRepository.deleteById(id);
    }
}