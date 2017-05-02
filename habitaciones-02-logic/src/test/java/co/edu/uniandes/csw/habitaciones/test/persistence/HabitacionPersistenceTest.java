/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.persistence;

import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.persistence.HabitacionPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author b.gamba10
 */
@RunWith(Arquillian.class)
public class HabitacionPersistenceTest {

    public static final String DEPLOY = "PruebaHabitacionPersistence";

    /**
     *
     * @return
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(HabitacionEntity.class.getPackage())
                .addPackage(HabitacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private HabitacionPersistence habitacionPersistence;

    @PersistenceContext(unitName = "habitacionesPU")
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<HabitacionEntity> data = new ArrayList<HabitacionEntity>();

    /**
     * Configuración inicial de la prueba.
     */
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        //em.createQuery("delete from DisponibilidadEntity").executeUpdate();
        em.createQuery("delete from HabitacionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            HabitacionEntity entity = factory.manufacturePojo(HabitacionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba crear una habitacion
     */
    @Test
    public void createHabitacionTest() {

        PodamFactory factory = new PodamFactoryImpl();
        HabitacionEntity newEntity = factory.manufacturePojo(HabitacionEntity.class);

        HabitacionEntity result = habitacionPersistence.create(newEntity);

        Assert.assertNotNull(result);
        
        HabitacionEntity entity = em.find(HabitacionEntity.class, result.getId());
        
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
        Assert.assertEquals(newEntity.getArea(), entity.getArea());
        Assert.assertEquals(newEntity.getRutaImagen(), entity.getRutaImagen());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
    }

    /**
     * Prueba para consultar la lista de Habitaciones.
     */
    @Test
    public void getHabitacionesTest() {
        List<HabitacionEntity> list = habitacionPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (HabitacionEntity ent : list) {
            boolean found = false;
            for (HabitacionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
}
