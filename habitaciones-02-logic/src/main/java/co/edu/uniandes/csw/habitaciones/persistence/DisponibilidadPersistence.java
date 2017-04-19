/* 
 * The MIT License
 *
 * Copyright 2017 ne.cabrera.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
public class DisponibilidadPersistence {

    @PersistenceContext(unitName = "habitacionesPU")
    protected EntityManager em;

    /**
     * Encuentra y retorna una disponibilidad de una habitacion
     * @param idHabitacion
     * @param id
     * @return Disponibilidad
     */
    public DisponibilidadEntity find(Long idHabitacion, Long id) {
        TypedQuery<DisponibilidadEntity> q = em.createQuery("select p from DisponibilidadEntity p where (p.habitacion.id = :idHabitacion) and (p.id = :id)", DisponibilidadEntity.class);
        q.setParameter("idHabitacion", idHabitacion);
        q.setParameter("id", id);
        return q.getSingleResult();
    }
   
    /**
     * Encuentra y retorna todas las disponibilidades de un habitacion
     * @param idHabitacion
     * @return Lista de disponibilidades
     */
    public List<DisponibilidadEntity> findAll(Long idHabitacion) {

        Query q = em.createQuery("select u from DisponibilidadEntity u where (u.habitacion.id = :idHabitacion) ", DisponibilidadEntity.class);
        q.setParameter("idHabitacion", idHabitacion);
        return q.getResultList();
    }

    /**
     * Crea una nueva disponibilidad
     * @param entity
     * @return Disponibilidad
     */
    public DisponibilidadEntity create( DisponibilidadEntity entity) {
        
        em.persist(entity);

        return entity;
    }

    /**
     * Modifica una disponibilidad ya existente
     * @param entity
     * @return Disponibiliad
     */
    public DisponibilidadEntity update(DisponibilidadEntity entity) {

        return em.merge(entity);

    }

    /**
     * Elimina una disponibilidad ya existente
     * @param id de la disponibilidad
     */
    public void delete(Long id) {

        DisponibilidadEntity entity = em.find(DisponibilidadEntity.class, id);
        em.remove(entity);
    }
}
