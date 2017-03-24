/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class PagoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * El id del pago
     */
    private Long id;

    /**
     * Fecha del pago
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDePago;

    /**
     * El monto pagado
     */
    private Double pago;

    /**
     * El tipo de tramite
     */
    private String tipoTramite;

    /**
     * Relacion one to one entre el pago y la reserva
     */
    @OneToOne(fetch = FetchType.LAZY)
    private ReservaEntity reserva;

    /**
     * Retorna la reserva
     *
     * @return reserva
     */
    public ReservaEntity getReserva() {
        return reserva;
    }

    /**
     * @param pReserva la reserva a asignar
     */
    public void setReserva(ReservaEntity pReserva) {
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

    /**
     * @return
     */
    public Boolean informacionCompleta() {
        return (stringUtilizable(fechaDePago.toString()) && stringUtilizable(tipoTramite)
                && stringUtilizable(pago.toString()));
    }

    private Boolean stringUtilizable(String palabra) {
        return (palabra != null) ? !palabra.isEmpty() : false;
    }
}
