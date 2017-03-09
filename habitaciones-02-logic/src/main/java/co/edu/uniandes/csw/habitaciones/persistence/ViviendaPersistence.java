/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.persistence;

import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
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
public class ViviendaPersistence 
{
    @PersistenceContext(unitName="habitacionesPU")
    protected EntityManager em;
    
    public ViviendaEntity find(Long id)
    {
        return em.find(ViviendaEntity.class, id);
    }
    
    public List<ViviendaEntity> findAll()
    {
        Query q = em.createQuery("select u from ViviendaEntity u");
        return q.getResultList();
    }
    
    public ViviendaEntity create(ViviendaEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public ViviendaEntity update(ViviendaEntity entity)
    {
        em.merge(entity);
        return entity;
    }
    
    public void delete(Long id)
    {
        ViviendaEntity entity = em.find(ViviendaEntity.class, id);
        em.remove(entity);
    }
}
