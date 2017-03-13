/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.ResenaEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author s.cortes
 */
@XmlRootElement
public class ResenaDetailDTO extends ResenaDTO
{
    
    private ViajeroDTO viajero;
    
    private HabitacionDTO habitacion;

    public ResenaDetailDTO() 
    {
        super();
    }

    public ResenaDetailDTO(ResenaEntity entity) 
    {
        super(entity);
        if(entity!= null)
        {
            habitacion = new HabitacionDTO( entity.getHabitacion( ) );
            viajero = new ViajeroDTO(entity.getViajero());
        }
    }

    public ViajeroDTO getViajero() {
        return viajero;
    }

    public void setViajero(ViajeroDTO viajero) {
        this.viajero = viajero;
    }
    
    public HabitacionDTO getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(HabitacionDTO habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    public ResenaEntity toEntity() {
        ResenaEntity entity = super.toEntity();
        
        if(this.getViajero() != null)
        {
            entity.setViajero(viajero.toEntity());
        }
        if(this.getHabitacion() != null)
        {
            entity.setHabitacion(habitacion.toEntity());
        }
        
        return entity;
    }
    
    
    
}
