/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.ejbs;

import co.edu.uniandes.csw.habitaciones.entities.ResenaEntity;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.persistence.ResenaPersistence;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.ReservaPersistence;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.cortes
 */
@Stateless
public class ResenaLogic
{
    //----------------------------------------------------------------------------------------------------
    // ATRIBUTOS
    //----------------------------------------------------------------------------------------------------

    /**
     * persistencia para la entidad del Resena
     */
    @Inject
    private ResenaPersistence persistence;

    @Inject
    private ReservaPersistence persistenceReserva;

    //----------------------------------------------------------------------------------------------------
    // METODOS
    //----------------------------------------------------------------------------------------------------
    /**
     * Retorna una lista con todos los Resenas que se encuentran persistidos
     *
     * @return
     */
    public List<ResenaEntity> findResenas()
    {
        return persistence.findAll();
    }

    public List<ResenaEntity> findResenasViajero(Long idViajero)
    {
        return persistence.findAllByViajero(idViajero);
    }

    public List<ResenaEntity> findResenasHabitacion(Long idHabitacion)
    {
        return persistence.findAllByHabitacion(idHabitacion);
    }

    /**
     * Retorna un un ResenaEntity a partir de un id dado por parámetro
     *
     * @param id el id del ResenaEntity que se desea buscar
     * @return un VIajeroEntity si se encuentra en la base datos, de lo
     * contrario null
     */
    public ResenaEntity findResena(Long id)
    {
        return persistence.find(id);
    }

    /**
     * Crea una nueva entidad en la base de datos
     *
     * @param entity entidad que se desea agregar
     * @return la entidad que fue agregada a la base de datos
     * @throws BusinessLogicException
     */
    public ResenaEntity createResena(ResenaEntity entity) throws BusinessLogicException
    {
        if (entity.getCalificacion() == null || (entity.getCalificacion() < 0) || (entity.getCalificacion() > 5))
        {
            throw new BusinessLogicException("La reseña debe tener una calificacion que sea entre 0 y 5");
        }
        if (entity.getHabitacion() != null && entity.getViajero() != null)
        {
            Long idViajero = entity.getViajero().getIdUsuario();
            Long idHabitacion = entity.getHabitacion().getId();
            ReservaEntity reserva = persistenceReserva.findReservaFromViajeroAndHabitacion(idViajero, idHabitacion);

            if (reserva == null)
            {
                throw new BusinessLogicException("No se puede generar la resena debido a que no hay registro de que el viajero "
                        + "haya hecho una reserva en la habitacion");
            } else if (reserva.getFechaInicio().compareTo(new Date()) > 0)
            {
                throw new BusinessLogicException("No se puede generar la resena debido a que aun no aun no ha iniciado la fecha inicial de la reserva");
            }
        } else
        {
            throw new BusinessLogicException("La reseña debe ser diligenciada por un viajero especifico"
                    + " y debe estar dirijida a una habitacion especifica");
        }

        return persistence.create(entity);
    }

    /**
     * Actualiza la información de un ResenaEntoty que se encuentre en la base
     * de datos
     *
     * @param entity la entidad que desea actualizar
     * @return la entidad actualizada
     * @throws
     * co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException
     */
    public ResenaEntity updateResena(ResenaEntity entity) throws BusinessLogicException
    {
        if (entity.getCalificacion() == null || (entity.getCalificacion() < 0) || (entity.getCalificacion() > 5))
        {
            throw new BusinessLogicException("La reseña debe tener una calificacion que sea entre 0 y 5");
        }
        if (entity.getHabitacion() != null && entity.getViajero() != null)
        {
            Long idViajero = entity.getViajero().getIdUsuario();
            Long idHabitacion = entity.getHabitacion().getId();
            ReservaEntity reserva = persistenceReserva.findReservaFromViajeroAndHabitacion(idViajero, idHabitacion);

            if (reserva == null)
            {
                throw new BusinessLogicException("No se puede generar la resena debido a que no hay registro de que el viajero "
                        + "haya hecho una reserva en la habitacion");
            } else if (reserva.getFechaInicio().compareTo(new Date()) > 0)
            {
                throw new BusinessLogicException("No se puede generar la resena debido a que aun no aun no ha iniciado la fecha inicial de la reserva");
            }
        } else
        {
            throw new BusinessLogicException("La reseña debe ser diligenciada por un viajero especifico"
                    + " y debe estar dirijida a una habitacion especifica");
        }
        return persistence.update(entity);
    }

    /**
     * Elimina un Resena a partir del id dado por parámetro
     *
     * @param id
     */
    public void delete(Long id)
    {
        persistence.delete(id);

    }
}
