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
public class DisponibilidadDTO implements Serializable {

    private Long id;
    private Date fechaInicioEstadia;
    private Date fechaTerminacionEstadia;

    public DisponibilidadDTO() {

    }

    public DisponibilidadDTO(DisponibilidadEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.fechaInicioEstadia = entity.getFechaInicioEstadia();
            this.fechaTerminacionEstadia = entity.getFechaTerminacionEstadia();
        }
    }

    public DisponibilidadEntity toEntity() {
        DisponibilidadEntity entity = new DisponibilidadEntity();
        entity.setId(this.getId());
        entity.setFechaInicioEstadia(this.getFechaInicioEstadia());
        entity.setFechaTerminacionEstadia(this.getFechaTerminacionEstadia());
        return entity;
    }

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

}
