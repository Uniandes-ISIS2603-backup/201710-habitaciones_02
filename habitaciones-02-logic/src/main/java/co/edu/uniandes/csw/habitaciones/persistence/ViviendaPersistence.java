/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class ViviendaPersistence
{

    /**
     * Entity manager de la persistencia
     */
    @PersistenceContext(unitName = "habitacionesPU")
    protected EntityManager em;

    /**
     * @param id el id de la vivienda buscada
     * @return la vivienda buscada si existe, de lo contrario retorna null
     */
    public ViviendaEntity find(Long id)
    {
        return em.find(ViviendaEntity.class, id);
    }

    /**
     * @return lista con todas las viviendas existentes
     */
    public List<ViviendaEntity> findAll()
    {
        Query q = em.createQuery("select u from ViviendaEntity u");
        return q.getResultList();
    }

    /**
     * @param entity viviendaEntity a agregar
     * @return la vivienda agregada
     */
    public ViviendaEntity create(ViviendaEntity entity)
    {
        em.persist(entity);
        return entity;
    }

    /**
     * @param entity viviendaEntity a ser actualizada
     * @return viviendaEntity actualizada
     */
    public ViviendaEntity update(ViviendaEntity entity)
    {
        em.merge(entity);
        return entity;
    }

    /**
     * @param id el id de la vivienda a eliminar
     */
    public void delete(Long id)
    {
        ViviendaEntity entity = em.find(ViviendaEntity.class, id);
        em.remove(entity);
    }
}
