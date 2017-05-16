/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.persistence;

import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import co.edu.uniandes.csw.habitaciones.persistence.*;
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
 * @author ne.cabrera
 */
@RunWith(Arquillian.class)
public class ViviendaPersistenceTest
{
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViviendaEntity.class.getPackage())
                .addPackage(ViviendaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private ViviendaPersistence viviendaPersistence;
    
    @PersistenceContext(unitName = "habitacionesPU")
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
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
     * Test of find method, of class ViviendaPersistence.
     */
    @Test
    public void testFind() throws Exception
    {
        ViviendaEntity entity = data.get(0);
        ViviendaEntity newEntity = viviendaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getCiudad(), newEntity.getCiudad());
        Assert.assertEquals(entity.getDireccion(), newEntity.getDireccion());
    }

    /**
     * Test of findAll method, of class ViviendaPersistence.
     */
    @Test
    public void testFindAll() throws Exception
    {
        List<ViviendaEntity> list = viviendaPersistence.findAll();
        Assert.assertEquals(data.size() , list.size());
        for(ViviendaEntity ent : list)
        {
            boolean found = false;
            for(ViviendaEntity entity : data)
            {
                if(ent.getId().equals(entity.getId()))
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of create method, of class ViviendaPersistence.
     */
    @Test
    public void testCreate() throws Exception
    {
        PodamFactory factory = new PodamFactoryImpl();
        ViviendaEntity newEntity = factory.manufacturePojo(ViviendaEntity.class);
        ViviendaEntity result = viviendaPersistence.create(newEntity);
        Assert.assertNotNull(result);
        ViviendaEntity entity = em.find(ViviendaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getCiudad(), entity.getCiudad());
        Assert.assertEquals(newEntity.getDireccion(), entity.getDireccion());
    }

    /**
     * Test of update method, of class ViviendaPersistence.
     */
    @Test
    public void testUpdate() throws Exception
    {
        ViviendaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ViviendaEntity newEntity = factory.manufacturePojo(ViviendaEntity.class);
        
        newEntity.setId(entity.getId());
        
        viviendaPersistence.update(newEntity);
        
        ViviendaEntity resp = em.find(ViviendaEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getCiudad(), resp.getCiudad());
        Assert.assertEquals(newEntity.getDireccion(), resp.getDireccion());
    }

    /**
     * Test of delete method, of class ViviendaPersistence.
     */
    @Test
    public void testDelete() throws Exception
    {
        ViviendaEntity entity = data.get(0);
        viviendaPersistence.delete(entity.getId());
        ViviendaEntity deleted = em.find(ViviendaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    private void clearData()
    {
        em.createQuery("delete from ViviendaEntity").executeUpdate();
    }
    
    private List<ViviendaEntity> data = new ArrayList<ViviendaEntity>();

    private void insertData()
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ViviendaEntity entity = factory.manufacturePojo(ViviendaEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
}
