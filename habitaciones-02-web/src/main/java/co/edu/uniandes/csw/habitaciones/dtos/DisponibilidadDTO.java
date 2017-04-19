/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.DisponibilidadEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author b.gamba10
 */
@XmlRootElement
public class DisponibilidadDTO implements Serializable
{

    private Long id;
    private Date fechaInicioEstadia;
    private Date fechaTerminacionEstadia;

    /**
     * Constructor
     */
    public DisponibilidadDTO()
    {

    }

    /**
     * Constructor que recibe un entity por parametro
     *
     * @param entity
     */
    public DisponibilidadDTO(DisponibilidadEntity entity)
    {
        if (entity != null)
        {
            this.id = entity.getId();
            this.fechaInicioEstadia = entity.getFechaInicioEstadia();
            this.fechaTerminacionEstadia = entity.getFechaTerminacionEstadia();
        }
    }

    /**
     * Pasa el objeto de DTO a Entidad
     *
     * @return
     */
    public DisponibilidadEntity toEntity()
    {
        DisponibilidadEntity entity = new DisponibilidadEntity();
        entity.setId(this.getId());
        entity.setFechaInicioEstadia(this.getFechaInicioEstadia());
        entity.setFechaTerminacionEstadia(this.getFechaTerminacionEstadia());
        return entity;
    }

    /**
     * Retorna el id de la disponibilidad
     *
     * @return
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Coloca el id de la disponibilidad
     *
     * @param id
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Retorna la fecha de inicio de la estadia
     *
     * @return
     */
    public Date getFechaInicioEstadia()
    {
        return fechaInicioEstadia;
    }

    /**
     * Coloca la fecha de inicio de la estadia
     *
     * @param fechaInicioEstadia
     */
    public void setFechaInicioEstadia(Date fechaInicioEstadia)
    {
        this.fechaInicioEstadia = fechaInicioEstadia;
    }

    /**
     * Retorna la fecha de terminacion de la estadia
     *
     * @return
     */
    public Date getFechaTerminacionEstadia()
    {
        return fechaTerminacionEstadia;
    }

    /**
     * Coloca la fecha de terminacion de la estadia
     *
     * @param fechaTerminacionEstadia
     */
    public void setFechaTerminacionEstadia(Date fechaTerminacionEstadia)
    {
        this.fechaTerminacionEstadia = fechaTerminacionEstadia;
    }

}
