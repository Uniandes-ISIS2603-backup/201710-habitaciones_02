/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.logic;

import co.edu.uniandes.csw.habitaciones.ejbs.PagoLogic;
import co.edu.uniandes.csw.habitaciones.entities.PagoEntity;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.persistence.PagoPersistence;
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
@RunWith( Arquillian.class)
public class PagoLogicTest
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    ReservaEntity reservaEntity;
    
    @Inject
    private PagoLogic pagoLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<PagoEntity> dataPago = new ArrayList<PagoEntity>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PagoEntity.class.getPackage())
                .addPackage(PagoLogic.class.getPackage())
                .addPackage(PagoPersistence.class.getPackage())
                .addPackage(ReservaEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     @Before
    public void setUp() {
        try {
            utx.begin();
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
        em.createQuery("delete from PagoEntity").executeUpdate();
        em.createQuery("delete from ReservaEntity").executeUpdate();
    }
    
    private void insertData()
    {
        reservaEntity = factory.manufacturePojo(ReservaEntity.class);
        reservaEntity.setId(1L);
        em.persist(reservaEntity);
        for(int i = 0; i < 3; i++)
        {
            PagoEntity entity = factory.manufacturePojo(PagoEntity.class);
            double p = entity.getPago();
            if(p < 0)
            {
                entity.setPago(-p);
            }
            entity.setReserva(reservaEntity);
            em.persist(entity);
            dataPago.add(entity);
        }
    }
    
//    public PagoLogicTest()
//    {
//    }

    /**
     * Test of getPagos method, of class PagoLogic.
     */
    @Test
    public void testGetPagos() throws Exception
    {
        System.out.println(em.find(PagoEntity.class, dataPago.get(0).getId()));
        List<PagoEntity> list = pagoLogic.getPagos();
        Assert.assertEquals(dataPago.size(), list.size());
        for(PagoEntity entity: list)
        {
            boolean found = false;
            for(PagoEntity ent: dataPago)
            {
                if(entity.getId().equals(ent.getId()))
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of getPago method, of class PagoLogic.
     */
    @Test
    public void testGetPago() throws Exception
    {
        PagoEntity entity = dataPago.get(0);
        PagoEntity resultEntity = pagoLogic.getPago(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getPago(), resultEntity.getPago());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Test of createPago method, of class PagoLogic.
     */
    @Test
    public void testCreatePago() throws Exception
    {
        PagoEntity newEntity = dataPago.get(0);
        PagoEntity result = pagoLogic.createPago(newEntity);
        Assert.assertNotNull(result);
        PagoEntity entity = em.find(PagoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getPago(), entity.getPago());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Test of updatePago method, of class PagoLogic.
     */
    @Test
    public void testUpdatePago() throws Exception
    {
        PagoEntity entity = dataPago.get(0);
        PagoEntity pojoEntity = factory.manufacturePojo(PagoEntity.class);
        pojoEntity.setId(entity.getId());
        pagoLogic.updatePago(pojoEntity);
        PagoEntity resp = em.find(PagoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getPago(), resp.getPago());
        Assert.assertEquals(pojoEntity.getTipoTramite(), resp.getTipoTramite());
    }

    /**
     * Test of deletePago method, of class PagoLogic.
     */
    @Test
    public void testDeletePago() throws Exception
    {
        PagoEntity entity = dataPago.get(1);
        pagoLogic.deletePago(entity.getId());
        PagoEntity deleted = em.find(PagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
