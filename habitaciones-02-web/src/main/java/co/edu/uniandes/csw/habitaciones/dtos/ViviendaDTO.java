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
public class ViviendaDTO implements Serializable {

    /**
     * el id de la vivienda
     */
    private Long id;

    /**
     * la ciudad en la que esta ubicada la vivienda
     */
    private String ciudad;

    /**
     * la direccion de la vivienda
     */
    private String direccion;

    /**
     * constructor por defecto de la vivienda
     */
    public ViviendaDTO() {
    }

    /**
     * @param entity metodo constructor que inicializa la viviendaDTO a partir
     * de la informacion de la viviendaEntity
     */
    public ViviendaDTO(ViviendaEntity entity) {
        if (entity != null) {
            id = entity.getId();
            ciudad = entity.getCiudad();
            direccion = entity.getDireccion();
        }
    }

    /**
     * @return viviendaEntity a partir de la informacion de la viviendaDTO
     */
    public ViviendaEntity toEntity() {
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
