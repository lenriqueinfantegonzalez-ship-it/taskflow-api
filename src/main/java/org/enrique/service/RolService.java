package org.enrique.service;

import org.enrique.model.Rol;
import org.enrique.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Capa de servicio para la gestión de la entidad Rol.
 * Contiene la lógica de negocio para los roles.
 */
@Service
public class RolService {

    private final RolRepository rolRepository;

    @Autowired
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    /**
     * Encuentra todos los roles disponibles.
     */
    public List<Rol> findAllRoles() {
        return rolRepository.findAll();
    }

    /**
     * Encuentra un rol por su ID.
     */
    public Optional<Rol> findRolById(Integer id) {
        return rolRepository.findById(id);
    }

    /**
     * Guarda un nuevo rol (generalmente solo para administración).
     */
    @Transactional
    public Rol saveRol(Rol rol) {
        return rolRepository.save(rol);
    }

    /**
     * Elimina un rol por su ID.
     */
    @Transactional
    public void deleteRol(Integer id) {
        if (!rolRepository.existsById(id)) {
            throw new RuntimeException("Rol no encontrado con ID: " + id);
        }
        rolRepository.deleteById(id);
    }
}