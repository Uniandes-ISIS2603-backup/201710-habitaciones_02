/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dg.guarin20
 */
@XmlRootElement
public class ReservaDetailDTO extends ReservaDTO {
    
    public ReservaDetailDTO()
    {
        
    }
    
    public ReservaDetailDTO(ReservaEntity entity)
    {
        super(entity);
    }
    
    @Override
     public ReservaEntity toEntity() {
        ReservaEntity entity = super.toEntity();
        return entity;
    }
    
    
}
