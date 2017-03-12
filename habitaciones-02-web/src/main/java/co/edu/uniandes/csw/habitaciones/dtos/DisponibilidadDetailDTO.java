/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.DisponibilidadEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author b.gamba10
 */
@XmlRootElement
public class DisponibilidadDetailDTO extends DisponibilidadDTO {

    private HabitacionDTO habitacion;
    
    public HabitacionDTO getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(HabitacionDTO habitacion) {
        this.habitacion = habitacion;
    }

    public DisponibilidadDetailDTO() {
        super();
    }

    public DisponibilidadDetailDTO(DisponibilidadEntity entity) {
        super(entity);
        
        if (entity != null) {
            habitacion = new HabitacionDTO(entity.getHabitacion());
        }

    }

    public DisponibilidadEntity toEntity() {

        DisponibilidadEntity entity = super.toEntity();
        
        if (this.getHabitacion() != null) {
            
            entity.setHabitacion(habitacion.toEntity());
        }
        return entity;
    }
    
    
}
