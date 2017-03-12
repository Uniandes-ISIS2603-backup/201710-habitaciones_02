/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.ejbs;
import co.edu.uniandes.csw.habitaciones.entities.ResenaEntity;
import co.edu.uniandes.csw.habitaciones.persistence.ResenaPersistence;
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
     public ResenaEntity createResena(ResenaEntity entity)
    {
        return persistence.create(entity);
    }
     
      public ResenaEntity updateResena(ResenaEntity entity)
    {
        return persistence.update(entity);
    }
      
    public void delete(Long id)
    {
        persistence.delete(id);
        
    }
}
