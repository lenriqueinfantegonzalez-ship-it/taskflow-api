package org.enrique.service;

import org.enrique.model.Fichaje;
import org.enrique.repository.FichajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Capa de servicio para la gestión de la entidad Fichaje.
 * Incluye la lógica para registrar entrada y salida.
 */
@Service
public class FichajeService {

    private final FichajeRepository fichajeRepository;

    @Autowired
    public FichajeService(FichajeRepository fichajeRepository) {
        this.fichajeRepository = fichajeRepository;
    }

    /**
     * Encuentra todos los registros de fichaje.
     */
    public List<Fichaje> findAllFichajes() {
        return fichajeRepository.findAll();
    }

    /**
     * Encuentra un registro de fichaje por su ID.
     */
    public Optional<Fichaje> findFichajeById(Integer id) {
        return fichajeRepository.findById(id);
    }

    /**
     * Encuentra registros de fichaje por el ID del usuario.
     */
    public List<Fichaje> findFichajesByUsuarioId(Integer idUsuario) {
        return fichajeRepository.findByUsuarioIdUsuario(idUsuario);
    }

    /**
     * Guarda un nuevo registro de fichaje (generalmente una nueva entrada).
     */
    @Transactional
    public Fichaje saveFichaje(Fichaje fichaje) {
        // Lógica de validación antes de guardar
        return fichajeRepository.save(fichaje);
    }

    /**
     * Elimina un registro de fichaje por su ID.
     */
    @Transactional
    public void deleteFichaje(Integer id) {
        if (!fichajeRepository.existsById(id)) {
            throw new RuntimeException("Registro de Fichaje no encontrado con ID: " + id);
        }
        fichajeRepository.deleteById(id);
    }
}