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

import co.edu.uniandes.csw.habitaciones.entities.ViajeroEntity;
import co.edu.uniandes.csw.habitaciones.persistence.ViajeroPersistence;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.cortes
 */
@Stateless
public class ViajeroLogic
{
    //----------------------------------------------------------------------------------------------------
    // ATRIBUTOS
    //----------------------------------------------------------------------------------------------------

    /**
     * persistencia para la entidad del viajero
     */
    @Inject
    private ViajeroPersistence persistence;

    //----------------------------------------------------------------------------------------------------
    // METODOS
    //----------------------------------------------------------------------------------------------------
    /**
     * Crea una nueva entidad en la base de datos
     *
     * @param entity entidad que se desea agregar
     * @return la entidad que fue agregada a la base de datos
     * @throws BusinessLogicException
     */
    public ViajeroEntity createViajero(ViajeroEntity entity) throws BusinessLogicException
    {
        if (!entity.informacionCompleta())
        {

            throw new BusinessLogicException("Algunos de los datos para registrase"
                    + " no fueron ingresados. Por favor, intente nuevamente");
        }

        return persistence.create(entity);
    }

    /**
     * Actualiza la información de un ViajeroEntoty que se encuentre en la base
     * de datos
     *
     * @param entity la entidad que desea actualizar
     * @return la entidad actualizada
     * @throws BusinessLogicException
     */
    public ViajeroEntity updateViajero(ViajeroEntity entity) throws BusinessLogicException
    {
        if (!entity.informacionCompleta())
        {
            throw new BusinessLogicException("Algunos de los datos para registrase"
                    + " no fueron ingresados. Por favor, intente nuevamente ["
                    + "contraseña: "+entity.getContrasena()
                    + ", correo: " + entity.getCorreoElectronico()
                    + ", direccion: " + entity.getDireccion()
                    + ", nombre " + entity.getNombre()
                    + ", tipoD: " + entity.getTipoDocumento()
                    + ", telefono: " + entity.getTelefono()
                    + ", numeroD: " + entity.getNumeroDocumento() + "]");
        }
        return persistence.update(entity);
    }

    /**
     * Retorna un un ViajeroEntity a partir de un id dado por parámetro
     *
     * @param id el id del ViajeroEntity que se desea buscar
     * @return un VIajeroEntity si se encuentra en la base datos, de lo
     * contrario null
     */
    public ViajeroEntity getViajero(Long id)
    {
        return persistence.find(id);
    }

    /**
     * Retorna una lista con todos los viajeros que se encuentran persistidos
     *
     * @return
     */
    public List<ViajeroEntity> getViajeros()
    {
        return persistence.findAll();
    }

    /**
     * Elimina un viajero a partir del id dado por parámetro
     *
     * @param id
     */
    public void deleteViajero(Long id)
    {
        persistence.delete(id);
    }

}
