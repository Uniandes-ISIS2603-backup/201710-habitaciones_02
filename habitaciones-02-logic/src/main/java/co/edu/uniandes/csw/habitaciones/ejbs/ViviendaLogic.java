/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
            throw new BusinessLogicException("Algunos de los datos para registrar la vivienda no fueron ingresados. Por favor, intente nuevamente");
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
