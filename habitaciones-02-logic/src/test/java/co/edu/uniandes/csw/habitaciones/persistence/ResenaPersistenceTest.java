/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.persistence;

import co.edu.uniandes.csw.habitaciones.entities.ResenaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author s.cortes
 */
@RunWith(Arquillian.class)
public class ResenaPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ResenaEntity.class.getPackage())
                .addPackage(ResenaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private ResenaPersistence persistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private final PodamFactory factory = new PodamFactoryImpl();
    
    private List<ResenaEntity> data = new ArrayList<ResenaEntity>();
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from ResenaEntity").executeUpdate();
    }
    
    private void insertData() {
        
        for (int i = 0; i < 4; i++) {
            ResenaEntity entity = factory.manufacturePojo(ResenaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createResena()
    {
        ResenaEntity entity = factory.manufacturePojo(ResenaEntity.class);
        ResenaEntity result = persistence.create(entity);
        
        Assert.assertNotNull(result);
        
        ResenaEntity entityBusq = em.find(ResenaEntity.class, result.getId());
        Assert.assertNotNull(entityBusq);
        Assert.assertEquals(entity.getCalificacion(), entityBusq.getCalificacion());
        Assert.assertEquals(entity.getComentario(), entityBusq.getComentario());
    }
    
    @Test
    public void findAllResenas()
    {
        List<ResenaEntity> lista = persistence.findAll();
        Assert.assertEquals(data.size(), lista.size());
        for(ResenaEntity entityUno: lista)
        {
            boolean encontrado = false;
            for(ResenaEntity entityDos : data)
            {
                if(entityUno.getId().equals(entityDos.getId()))
                {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
    
    @Test
    public void findResena()
    {
        ResenaEntity entity = data.get(0);
        ResenaEntity entityBusq = persistence.find(entity.getId());
        
        Assert.assertNotNull(entityBusq);
        Assert.assertEquals(entity.getCalificacion(), entityBusq.getCalificacion());
        Assert.assertEquals(entity.getComentario(), entityBusq.getComentario());
    }
    
    @Test
    public void deleteResena()
    {
        ResenaEntity entity = data.get(0);
        persistence.delete(entity.getId());
        
        ResenaEntity entityBusq = persistence.find(entity.getId());
        Assert.assertNull(entityBusq);
    }
    
    @Test 
    public void updateResena()
    {
        ResenaEntity entity = data.get(0);   
        ResenaEntity entityUp = factory.manufacturePojo(ResenaEntity.class);
        
        entityUp.setId(entity.getId());
        persistence.update(entityUp);
        
        ResenaEntity entityBusq = persistence.find(entity.getId());
        
        Assert.assertNotNull(entityBusq);
        Assert.assertEquals(entityUp.getCalificacion(), entityBusq.getCalificacion());
        Assert.assertEquals(entityUp.getComentario(), entityBusq.getComentario());
        
    }
}
