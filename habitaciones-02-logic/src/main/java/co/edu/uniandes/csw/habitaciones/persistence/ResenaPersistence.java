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
    /**
     * Entity manager de la persistencia
     */
    @PersistenceContext(unitName="habitacionesPU")
    protected EntityManager em;
    
    
    /**
     * retorna un Resena entity de acuerdo a un ID
     * @param id el id de la entidad que se desea encontrar
     * @return null si no encuentra la entidad con el ID, la entidad si la encuentra
     */
    public ResenaEntity find(Long id)
    {
        return em.find(ResenaEntity.class, id);
        
    }
    
    /**
     * Retorna una lista con todas las entidades (tuplas) de la tabla
     * @return lista de entidades Resena
     */
    public List<ResenaEntity> findAll()
    {
     Query q = em.createQuery("select u from ReservaEntity u");
     return q.getResultList();
        
    }
    
    /**
     * inserta a la tabla de Resenas un nuevo ViajeroEntity
     * @param entity la entidad que se desea agregar
     * @return  la entidad que fue agregada
     */
    public ResenaEntity create(ResenaEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    /**
     * modifica la información de un ResenaEntity
     * @param entity la entidad que se desea actualizar 
     * @return la entidad que fue actualizada
     */
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
