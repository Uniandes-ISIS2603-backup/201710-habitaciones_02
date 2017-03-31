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


/**
 *
 * @author dg.guarin20
 */

@Entity
public class ReservaEntity implements Serializable{
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * El id de la reserva
     */
    private Long id;
    
    /**
     * La fecha en que iniciara la reserva de la habitacion
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    
    /**
     * la fecha en que termina la reserva de la habitacion
     */
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaTerminacion;
    
    /**
     * aqui se verifica si esta cancelado o no
     */
    private Boolean cancelado;
    
    /**
     * Una relacion de many to one con viajero que hace la reserva
     */
    @ManyToOne
    private ViajeroEntity viajero;
    /**
     * Una relacion de many to one con el anfitrion a que le hicieron la reserva
     */
    @ManyToOne
    private AnfitrionEntity anfitrion;
    
    /**
     * Una relacion de many to one a la habitacion que le hicieron la reserva
     */
    @ManyToOne
    private HabitacionEntity habitacion;
    
    /**
     * Una relacion de one to one con el pago
     */
    @OneToOne(mappedBy = "reserva")
    private PagoEntity pago;

    /**
     * Retorna el id de la reserva
     * @return el id de la reserva
     */
    public Long getId() { 
        return id;
    }
    /**
     * Asigna el id a la reserva
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Retorna la fecha de inicio de la reserva
     * @return el la fecha de inicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }
    /**
     * Asigna la fecha de inicio de la reserva
     * @param fechaInicio 
     */
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    /**
     * Retorna la ultima fecha de la reserva
     * @return la ultima fecha de la reserva
     */
    public Date getFechaTerminacion() {
        return fechaTerminacion;
    }
    /**
     * Asigna la ultima fecha de la reserva
     * @param fechaTerminacion 
     */
    public void setFechaTerminacion(Date fechaTerminacion) {
        this.fechaTerminacion = fechaTerminacion;
    }
    /**
     * retorna si la reserva esta cancelado o no
     * @return cancelado
     */
    public Boolean getCancelado() {
        return cancelado;
    }
    /**
     * asigna el estado de la reserva
     * @param cancelado 
     */
    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }
    /**
     * Retorna el viajero que hizo la reserva
     * @return viajero
     */
    public ViajeroEntity getViajero() {
        return viajero;
    }
    /**
     * asigna el viajero de la reserva
     * @param viajero 
     */
    public void setViajero(ViajeroEntity viajero) {
        this.viajero = viajero;
    }
    /**
     * retorna el anfitrion al que le hicieron la reserva
     * @return anfitrion
     */
    public AnfitrionEntity getAnfitrion() {
        return anfitrion;
    }
    /**
     * asigna el anfitrion
     * @param anfitrion 
     */
    public void setAnfitrion(AnfitrionEntity anfitrion) {
        this.anfitrion = anfitrion;
    }
    /**
     * Retorna la habitacion que le hicieron la reserva
     * @return habitacion
     */
    public HabitacionEntity getHabitacion() {
        return habitacion;
    }
    /**
     * Asigna la habitacion de la reserva
     * @param habitacion 
     */
    public void setHabitacion(HabitacionEntity habitacion) {
        this.habitacion = habitacion;
    }
    /**
     * retorna el pago
     * @return pago
     */
    public PagoEntity getPago() {
        return pago;
    }
    /**
     * asigna el pago
     * @param pago 
     */
    public void setPago(PagoEntity pago) {
        this.pago = pago;
    }
    /**
     * En este se verifica si cada informacion de la reserva este completa sino retorna false
     * @return el estado de la informacion
     */
    public boolean informacionCompleta()
    {
        if(fechaInicio == null || fechaTerminacion == null )
        {
            return false;
        }
        if(viajero.informacionCompleta() == false || anfitrion.informacionCompleta() == false || habitacion.informacionCompleta() == false)
        {
            return false;
        }
            
            return true;
    }
    /**
     * Verifica si las fechas estan en orden es decir si el usuario puso la fecha de inicio
     * primero que la fecha final
     * @return el estado de las fechas
     */
    public boolean checkInfoFechas()
    {

        if(fechaInicio.after(fechaTerminacion))
        {
            return false;
        }
        return true;
    }
    /**
     * En este se verificaria si las fechas no cruzan con otras fechas.
     * @return estado
     */
    public boolean checkInfo()
    {
        return true;
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
