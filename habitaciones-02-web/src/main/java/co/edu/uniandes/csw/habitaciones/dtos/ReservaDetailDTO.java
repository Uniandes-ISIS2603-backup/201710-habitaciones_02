/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author dg.guarin20
 */
@XmlRootElement
public class ReservaDetailDTO extends ReservaDTO {
    
    //--------------
    //Atributos
    //--------------
    
    
    /**
     * el dto de la habitacion
     */
    private HabitacionDTO habitacion;
     /**
     * el dto del pago
     */
    private PagoDTO pago;
     /**
     * el dto del viajero
     */
    private ViajeroDTO viajero;
     /**
     * el dto del anfitrion
     */
    private AnfitrionDTO anfitrion;
    
    /**
     * Metodo contructor
     */
    public ReservaDetailDTO()
    {
        super();
    }
    /**
     * Metodo que inicializa la reserva, y los objetos habitacion, pago, viajero y anfitrion.
     * @param entity 
     */
    public ReservaDetailDTO(ReservaEntity entity)
    {
        super(entity);
        if(entity!= null)
        {
            habitacion = new HabitacionDTO( entity.getHabitacion( ) );
            pago = new PagoDTO( entity.getPago());
            viajero = new ViajeroDTO(entity.getViajero());
            anfitrion = new AnfitrionDTO(entity.getAnfitrion());
        }
 
    }

    /**
     * retorna el viajero relacionado con la reserva
     * @return viajero
     */
    public ViajeroDTO getViajero() {
        return viajero;
    }
    /**
     * asigna el viajero a la reserva
     * @param viajero 
     */
    
    public void setViajero(ViajeroDTO viajero) {
        this.viajero = viajero;
    }
    
    /**
     * retorna el anfitrion de la reserva
     * @return anfitrion
     */
    public AnfitrionDTO getAnfitrion() {
        return anfitrion;
    }
    /**
     * retorna el pago de la reserva
     * @return pago
     */
    public PagoDTO getPago()
    {
        return pago;
    }
    
    
    /**
     * asigna el pago de la reserva
     * @param pago 
     */
    public void setPago(PagoDTO pago)   
    {
        this.pago = pago;
    }
    
    /**
     * asigna el anfitrion de la reserva
     * @param anfitrion 
     */
    public void setAnfitrion(AnfitrionDTO anfitrion) {
        this.anfitrion = anfitrion;
    }
    /**
     * retorna la habitacion dto de la reserva
     * @return habitacion
     */
    public HabitacionDTO getHabitacion()
    {
        return habitacion;
    }
    
    /**
     * asigna la habitacion de la reserva
     * @param habitacion 
     */
    public void setHabitacion(HabitacionDTO habitacion)
    {
        this.habitacion = habitacion;
    }
    
    /**
     * Este metodo tiene como proposito pasar los dto a entity.
     * @return la clase ReservaEntity
     */
     @Override
     public ReservaEntity toEntity()
     {   
        ReservaEntity entity = super.toEntity();

        if(this.getViajero() != null)
        {
            entity.setViajero(viajero.toEntity());
        }
        if(this.getAnfitrion() != null)
        {
            entity.setAnfitrion(anfitrion.toEntity());
        }
        if(this.getPago()!= null)
        {
            entity.setPago(this.pago.toEntity());
        }
        

        return entity;        
    }
    
    
}
