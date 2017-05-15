/* 
 * The MIT License
 *
 * Copyright 2017 Los Favoritos.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ne.cabrera
 */
@Entity
public class PagoEntity implements Serializable
{

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
    @PodamExclude
    @OneToOne(fetch = FetchType.LAZY)
    private ReservaEntity reserva;

    /**
     * Retorna la reserva
     *
     * @return reserva
     */
    public ReservaEntity getReserva()
    {
        return reserva;
    }

    /**
     * @param pReserva la reserva a asignar
     */
    public void setReserva(ReservaEntity pReserva)
    {
        this.reserva = pReserva;
    }

    /**
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * @return the fechaDePago
     */
    public Date getFechaDePago()
    {
        return fechaDePago;
    }

    /**
     * @param fechaDePago the fechaDePago to set
     */
    public void setFechaDePago(Date fechaDePago)
    {
        this.fechaDePago = fechaDePago;
    }

    /**
     * @return the pago
     */
    public Double getPago()
    {
        return pago;
    }

    /**
     * @param pago the pago to set
     */
    public void setPago(Double pago)
    {
        this.pago = pago;
    }

    /**
     * @return the tipoTramite
     */
    public String getTipoTramite()
    {
        return tipoTramite;
    }

    /**
     * @param tipoTramite the tipoTramite to set
     */
    public void setTipoTramite(String tipoTramite)
    {
        this.tipoTramite = tipoTramite;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this.getId() != null)
        {
            return this.getId().equals(((PagoEntity) obj).getId());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode()
    {
        if (this.getId() != null)
        {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }

    /**
     * @return
     */
    public Boolean informacionCompleta()
    {
        return (stringUtilizable(fechaDePago.toString()) && stringUtilizable(tipoTramite)
                && stringUtilizable(pago.toString()));
    }

    private Boolean stringUtilizable(String palabra)
    {
        Boolean respuesta = false;
        if(palabra != null && !palabra.isEmpty())
        {
            respuesta = true;
        }
        return respuesta;
    }
}
