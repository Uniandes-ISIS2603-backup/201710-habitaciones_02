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
 * @author ne.cabrera
 */
@Entity
public class PagoEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDePago;
    
    private Double pago;
    
    private String tipoTramite;
    
    @OneToOne
    private ReservaEntity reserva;
    
    public ReservaEntity getReserva()
    {
        return reserva;
    }
    
    public void setReserva(ReservaEntity pReserva)
    {
        this.reserva = pReserva;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the fechaDePago
     */
    public Date getFechaDePago() {
        return fechaDePago;
    }

    /**
     * @param fechaDePago the fechaDePago to set
     */
    public void setFechaDePago(Date fechaDePago) {
        this.fechaDePago = fechaDePago;
    }

    /**
     * @return the pago
     */
    public Double getPago() {
        return pago;
    }

    /**
     * @param pago the pago to set
     */
    public void setPago(Double pago) {
        this.pago = pago;
    }

    /**
     * @return the tipoTramite
     */
    public String getTipoTramite() {
        return tipoTramite;
    }

    /**
     * @param tipoTramite the tipoTramite to set
     */
    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this.getId() != null) {
            return this.getId().equals(((PagoEntity) obj).getId());
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
