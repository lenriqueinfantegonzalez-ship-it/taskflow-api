package org.enrique.repository;

import org.enrique.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de Spring Data JPA para la entidad Proyecto.
 * Proporciona métodos CRUD básicos y usa Integer como el tipo de la clave primaria.
 */
@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
    
    // Aquí se podrían añadir métodos de búsqueda personalizados, por ejemplo:
    // List<Proyecto> findByEstado(String estado);
    
}