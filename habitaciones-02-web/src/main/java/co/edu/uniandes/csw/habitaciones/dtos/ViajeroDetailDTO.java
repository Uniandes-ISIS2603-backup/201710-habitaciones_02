/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViajeroEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author s.cortes
 */
@XmlRootElement
public class ViajeroDetailDTO extends ViajeroDTO {
    //----------------------------------------------------------------------------------------------------
    // ATRIBUTOS DEL DETAIL DTO
    //----------------------------------------------------------------------------------------------------

    private List<ReservaDTO> reservas;
    
    //----------------------------------------------------------------------------------------------------
    // METODOS CONSTRUCTORES
    //----------------------------------------------------------------------------------------------------
    public ViajeroDetailDTO()
    {
        super();

    }

    public ViajeroDetailDTO(ViajeroEntity entity)
    {
        super(entity);
        if(entity != null)
        {
            reservas = new ArrayList<>();
            for(ReservaEntity entityReserva : entity.getReservas())
            {
                reservas.add(new ReservaDTO(entityReserva));
            }
        }
    }

    //----------------------------------------------------------------------------------------------------
    // METODOS
    //----------------------------------------------------------------------------------------------------

    @Override
    public ViajeroEntity toEntity()
    {
        ViajeroEntity entity = super.toEntity();
        if(reservas != null)
        {
            List<ReservaEntity> reservasEntity = new ArrayList<>();
            for(ReservaDTO dtoReserva : reservas)
            {
                reservasEntity.add(dtoReserva.toEntity());
            }
            entity.setReservas(reservasEntity);
        }
        
        return entity;
    }

    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
    
    
}
