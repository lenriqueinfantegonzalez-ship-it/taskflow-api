package org.enrique.repository;

import org.enrique.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de Spring Data JPA para la entidad Tarea.
 * La clave primaria es Integer (id_tarea).
 */
@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> {
    // Spring Data JPA provee los métodos CRUD básicos.
    
    // Aquí se podrían añadir métodos de búsqueda personalizados, por ejemplo:
    // List<Tarea> findByProyectoIdProyecto(Integer idProyecto);
    // List<Tarea> findByEstado(String estado);
}