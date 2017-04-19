/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.persistence;

import co.edu.uniandes.csw.habitaciones.entities.PagoEntity;
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
public class PagoPersistence
{

    /**
     * Entity manager de la persistencia
     */
    @PersistenceContext(unitName = "habitacionesPU")
    protected EntityManager em;

    /**
     * @param id el id del pago buscado
     * @return el pago buscado
     */
    public PagoEntity find(Long id)
    {
        return em.find(PagoEntity.class, id);
    }

    /**
     * @return lista con todos los pagos
     */
    public List<PagoEntity> findAll()
    {
        Query q = em.createQuery("select u from PagoEntity u");
        return q.getResultList();
    }

    /**
     * @param entity pago entiy a ser agregado
     * @return el pago agregado
     */
    public PagoEntity create(PagoEntity entity)
    {
        em.persist(entity);
        return entity;
    }

    /**
     * @param entity pagoEntity a ser actualizado
     * @return el pago actualizado
     */
    public PagoEntity update(PagoEntity entity)
    {
        em.merge(entity);
        return entity;
    }

    /**
     * @param id el id de la vivienda a eliminar
     */
    public void delete(Long id)
    {
        PagoEntity entity = em.find(PagoEntity.class, id);
        em.remove(entity);
    }
}
