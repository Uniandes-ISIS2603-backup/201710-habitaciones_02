/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.persistence;

import co.edu.uniandes.csw.habitaciones.entities.PagoEntity;
import co.edu.uniandes.csw.habitaciones.persistence.PagoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
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
public class PagoPersistenceTest
{
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PagoEntity.class.getPackage())
                .addPackage(PagoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private PagoPersistence pagoPersistence;
    
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
    
    private void clearData()
    {
        em.createQuery("delete from ViviendaEntity").executeUpdate();
    }
    
    private List<PagoEntity> data = new ArrayList<PagoEntity>();

    private void insertData()
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PagoEntity entity = factory.manufacturePojo(PagoEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Test of find method, of class PagoPersistence.
     */
    @Test
    public void testFind() throws Exception
    {
        PagoEntity entity = data.get(0);
        PagoEntity newEntity = pagoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPago(), newEntity.getPago());
    }

    /**
     * Test of findAll method, of class PagoPersistence.
     */
    @Test
    public void testFindAll() throws Exception
    {
        List<PagoEntity> list = pagoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(PagoEntity ent : list)
        {
            boolean found = false;
            for(PagoEntity entity : data)
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
     * Test of create method, of class PagoPersistence.
     */
    @Test
    public void testCreate() throws Exception
    {
        PodamFactory factory = new PodamFactoryImpl();
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        PagoEntity result = pagoPersistence.create(newEntity);
        Assert.assertNotNull(result);
        PagoEntity entity = em.find(PagoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getPago(), entity.getPago());
    }

    /**
     * Test of update method, of class PagoPersistence.
     */
    @Test
    public void testUpdate() throws Exception
    {
        PagoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        
        newEntity.setId(entity.getId());
        
        pagoPersistence.update(newEntity);
        
        PagoEntity resp = em.find(PagoEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getPago(), resp.getPago());
    }

    /**
     * Test of delete method, of class PagoPersistence.
     */
    @Test
    public void testDelete() throws Exception
    {
        PagoEntity entity = data.get(0);
        pagoPersistence.delete(entity.getId());
        PagoEntity deleted = em.find(PagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
