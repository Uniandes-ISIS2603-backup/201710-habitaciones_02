/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.ejbs;

import co.edu.uniandes.csw.habitaciones.entities.DisponibilidadEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.DisponibilidadPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author b.gamba10
 */
@Stateless
public class DisponibilidadLogic {

   @Inject
    private DisponibilidadPersistence persistence;

    public List<DisponibilidadEntity> getDisponibilidades(Long id) {

        List<DisponibilidadEntity> lista = new ArrayList<>();

        for (DisponibilidadEntity entity : persistence.findAll()) {
            if (entity.getId().equals(id)) {

                lista.add(entity);

            }
        }
        return lista;
    }

    public DisponibilidadEntity getDisponibilidad(Long idHabitacion, Long id) {

        if (persistence.find(id).getHabitacion().getId().equals(idHabitacion)) {
            
            return persistence.find(id);
            
        } else {
            return null;
        }
    }

    public DisponibilidadEntity createDisponibilidad( DisponibilidadEntity entity) throws BusinessLogicException {

        if (entity.getFechaInicioEstadia().after(entity.getFechaTerminacionEstadia())) {

            throw new BusinessLogicException("La fecha de termincion de la estadia debe ser posterior a la del inicio.");

        } else {
            
            persistence.create(entity);
            return entity;
        }
    }

    public DisponibilidadEntity updateDisponibilidad(DisponibilidadEntity entity) {

        return persistence.update(entity);
    }

    public void deleteDisponibilidad(Long id) {

        persistence.delete(id);
    }
}
