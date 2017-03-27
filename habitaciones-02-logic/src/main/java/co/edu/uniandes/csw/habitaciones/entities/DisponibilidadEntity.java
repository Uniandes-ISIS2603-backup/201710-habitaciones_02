/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author b.gamba10
 */
@Entity
public class DisponibilidadEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicioEstadia;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaTerminacionEstadia;
    
    @ManyToOne
    private HabitacionEntity habitacion;

    /**
     * Retorna el id de la disponibilidad
     * @return 
     */
    public Long getId() {
        return id;
    }

    /**
     * Coloca el id de una disponibilidad
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }
    

    /**
     * Retorna la fecha de inicio de la estadia
     * @return 
     */
    public Date getFechaInicioEstadia() {
        return fechaInicioEstadia;
    }

    /**
     * Coloca la fecha de inicio de la estadia
     * @param fechaInicioEstadia 
     */
    public void setFechaInicioEstadia(Date fechaInicioEstadia) {
        this.fechaInicioEstadia = fechaInicioEstadia;
    }

    /**
     * Retorna la fecha de terminacion de la estadia
     * @return 
     */
    public Date getFechaTerminacionEstadia() {
        return fechaTerminacionEstadia;
    }

    /**
     * Coloca la fecha de terminacion de la estadia
     * @param fechaTerminacionEstadia 
     */
    public void setFechaTerminacionEstadia(Date fechaTerminacionEstadia) {
        this.fechaTerminacionEstadia = fechaTerminacionEstadia;
    }

    /**
     * Retorna la habitacion
     * @return 
     */
    public HabitacionEntity getHabitacion() {
        return habitacion;
    }

    /**
     * Coloca la habitacion
     * @param habitacion 
     */
    public void setHabitacion(HabitacionEntity habitacion) {
        this.habitacion = habitacion;
    }
    
    
    /**
     * 
     * @param obj
     * @return 
     */
     @Override
    public boolean equals(Object obj) {
        if (this.getId() != null) {
            return this.getId().equals(((DisponibilidadEntity) obj).getId());
        }
        return super.equals(obj);
    }

    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
}
