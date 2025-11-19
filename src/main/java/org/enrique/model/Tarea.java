package org.enrique.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidad TAREA: Representa una unidad de trabajo asignada a un Proyecto.
 * Reemplaza la antigua entidad Task y se ajusta a la tabla TAREA.
 */
@Entity
@Table(name = "TAREA")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarea")
    private Integer idTarea;

    // Relación ManyToOne con PROYECTO
    @ManyToOne
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto;

    @Column(name = "titulo", length = 150)
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "prioridad", length = 20)
    private String prioridad; // Ejemplo: ALTA, MEDIA, BAJA

    @Column(name = "estado", length = 20)
    private String estado; // Ejemplo: PENDIENTE, EN_CURSO, COMPLETADA

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion; // Usamos LocalDateTime para TIMESTAMP

    @Column(name = "fecha_limite")
    private LocalDate fechaLimite; // Usamos LocalDate para DATE

    // ---------------------------------
    // Nota: Las relaciones ManyToMany con USUARIO (USUARIO_TAREA) 
    // y OneToMany con COMENTARIO se añadirán en pasos posteriores para 
    // simplificar la implementación inicial.
    // ---------------------------------

    // --- Constructores ---
    public Tarea() {
    }
    
    // --- Getters y Setters ---

    public Integer getIdTarea() { return idTarea; }
    public void setIdTarea(Integer idTarea) { this.idTarea = idTarea; }

    public Proyecto getProyecto() { return proyecto; }
    public void setProyecto(Proyecto proyecto) { this.proyecto = proyecto; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public LocalDate getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDate fechaLimite) { this.fechaLimite = fechaLimite; }
}