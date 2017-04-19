/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.ejbs;

import co.edu.uniandes.csw.habitaciones.entities.DisponibilidadEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.DisponibilidadPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author b.gamba10
 */
@Stateless
public class DisponibilidadLogic
{

    @Inject
    private DisponibilidadPersistence persistence;

    /**
     * Retorna una disponibilidad de una habitacion
     *
     * @param idHabitacion
     * @param id
     * @return
     */
    public DisponibilidadEntity getDisponibilidad(Long idHabitacion, Long id)
    {

        return persistence.find(idHabitacion, id);
    }

    /**
     * Retorna todas las disponibilidades de una habitacion
     *
     * @param idHabitacion
     * @return
     */
    public List<DisponibilidadEntity> getDisponibilidades(Long idHabitacion)
    {

        return persistence.findAll(idHabitacion);
    }

    /**
     * Crea una nueva disponibilidad
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public DisponibilidadEntity createDisponibilidad(DisponibilidadEntity entity) throws BusinessLogicException
    {

        if (entity.getFechaInicioEstadia().after(entity.getFechaTerminacionEstadia()))
        {

            throw new BusinessLogicException("La fecha de termincion de la estadia debe ser posterior a la del inicio.");

        }

        persistence.create(entity);
        return entity;

    }

    /**
     * Modifica una disponibilidad ya existente
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public DisponibilidadEntity updateDisponibilidad(DisponibilidadEntity entity) throws BusinessLogicException
    {

        if (entity.getFechaInicioEstadia().after(entity.getFechaTerminacionEstadia()))
        {

            throw new BusinessLogicException("La fecha de termincion de la estadia debe ser posterior a la del inicio.");

        }
        return persistence.update(entity);
    }

    /**
     * Elimina una disponibilidad
     *
     * @param id
     */
    public void deleteDisponibilidad(Long id)
    {

        persistence.delete(id);
    }
}
