/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.DisponibilidadEntity;
import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author b.gamba10
 */
@XmlRootElement
public class DisponibilidadDetailDTO extends DisponibilidadDTO {
    
    private HabitacionEntity habitacion;
    
    public DisponibilidadDetailDTO() {
        super();
    }

    public DisponibilidadDetailDTO(DisponibilidadEntity entity) {
        super(entity);
        
    }
    
    public DisponibilidadEntity toEntity(){
        
        DisponibilidadEntity entity = super.toEntity();
        entity.setHabitacion(habitacion);
        return entity;
    }
}
