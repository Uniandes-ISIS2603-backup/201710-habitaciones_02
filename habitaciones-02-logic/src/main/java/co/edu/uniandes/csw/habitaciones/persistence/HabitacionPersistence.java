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
public class HabitacionPersistence
{

    @PersistenceContext(unitName = "habitacionesPU")
    protected EntityManager em;

    /**
     * Encuentra y retorna una habitacion por su id
     *
     * @param id de la habitacion
     * @return Habitacion
     */
    public HabitacionEntity find(Long id)
    {
        return em.find(HabitacionEntity.class, id);
    }

    /**
     * Retorna todas las habitaciones
     *
     * @return lista con las habitaciones
     */
    public List<HabitacionEntity> findAll()
    {

        Query q = em.createQuery("select u from HabitacionEntity u");
        return q.getResultList();
    }

    //Hecho por Nicolas, es parte de un TODO
    /**
     * Encuentra todas las habitaciones de una vivienda
     *
     * @param idVivienda
     * @return
     */
    public List<HabitacionEntity> findAllVivienda(Long idVivienda)
    {

        Query q = em.createQuery("select u from HabitacionEntity u where (u.vivienda.id = :idVivienda) ", HabitacionEntity.class);
        q.setParameter("idVivienda", idVivienda);
        return q.getResultList();
    }

    /**
     * Crea una nueva habitacion
     *
     * @param entity
     * @return
     */
    public HabitacionEntity create(HabitacionEntity entity)
    {

        em.persist(entity);

        return entity;
    }

    /**
     * Modidifca una habitacion ya existente
     *
     * @param entity
     * @return Habitacion
     */
    public HabitacionEntity update(HabitacionEntity entity)
    {

        return em.merge(entity);

    }

    /**
     * Elimina una habitacion
     *
     * @param id de la habitacion
     */
    public void delete(Long id)
    {

        HabitacionEntity entity = em.find(HabitacionEntity.class, id);
        em.remove(entity);
    }

}
