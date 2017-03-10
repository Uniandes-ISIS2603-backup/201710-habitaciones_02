/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.entities;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
/**
 *
 * @author dg.guarin20
 */

@Entity

public class ReservaEntity implements Serializable{
    
    @ManyToOne
    private ViajeroEntity viajero;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaIniciodeEstadia;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaTerminacionEstadia;
    private Boolean cancelado;
   
    /**
     * @return id long
     */ 
    public Long getId()
    {
        return id;
    }
     /**
     * @param i long
     */ 
    public void setId(Long i)
    {
        this.id = i;
    }
     /**
     * @return fechaInicioEstadia Date
     */ 
    public Date getFechaInicio()
    {
        return fechaIniciodeEstadia;
    }
     /**
     * @param fechaIn Date
     */ 
    public void setFechaInicioEstadia(Date fechaIn)
    {
        this.fechaIniciodeEstadia = fechaIn;
    }
     /**
     * @return fechaTerminacionEstadia date
     */ 
    public Date getfechaTerminacionEstadia()
    {
        return fechaTerminacionEstadia;
        
    }
      /**
     * @param fechaT date
     */ 
    public void setFechaTerminacionEstadia(Date fechaT)
    {
        this.fechaTerminacionEstadia = fechaT;
    }
     /**
     * @return cancelad un boolean
     */ 
    public Boolean getCancelado()
    {
        return cancelado;
    }
      /**
     * @param can lo que cancela
     */ 
    public void setCancelado (boolean can)
    {
        this.cancelado = can;
    }
    
     @Override
    public boolean equals(Object obj) {
        if (this.getId() != null) {
            return this.getId().equals(((ReservaEntity) obj).getId());
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
