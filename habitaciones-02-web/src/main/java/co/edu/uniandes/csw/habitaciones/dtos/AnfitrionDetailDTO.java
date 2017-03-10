/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;

import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author df.sanabria761
 */
@XmlRootElement
public class AnfitrionDetailDTO extends AnfitrionDTO
{
    
    
    /**
     * Constructor por defecto del Detail DTO
     */
    public AnfitrionDetailDTO()
    {
        
    }
    /**
     * Constructor del detail dto a partir de un entity anfitrión
     * @param entity Entity del anfitrión a crear
     */
    public AnfitrionDetailDTO(AnfitrionEntity entity)
    {
        super(entity);
    }
    
    @Override
    public AnfitrionEntity toEntity()
    {
        return super.toEntity();
    }
}
