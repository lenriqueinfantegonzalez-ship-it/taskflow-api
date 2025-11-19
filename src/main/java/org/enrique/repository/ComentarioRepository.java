package org.enrique.repository;

import org.enrique.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de Spring Data JPA para la entidad Comentario.
 * La clave primaria es Integer (id_comentario).
 */
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    
    /**
     * Busca todos los comentarios asociados a una tarea específica.
     * @param idTarea El ID de la tarea.
     * @return Una lista de comentarios.
     */
    List<Comentario> findByTareaIdTarea(Integer idTarea);
}