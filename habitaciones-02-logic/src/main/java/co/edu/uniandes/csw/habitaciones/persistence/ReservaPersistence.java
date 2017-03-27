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
import javax.persistence.TypedQuery;

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
    
    public List<ReservaEntity> findAllByViajero(Long idViajero)
    {
        TypedQuery<ReservaEntity> q
                = em.createQuery("select u from ReservaEntity u where u.viajero.idUsuario = :idViajero", ReservaEntity.class);
        q = q.setParameter("idViajero", idViajero);

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
    
    /**
     * Este metodo verifica si existe una reserva que relacione a un viajero con una habitaci'on
     * @param idViajero el id del viajero que se quiere relacionar a una habitacion
     * @param idHabitacion el id de una habitacion que se quiere relacionar con un viajero
     * @return true si existe una reserva con dicha relacion, false de lo contrario.
     */
    public ReservaEntity findReservaFromViajeroAndHabitacion(Long idViajero, Long idHabitacion)
    {
       TypedQuery<ReservaEntity> q
                = em.createQuery("select u from ResenaEntity u where u.viajero.idUsuario = :idViajero AND u.habitacion.id = :idHabitacion AND u.cancelado = 0 ORDER BY u.FECHAINICIO DESC", ReservaEntity.class);
        q = q.setParameter("idViajero", idViajero);
        q = q.setParameter("idHabitacion", idHabitacion);
        
       List<ReservaEntity> sameCorreo = q.getResultList();
        if (sameCorreo.isEmpty() ) 
        {
            return null; 
        } else 
        {
            return sameCorreo.get(0);
        }
    }
}
