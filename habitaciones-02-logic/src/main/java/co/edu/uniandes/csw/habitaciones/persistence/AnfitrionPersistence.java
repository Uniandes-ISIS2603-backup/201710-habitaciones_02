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

import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author df.sanabria761
 */
@Stateless
public class AnfitrionPersistence {

    @PersistenceContext(unitName = "habitacionesPU")
    protected EntityManager em;

    public AnfitrionEntity find(Long id) {
        return em.find(AnfitrionEntity.class, id);
    }

    public Boolean findByDocumento(String documento) {
        List<AnfitrionEntity> lista = findAll();
        Boolean encontrado = false;

        for (AnfitrionEntity anfitrionEntity : lista) {
            AnfitrionEntity ent = anfitrionEntity;
            if (ent.getNumeroDocumento().equals(documento)) {
                encontrado = true;
            }
        }
        return encontrado;
    }

    public List<AnfitrionEntity> findAll() {
        Query query = em.createQuery("select u from AnfitrionEntity u");
        return query.getResultList();
    }

    public AnfitrionEntity create(AnfitrionEntity entity) {
        em.persist(entity);
        return entity;
    }

    public AnfitrionEntity update(AnfitrionEntity entity) {
        return em.merge(entity);
    }
    public AnfitrionEntity findLogin(String pCorreo, String pConstrasena){
        TypedQuery<AnfitrionEntity> q;
        q = em.createQuery("select u from ViajeroEntity u where u.correoElectronico = :pCorreo ANd u.contrasena = :pConstrasena",AnfitrionEntity.class);
        q = q.setParameter("pCorreo", pCorreo);
        q = q.setParameter("pConstrasena", pConstrasena);
        
        List<AnfitrionEntity> lista =  q.getResultList();
        
        return (!lista.isEmpty()) ? lista.get(0) : null ;
    }
    public void delete(Long id) {
        AnfitrionEntity entity = em.find(AnfitrionEntity.class, id);
        em.remove(entity);
    }

}
