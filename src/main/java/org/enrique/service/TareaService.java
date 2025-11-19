package org.enrique.service;

import org.enrique.model.Tarea;
import org.enrique.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Capa de servicio para la gestión de la entidad Tarea.
 * Contiene la lógica de negocio central.
 */
@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    @Autowired
    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    /**
     * Encuentra todas las tareas.
     */
    public List<Tarea> findAllTareas() {
        return tareaRepository.findAll();
    }

    /**
     * Encuentra una tarea por su ID.
     */
    public Optional<Tarea> findTareaById(Integer id) {
        return tareaRepository.findById(id);
    }

    /**
     * Guarda una nueva tarea o actualiza una existente.
     */
    @Transactional
    public Tarea saveTarea(Tarea tarea) {
        // Lógica de validación o asignación de valores por defecto antes de guardar.
        return tareaRepository.save(tarea);
    }

    /**
     * Actualiza una tarea existente.
     */
    @Transactional
    public Tarea updateTarea(Integer id, Tarea tareaDetails) {
        Tarea existingTarea = tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con ID: " + id));

        // Actualización de campos
        existingTarea.setTitulo(tareaDetails.getTitulo());
        existingTarea.setDescripcion(tareaDetails.getDescripcion());
        existingTarea.setPrioridad(tareaDetails.getPrioridad());
        existingTarea.setEstado(tareaDetails.getEstado());
        existingTarea.setFechaLimite(tareaDetails.getFechaLimite());
        existingTarea.setProyecto(tareaDetails.getProyecto()); 

        return tareaRepository.save(existingTarea);
    }

    /**
     * Elimina una tarea por su ID.
     */
    @Transactional
    public void deleteTarea(Integer id) {
        if (!tareaRepository.existsById(id)) {
            throw new RuntimeException("Tarea no encontrada con ID: " + id);
        }
        tareaRepository.deleteById(id);
    }
}