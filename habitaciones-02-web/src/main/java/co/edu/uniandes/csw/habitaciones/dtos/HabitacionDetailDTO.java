/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.DisponibilidadEntity;
import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import com.gs.collections.impl.list.fixed.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author b.gamba10
 */
@XmlRootElement
public class HabitacionDetailDTO extends HabitacionDTO {

    private List<DisponibilidadDTO> disponibilidades;

    private List<ReservaDTO> reservas;

    private ViviendaEntity vivienda;

    public HabitacionDetailDTO() {
        
        super();
        disponibilidades = new ArrayList<>();
        reservas = new ArrayList<>();
    }

    public HabitacionDetailDTO(HabitacionEntity entity) {
        super(entity);
        
        if (entity != null) {
            disponibilidades = new ArrayList<>();
            for (DisponibilidadEntity entityDis : entity.getDisponibilidades()) {
                
                DisponibilidadDTO disponibilidad = new DisponibilidadDTO(entityDis);
                disponibilidades.add(disponibilidad);
            }
            
            reservas = new ArrayList<>();
            for (ReservaEntity entityRes : entity.getReservas()) {
                
                ReservaDTO reserva = new ReservaDTO(entityRes);
                reservas.add(reserva);
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

        entity.setDisponibilidades(listaDisponibilidades);
        entity.setReservas(listaReservas);
        entity.setVivienda(vivienda);

        return entity;
    }

}
