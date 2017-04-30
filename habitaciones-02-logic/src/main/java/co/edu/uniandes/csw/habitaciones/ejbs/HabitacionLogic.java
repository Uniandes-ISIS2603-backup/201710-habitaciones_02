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

import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.HabitacionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author b.gamba10
 */
@Stateless
public class HabitacionLogic {

    @Inject
    private HabitacionPersistence persistence;

    /**
     * Retorna una lista con todas las habitaciones
     *
     * @return
     */
    public List<HabitacionEntity> getHabitaciones() {

        return persistence.findAll();
    }

    //Hecho por Nicolas
    /**
     * Retorna las habitaciones de una vivienda
     *
     * @param idVivienda
     * @return
     */
    public List<HabitacionEntity> getHabitacionesVivienda(Long idVivienda) {

        return persistence.findAllVivienda(idVivienda);
    }

    /**
     * Retorna una habitacion con su id dado por parametro
     *
     * @param id
     * @return
     */
    public HabitacionEntity getHabitacion(Long id) {

        return persistence.find(id);
    }

    /**
     * Crea una nueva habitacion
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public HabitacionEntity createHabitacion(HabitacionEntity entity) throws BusinessLogicException {

        String problemas = "Se generaron errores al intentar agregar una habitacion:\n";
        Boolean problema = false;

        if (!entity.informacionCompleta()) {
            problemas += "La informacion no esta completa:\n"
                    + "   -Area: <" + entity.getArea().toString() + ">\n"
                    + "   -Valor Alquier: <" + entity.getValorAlquiler().toString() + ">\n"
                    + "   -Ruta imagen: <" + entity.getRutaImagen() + ">\n"
                    + "   -Capacidad: <" + entity.getCapacidad() + ">\n"
                    + "   -Descripcion: <" + entity.getDescripcion() + ">\n";
            problema = true;
        } else {
            if (entity.getArea() <= 0) {
                problemas += "El area debe ser mayor que cero. \n";
                problema = true;
            }

            if (entity.getValorAlquiler() <= 0) {
                problemas += "El valor del alquiler debe ser mayor que cero.";
                problema = true;
            }

            if (entity.getCapacidad() <= 0) {
                problemas += "La capacidad debe ser mayor que cero.";
                problema = true;
            }

        }

        if (problema) {
            throw new BusinessLogicException(problemas);
        }

        persistence.create(entity);
        return entity;

    }

    /**
     * Modifica una habitacion ya existente
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public HabitacionEntity updateHabitacion(HabitacionEntity entity) throws BusinessLogicException {

        String problemas = "Se generaron errores al intentar actualizar la habitacion:\n";
        Boolean problema = false;

        if (!entity.informacionCompleta()) {
            problemas += "La informacion no esta completa:\n"
                    + "   -Area: <" + entity.getArea().toString() + ">\n"
                    + "   -Valor Alquier: <" + entity.getValorAlquiler().toString() + ">\n"
                    + "   -Ruta imagen: <" + entity.getRutaImagen() + ">\n"
                    + "   -Descripcion: <" + entity.getDescripcion() + ">\n";
            problema = true;
        } else {
            if (entity.getArea() <= 0) {
                problemas += "El area debe ser mayor que cero. \n";
                problema = true;
            }

            if (entity.getValorAlquiler() <= 0) {
                problemas += "El valor del alquiler debe ser mayor que cero.";
                problema = true;
            }

        }

        if (problema) {
            throw new BusinessLogicException(problemas);
        }

        return persistence.update(entity);

    }

    /**
     * Elimina una habitacion
     *
     * @param id
     */
    public void deleteHabitacion(Long id) {

        persistence.delete(id);
    }
}
