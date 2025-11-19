package org.enrique.repository;

import org.enrique.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Usuario.
 * Hereda de JpaRepository para obtener automáticamente los métodos CRUD.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    /**
     * Buscar un usuario por su nombre de usuario (username).
     * Spring Data JPA genera la consulta automáticamente.
     */
    Usuario findByUsername(String username);
}