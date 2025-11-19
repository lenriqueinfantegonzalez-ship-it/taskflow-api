package org.enrique.repository;

import org.enrique.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de Spring Data JPA para la entidad Rol.
 * Proporciona métodos CRUD básicos y usa Integer como el tipo de la clave primaria.
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    
    // Spring Data JPA permite buscar por campos automáticamente
    // Ejemplo: Optional<Rol> findByNombreRol(String nombreRol);
    
}