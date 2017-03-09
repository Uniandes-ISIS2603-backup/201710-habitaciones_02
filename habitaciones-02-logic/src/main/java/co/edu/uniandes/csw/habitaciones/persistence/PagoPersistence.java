/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.persistence;

import co.edu.uniandes.csw.habitaciones.entities.PagoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ne.cabrera
 */
@Stateless
public class PagoPersistence 
{
     @PersistenceContext(unitName="habitacionesPU")
    protected EntityManager em;
    
    public PagoEntity find(Long id)
    {
        return em.find(PagoEntity.class, id);
    }
    
    public List<PagoEntity> findAll()
    {
        Query q = em.createQuery("select u from PagoEntity u");
        return q.getResultList();
    }
    
    public PagoEntity create(PagoEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public PagoEntity update(PagoEntity entity)
    {
        em.merge(entity);
        return entity;
    }
    
    public void delete(Long id)
    {
        PagoEntity entity = em.find(PagoEntity.class, id);
        em.remove(entity);
    }
}
