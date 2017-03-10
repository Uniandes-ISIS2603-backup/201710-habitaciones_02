/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dg.guarin20
 */

@XmlRootElement
public class ReservaDTO implements Serializable{
    
    private Long id;
    private Date fechaIniciodeEstadia;
    private Date fechaTerminacionEstadia;
    private Boolean cancelado;

public ReservaDTO()
{
    
}

public ReservaDTO(ReservaEntity entity)
{
    if(entity!= null)
    {
        this.id = entity.getId();
        this.fechaIniciodeEstadia = entity.getFechaInicio();
        this.fechaTerminacionEstadia = entity.getfechaTerminacionEstadia();
        this.cancelado = entity.getCancelado();
    }
}
public ReservaEntity toEntity()
{   
     
    ReservaEntity entity = new ReservaEntity(); 
    
    entity.setId(this.id);
    entity.setFechaInicioEstadia(this.fechaIniciodeEstadia);
    entity.setFechaTerminacionEstadia(this.fechaTerminacionEstadia);
    entity.setCancelado(this.cancelado);
    return entity;
    
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
    public void setCancelado (Boolean can)
    {
        this.cancelado = can;
    }
    
    
}
