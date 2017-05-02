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

import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.ReservaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author dg.guarin20
 */
@Stateless
public class ReservaLogic {

    /**
     * La persistencia de la reserva
     */
    @Inject
    private ReservaPersistence persistence;

    /**
     * Encuentra y retorna una lista de todas las reservas de entity.
     *
     * @return una lista de las entidades de la reserva
     */
    public List<ReservaEntity> findReservas() {
        return persistence.findAll();
    }

    /**
     * Retorna una lista de las reservas por el id del viajero.
     *
     * @param idViajero
     * @return una lista de las entidades de reserva
     */
    public List<ReservaEntity> findReservasByViajero(Long idViajero) {
        return persistence.findAllByViajero(idViajero);
    }

    /**
     * Retorna una reserva por su id
     *
     * @param id
     * @return retorna una reserva
     */
    public ReservaEntity findReserva(Long id) {
        return persistence.find(id);
    }

    /**
     * Crea la reserva y tiene que revisar si cumple con los parametros de
     * acuerdo de ellos.
     *
     * @param entity
     * @return entitad de la reserva creada
     * @throws BusinessLogicException
     */
    public ReservaEntity createReserva(ReservaEntity entity) throws BusinessLogicException { //TODO no hay ninguna validación de las reglas de negocio. QUé pasa si la habitación no está disponible
        //TOD qué pasa con la validez de las fechas de las reservas
        if (!entity.informacionCompleta()) {
            throw new BusinessLogicException("falta informacion");
        }
        if (!entity.checkInfoFechas()) {
            throw new BusinessLogicException("las fechas no estan en orden ");
        }
        if (!entity.checkInfo()) {
            throw new BusinessLogicException("Ya tiene reservas en esta fecha");
        }

        return persistence.create(entity);
    }

    /**
     * hace un cambio en una entidad ya creada, pero primero verifica si cumple.
     *
     * @param entity
     * @return la entidad de la reserva modificada
     * @throws BusinessLogicException
     */
    public ReservaEntity updateReserva(ReservaEntity entity) throws BusinessLogicException {//TODO no hay ninguna validación de las reglas de negocio. QUé pasa si la habitación no está disponible
        //TOD qué pasa con la validez de las fechas de las reservas
        if (!entity.informacionCompleta()) {
            throw new BusinessLogicException("falta informacion");
        }
        if (!entity.checkInfoFechas()) {
            throw new BusinessLogicException("las fechas no estan en orden ");
        }
        if (!entity.checkInfo()) {
            throw new BusinessLogicException("Ya tiene reservas en esta fecha");
        }

        return persistence.update(entity);
    }

    /**
     * Elimina la reserva con el id entrado en parametro
     *
     * @param id
     */
    public void delete(Long id) {
        persistence.delete(id);

    }

}
