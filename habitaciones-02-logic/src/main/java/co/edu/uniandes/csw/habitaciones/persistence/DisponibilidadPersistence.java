/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.persistence;

import co.edu.uniandes.csw.habitaciones.entities.DisponibilidadEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author b.gamba10
 */
@Stateless
public class DisponibilidadPersistence {
    
    @PersistenceContext(unitName = "habitacionesPU")
    protected EntityManager em;
    
    public DisponibilidadEntity find(Long id) {
        return em.find(DisponibilidadEntity.class, id);
    }
    
    public List<DisponibilidadEntity> findAll() {
        
        Query q = em.createQuery("select u from DisponibilidadEntity u");
        return q.getResultList();
    }
    
    public DisponibilidadEntity create(DisponibilidadEntity entity) {
        
        em.persist(entity);
        
        return entity;
    }
    
    public DisponibilidadEntity update(DisponibilidadEntity entity) {
        
        return em.merge(entity);
        
    }
    
    public void delete(Long id) {
        
        DisponibilidadEntity entity = em.find(DisponibilidadEntity.class, id);
        em.remove(entity);
    }
}
