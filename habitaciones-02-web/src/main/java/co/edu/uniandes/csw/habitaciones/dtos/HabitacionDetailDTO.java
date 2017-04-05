/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.DisponibilidadEntity;
import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ResenaEntity;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity; //TODO quitar
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author b.gamba10
 */
@XmlRootElement
public class HabitacionDetailDTO extends HabitacionDTO {

    private List<DisponibilidadDTO> disponibilidades = new ArrayList<>();

    private List<ReservaDTO> reservas = new ArrayList<>();

    private List<ResenaDTO> resenas = new ArrayList<>();

    private ViviendaDTO vivienda;

    public HabitacionDetailDTO() {

        super();

    }

    public HabitacionDetailDTO(HabitacionEntity entity) {
        super(entity);

        if (entity != null) {

            for (DisponibilidadEntity entityDis : entity.getDisponibilidades()) {

                DisponibilidadDTO disponibilidad = new DisponibilidadDTO(entityDis);
                disponibilidades.add(disponibilidad);
            }

            for (ReservaEntity entityRes : entity.getReservas()) {

                ReservaDTO reserva = new ReservaDTO(entityRes);
                reservas.add(reserva);
            }

            for (ResenaEntity entityResena : entity.getResenas()) {

                ResenaDTO resena = new ResenaDTO(entityResena);
                resenas.add(resena);
            }
            // TODO esto depende del valor que traiga el entity. Si no es null entity.getVivienda() entonces se hace el new con ese valor
            if (vivienda != null) {
                vivienda = new ViviendaDTO(entity.getVivienda());
            } else {
                vivienda = new ViviendaDTO();
            }
        }

    }

    public HabitacionEntity toEntity() {

        HabitacionEntity entity = super.toEntity();

        List<DisponibilidadEntity> listaDisponibilidades = new ArrayList<>();
        for (DisponibilidadDTO dtoDisponibilidad : disponibilidades) {

            listaDisponibilidades.add(dtoDisponibilidad.toEntity());
        }

        List<ReservaEntity> listaReservas = new ArrayList<>();
        for (ReservaDTO dtoReserva : reservas) {

            listaReservas.add(dtoReserva.toEntity());
        }

        List<ResenaEntity> listaResenas = new ArrayList<>();
        for (ResenaDTO dtoResena : resenas) {

            listaResenas.add(dtoResena.toEntity());
        }

        entity.setDisponibilidades(listaDisponibilidades);
        entity.setReservas(listaReservas);
        if (vivienda != null) {
            entity.setVivienda(vivienda.toEntity());
        }
        return entity;
    }

    public List<DisponibilidadDTO> getDisponibilidades() {
        return disponibilidades;
    }

    public void setDisponibilidades(List<DisponibilidadDTO> disponibilidades) {
        this.disponibilidades = disponibilidades;
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

    public ViviendaDTO getVivienda() {
        return vivienda;
    }

    public void setVivienda(ViviendaDTO vivienda) {
        this.vivienda = vivienda;
    }

}
