/* 
 * The MIT License
 *
 * Copyright 2017 Los Favoritos.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
public class ResenaLogic {
    //----------------------------------------------------------------------------------------------------
    // CONSTANTES  
    //----------------------------------------------------------------------------------------------------

    private final static int CALIFICACION_MIN = 0;

    private final static int CALIFICACION_MAX = 5;

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
    public List<ResenaEntity> findResenas() {
        return persistence.findAll();
    }

    /**
     * Retorna una lista con las resenas de un viajero en especifico
     *
     * @param idViajero
     * @return la lista de resenas
     */
    public List<ResenaEntity> findResenasViajero(Long idViajero) {
        return persistence.findAllByViajero(idViajero);
    }

    /**
     * Retorna una lista con las resenas de un viajero especifico cuya
     * calificaion se encuentre dentro de un rango.
     *
     * @param idViajero el viajero
     * @param minimo el minimo del rango
     * @param maximo el maximo del rango
     * @return una lista con las resenas
     * @throws BusinessLogicException si el minimo del rango es mayor al maximo
     * del rango
     */
    public List<ResenaEntity> finResenasViajeroPorRango(Long idViajero,
            Long minimo, Long maximo) throws BusinessLogicException {
        if (minimo > maximo) {
            throw new BusinessLogicException("El rango dado no genera error: el "
                    + "valor minimo debe ser menor al valor maximo");
        }
        return persistence.findAllByViajeroAndRange(idViajero, minimo, maximo);
    }

    /**
     * Retorna una lista de resenas de una habitacion en especifico
     *
     * @param idHabitacion
     * @return la lista de resenas
     */
    public List<ResenaEntity> findResenasHabitacion(Long idHabitacion) {
        return persistence.findAllByHabitacion(idHabitacion);
    }

    /**
     * Retorna una lista con las resenas de una habitacion en especifico cuya
     * calificaion se encuentre dentro de un rango.
     *
     * @param idHabitacion la habitacion
     * @param minimo el minimo del rango
     * @param maximo el maximo del rango
     * @return una lista con las resenas
     * @throws BusinessLogicException si el minimo del rango es mayor al maximo
     * del rango
     */
    public List<ResenaEntity> finResenasHabitacionPorRango(Long idHabitacion,
            Long minimo, Long maximo) throws BusinessLogicException {
        if (minimo > maximo) {
            throw new BusinessLogicException("El rango dado no genera error: el "
                    + "valor minimo debe ser menor al valor maximo");
        }
        return persistence.findAllByHabitacionAndRange(idHabitacion, minimo, maximo);
    }

    /**
     * Retorna un un ResenaEntity a partir de un id dado por parámetro
     *
     * @param id el id del ResenaEntity que se desea buscar
     * @return un VIajeroEntity si se encuentra en la base datos, de lo
     * contrario null
     */
    public ResenaEntity findResena(Long id) {
        return persistence.find(id);
    }

    /**
     * Crea una nueva entidad en la base de datos
     *
     * @param entity entidad que se desea agregar
     * @return la entidad que fue agregada a la base de datos
     * @throws BusinessLogicException
     */
    public ResenaEntity createResena(ResenaEntity entity) throws BusinessLogicException {
        if (entity.getCalificacion() == null 
            || (entity.getCalificacion() < CALIFICACION_MIN) 
            || (entity.getCalificacion() > CALIFICACION_MAX)) {
            
            throw new BusinessLogicException("La reseña debe tener una calificacion que sea entre 0 y 5");
        }
        if (entity.getHabitacion() != null && entity.getViajero() != null) {
            
            Long idViajero = entity.getViajero().getIdUsuario();
            Long idHabitacion = entity.getHabitacion().getId();
            ReservaEntity reserva = persistenceReserva.findReservaFromViajeroAndHabitacion(idViajero, idHabitacion);

            if (reserva == null) {
                
                throw new BusinessLogicException("No se puede generar la resena debido"
                        + " a que no hay registro de que el viajero "
                        + "haya hecho una reserva en la habitacion");

            } 
            else if (reserva.getFechaInicio().compareTo(new Date()) > 0) {
                
                throw new BusinessLogicException("No se puede generar la resena debido "
                        + "a que aun no aun no ha iniciado la fecha inicial de la reserva");
            }
        } 
        else {
            
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
    public ResenaEntity updateResena(ResenaEntity entity) throws BusinessLogicException {
        if (entity.getCalificacion() == null 
            || (entity.getCalificacion() < CALIFICACION_MIN) 
            || (entity.getCalificacion() > CALIFICACION_MAX)) {
            
            throw new BusinessLogicException("La reseña debe tener una calificacion que sea entre 0 y 5");
        }
        if (entity.getHabitacion() != null && entity.getViajero() != null) {
            
            Long idViajero = entity.getViajero().getIdUsuario();
            Long idHabitacion = entity.getHabitacion().getId();
            ReservaEntity reserva = persistenceReserva.findReservaFromViajeroAndHabitacion(idViajero, idHabitacion);

            if (reserva == null) {
                
                throw new BusinessLogicException("No se puede generar la resena debido"
                        + " a que no hay registro de que el viajero "
                        + "haya hecho una reserva en la habitacion");
            } 
            else if (reserva.getFechaInicio().compareTo(new Date()) > 0) {
                
                throw new BusinessLogicException("No se puede generar la resena debido"
                        + " a que aun no aun no ha iniciado la fecha inicial de la reserva");
            }
        } 
        else {
            
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
    public void delete(Long id) {
        persistence.delete(id);

    }
}
