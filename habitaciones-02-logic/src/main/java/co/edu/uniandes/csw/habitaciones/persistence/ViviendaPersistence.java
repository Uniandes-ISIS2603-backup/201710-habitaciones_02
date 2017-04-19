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

import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ne.cabrera
 */
@Stateless
public class ViviendaPersistence {

    /**
     * Entity manager de la persistencia
     */
    @PersistenceContext(unitName = "habitacionesPU")
    protected EntityManager em;

    /**
     * @param id el id de la vivienda buscada
     * @return la vivienda buscada si existe, de lo contrario retorna null
     */
    public ViviendaEntity find(Long id) {
        return em.find(ViviendaEntity.class, id);
    }

    /**
     * @return lista con todas las viviendas existentes
     */
    public List<ViviendaEntity> findAll() {
        Query q = em.createQuery("select u from ViviendaEntity u");
        return q.getResultList();
    }

    /**
     * @param entity viviendaEntity a agregar
     * @return la vivienda agregada
     */
    public ViviendaEntity create(ViviendaEntity entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * @param entity viviendaEntity a ser actualizada
     * @return viviendaEntity actualizada
     */
    public ViviendaEntity update(ViviendaEntity entity) {
        em.merge(entity);
        return entity;
    }

    /**
     * @param id el id de la vivienda a eliminar
     */
    public void delete(Long id) {
        ViviendaEntity entity = em.find(ViviendaEntity.class, id);
        em.remove(entity);
    }
}
