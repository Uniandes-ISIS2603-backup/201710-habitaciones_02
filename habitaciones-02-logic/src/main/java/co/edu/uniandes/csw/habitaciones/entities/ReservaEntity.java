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
import javax.persistence.OneToOne;
import javax.persistence.TemporalType;

/**
 *
 * @author dg.guarin20
 */

@Entity
public class ReservaEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date fechaIniciodeEstadia;
    
    @Temporal(TemporalType.DATE)
    private Date fechaTerminacionEstadia;
    
    private Boolean cancelado;
    
    @ManyToOne
    private ViajeroEntity viajero;
    
    @ManyToOne
    private AnfitrionEntity anfitrion;
    
    @ManyToOne
    private HabitacionEntity habitacion;
    
    @OneToOne(mappedBy = "reserva")
    private PagoEntity pago;
    
    public ViajeroEntity getviajero()
    {
        return viajero;
    }
    public void setViajero(ViajeroEntity v)
    {
        viajero =v;
    }
    public AnfitrionEntity getanfitrion()
    {
        return anfitrion;
    }
     public void setAnfitrion(AnfitrionEntity an)
    {
        anfitrion =an;
    }
    public HabitacionEntity getHabitacion()
    {
        return habitacion;
        
    }
     public void setHabitacion(HabitacionEntity h)
    {
        habitacion = h;
    }
    public PagoEntity getPago()
    {
        return pago;
    }
     public void setPago(PagoEntity p)
    {
        pago = p;
    }
   
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
    public void setId(Long id)
    {
        this.id = id;
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
    public void setFechaInicioEstadia(Date fechaIniciodeEstadia)
    {
        this.fechaIniciodeEstadia = fechaIniciodeEstadia;
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
    public void setFechaTerminacionEstadia(Date fechaTerminacionEstadia)
    {
        this.fechaTerminacionEstadia = fechaTerminacionEstadia;
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
    public void setCancelado (Boolean cancelado)
    {
       this.cancelado = cancelado;
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
