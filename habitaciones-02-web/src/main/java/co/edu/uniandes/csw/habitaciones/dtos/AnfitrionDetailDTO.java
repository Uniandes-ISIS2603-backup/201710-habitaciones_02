/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ResenaEntity;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author df.sanabria761
 */
@XmlRootElement
public class AnfitrionDetailDTO extends AnfitrionDTO
{
    
    private List<ViviendaDTO> viviendas;
    private List<ReservaDTO> reservas;
    private List<ResenaDTO> resenas;
    /**
     * Constructor por defecto del Detail DTO
     */
    public AnfitrionDetailDTO()
    {
        
    }
    /**
     * Constructor del detail dto a partir de un entity anfitrión
     * @param entity Entity del anfitrión a crear
     */
    public AnfitrionDetailDTO(AnfitrionEntity entity) {
        super(entity);
        if (entity != null) {
            viviendas = new ArrayList<ViviendaDTO>();
            for (ViviendaEntity entity2 : entity.getViviendas()) {
                viviendas.add(new ViviendaDTO(entity2));
            }
            reservas = new ArrayList<>();
            for (ReservaEntity entityReserva : entity.getReservas()) {
                ReservaDTO reserva = new ReservaDTO(entityReserva);
                reservas.add(reserva);
            }
            resenas = new ArrayList<>();
            for(ResenaEntity enitytResena : entity.getResenas())
            {
                resenas.add(new ResenaDTO(enitytResena));
            }
        }
    }
    
    @Override
    public AnfitrionEntity toEntity()
    {
        return super.toEntity();
    }

    public List<ViviendaDTO> getViviendas() {
        return viviendas;
    }

    public void setViviendas(List<ViviendaDTO> viviendas) {
        this.viviendas = viviendas;
    }

    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }

    public List<ResenaDTO> getResenas() {
        return resenas;
    }

    public void setResenas(List<ResenaDTO> resenas) {
        this.resenas = resenas;
    }
    
    
}
