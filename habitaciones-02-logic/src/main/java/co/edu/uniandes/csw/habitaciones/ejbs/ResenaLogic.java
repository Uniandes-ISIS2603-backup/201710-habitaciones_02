/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.ejbs;
import co.edu.uniandes.csw.habitaciones.entities.ResenaEntity;
import co.edu.uniandes.csw.habitaciones.persistence.ResenaPersistence;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.cortes
 */
@Stateless
public class ResenaLogic
{
    @Inject private ResenaPersistence persistence;
    
    public List<ResenaEntity> findResenas()
    {
        return persistence.findAll();
    }
   
    public ResenaEntity findResena(Long id)
    {
        return persistence.find(id);
    }
     public ResenaEntity createResena(ResenaEntity entity) throws BusinessLogicException
    {
        if(entity.getCalificacion() == null || (entity.getCalificacion() < 0) || (entity.getCalificacion() > 5))
        {
            throw new BusinessLogicException("La resena debe tener un acalificacion que sea entre 0 y 5");
        }
        else if(entity.getHabitacion() == null)
        {
            throw new BusinessLogicException("La resena debe estar dirijida a una habitacion especifica");
        }
        
        return persistence.create(entity);
    }
     
     public ResenaEntity updateResena(ResenaEntity entity) throws BusinessLogicException
    {
        if(entity.getCalificacion() == null || (entity.getCalificacion() < 0) || (entity.getCalificacion() > 5))
        {
            throw new BusinessLogicException("La resena debe tener un acalificacion que sea entre 0 y 5");
        }
        return persistence.update(entity);
    }
      
    public void delete(Long id)
    {
        persistence.delete(id);
        
    }
}
