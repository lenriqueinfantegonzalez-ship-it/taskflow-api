package org.enrique.service;

import org.enrique.model.Proyecto;
import org.enrique.repository.ProyectoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Capa de servicio para la gestión de la entidad Proyecto.
 * Contiene la lógica de negocio central para las operaciones CRUD.
 */
@Service
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;

    public ProyectoService(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    /**
     * Encuentra todos los proyectos.
     * @return Una lista de todos los objetos Proyecto.
     */
    public List<Proyecto> findAllProyectos() {
        return proyectoRepository.findAll();
    }

    /**
     * Encuentra un proyecto por su ID.
     * @param id El ID del proyecto.
     * @return Un Optional que contiene el proyecto si se encuentra, o vacío en caso contrario.
     */
    public Optional<Proyecto> findProyectoById(Integer id) {
        return proyectoRepository.findById(id);
    }

    /**
     * Guarda un nuevo proyecto o actualiza uno existente.
     * @param proyecto El objeto Proyecto a guardar.
     * @return El objeto Proyecto guardado.
     */
    @Transactional
    public Proyecto saveProyecto(Proyecto proyecto) {
        // Lógica: Podríamos añadir validación de fechas o normalización del estado aquí.
        return proyectoRepository.save(proyecto);
    }

    /**
     * Actualiza un proyecto existente con nuevos detalles.
     * @param id El ID del proyecto a actualizar.
     * @param proyectoDetails El objeto Proyecto con los datos de actualización.
     * @return El proyecto actualizado.
     * @throws RuntimeException si el proyecto no es encontrado.
     */
    @Transactional
    public Proyecto updateProyecto(Integer id, Proyecto proyectoDetails) {
        Proyecto existingProyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + id));

        // Actualización de campos
        existingProyecto.setNombre(proyectoDetails.getNombre());
        existingProyecto.setDescripcion(proyectoDetails.getDescripcion());
        // La fecha de creación no se suele actualizar
        existingProyecto.setFechaLimite(proyectoDetails.getFechaLimite());
        existingProyecto.setEstado(proyectoDetails.getEstado());

        return proyectoRepository.save(existingProyecto);
    }

    /**
     * Elimina un proyecto por su ID.
     * @param id El ID del proyecto a eliminar.
     */
    @Transactional
    public void deleteProyecto(Integer id) {
        if (proyectoRepository.existsById(id)) {
            proyectoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Proyecto no encontrado con ID: " + id);
        }
    }
}