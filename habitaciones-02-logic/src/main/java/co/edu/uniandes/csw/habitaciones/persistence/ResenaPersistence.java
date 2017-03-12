/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.persistence;

import co.edu.uniandes.csw.habitaciones.entities.ResenaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author s.cortes
 */
@Stateless
public class ResenaPersistence
{
    @PersistenceContext(unitName="habitacionesPU")
    protected EntityManager em;
    
    public ResenaEntity find(Long id)
    {
        return em.find(ResenaEntity.class, id);
        
    }
    public List<ResenaEntity> findAll()
    {
     Query q = em.createQuery("select u from ReservaEntity u");
     return q.getResultList();
        
    }
    public ResenaEntity create(ResenaEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public ResenaEntity update(ResenaEntity entity)
    {
        em.merge(entity);
        return entity;
    }
    public void delete(Long id)
    {
        ResenaEntity entity = em.find(ResenaEntity.class, id);
        em.remove(entity);
        
    }
}
