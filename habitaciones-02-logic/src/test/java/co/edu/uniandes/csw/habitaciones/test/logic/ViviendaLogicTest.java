/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.logic;

import co.edu.uniandes.csw.habitaciones.ejbs.HabitacionLogic;
import co.edu.uniandes.csw.habitaciones.ejbs.ViviendaLogic;
import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.HabitacionPersistence;
import co.edu.uniandes.csw.habitaciones.persistence.ViviendaPersistence;
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
public class ViviendaLogicTest
{
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    AnfitrionEntity anfitrionEntity;
    
    @Inject
    private ViviendaLogic viviendaLogic;
    
    @PersistenceContext
    private EntityManager em;
    
     @Inject
    private UserTransaction utx;
     
    private List<ViviendaEntity> dataVivienda = new ArrayList<ViviendaEntity>();
    
    private List<AnfitrionEntity> dataAnfitrion = new ArrayList<AnfitrionEntity>();
    
    private List<HabitacionEntity> dataHabitacion = new ArrayList<HabitacionEntity>();
    
    
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViviendaEntity.class.getPackage())
                .addPackage(ViviendaLogic.class.getPackage())
                .addPackage(ViviendaPersistence.class.getPackage())
                .addPackage(HabitacionEntity.class.getPackage())
                .addPackage(AnfitrionEntity.class.getPackage())
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
        em.createQuery("delete from HabitacionEntity").executeUpdate();
        em.createQuery("delete from ViviendaEntity").executeUpdate();
        em.createQuery("delete from AnfitrionEntity").executeUpdate();
    }
    
     private void insertData() {

        for (int i = 0; i < 3; i++) {
            HabitacionEntity habitaciones = factory.manufacturePojo(HabitacionEntity.class);
            em.persist(habitaciones);
            dataHabitacion.add(habitaciones);
        }

        anfitrionEntity = factory.manufacturePojo(AnfitrionEntity.class);
        anfitrionEntity.setIdUsuario(1L);
        em.persist(anfitrionEntity);
        for (int i = 0; i < 3; i++) 
        {
            ViviendaEntity entity = factory.manufacturePojo(ViviendaEntity.class);
            entity.setAnfitrion(anfitrionEntity);
            em.persist(entity);
            dataVivienda.add(entity);

            if (i == 0) {
                dataHabitacion.get(i).setVivienda(entity);
            }
        }
    }
    
//    public ViviendaLogicTest()
//    {
//    }
//
    /**
     * Test of getViviendas method, of class ViviendaLogic.
     */
    @Test
    public void testGetViviendas() throws Exception
    {
        List<ViviendaEntity> list = viviendaLogic.getViviendas();
        Assert.assertEquals(dataVivienda.size(), list.size());
        for (ViviendaEntity entity : list) {
            boolean found = false;
            for (ViviendaEntity storedEntity : dataVivienda) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Test of getVivienda method, of class ViviendaLogic.
     */
    @Test
    public void testGetVivienda() throws BusinessLogicException
    {
        ViviendaEntity entity = dataVivienda.get(0);
        ViviendaEntity resultEntity = viviendaLogic.getVivienda(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getDireccion(), resultEntity.getDireccion());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Test of createVivienda method, of class ViviendaLogic.
     */
    @Test
    public void testCreateVivienda() throws BusinessLogicException
    {
        ViviendaEntity newEntity = factory.manufacturePojo(ViviendaEntity.class);
        ViviendaEntity result = viviendaLogic.createVivienda(newEntity);
        Assert.assertNotNull(result);
        ViviendaEntity entity = em.find(ViviendaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getCiudad(), entity.getCiudad());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Test of updateVivienda method, of class ViviendaLogic.
     */
    @Test
    public void testUpdateVivienda() throws Exception
    {
        ViviendaEntity entity = dataVivienda.get(0);
        ViviendaEntity pojoEntity = factory.manufacturePojo(ViviendaEntity.class);

        pojoEntity.setId(entity.getId());

        viviendaLogic.updateVivienda(pojoEntity);

        ViviendaEntity resp = em.find(ViviendaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getDireccion(), resp.getDireccion());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }

    /**
     * Test of deleteVivienda method, of class ViviendaLogic.
     */
    @Test
    public void testDeleteVivienda() throws Exception
    {
        ViviendaEntity entity = dataVivienda.get(1);
        viviendaLogic.deleteVivienda(entity.getId());
        ViviendaEntity deleted = em.find(ViviendaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
