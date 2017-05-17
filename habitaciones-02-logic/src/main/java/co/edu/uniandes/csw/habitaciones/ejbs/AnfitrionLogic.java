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

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.AnfitrionPersistence;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author df.sanabria761
 */
@Stateless
public class AnfitrionLogic {

    @Inject
    private AnfitrionPersistence persistence;

    public List<AnfitrionEntity> getAnfitriones() {
        return persistence.findAll();

    }

    public AnfitrionEntity getAnfitrion(Long id) {
        return persistence.find(id);
    }

    public AnfitrionEntity createAnfitrion(AnfitrionEntity entity)
            throws BusinessLogicException {
        if (!entity.informacionCompleta()) {
            throw new BusinessLogicException("La información ingresada "
                    + "no está completa. Favor rectificar e intentar nuevamente");
        }
        return persistence.create(entity);
    }

    public AnfitrionEntity updateAnfitrion(AnfitrionEntity entity) {
        AnfitrionEntity en = getAnfitrion(entity.getIdUsuario());
        if (en == null) {
            throw new WebApplicationException("No existe tal"
                    + " anftrión para actualizar ");
        } else {
            return persistence.update(entity);
        }
    }
    
    public AnfitrionEntity getAnfitrionLogin(String pCorreo, String pContrasena) throws BusinessLogicException
    {
        AnfitrionEntity resultado = persistence.findLogin(pCorreo, pContrasena);
        
        if(resultado == null)
        {
            throw new BusinessLogicException("No existe un usuario registrao con los datos "
                    + "que fueron ingresados! Por favor intente nuevamente.");
        }
        
        return resultado;
    }
    public void deleteAnfitrion(Long id) {
        persistence.delete(id);
    }
    public AnfitrionEntity getNextAnfitrion(Long id){
        return persistence.find(id+1);
    }
    public AnfitrionEntity getPrevAnfitrion(Long id) throws BusinessLogicException{
        if(id!=1){
            return persistence.find(id-1);
        }else{
            throw new BusinessLogicException("No hay anfitrion anterior.");
        }
    }
}
