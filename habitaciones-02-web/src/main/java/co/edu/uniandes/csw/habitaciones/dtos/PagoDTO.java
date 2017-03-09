/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.PagoEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ne.cabrera
 */
@XmlRootElement
public class PagoDTO 
{
    private Long id;
    
    private Date fechaDePago;
    
    private Double pago;
    
    private String tipoTramite;
    
    public PagoDTO()
    {
    }
    
    public PagoDTO(PagoEntity entity)
    {
        if(entity != null)
        {
            id = entity.getId();
            fechaDePago = entity.getFechaDePago();
            pago = entity.getPago();
            tipoTramite = entity.getTipoTramite();
        }
    }
    
    public PagoEntity toEntity()
    {
        PagoEntity entity = new PagoEntity();
        entity.setId(this.getId());
        entity.setFechaDePago(this.fechaDePago);
        entity.setPago(this.getPago());
        entity.setPago(this.getPago());
        return entity;
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
}
