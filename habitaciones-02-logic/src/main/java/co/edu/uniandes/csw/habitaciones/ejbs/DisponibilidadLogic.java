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
public class DisponibilidadLogic {

    @Inject
    private DisponibilidadPersistence persistence;

    /**
     * Retorna una disponibilidad de una habitacion
     *
     * @param idHabitacion
     * @param id
     * @return
     */
    public DisponibilidadEntity getDisponibilidad(Long idHabitacion, Long id) {

        return persistence.find(idHabitacion, id);
    }

    /**
     * Retorna todas las disponibilidades de una habitacion
     *
     * @param idHabitacion
     * @return
     */
    public List<DisponibilidadEntity> getDisponibilidades(Long idHabitacion) {

        return persistence.findAll(idHabitacion);
    }

    /**
     * Crea una nueva disponibilidad
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public DisponibilidadEntity createDisponibilidad(DisponibilidadEntity entity) throws BusinessLogicException {

        if (entity.getFechaInicioEstadia().after(entity.getFechaTerminacionEstadia())) {

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
    public DisponibilidadEntity updateDisponibilidad(DisponibilidadEntity entity) throws BusinessLogicException {

        if (entity.getFechaInicioEstadia().after(entity.getFechaTerminacionEstadia())) {

            throw new BusinessLogicException("La fecha de termincion de la estadia debe ser posterior a la del inicio.");

        }
        return persistence.update(entity);
    }

    /**
     * Elimina una disponibilidad
     *
     * @param id
     */
    public void deleteDisponibilidad(Long id) {

        persistence.delete(id);
    }
}
