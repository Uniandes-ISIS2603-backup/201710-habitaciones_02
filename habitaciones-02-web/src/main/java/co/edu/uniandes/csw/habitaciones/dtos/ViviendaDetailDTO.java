/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ne.cabrera
 */
@XmlRootElement
public class ViviendaDetailDTO extends ViviendaDTO
{
    //private List<HabitacionDTO> habitaciones;
    public ViviendaDetailDTO()
    {
        super();
    }
    
    public ViviendaDetailDTO(ViviendaEntity entity)
    {
        super(entity);
        //if(entity != null)
        //{   
        //}
    }
    
    @Override
    public ViviendaEntity toEntity()
    {
        ViviendaEntity entity = super.toEntity();
        return entity;
    }
}
