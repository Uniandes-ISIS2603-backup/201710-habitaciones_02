
package co.edu.uniandes.csw.habitaciones.test.persistence;

import co.edu.uniandes.csw.habitaciones.entities.DisponibilidadEntity;
import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.persistence.DisponibilidadPersistence;
import co.edu.uniandes.csw.habitaciones.persistence.HabitacionPersistence;
import java.util.ArrayList;
import java.util.Date;
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
public class DisponibilidadPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DisponibilidadEntity.class.getPackage())
                .addPackage(DisponibilidadPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private DisponibilidadPersistence persistence;
    
    @Inject
    private HabitacionPersistence persistenceH;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private final PodamFactory factory = new PodamFactoryImpl();
    
    private List<DisponibilidadEntity> data = new ArrayList<DisponibilidadEntity>();
    
    private List<HabitacionEntity> dataHabitacion = new ArrayList<HabitacionEntity>();

    
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
        em.createQuery("delete from DisponibilidadEntity").executeUpdate();
       
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        
        for (int i = 0; i < 2; i++) {
            HabitacionEntity habitacion = factory.manufacturePojo(HabitacionEntity.class);
            dataHabitacion.add(habitacion);
            em.persist(habitacion);
        }

        //Se agregan reservas y resenas a la base de datos y a las listas
        //la mitad de las resenas y reservas tendran viajeros y habitaciones distintas
        //a la otra mitad
        Date fechaActual = new Date();
        for (int i = 0; i < 4; i++) {

            DisponibilidadEntity entity = factory.manufacturePojo(DisponibilidadEntity.class);
            entity.setFechaInicioEstadia(fechaActual);

            int j = (i < (4 / 2)) ? 0 : 1;

            entity.setHabitacion(dataHabitacion.get(j));

            em.persist(entity);

            data.add(entity);

        }
    }
    
    @Test
    public void createDisponibilidad()
    {
        HabitacionEntity entityH = dataHabitacion.get(0);
        DisponibilidadEntity entity = data.get(0);

        HabitacionEntity resultH = persistenceH.create(entityH);
        DisponibilidadEntity result = persistence.create(entity);
        
        Assert.assertNotNull(resultH);
        Assert.assertNotNull(result);
        DisponibilidadEntity newEntity = em.find(DisponibilidadEntity.class, result.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
       
        
    }
    
    @Test
    public void findDisponibilidades()
    {
        List<DisponibilidadEntity> lista = persistence.findAll(dataHabitacion.get(0).getId());
        Assert.assertEquals(data.size()/2, lista.size());
        for(DisponibilidadEntity entityUno: lista)
        {
            boolean encontrado = false;
            for(DisponibilidadEntity entityDos: data)
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
    public void findDisponibilidad()
    {
        DisponibilidadEntity entity = data.get(0);
        DisponibilidadEntity newEntity = persistence.find(dataHabitacion.get(0).getId(), data.get(0).getId());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        
        
    }
    
    @Test
    public void deleteDisponibilidad()
    {
        DisponibilidadEntity entity = data.get(0);
        persistence.delete(entity.getId());
     
        DisponibilidadEntity entityBusq = persistence.find(dataHabitacion.get(0).getId(), data.get(0).getId());
        Assert.assertNull(entityBusq);
    }
    
    @Test
    public void updateDisponibilidad()
    {
        DisponibilidadEntity entity = data.get(0);
        DisponibilidadEntity entityUp = factory.manufacturePojo(DisponibilidadEntity.class);
        
        entityUp.setId(entity.getId());
        
        
        DisponibilidadEntity newEntity = persistence.update(entityUp);
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entityUp.getId());
        Assert.assertEquals(newEntity.getFechaInicioEstadia(), entityUp.getFechaInicioEstadia());
        Assert.assertEquals(newEntity.getFechaTerminacionEstadia(), entityUp.getFechaTerminacionEstadia());

        
    }
    
}

        
   