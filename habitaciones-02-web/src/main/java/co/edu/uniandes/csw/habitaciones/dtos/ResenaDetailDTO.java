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
    /**
     * Viajero que publicó la reseña
     */
    private ViajeroDTO viajero;
    
    /**
     * habitacion que fue reseñada
     */
    private HabitacionDTO habitacion;

    /**
     * metodo default para el DetailDTO
     */
    public ResenaDetailDTO() 
    {
        super();
    }

    /**
     * Metodo constructor
     * @param entity entidad de la reseña
     */
    public ResenaDetailDTO(ResenaEntity entity) 
    {
        super(entity);
        if(entity!= null)
        {
            viajero = new ViajeroDTO(entity.getViajero());
            habitacion = new HabitacionDTO( entity.getHabitacion( ) );
        }
    }

    /**
     * retorna el viajero creador de la reseña
     * @return el viajero 
     */
    public ViajeroDTO getViajero() {
        return viajero;
    }

    /**
     * asigna el ciajero a la entidad
     * @param viajero el viajero
     */
    public void setViajero(ViajeroDTO viajero) {
        this.viajero = viajero;
    }
    
    /**
     * retorna la habitacin de la reseña
     * @return la habitacion
     */
    public HabitacionDTO getHabitacion() {
        return habitacion;
    }

     /**
     * asigna la habitacin a la reserva
     * @param habitacion la habitacion
     */
    public void setHabitacion(HabitacionDTO habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    public ResenaEntity toEntity() {
        ResenaEntity entity = super.toEntity();
        
        if(this.getViajero() != null)
        {
            entity.setViajero(this.viajero.toEntity());
        }
        if(this.getHabitacion() != null)
        {
            entity.setHabitacion(this.habitacion.toEntity());
        }
        
        return entity;
    }
    
    
    
}
