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
public class ReservaDTO implements Serializable {

    //-----------------
    //Atributos
    //-----------------
    /**
     * el id de la reserva
     */
    private Long id;
    /**
     * la fecha inicial cuando comienza una reserva
     */
    private Date fechaInicio;
    /**
     * la fecha final de la reserva
     */
    private Date fechaTerminacion;
    /**
     * estado de la reserva
     */
    private Boolean cancelado;

    private Integer precio;

    /**
     * Metodo Constructor por defecto de la clase
     */
    public ReservaDTO() {

    }

    /**
     * Metodo constructor lo cual inicializa todo los atributos de serva
     *
     * @param entity
     */
    public ReservaDTO(ReservaEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.fechaInicio = entity.getFechaInicio();
            this.fechaTerminacion = entity.getFechaTerminacion();
            this.cancelado = entity.getCancelado();
            this.precio = entity.getPrecio();
        }
    }

    /**
     * Metodo que cambia un dto a un entity
     *
     * @return el entity del dto modificado
     */
    public ReservaEntity toEntity() {
        ReservaEntity entity = new ReservaEntity();
        entity.setId(this.getId());
        entity.setFechaInicio(this.getFechaInicio());
        entity.setFechaTerminacion(this.getFechaTerminacion());
        entity.setCancelado(this.getCancelado());
        entity.setPrecio(this.getPrecio());
        return entity;

    }

    /**
     * @return id long
     */
    public Long getId() {
        return id;
    }

    /**
     * asigna el valor al id
     *
     * @param i long
     */
    public void setId(Long i) {
        this.id = i;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    /**
     * @return fechaInicioEstadia Date
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Asigna la fecha de inicio
     *
     * @param fechaIn Date
     */
    public void setFechaInicio(Date fechaIn) {
        this.fechaInicio = fechaIn;
    }

    /**
     * @return fechaTerminacionEstadia date
     */
    public Date getFechaTerminacion() {
        return fechaTerminacion;

    }

    /**
     * Asigna la fecha final de la reserva
     *
     * @param fechaT date
     */
    public void setFechaTerminacion(Date fechaT) {
        this.fechaTerminacion = fechaT;
    }

    /**
     * @return cancelad un boolean
     */
    public Boolean getCancelado() {
        return cancelado;
    }

    /**
     * Asigna el estado de la reserva
     *
     * @param can lo que cancela
     */
    public void setCancelado(Boolean can) {
        this.cancelado = can;
    }

}
