package org.enrique.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entidad FICHAJE: Registra la entrada y salida de los usuarios para el control de tiempo.
 */
@Entity
@Table(name = "FICHAJE")
public class Fichaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fichaje")
    private Integer idFichaje;

    // Relación ManyToOne con USUARIO
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora_entrada")
    private LocalTime horaEntrada;

    @Column(name = "hora_salida")
    private LocalTime horaSalida;

    // Usamos BigDecimal para manejar la precisión decimal de las horas
    @Column(name = "horas_totales", precision = 5, scale = 2)
    private BigDecimal horasTotales;

    // --- Constructores ---
    public Fichaje() {
    }

    // --- Getters y Setters ---

    public Integer getIdFichaje() { return idFichaje; }
    public void setIdFichaje(Integer idFichaje) { this.idFichaje = idFichaje; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHoraEntrada() { return horaEntrada; }
    public void setHoraEntrada(LocalTime horaEntrada) { this.horaEntrada = horaEntrada; }

    public LocalTime getHoraSalida() { return horaSalida; }
    public void setHoraSalida(LocalTime horaSalida) { this.horaSalida = horaSalida; }

    public BigDecimal getHorasTotales() { return horasTotales; }
    public void setHorasTotales(BigDecimal horasTotales) { this.horasTotales = horasTotales; }
}