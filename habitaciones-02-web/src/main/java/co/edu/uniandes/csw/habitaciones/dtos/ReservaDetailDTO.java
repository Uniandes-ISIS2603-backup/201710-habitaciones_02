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
    
    //private HabitacionDTO habitacion;
    private PagoDTO pago;
    private ViajeroDTO viajero;
    private AnfitrionDTO anfitrion;
    
    
    public ReservaDetailDTO()
    {
        super();
    }
    
    public ReservaDetailDTO(ReservaEntity entity)
    {
        super(entity);
        if(entity!= null)
        {
            //habitacion = new HabitacionDTO( entity.getHabitacion( ) );
            pago = new PagoDTO( entity.getPago());
            viajero = new ViajeroDTO(entity.getViajero());
            anfitrion = new AnfitrionDTO(entity.getAnfitrion());
        }
 
    }

    public ViajeroDTO getViajero() {
        return viajero;
    }

    public void setViajero(ViajeroDTO viajero) {
        this.viajero = viajero;
    }

    public AnfitrionDTO getAnfitrion() {
        return anfitrion;
    }
    public PagoDTO getPago()
           
    {
        return pago;
    }
    public void setPago(PagoDTO pago)   
    {
        this.pago = pago;
    }
    
    public void setAnfitrion(AnfitrionDTO anfitrion) {
        this.anfitrion = anfitrion;
    }
    
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
