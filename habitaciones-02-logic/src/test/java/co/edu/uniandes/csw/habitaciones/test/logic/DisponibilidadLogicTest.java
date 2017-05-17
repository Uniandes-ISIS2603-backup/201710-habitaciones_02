package co.edu.uniandes.csw.habitaciones.test.logic;

import co.edu.uniandes.csw.habitaciones.ejbs.DisponibilidadLogic;
import co.edu.uniandes.csw.habitaciones.entities.DisponibilidadEntity;
import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.DisponibilidadPersistence;
import java.util.ArrayList;
import java.util.Date;
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
public class DisponibilidadLogicTest {

    private final static int MAX_HABITACIONES = 2;

    @PersistenceContext
    private EntityManager em;

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private UserTransaction utx;

    @Inject
    private DisponibilidadLogic logic;

    private List<DisponibilidadEntity> data = new ArrayList<DisponibilidadEntity>();

    private List<HabitacionEntity> dataHabitacion = new ArrayList<HabitacionEntity>();

    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DisponibilidadEntity.class.getPackage())
                .addPackage(DisponibilidadLogic.class.getPackage())
                .addPackage(DisponibilidadPersistence.class.getPackage())
                .addPackage(HabitacionEntity.class.getPackage())
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
     */
    private void clearData() {
        em.createQuery("delete from DisponibilidadEntity").executeUpdate();
        em.createQuery("delete from HabitacionEntity").executeUpdate();

    }

    private void insertData() {

        for (int i = 0; i < MAX_HABITACIONES; i++) {
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
    public void createDisponibilidad() {

        DisponibilidadEntity entity = factory.manufacturePojo(DisponibilidadEntity.class);
        entity.setHabitacion(dataHabitacion.get(0));

        Date fechaActual = new Date();
        entity.setFechaInicioEstadia(new Date(fechaActual.getTime() - 86400000));
        entity.setFechaTerminacionEstadia(new Date(fechaActual.getTime() + 86400000));

        try {
            DisponibilidadEntity result = logic.createDisponibilidad(entity);
            Assert.assertNotNull(result);
            DisponibilidadEntity newEntity = em.find(DisponibilidadEntity.class, result.getId());
            Assert.assertNotNull(newEntity);
            Assert.assertEquals(newEntity.getId(), entity.getId());
            Assert.assertEquals(newEntity.getFechaInicioEstadia(), entity.getFechaInicioEstadia());
            Assert.assertEquals(newEntity.getFechaTerminacionEstadia(), entity.getFechaTerminacionEstadia());

        } catch (BusinessLogicException e) {
            Assert.fail("No deberia generar excepcion: " + e.getMessage());
        }
    }

    @Test
    public void createDisponibilidad2() {
        try {
            DisponibilidadEntity entity = factory.manufacturePojo(DisponibilidadEntity.class);
            entity.setHabitacion(dataHabitacion.get(0));

            Date fechaActual = new Date();
            entity.setFechaInicioEstadia(new Date(fechaActual.getTime() + 86400000));
            entity.setFechaTerminacionEstadia(new Date(fechaActual.getTime() - 86400000));

            DisponibilidadEntity result = logic.createDisponibilidad(entity);
            Assert.fail("Deberia generar excepcion. Fehcas mal definidas "
                    + "al maximo establecida");
        } catch (BusinessLogicException e) {

        }
    }

    @Test
    public void findResenas() {
        List<DisponibilidadEntity> lista = logic.getDisponibilidades(dataHabitacion.get(0).getId());
        Assert.assertEquals(data.size(), lista.size());//expected data.size()

        for (DisponibilidadEntity entityUno : lista) {
            boolean encontrado = false;

            for (DisponibilidadEntity entityDos : data) {
                if (entityUno.getId().equals(entityDos.getId())) {
                    encontrado = true;

                }
            }
            Assert.assertTrue(encontrado);
        }
    }

    @Test
    public void findResena() {
        DisponibilidadEntity entity = dataHabitacion.get(0).getDisponibilidades().get(0);
        DisponibilidadEntity newEntity;
        newEntity = logic.getDisponibilidad(dataHabitacion.get(0).getId(), entity.getId());

        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getFechaInicioEstadia(), entity.getFechaInicioEstadia());
        Assert.assertEquals(newEntity.getFechaTerminacionEstadia(), entity.getFechaTerminacionEstadia());

    }

    @Test
    public void deleteResena() {
        DisponibilidadEntity entity = data.get(0);
        logic.deleteDisponibilidad(entity.getId());

        DisponibilidadEntity entityBusq = logic.getDisponibilidad(entity.getHabitacion().getId(), entity.getId());
        Assert.assertNull(entityBusq);
    }

    @Test
    public void updateDisponibilidad() {
        try {
            DisponibilidadEntity entity = data.get(0);
            DisponibilidadEntity entityUp
                    = factory.manufacturePojo(DisponibilidadEntity.class);

            entityUp.setId(entity.getId());

            entityUp.setHabitacion(dataHabitacion.get(0));

            Date fechaActual = new Date();
            entityUp.setFechaInicioEstadia(new Date(fechaActual.getTime() - 86400000));
            entityUp.setFechaTerminacionEstadia(new Date(fechaActual.getTime() + 86400000));

            logic.updateDisponibilidad(entityUp);
            DisponibilidadEntity newEntity = logic.getDisponibilidad(entity.getHabitacion().getId(), entity.getId());

            Assert.assertNotNull(newEntity);
            Assert.assertEquals(newEntity.getId(), entityUp.getId());
            Assert.assertEquals(newEntity.getFechaInicioEstadia(), entityUp.getFechaInicioEstadia());
            Assert.assertEquals(newEntity.getFechaTerminacionEstadia(), entityUp.getFechaTerminacionEstadia());

        } catch (BusinessLogicException e) {

            Assert.fail("No deberia generar excepcion: " + e.getMessage());
        }

    }

    @Test
    public void updateDisponibilidad2() {

        try {

            DisponibilidadEntity entity = data.get(0);
            DisponibilidadEntity entityUp
                    = factory.manufacturePojo(DisponibilidadEntity.class);

            entityUp.setId(entity.getId());

            entityUp.setHabitacion(dataHabitacion.get(0));

            Date fechaActual = new Date();
            entityUp.setFechaInicioEstadia(new Date(fechaActual.getTime() + 86400000));
            entityUp.setFechaTerminacionEstadia(new Date(fechaActual.getTime() - 86400000));

            logic.updateDisponibilidad(entityUp);

            Assert.fail("Deberia generar excepcion");
        } catch (BusinessLogicException e) {

        }
    }


//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(newEntity.getId(), entity.getId());
//        Assert.assertEquals(newEntity.getFechaInicioEstadia(), entity.getFechaInicioEstadia());
//        Assert.assertEquals(newEntity.getFechaTerminacionEstadia(), entity.getFechaTerminacionEstadia());
//    }
}
