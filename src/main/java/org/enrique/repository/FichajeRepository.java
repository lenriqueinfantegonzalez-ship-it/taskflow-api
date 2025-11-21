package org.enrique.repository;

import org.enrique.model.Fichaje;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate; // Changed from java.util.Date to LocalDate
import java.util.List;

public interface FichajeRepository extends JpaRepository<Fichaje, Integer> {

    /**
     * Busca fichajes en un rango de fechas.
     * FIX: Se cambiaron los parámetros de Date a LocalDate para coincidir con la Entidad.
     */
    List<Fichaje> findByUsuarioIdUsuarioAndFechaBetween(
        Integer idUsuario, 
        LocalDate fechaInicio, 
        LocalDate fechaFin
    );

    /**
     * Busca todos los fichajes de un usuario.
     */
    List<Fichaje> findByUsuarioIdUsuario(Integer idUsuario);

}