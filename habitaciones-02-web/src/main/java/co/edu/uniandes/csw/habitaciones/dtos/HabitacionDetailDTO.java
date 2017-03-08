/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author b.gamba10
 */
@XmlRootElement
public class HabitacionDetailDTO extends HabitacionDTO{

    public HabitacionDetailDTO() {
        super();
    }

    public HabitacionDetailDTO(HabitacionEntity entity) {
        super(entity);
    }
    
    public HabitacionEntity toEntity(){
        HabitacionEntity entity = super.toEntity();
        return entity;
    }
    
    
    
}
