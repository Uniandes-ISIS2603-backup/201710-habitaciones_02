/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.ejbs;

import co.edu.uniandes.csw.habitaciones.entities.ViajeroEntity;
import co.edu.uniandes.csw.habitaciones.persistence.ViajeroPersistence;
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
    
    @Inject
    private ViajeroPersistence persistence;

    //----------------------------------------------------------------------------------------------------
    // METODOS
    //----------------------------------------------------------------------------------------------------

    
    /**
     * Crea una nueva entidad en la base de datos
     * @param entity entidad que se desea agregar
     * @return la entidad que fue agregada a la base de datos
     */
    public ViajeroEntity createViajero (ViajeroEntity entity)
    {
        return persistence.create(entity);
    }
    
    /**
     * Actualiza la información de un ViajeroEntoty que se encuentre en la base de datos
     * @param entity la entidad que desea actualizar
     * @return la entidad actualizada
     */
    public ViajeroEntity updateViajero (ViajeroEntity entity)
    {
        return persistence.update(entity);
    }
    
    /**
     * Retorna un un ViajeroEntity a partir de un id dado por parámetro
     * @param id el id del ViajeroEntity que se desea buscar
     * @return un VIajeroEntity si se encuentra en la base datos, de lo contrario null
     */
    public ViajeroEntity getViajero(Long id)
    {
        return persistence.find(id);
    }
    
    /**
     * Retorna una lista con todos los viajeros que se encuentran persistidos
     * @return 
     */
    public List<ViajeroEntity> getViajeros()
    {
        return persistence.findAll();
    }
    
    /**
     * Elimina un viajero a partir del id dado por parámetro
     * @param id 
     */
    public void removeViajero(Long id)
    {
        persistence.delete(id);
    }
    
}