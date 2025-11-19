package org.enrique.controller;

import org.enrique.model.Rol;
import org.enrique.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller para la gestión de roles.
 * Endpoint principal: /api/roles
 */
@RestController
@RequestMapping("/api/roles") 
public class RolController {

    private final RolService rolService;

    @Autowired
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    /**
     * Obtiene una lista de todos los roles.
     */
    @GetMapping
    public List<Rol> getAllRoles() {
        return rolService.findAllRoles();
    }

    /**
     * Obtiene un rol por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable Integer id) {
        return rolService.findRolById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo rol.
     */
    @PostMapping
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol) {
        Rol savedRol = rolService.saveRol(rol);
        return new ResponseEntity<>(savedRol, HttpStatus.CREATED);
    }

    /**
     * Elimina un rol por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Integer id) {
        try {
            rolService.deleteRol(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}