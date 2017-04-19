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
import javax.persistence.TypedQuery;

/**
 *
 * @author b.gamba10
 */
@Stateless
public class DisponibilidadPersistence
{

    @PersistenceContext(unitName = "habitacionesPU")
    protected EntityManager em;

    /**
     * Encuentra y retorna una disponibilidad de una habitacion
     *
     * @param idHabitacion
     * @param id
     * @return Disponibilidad
     */
    public DisponibilidadEntity find(Long idHabitacion, Long id)
    {
        TypedQuery<DisponibilidadEntity> q = em.createQuery("select p from DisponibilidadEntity p where (p.habitacion.id = :idHabitacion) and (p.id = :id)", DisponibilidadEntity.class);
        q.setParameter("idHabitacion", idHabitacion);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    /**
     * Encuentra y retorna todas las disponibilidades de un habitacion
     *
     * @param idHabitacion
     * @return Lista de disponibilidades
     */
    public List<DisponibilidadEntity> findAll(Long idHabitacion)
    {

        Query q = em.createQuery("select u from DisponibilidadEntity u where (u.habitacion.id = :idHabitacion) ", DisponibilidadEntity.class);
        q.setParameter("idHabitacion", idHabitacion);
        return q.getResultList();
    }

    /**
     * Crea una nueva disponibilidad
     *
     * @param entity
     * @return Disponibilidad
     */
    public DisponibilidadEntity create(DisponibilidadEntity entity)
    {

        em.persist(entity);

        return entity;
    }

    /**
     * Modifica una disponibilidad ya existente
     *
     * @param entity
     * @return Disponibiliad
     */
    public DisponibilidadEntity update(DisponibilidadEntity entity)
    {

        return em.merge(entity);

    }

    /**
     * Elimina una disponibilidad ya existente
     *
     * @param id de la disponibilidad
     */
    public void delete(Long id)
    {

        DisponibilidadEntity entity = em.find(DisponibilidadEntity.class, id);
        em.remove(entity);
    }
}
