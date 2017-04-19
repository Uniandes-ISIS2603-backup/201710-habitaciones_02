/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.ResenaEntity;
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
public class ViajeroDetailDTO extends ViajeroDTO
{
    //----------------------------------------------------------------------------------------------------
    // ATRIBUTOS DEL DETAIL DTO
    //----------------------------------------------------------------------------------------------------

    /**
     * Lista de reservas
     */
    private List<ReservaDTO> reservas;

    /**
     * lista de reseñas
     */
    private List<ResenaDTO> resenas;

    //----------------------------------------------------------------------------------------------------
    // METODOS CONSTRUCTORES
    //----------------------------------------------------------------------------------------------------
    /**
     * Metodo constructor por defecto
     */
    public ViajeroDetailDTO()
    {
        super();
        //metodo constructor por defecto
    }

    /**
     * Metodo constructor
     *
     * @param entity entidad del viajero
     */
    public ViajeroDetailDTO(ViajeroEntity entity)
    {
        super(entity);
        if (entity != null)
        {
            reservas = new ArrayList<>();
            for (ReservaEntity entityReserva : entity.getReservas())
            {
                reservas.add(new ReservaDTO(entityReserva));
            }

            resenas = new ArrayList<>();
            for (ResenaEntity enitytResena : entity.getResenas())
            {
                resenas.add(new ResenaDTO(enitytResena));
            }
        }
    }

    //----------------------------------------------------------------------------------------------------
    // METODOS
    //----------------------------------------------------------------------------------------------------
    /**
     * Retorna las resevas del viajero
     *
     * @return lista de reservas
     */
    public List<ReservaDTO> getReservas()
    {
        return reservas;
    }

    /**
     * asigna las reservas del viajero
     *
     * @param reservas la lista de reservas a asignar
     */
    public void setReservas(List<ReservaDTO> reservas)
    {
        this.reservas = reservas;
    }

    /**
     * retorna la lista de reseñas
     *
     * @return lista de reseñas
     */
    public List<ResenaDTO> getResenas()
    {
        return resenas;
    }

    /**
     * asigna las reseñas del viajero
     *
     * @param resenas la lista de las reseñas a asignar
     */
    public void setResenas(List<ResenaDTO> resenas)
    {
        this.resenas = resenas;
    }

    @Override
    public ViajeroEntity toEntity()
    {
        ViajeroEntity entity = super.toEntity();
        if (reservas != null)
        {
            List<ReservaEntity> reservasEntity = new ArrayList<>();
            for (ReservaDTO dtoReserva : reservas)
            {
                reservasEntity.add(dtoReserva.toEntity());
            }
            entity.setReservas(reservasEntity);
        }
        if (resenas != null)
        {
            List<ResenaEntity> resenasEntity = new ArrayList<>();
            for (ResenaDTO dtoResena : resenas)
            {
                resenasEntity.add(dtoResena.toEntity());
            }
            entity.setResenas(resenasEntity);
        }

        return entity;
    }

}
