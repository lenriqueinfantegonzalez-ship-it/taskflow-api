package org.enrique.repository;

import org.enrique.model.Fichaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

/**
 * Repositorio de Spring Data JPA para la entidad Fichaje (Time Record).
 * La clave primaria es Integer (id_fichaje).
 */
@Repository
public interface FichajeRepository extends JpaRepository<Fichaje, Integer> {
    
    /**
     * Busca todos los registros de fichaje para un usuario específico.
     * @param idUsuario El ID del usuario.
     * @return Una lista de registros de fichaje.
     */
    List<Fichaje> findByUsuarioIdUsuario(Integer idUsuario);

    /**
     * Busca registros de fichaje para un usuario en un rango de fechas.
     * Nota: Asume que los campos de fecha en Fichaje son de tipo Date o LocalDateTime.
     */
    List<Fichaje> findByUsuarioIdUsuarioAndFechaFichajeBetween(Integer idUsuario, Date startDate, Date endDate);
}