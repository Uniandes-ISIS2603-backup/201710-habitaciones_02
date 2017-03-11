/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.ejbs;

import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.persistence.ReservaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author dg.guarin20
 */
@Stateless
public class ReservaLogic {
    
    @Inject private ReservaPersistence persistence;
  
    public List<ReservaEntity> findReservas()
    {
        return persistence.findAll();
    }
   
    public ReservaEntity findReserva(Long id)
    {
        return persistence.find(id);
    }
     public ReservaEntity createReserva(ReservaEntity entity)
    {
        return persistence.create(entity);
    }
     
      public ReservaEntity updateReserva(ReservaEntity entity)
    {
        return persistence.update(entity);
    }
      
    public void delete(Long id)
    {
        persistence.delete(id);
        
    }

}
