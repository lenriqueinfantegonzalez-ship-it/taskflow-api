package org.enrique.controller;

import org.enrique.model.Usuario;
import org.enrique.service.UsuarioService; // Importamos la nueva capa de servicio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller para la gestión de usuarios.
 * Utiliza UsuarioService para la lógica de negocio.
 */
@RestController
@RequestMapping("/api/users") // Usando '/api/users' para seguir el patrón de '/api/tasks'
public class UserController {

    private final UsuarioService usuarioService;

    /**
     * Inyección de dependencia (preferiblemente por constructor para componentes Spring).
     * @param usuarioService El servicio de negocio para usuarios.
     */
    @Autowired
    public UserController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Obtiene una lista de todos los usuarios.
     * GET /api/users
     * @return Lista de todos los objetos Usuario.
     */
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAllUsuarios();
    }

    /**
     * Obtiene un usuario por su ID.
     * GET /api/users/{id}
     * @param id ID del usuario (Integer).
     * @return Respuesta HTTP 200 con el Usuario o 404 si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        return usuarioService.findUsuarioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo usuario.
     * POST /api/users
     * @param usuario El objeto Usuario a crear.
     * @return El objeto Usuario guardado con código de estado 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        // La lógica de hashing de contraseña debería estar en el servicio antes de llamar a save.
        Usuario savedUsuario = usuarioService.saveUsuario(usuario);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    /**
     * Actualiza un usuario existente por su ID.
     * PUT /api/users/{id}
     * @param id ID del usuario a actualizar (Integer).
     * @param usuarioDetails Objeto Usuario con los datos a actualizar.
     * @return Respuesta HTTP 200 con el Usuario actualizado o 404 si no se encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioDetails) {
        try {
            Usuario updatedUsuario = usuarioService.updateUsuario(id, usuarioDetails);
            return ResponseEntity.ok(updatedUsuario);
        } catch (RuntimeException e) {
            // Maneja la excepción lanzada por el servicio si no se encuentra el usuario
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un usuario por su ID.
     * DELETE /api/users/{id}
     * @param id ID del usuario a eliminar (Integer).
     * @return Respuesta HTTP 204 (No Content) si es exitoso o 404 si no se encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // Maneja la excepción lanzada por el servicio si no se encuentra el usuario
            return ResponseEntity.notFound().build();
        }
    }
}