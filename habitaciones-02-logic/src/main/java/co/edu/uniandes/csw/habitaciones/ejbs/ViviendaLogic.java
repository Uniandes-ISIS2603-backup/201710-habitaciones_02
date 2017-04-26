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

import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.ViviendaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ne.cabrera
 */
@Stateless
public class ViviendaLogic
{

    /**
     * persistencia para la entidad de la vivienda
     */
    @Inject
    private ViviendaPersistence persistence;

    /**
     * @return lista de viviendas
     */
    public List<ViviendaEntity> getViviendas()
    {
        return persistence.findAll();
    }

    /**
     * @param id el id de la vivienda buscada
     * @return vivienda con el mismo id al dado por parametro
     */
    public ViviendaEntity getVivienda(Long id)
    {
        return persistence.find(id);
    }

    /**
     * @param vivienda vivienda a agregar a la persistencia
     * @return la vivienda agregada
     * @throws BusinessLogicException si los datos de la vivienda estan
     * incompletos
     */
    public ViviendaEntity createVivienda(ViviendaEntity vivienda) throws BusinessLogicException
    {
        //if(vivienda.getHabitaciones().isEmpty())
        //{
        //    throw new BusinessLogicException("Se debe agregar almenos 1 habitacion");
        //}
        //else
        //{
        if (!vivienda.informacionCompleta())
        {
            throw new BusinessLogicException("Algunos de los datos para registrar la "
                    + "vivienda no fueron ingresados. Por favor, intente nuevamente");
        } else
        {
            persistence.create(vivienda);
        }
        //}
        return vivienda;
    }

    /**
     * @param vivienda la vivienda a actualizar
     * @return la vivienda actulizada
     */
    public ViviendaEntity updateVivienda(ViviendaEntity vivienda)
    {
        return persistence.update(vivienda);
    }

    /**
     * @param id el id de la vivienda a eliminar
     */
    public void deleteVivienda(Long id)
    {
        persistence.delete(id);
    }
}
