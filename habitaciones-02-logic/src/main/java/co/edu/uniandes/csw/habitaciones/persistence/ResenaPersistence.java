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

import co.edu.uniandes.csw.habitaciones.entities.ResenaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
    @PersistenceContext(unitName = "habitacionesPU")
    protected EntityManager em;

    /**
     * retorna un Resena entity de acuerdo a un ID
     *
     * @param id el id de la entidad que se desea encontrar
     * @return null si no encuentra la entidad con el ID, la entidad si la
     * encuentra
     */
    public ResenaEntity find(Long id)
    {
        return em.find(ResenaEntity.class, id);

    }

    /**
     * Retorna una lista con todas las entidades (tuplas) de la tabla
     *
     * @return lista de entidades Resena
     */
    public List<ResenaEntity> findAll()
    {
        Query q = em.createQuery("select u from ReservaEntity u");
        return q.getResultList();

    }

    public List<ResenaEntity> findAllByViajero(Long idViajero)
    {
        TypedQuery<ResenaEntity> q
                = em.createQuery("select u from ResenaEntity u where u.viajero.idUsuario = :idViajero", ResenaEntity.class);
        q = q.setParameter("idViajero", idViajero);

        return q.getResultList();

    }

    public List<ResenaEntity> findAllByHabitacion(Long idHabitacion)
    {
        TypedQuery<ResenaEntity> q
                = em.createQuery("select u from ResenaEntity u where u.habitacion.id = :idHabitacion", ResenaEntity.class);
        q = q.setParameter("idHabitacion", idHabitacion);

        return q.getResultList();
    }

    /**
     * inserta a la tabla de Resenas un nuevo ViajeroEntity
     *
     * @param entity la entidad que se desea agregar
     * @return la entidad que fue agregada
     */
    public ResenaEntity create(ResenaEntity entity)
    {
        em.persist(entity);
        return entity;
    }

    /**
     * modifica la información de un ResenaEntity
     *
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
