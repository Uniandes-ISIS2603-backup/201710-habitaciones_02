/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.ejbs;


import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import co.edu.uniandes.csw.habitaciones.persistence.AnfitrionPersistence;
/**
 *
 * @author df.sanabria761
 */
@Stateless
public class AnfitrionLogic {
    @Inject
    private AnfitrionPersistence persistence;
    
    public List<AnfitrionEntity> getAnfitriones() 
    {
        return persistence.findAll();
        
    }
    public AnfitrionEntity getAnfitrion(Long id)
    {
        return persistence.find(id);
    }
    public AnfitrionEntity createAnfitrion (AnfitrionEntity entity)
    {
        return persistence.create(entity);
    }
    public AnfitrionEntity updateAnfitrion (AnfitrionEntity entity)
    {
        return persistence.update(entity);
    }
    
    public void deleteAnfitrion(Long id)
    {
        persistence.delete(id);
    }
}
