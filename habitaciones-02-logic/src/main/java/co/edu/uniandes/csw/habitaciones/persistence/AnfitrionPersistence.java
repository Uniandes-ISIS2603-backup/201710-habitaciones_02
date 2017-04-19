/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.persistence;

import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author df.sanabria761
 */
@Stateless
public class AnfitrionPersistence
{

    @PersistenceContext(unitName = "habitacionesPU")
    protected EntityManager em;

    public AnfitrionEntity find(Long id)
    {
        return em.find(AnfitrionEntity.class, id);
    }

    public Boolean findByDocumento(String documento)
    {
        List<AnfitrionEntity> lista = findAll();
        Boolean encontrado = false;

        for (AnfitrionEntity anfitrionEntity : lista)
        {
            AnfitrionEntity ent = anfitrionEntity;
            if (ent.getNumeroDocumento().equals(documento))
            {
                encontrado = true;
            }
        }
        return encontrado;
    }

    public List<AnfitrionEntity> findAll()
    {
        Query query = em.createQuery("select u from AnfitrionEntity u");
        return query.getResultList();
    }

    public AnfitrionEntity create(AnfitrionEntity entity)
    {
        em.persist(entity);
        return entity;
    }

    public AnfitrionEntity update(AnfitrionEntity entity)
    {
        return em.merge(entity);
    }

    public void delete(Long id)
    {
        AnfitrionEntity entity = em.find(AnfitrionEntity.class, id);
        em.remove(entity);
    }

}
