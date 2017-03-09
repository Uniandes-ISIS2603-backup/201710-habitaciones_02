/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
/**
 *
 * @author ne.cabrera
 */

@XmlRootElement
public class ViviendaDTO implements Serializable
{
    private Long id;
    
    private String ciudad;
    
    private String direccion;
    
    public ViviendaDTO()
    {
    }
    
    public ViviendaDTO(ViviendaEntity entity)
    {
        if(entity != null)
        {
            id = entity.getId();
            ciudad = entity.getCiudad();
            direccion = entity.getDireccion();
        }
    }
    
    public ViviendaEntity toEntity()
    {
        ViviendaEntity entity = new ViviendaEntity();
        entity.setId(this.getId());
        entity.setCiudad(this.getCiudad());
        entity.setDireccion(this.getDireccion());
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
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
