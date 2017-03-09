/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.PagoEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ne.cabrera
 */
@XmlRootElement
public class PagoDetailDTO extends PagoDTO
{
    //private TramiteDTO tramite
    public PagoDetailDTO()
    {
        super();
    }
    
    public PagoDetailDTO(PagoEntity entity)
    {
        super(entity);
        //if(entity != null)
        //{
        //}
    }
    
    @Override
    public PagoEntity toEntity()
    {
        PagoEntity entity = super.toEntity();
        return entity;
    }
}
