
package co.edu.uniandes.csw.habitaciones.test.persistence;

import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.persistence.HabitacionPersistence;

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
public class HabitacionPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HabitacionEntity.class.getPackage())
                .addPackage(HabitacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private HabitacionPersistence persistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private final PodamFactory factory = new PodamFactoryImpl();
    
    private List<HabitacionEntity> data = new ArrayList<HabitacionEntity>();
    
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
    public void createHabitacion()
    {
        HabitacionEntity entity = factory.manufacturePojo(HabitacionEntity.class);

        HabitacionEntity result = persistence.create(entity);
        
        Assert.assertNotNull(result);
        HabitacionEntity newEntity = em.find(HabitacionEntity.class, result.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
        Assert.assertEquals(newEntity.getArea(), entity.getArea());
        Assert.assertEquals(newEntity.getRutaImagen(), entity.getRutaImagen());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        
    }
    
    @Test
    public void findHabitaciones()
    {
        List<HabitacionEntity> lista = persistence.findAll();
        Assert.assertEquals(data.size(), lista.size());
        for(HabitacionEntity entityUno: lista)
        {
            boolean encontrado = false;
            for(HabitacionEntity entityDos: data)
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
    public void findHabitacion()
    {
        HabitacionEntity entity = data.get(0);
        HabitacionEntity newEntity = persistence.find(entity.getId());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
        Assert.assertEquals(newEntity.getArea(), entity.getArea());
        Assert.assertEquals(newEntity.getRutaImagen(), entity.getRutaImagen());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        
    }
    
    @Test
    public void deleteHabitacion()
    {
        HabitacionEntity entity = data.get(0);
        persistence.delete(entity.getId());
     
        HabitacionEntity entityBusq = persistence.find(entity.getId());
        Assert.assertNull(entityBusq);
    }
    
    @Test
    public void updateHabitacion()
    {
        HabitacionEntity entity = data.get(0);
        HabitacionEntity entityUp = factory.manufacturePojo(HabitacionEntity.class);
        
        entityUp.setId(entity.getId());
        persistence.update(entityUp);
        
        HabitacionEntity newEntity = persistence.find(entity.getId());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entityUp.getId());
        Assert.assertEquals(newEntity.getCapacidad(), entityUp.getCapacidad());
        Assert.assertEquals(newEntity.getArea(), entityUp.getArea());
        Assert.assertEquals(newEntity.getRutaImagen(), entityUp.getRutaImagen());
        Assert.assertEquals(newEntity.getDescripcion(), entityUp.getDescripcion());
        
    }
    
}

        
   