package co.edu.uniandes.csw.habitaciones.test.logic;

import co.edu.uniandes.csw.habitaciones.ejbs.HabitacionLogic;
import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.HabitacionPersistence;
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
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class HabitacionLogicTest {

    @PersistenceContext
    private EntityManager em;

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private UserTransaction utx;

    @Inject
    private HabitacionLogic logic;

    private List<HabitacionEntity> data = new ArrayList<HabitacionEntity>();

    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HabitacionEntity.class.getPackage())
                .addPackage(HabitacionLogic.class.getPackage())
                .addPackage(HabitacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     *
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
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from HabitacionEntity").executeUpdate();
        // em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            HabitacionEntity entity = factory.manufacturePojo(HabitacionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createHabitacion() {
        try {
            HabitacionEntity entity = factory.manufacturePojo(HabitacionEntity.class);
            entity.setArea(20.0);
            entity.setCapacidad(5);
            entity.setValorAlquiler(30.0);
            
            HabitacionEntity result = logic.createHabitacion(entity);
            

            Assert.assertNotNull(result);

            HabitacionEntity newEntity = em.find(HabitacionEntity.class, result.getId());

            Assert.assertNotNull(newEntity);
            Assert.assertEquals(newEntity.getId(), entity.getId());
            Assert.assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
            Assert.assertEquals(newEntity.getArea(), entity.getArea());
            Assert.assertEquals(newEntity.getRutaImagen(), entity.getRutaImagen());
            Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());

            
            
        } catch (BusinessLogicException e) 
        {
            Assert.fail("El metodo no deberia generar una excepcion " );
        }

    }
    
    @Test
    public void createHabitacion2() {
        try {
            HabitacionEntity entity = factory.manufacturePojo(HabitacionEntity.class);
            entity.setArea(-20.0);
            entity.setCapacidad(5);
            entity.setValorAlquiler(30.0);
            
            HabitacionEntity result = logic.createHabitacion(entity);
            
            Assert.fail("El metodo deberia generar una excepcion " );
            Assert.assertNotNull(result);
            
        } catch (BusinessLogicException e) 
        {
            
        }

    }
    
    @Test
    public void createHabitacion3() {
        try {
            HabitacionEntity entity = factory.manufacturePojo(HabitacionEntity.class);
            entity.setArea(20.0);
            entity.setCapacidad(-5);
            entity.setValorAlquiler(30.0);
            
            HabitacionEntity result = logic.createHabitacion(entity);
            
            Assert.fail("El metodo deberia generar una excepcion " );
            Assert.assertNotNull(result);

        } catch (BusinessLogicException e) 
        {
           
        }

    }
    
    @Test
    public void createHabitacion4() {
        try {
            HabitacionEntity entity = factory.manufacturePojo(HabitacionEntity.class);
            entity.setArea(20.0);
            entity.setCapacidad(5);
            entity.setValorAlquiler(-30.0);
            
            HabitacionEntity result = logic.createHabitacion(entity);
            
            Assert.fail("El metodo deberia generar una excepcion " );
            Assert.assertNotNull(result);

            
        } catch (BusinessLogicException e) 
        {
            
        }

    }

    @Test
    public void getHabitaciones() {
        List<HabitacionEntity> lista = logic.getHabitaciones();
        Assert.assertEquals(data.size(), lista.size());
        for (HabitacionEntity entityUno : lista) {
            boolean encontrado = false;

            for (HabitacionEntity entityDos : data) {
                if (entityUno.getId().equals(entityDos.getId())) {
                    encontrado = true;
                }
            }

            Assert.assertTrue(encontrado);
        }
    }

    @Test
    public void getHabitacion() {
        HabitacionEntity entity = data.get(0);
        HabitacionEntity newEntity = logic.getHabitacion(entity.getId());

        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
        Assert.assertEquals(newEntity.getArea(), entity.getArea());
        Assert.assertEquals(newEntity.getRutaImagen(), entity.getRutaImagen());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());

    }

    @Test
    public void deleteHabitacion() {
        HabitacionEntity entity = data.get(0);
        logic.deleteHabitacion(entity.getId());

        HabitacionEntity entityBusq = logic.getHabitacion(entity.getId());
        Assert.assertNull(entityBusq);
    }

    @Test
    public void updateHabitacion() {
        try {
            HabitacionEntity entity = data.get(0);
            HabitacionEntity entityUp = factory.manufacturePojo(HabitacionEntity.class);
            entityUp.setId(entity.getId());
            entityUp.setArea(20.0);
            entityUp.setCapacidad(4);
            entityUp.setValorAlquiler(30.0);

            logic.updateHabitacion(entityUp);

            HabitacionEntity newEntity = logic.getHabitacion(entity.getId());

            Assert.assertNotNull(newEntity);
            Assert.assertEquals(newEntity.getId(), entityUp.getId());
            Assert.assertEquals(newEntity.getCapacidad(), entityUp.getCapacidad());
            Assert.assertEquals(newEntity.getArea(), entityUp.getArea());
            Assert.assertEquals(newEntity.getRutaImagen(), entityUp.getRutaImagen());
            Assert.assertEquals(newEntity.getDescripcion(), entityUp.getDescripcion());

        } catch (BusinessLogicException e) 
        {
            Assert.fail("No deberia lanzar excepcion  " + e);
        }

    }

   

}
