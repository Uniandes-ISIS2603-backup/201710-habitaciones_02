/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;



/**
 *
 * @author dg.guarin20
 */
@XmlRootElement
public class ReservaDetailDTO extends ReservaDTO {
    
    private HabitacionDTO habitacion;
    private PagoDTO pago;
    
    
    public ReservaDetailDTO()
    {
        super();
    }
    
    public ReservaDetailDTO(ReservaEntity entity)
    {
        super(entity);
        if(entity!= null)
        {
            habitacion = new HabitacionDTO( entity.getHabitacion( ) );
            pago = new PagoDTO( entity.getPago());
        }
        
        
    }
    
     @Override
     public ReservaEntity toEntity()
     {   
        ReservaEntity entity = super.toEntity();
        entity.setPago(this.pago.toEntity());
        entity.setHabitacion(this.habitacion.toEntity());
        return entity;        
    }
    
    
}
