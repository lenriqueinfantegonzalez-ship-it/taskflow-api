package org.enrique.repository;

import org.enrique.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    Usuario findByEmail(String email);

    // NUEVO: Buscar usuarios por nombre (puede haber varios 'Luis')
    List<Usuario> findByNombre(String nombre);
}