/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.habitaciones.entities.ViajeroEntity;
import java.util.List;
import javax.persistence.Query;
/**
 *
 * @author s.cortes
 */
@Stateless
public class ViajeroPersistence
{
    
    //----------------------------------------------------------------------------------------------------
    // ATRIBUTOS
    //----------------------------------------------------------------------------------------------------
    
    @PersistenceContext(unitName="habitacionesPU")
    protected EntityManager em;
    
    
    //----------------------------------------------------------------------------------------------------
    // METODOS
    //----------------------------------------------------------------------------------------------------
    
    /**
     * 
     * @param entity
     * @return 
     */
    public ViajeroEntity create(ViajeroEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    public ViajeroEntity update(ViajeroEntity entity)
    {
        return em.merge(entity);
    }
    
    public List<ViajeroEntity> findAll()
    {
        Query query = em.createQuery("select u from ViajeroEntity u");
        return query.getResultList();
    }
    
    public ViajeroEntity find(Long id)
    {
        return em.find(ViajeroEntity.class, id);
    }
    
    public void delete(Long id)
    {
        ViajeroEntity entity = em.find(ViajeroEntity.class, id);
        em.remove(entity);
    }
    
}
