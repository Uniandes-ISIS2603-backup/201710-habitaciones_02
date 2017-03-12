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
    @Inject private ViviendaPersistence persistence;
    
    public List<ViviendaEntity> getViviendas()
    {
        return persistence.findAll();
    }
    
    public ViviendaEntity getVivienda(Long id)
    {
        return persistence.find(id);
    }
    
    public ViviendaEntity createVivienda(ViviendaEntity vivienda) throws BusinessLogicException
    {
        //if(vivienda.getHabitaciones().isEmpty())
        //{
        //    throw new BusinessLogicException("Se debe agregar almenos 1 habitacion");
        //}
        //else
        //{
            persistence.create(vivienda);
        //}
        return vivienda;
    }
    
    public ViviendaEntity updateVivienda(ViviendaEntity vivienda)
    {
        return persistence.update(vivienda);
    }
    
    public void deleteVivienda( Long id )
    {
        persistence.delete(id);
    }
}
