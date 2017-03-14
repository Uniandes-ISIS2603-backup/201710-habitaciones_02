/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;


import co.edu.uniandes.csw.habitaciones.entities.PagoEntity;
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
    
    //private HabitacionDTO habitacion;
    private List<PagoDTO> pago;
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
           pago = new ArrayList<>();
           for(PagoEntity pagoEntity: entity.getPago())
           {
               pago.add(new PagoDTO(pagoEntity));
           }
            
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

    public void setAnfitrion(AnfitrionDTO anfitrion) {
        this.anfitrion = anfitrion;
    }
    
    public List<PagoDTO> getPago()
    {
        return pago;
    }
    public void setPago(List<PagoDTO> p)
    {
        this.pago = p;
    }
    
     @Override
     public ReservaEntity toEntity()
     {   
        ReservaEntity entity = super.toEntity();

        if(this.getViajero() != null)
        {
            entity.setViajero(viajero.toEntity());
        }
        if(this.pago!= null)
        {
            List<PagoEntity> pagoEntity = new ArrayList<>();
            for(PagoDTO pagodto: pago)
            {
                pagoEntity.add(pagodto.toEntity());
            }
            entity.setPago(pagoEntity);
        }
        if(this.getAnfitrion() != null)
        {
            entity.setAnfitrion(anfitrion.toEntity());
        }

        //entity.setPago(this.pago.toEntity());
        //entity.setHabitacion(this.habitacion.toEntity());

        return entity;        
    }
    
    
}
