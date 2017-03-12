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
    private List<HabitacionDTO> habitacion;
    public ViviendaDetailDTO()
    {
        super();
    }
    
    public ViviendaDetailDTO(ViviendaEntity entity)
    {
        super(entity);
        if(entity != null)
        {
        habitacion = new ArrayList<>();
        for(HabitacionEntity ent: entity.getHabitaciones())
        {
            habitacion.add(new HabitacionDTO(ent));
        }
        }
    }
    
    @Override
    public ViviendaEntity toEntity()
    {
        ViviendaEntity entity = super.toEntity();
        if(entity != null)
        {
            if(getHabitaciones() == null)
            {
                setHabitaciones(new ArrayList<>());
            }
        List<HabitacionEntity> habs = new ArrayList<>();
        for(HabitacionDTO habs2 : this.getHabitaciones())
        {
            habs.add(habs2.toEntity());
        }
        entity.setHabitaciones(habs);
        }
        return entity;
    }

    /**
     * @return the habitaciones
     */
    public List<HabitacionDTO> getHabitaciones() 
    {
        return habitacion;
    }

    /**
     * @param habitaciones the habitaciones to set
     */
    public void setHabitaciones(List<HabitacionDTO> habitaciones) 
    {
        this.habitacion = habitaciones;
    }
    
    
}

