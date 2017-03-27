/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.persistence;

import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author b.gamba10
 */
@Stateless
public class HabitacionPersistence {
    
    @PersistenceContext(unitName = "habitacionesPU")
    protected EntityManager em;
    
    /**
     * Encuentra y retorna una habitacion por su id
     * @param id de la habitacion
     * @return Habitacion
     */
    public HabitacionEntity find(Long id) {
        return em.find(HabitacionEntity.class, id);
    }
    
    /**
     * Retorna todas las habitaciones
     * @return lista con las habitaciones
     */
    public List<HabitacionEntity> findAll() {
        
        Query q = em.createQuery("select u from HabitacionEntity u");
        return q.getResultList();
    }
    
    //Hecho por Nicolas, es parte de un TODO
    /**
     * Encuentra todas las habitaciones de una vivienda
     * @param idVivienda
     * @return 
     */
     public List<HabitacionEntity> findAllVivienda(Long idVivienda) {

        Query q = em.createQuery("select u from HabitacionEntity u where (u.vivienda.id = :idVivienda) ", HabitacionEntity.class);
        q.setParameter("idVivienda", idVivienda);
        return q.getResultList();
    }
    
     /**
      * Crea una nueva habitacion
      * @param entity
      * @return 
      */
    public HabitacionEntity create(HabitacionEntity entity) {
        
        em.persist(entity);
        
        return entity;
    }
    
    /**
     * Modidifca una habitacion ya existente
     * @param entity
     * @return Habitacion
     */
    public HabitacionEntity update(HabitacionEntity entity) {
        
        return em.merge(entity);
        
    }
    
    /**
     * Elimina una habitacion
     * @param id de la habitacion
     */
    public void delete(Long id) {
        
        HabitacionEntity entity = em.find(HabitacionEntity.class, id);
        em.remove(entity);
    }
    
}
