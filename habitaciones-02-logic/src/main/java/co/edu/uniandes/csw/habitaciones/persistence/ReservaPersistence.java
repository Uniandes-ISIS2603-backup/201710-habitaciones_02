/* 
 * The MIT License
 *
 * Copyright 2017 Los Favoritos.
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

    /**
     * Entity manager de la persistencia
     */
    @PersistenceContext(unitName = "habitacionesPU")
    protected EntityManager em;

    /**
     * Busca la clase reservaEntity por el id y la retorna
     *
     * @param id
     * @return ReservaEntity
     */
    public ReservaEntity find(Long id) {
        return em.find(ReservaEntity.class, id);

    }

    /**
     * Crea una lista que retorna con todas las clases de ReservaEntity
     *
     * @return List de ReservaEntity
     */
    public List<ReservaEntity> findAll() {
        Query q = em.createQuery("select u from ReservaEntity u");
        return q.getResultList();
    }

    /**
     * Busca todas las clases ReservaEntity que tenga un viajero por su id.
     *
     * @param idViajero
     * @return List de ReservaEntity
     */
    public List<ReservaEntity> findAllByViajero(Long idViajero) {
        TypedQuery<ReservaEntity> q
                = em.createQuery("select u from ReservaEntity u where u.viajero.idUsuario = :idViajero", ReservaEntity.class);
        q = q.setParameter("idViajero", idViajero);

        return q.getResultList();
    }

    /**
     * Crea una clase ReservaEntity a con el entity dado en parametro y la
     * retorna
     *
     * @param entity
     * @return la clase ReservaEntity
     */
    public ReservaEntity create(ReservaEntity entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * Actualiza una reserva con lo que se da en parametro y la retorna
     *
     * @param entity
     * @return
     */
    public ReservaEntity update(ReservaEntity entity) {
        em.merge(entity);
        return entity;
    }

    /**
     * Elimina una reserva por su eliminacion
     *
     * @param id
     */
    public void delete(Long id) {
        ReservaEntity entity = em.find(ReservaEntity.class, id);
        em.remove(entity);

    }

    /**
     * Este metodo verifica si existe una reserva que relacione a un viajero con
     * una habitacion
     *
     * @param idViajero el id del viajero que se quiere relacionar a una
     * habitacion
     * @param idHabitacion el id de una habitacion que se quiere relacionar con
     * un viajero
     * @return true si existe una reserva con dicha relacion, false de lo
     * contrario.
     */
    public ReservaEntity findReservaFromViajeroAndHabitacion(Long idViajero, Long idHabitacion) {
        TypedQuery<ReservaEntity> q
                = em.createQuery("select u from ResenaEntity u where u.viajero.idUsuario = :idViajero AND u.habitacion.id = :idHabitacion AND u.cancelado = 0 ORDER BY u.FECHAINICIO DESC", ReservaEntity.class);
        q = q.setParameter("idViajero", idViajero);
        q = q.setParameter("idHabitacion", idHabitacion);

        List<ReservaEntity> sameCorreo = q.getResultList();
        if (sameCorreo.isEmpty()) {
            return null;
        } else {
            return sameCorreo.get(0);
        }
    }
}
