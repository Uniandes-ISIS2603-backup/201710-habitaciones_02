/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.persistence;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.habitaciones.entities.ViajeroEntity;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
/**
 *
 * @author s.cortes
 */
@Stateless
public class ViajeroPersistence
{
    
    //----------------------------------------------------------------------------------------------------
    // ATRIBUTOS
    //----------------------------------------------------------------------------------------------------
    
    /**
     * Entity manager de la persistencia
     */
    @PersistenceContext(unitName="habitacionesPU")
    protected EntityManager em;
    
    
    //----------------------------------------------------------------------------------------------------
    // METODOS
    //----------------------------------------------------------------------------------------------------
    
    /**
     * inserta a la tabla de viajeros un nuevo ViajeroEntity
     * @param entity la entidad que se desea agregar
     * @return  la entidad que fue agregada
     */
    public ViajeroEntity create(ViajeroEntity entity)
    {
        em.persist(entity);
        return entity;
    }
    
    /**
     * modifica la información de un viajeroEntity
     * @param entity la entidad que se desea actualizar 
     * @return la entidad que fue actualizada
     */
    public ViajeroEntity update(ViajeroEntity entity)
    {
        return em.merge(entity);
    }
    
    /**
     * Retorna una lista con todas las entidades (tuplas) de la tabla
     * @return lista de entidades viajero
     */
    public List<ViajeroEntity> findAll()
    {
        Query query = em.createQuery("select u from ViajeroEntity u");
        return query.getResultList();
    }
    
    /**
     * retorna un viajero entity de acuerdo a un ID
     * @param id el id de la entidad que se desea encontrar
     * @return null si no encuentra la entidad con el ID, la entidad si la encuentra
     */
    public ViajeroEntity find(Long id)
    {
        ViajeroEntity entity = em.find(ViajeroEntity.class, id);
        return entity;
    }
    
    /**
     * Metodo auxiliar para buscar de acuerdo a un correo electrónico
     * @param correoElectronico el correo electrónico de la entidad que se desea encontrar
     * @return la primera entidad que tenga ese correo electronico, null si no encuentra ninguna con ese correo electronico
     */
    public ViajeroEntity searchByEmail(String correoElectronico)
    {
        TypedQuery<ViajeroEntity> q
                = em.createQuery("select u from UsuarioEntity u where u.correoElectronico = :correo", ViajeroEntity.class);
        q = q.setParameter("correo", correoElectronico);

       List<ViajeroEntity> sameCorreo = q.getResultList();
        if (sameCorreo.isEmpty() ) {
            return null; 
        } else {
            return sameCorreo.get(0);
        }
    }
    
    /**
     * Elimina una entidad (tupla) de la tabla a partir de su id
     * @param id el id de la entidad que se desea eliminar.
     */
    public void delete(Long id)
    {
        ViajeroEntity entity = em.find(ViajeroEntity.class, id);
        em.remove(entity);
    }
    
}
