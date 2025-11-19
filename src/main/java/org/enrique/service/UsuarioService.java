package org.enrique.service;

import org.enrique.model.Usuario;
import org.enrique.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Capa de servicio para la gestión de la entidad Usuario.
 * Contiene la lógica de negocio central para las operaciones CRUD.
 */
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Inyección de dependencia del repositorio.
     * @param usuarioRepository El repositorio JPA para la entidad Usuario.
     */
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Encuentra todos los usuarios.
     * @return Una lista de todos los objetos Usuario.
     */
    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Encuentra un usuario por su ID.
     * @param id El ID del usuario.
     * @return Un Optional que contiene el usuario si se encuentra, o vacío en caso contrario.
     */
    public Optional<Usuario> findUsuarioById(Integer id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Guarda un nuevo usuario o actualiza uno existente.
     * En una aplicación real, aquí se realizaría el hashing de contraseñas.
     * @param usuario El objeto Usuario a guardar.
     * @return El objeto Usuario guardado.
     */
    @Transactional
    public Usuario saveUsuario(Usuario usuario) {
        // Lógica de negocio: Por ejemplo, validar campos, hashear contraseña, etc.
        // Aquí se simula el guardado directo:
        return usuarioRepository.save(usuario);
    }

    /**
     * Actualiza un usuario existente con nuevos detalles.
     * @param id El ID del usuario a actualizar.
     * @param usuarioDetails El objeto Usuario con los datos de actualización.
     * @return El usuario actualizado.
     * @throws RuntimeException si el usuario no es encontrado.
     */
    @Transactional
    public Usuario updateUsuario(Integer id, Usuario usuarioDetails) {
        Usuario existingUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        // Actualización de campos
        existingUsuario.setNombre(usuarioDetails.getNombre());
        existingUsuario.setApellido(usuarioDetails.getApellido());
        existingUsuario.setEmail(usuarioDetails.getEmail());
        existingUsuario.setTelefono(usuarioDetails.getTelefono());
        existingUsuario.setContrasenaHash(usuarioDetails.getContrasenaHash());
        existingUsuario.setActivo(usuarioDetails.getActivo());
        existingUsuario.setRol(usuarioDetails.getRol());

        return usuarioRepository.save(existingUsuario);
    }

    /**
     * Elimina un usuario por su ID.
     * @param id El ID del usuario a eliminar.
     */
    @Transactional
    public void deleteUsuario(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }
}