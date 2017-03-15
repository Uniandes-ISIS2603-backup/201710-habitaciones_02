/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author s.cortes
 */
@Entity
public class ResenaEntity implements Serializable
{
    //----------------------------------------------------------------------------------------------------
    // ATRIBUTOS
    //----------------------------------------------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * El ID de la reseña
     */
    private Long id;
    
    /**
     * Calificacion de la reseña
     */
    private Double calificacion;
    
    /**
     * comentario de la reseña
     */
    private String comentario;
    
    /**
     * Viajero que publicó la reseña
     */
    @ManyToOne
    private ViajeroEntity viajero;
    
    /**
     * habitacion que fue reseñada
     */
    @ManyToOne
    private HabitacionEntity habitacion;
    
    
    //----------------------------------------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------------------------------------

    /**
     * restorna el id de la reseña
     * @return id de la reseña
     */
    public Long getId() {
        return id;
    }

    /**
     * Le asigna un id a la reseña
     * @param id el id a asignar
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * retorna la calificacion de la reseña
     * @return calificacion de la reseña
     */
    public Double getCalificacion() {
        return calificacion;
    }

    /**
     * asiga una calificacion a la reseña
     * @param calificacion la nueva calificacion 
     */
    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * retorna el comentario de la reseña
     * @return el comentario de la reseña
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * asigna un comentario a la reseña
     * @param comentario el nuevo comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * retorna el viajero creador de la reseña
     * @return el viajero 
     */
    public ViajeroEntity getViajero() {
        return viajero;
    }

    /**
     * asigna el ciajero a la entidad
     * @param viajero el viajero
     */
    public void setViajero(ViajeroEntity viajero) {
        this.viajero = viajero;
    }

    /**
     * retorna la habitacin de la reseña
     * @return la habitacion
     */
    public HabitacionEntity getHabitacion() {
        return habitacion;
    }

    /**
     * asigna la habitacin a la reserva
     * @param habitacion la habitacion
     */
    public void setHabitacion(HabitacionEntity habitacion) {
        this.habitacion = habitacion;
    }
    
    
    
}
