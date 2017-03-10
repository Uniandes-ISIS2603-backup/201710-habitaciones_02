/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.persistence;

import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dg.guarin20
 */
@Stateless
public class ReservaPersistence {
    
    @PersistenceContext(unitName="habitacionesPU")
    protected EntityManager em;
    
    public ReservaEntity find(Long id)
    {
        return em.find(ReservaEntity.class, id);
        
    }
    public List<ReservaEntity> findAll()
    {
     Query q = em.createQuery("select u from ReservaEntity u");
     return q.getResultList();
        
    }
    public ReservaEntity create(ReservaEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public ReservaEntity update(ReservaEntity entity)
    {
        em.merge(entity);
        return entity;
    }
    public void delete(Long id)
    {
        ReservaEntity entity = em.find(ReservaEntity.class, id);
        em.remove(entity);
        
    }
    
}
