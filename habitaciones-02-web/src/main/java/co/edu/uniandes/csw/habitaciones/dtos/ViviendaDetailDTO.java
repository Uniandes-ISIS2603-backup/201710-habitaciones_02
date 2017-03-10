/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ne.cabrera
 */
@XmlRootElement
public class ViviendaDetailDTO extends ViviendaDTO
{
    private List<HabitacionDTO> habitaciones;
    public ViviendaDetailDTO()
    {
        super();
    }
    
    public ViviendaDetailDTO(ViviendaEntity entity)
    {
        super(entity);
        habitaciones = new ArrayList<HabitacionDTO>();
        for(HabitacionEntity ent: entity.getHabitaciones())
        {
            habitaciones.add(new HabitacionDTO(ent));
        }
    }
    
    @Override
    public ViviendaEntity toEntity()
    {
        ViviendaEntity entity = super.toEntity();
        List<HabitacionEntity> habs = new ArrayList<HabitacionEntity>();
        for(HabitacionDTO habs2 : this.habitaciones)
        {
            habs.add(habs2.toEntity());
        }
        entity.setHabitaciones(habs);
        return entity;
    }
}
