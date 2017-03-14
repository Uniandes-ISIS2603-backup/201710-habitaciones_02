/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.PagoEntity;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import java.awt.List;
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
    private Date fechaInicio;
    private Date fechaTerminacion;
    private Boolean cancelado;
    
public ReservaDTO()
{
    
}

public ReservaDTO(ReservaEntity entity)
{
    if(entity!= null)
    {
        this.id = entity.getId();
        this.fechaInicio = entity.getFechaInicio();
        this.fechaTerminacion = entity.getFechaTerminacion();
        this.cancelado = entity.getCancelado();
    }
}
public ReservaEntity toEntity()
{   
    ReservaEntity entity = new ReservaEntity(); 
    entity.setId(this.getId());
    entity.setFechaInicio(this.getFechaInicio());
    entity.setFechaTerminacion(this.getFechaTerminacion());
    entity.setCancelado(this.getCancelado());
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
        return fechaInicio;
    }
     /**
     * @param fechaIn Date
     */ 
    public void setFechaInicio(Date fechaIn)
    {
        this.fechaInicio = fechaIn;
    }
     /**
     * @return fechaTerminacionEstadia date
     */ 
    public Date getFechaTerminacion()
    {
        return fechaTerminacion;
        
    }
      /**
     * @param fechaT date
     */ 
    public void setFechaTerminacion(Date fechaT)
    {
        this.fechaTerminacion = fechaT;
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
