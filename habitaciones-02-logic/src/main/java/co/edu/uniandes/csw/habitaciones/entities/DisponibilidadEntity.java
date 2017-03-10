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
import javax.persistence.OneToOne;
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
    
    @OneToOne
    private HabitacionEntity habitacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicioEstadia() {
        return fechaInicioEstadia;
    }

    public void setFechaInicioEstadia(Date fechaInicioEstadia) {
        this.fechaInicioEstadia = fechaInicioEstadia;
    }

    public Date getFechaTerminacionEstadia() {
        return fechaTerminacionEstadia;
    }

    public void setFechaTerminacionEstadia(Date fechaTerminacionEstadia) {
        this.fechaTerminacionEstadia = fechaTerminacionEstadia;
    }
    
     @Override
    public boolean equals(Object obj) {
        if (this.getId() != null) {
            return this.getId().equals(((DisponibilidadEntity) obj).getId());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
}
