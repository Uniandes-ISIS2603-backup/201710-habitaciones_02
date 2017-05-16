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
        em.createQuery("delete from DisponibilidadEntity").executeUpdate();
        em.createQuery("delete from HabitacionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            DisponibilidadEntity entity = factory.manufacturePojo(DisponibilidadEntity.class);
            em.persist(entity);
            data.add(entity);
        }

        for (int i = 0; i < 3; i++) {
            HabitacionEntity habitacion = factory.manufacturePojo(HabitacionEntity.class);
            dataHabitacion.add(habitacion);
        }
    }

    @Test
    public void createDisponibilidad() {

        DisponibilidadEntity entity = factory.manufacturePojo(DisponibilidadEntity.class);
        entity.setHabitacion(dataHabitacion.get(0));
        
        Date fecha1 = new Date(System.currentTimeMillis());
        Date fecha2 = new Date(System.currentTimeMillis() + 10);
        
        entity.setFechaInicioEstadia(fecha1);
        entity.setFechaTerminacionEstadia(fecha2);

        try {

            DisponibilidadEntity result = logic.createDisponibilidad(entity);

            Assert.assertNotNull(result);

            DisponibilidadEntity newEntity = em.find(DisponibilidadEntity.class, result.getId());

            Assert.assertNotNull(newEntity);
            Assert.assertEquals(newEntity.getId(), entity.getId());
            Assert.assertEquals(newEntity.getFechaInicioEstadia(), entity.getFechaInicioEstadia());
            Assert.assertEquals(newEntity.getFechaTerminacionEstadia(), entity.getFechaTerminacionEstadia());

        } catch (BusinessLogicException e) {

           Assert.fail("No deberia generar excepcion     " + e.getMessage());

        }

    }
//
//    @Test
//    public void getViajeros() {
//        List<ViajeroEntity> lista = logic.getViajeros();
//        Assert.assertEquals(data.size(), lista.size());
//        for (ViajeroEntity entityUno : lista) {
//            boolean encontrado = false;
//
//            for (ViajeroEntity entityDos : data) {
//                if (entityUno.getIdUsuario().equals(entityDos.getIdUsuario())) {
//                    encontrado = true;
//                }
//            }
//
//            Assert.assertTrue(encontrado);
//        }
//    }
//
//    @Test
//    public void getViajero() {
//        ViajeroEntity entity = data.get(0);
//        ViajeroEntity entityBusq = logic.getViajero(entity.getIdUsuario());
//
//        Assert.assertNotNull(entityBusq);
//        Assert.assertEquals(entity.getCorreoElectronico(), entityBusq.getCorreoElectronico());
//        Assert.assertEquals(entity.getContrasena(), entityBusq.getContrasena());
//        Assert.assertEquals(entity.getDireccion(), entityBusq.getDireccion());
//        Assert.assertEquals(entity.getImagen(), entityBusq.getImagen());
//        Assert.assertEquals(entity.getNombre(), entityBusq.getNombre());
//        Assert.assertEquals(entity.getNumeroDocumento(), entityBusq.getNumeroDocumento());
//        Assert.assertEquals(entity.getTelefono(), entityBusq.getTelefono());
//        Assert.assertEquals(entity.getTipoDocumento(), entityBusq.getTipoDocumento());
//
//    }
//
//    @Test
//    public void deleteViajero() {
//        ViajeroEntity entity = data.get(0);
//        logic.deleteViajero(entity.getIdUsuario());
//
//        ViajeroEntity entityBusq = logic.getViajero(entity.getIdUsuario());
//        Assert.assertNull(entityBusq);
//    }
//
//    @Test
//    public void updateViajero() {
//        try {
//            ViajeroEntity entity = data.get(0);
//            ViajeroEntity entityUp = factory.manufacturePojo(ViajeroEntity.class);
//            entityUp.setIdUsuario(entity.getIdUsuario());
//
//            logic.updateViajero(entityUp);
//
//            ViajeroEntity entityBusq = logic.getViajero(entity.getIdUsuario());
//
//            Assert.assertNotNull(entityBusq);
//            Assert.assertEquals(entityUp.getNombre(), entityBusq.getNombre());
//            Assert.assertEquals(entityUp.getContrasena(), entityBusq.getContrasena());
//            Assert.assertEquals(entityUp.getCorreoElectronico(), entityBusq.getCorreoElectronico());
//            Assert.assertEquals(entityUp.getDireccion(), entityBusq.getDireccion());
//            Assert.assertEquals(entityUp.getImagen(), entityBusq.getImagen());
//            Assert.assertEquals(entityUp.getNumeroDocumento(), entityBusq.getNumeroDocumento());
//            Assert.assertEquals(entityUp.getTelefono(), entityBusq.getTelefono());
//            Assert.assertEquals(entityUp.getTipoDocumento(), entityBusq.getTipoDocumento());
//
//        } catch (BusinessLogicException e) {
//            Assert.fail("No deberia lanzar excepcion");
//        }
//
//        try {
//            ViajeroEntity entity = data.get(0);
//            ViajeroEntity entityUp = factory.manufacturePojo(ViajeroEntity.class);
//            entityUp.setContrasena("");
//            entityUp.setNombre(null);
//            entityUp.setIdUsuario(entity.getIdUsuario());
//
//            logic.updateViajero(entityUp);
//
//            Assert.fail("Deberia lanzar excepcion. "
//                    + "Se esta intentando actualizar datos en Null o vacio");
//        } catch (BusinessLogicException e) {
//
//        }
//    }
//
//////////////////
//    /**
//     * Configuración inicial de la prueba.
//     *
//     * @generated
//     */
//    @Before
//    public void configTest() {
//        try {
//            utx.begin();
//            clearData();
//            insertData();
//            utx.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                utx.rollback();
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * Limpia las tablas que están implicadas en la prueba.
//     *
//     * @generated
//     */
//    private void clearData() {
//        em.createQuery("delete from DisponibilidadEntity").executeUpdate();
//    }
//
//    /**
//     * @generated
//     */
//    private List<DisponibilidadEntity> data = new ArrayList<DisponibilidadEntity>();
//
//    /**
//     * Inserta los datos iniciales para el correcto funcionamiento de las
//     * pruebas.
//     *
//     * @generated
//     */
//    private void insertData() {
//        for (int i = 0; i < 3; i++) {
//            PodamFactory factory = new PodamFactoryImpl();
//            DisponibilidadEntity entity = factory.manufacturePojo(DisponibilidadEntity.class);
//
//            em.persist(entity);
//            data.add(entity);
//        }
//    }
//
//    /**
//     * Prueba para crear un Disponibilidad.
//     *
//     * @generated
//     */
//    @Test
//    public void createDisponibilidadTest() throws BusinessLogicException {
//        PodamFactory factory = new PodamFactoryImpl();
//
//        DisponibilidadEntity entity = factory.manufacturePojo(DisponibilidadEntity.class);
//        DisponibilidadEntity newEntity = disponibilidadLogic.createDisponibilidad(entity);
//
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(newEntity.getId(), entity.getId());
//        Assert.assertEquals(newEntity.getFechaInicioEstadia(), entity.getFechaInicioEstadia());
//        Assert.assertEquals(newEntity.getFechaTerminacionEstadia(), entity.getFechaTerminacionEstadia());
//    }
//
//    /**
//     * Prueba para consultar la lista de Disponibilidades.
//     *
//     * @generated
//     */
////    @Test
////    public void getDisponibilidadsTest() {
////        List<DisponibilidadEntity> list = disponibilidadLogic.getDisponibilidades();
////        Assert.assertEquals(data.size(), list.size());
////        for (DisponibilidadEntity entity : list) {
////            boolean found = false;
////            for (DisponibilidadEntity storedEntity : data) {
////                if (entity.getId().equals(storedEntity.getId())) {
////                    found = true;
////                }
////            }
////            Assert.assertTrue(found);
////        }
////    }
//    /**
//     * Prueba para consultar un Disponibilidad.
//     *
//     * @generated
//     */
//    @Test
//    public void getDisponibilidadTest() {
//        DisponibilidadEntity entity = data.get(0);
//        DisponibilidadEntity newEntity = disponibilidadLogic.getDisponibilidad(entity.getHabitacion().getId(), entity.getId());
//
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(newEntity.getId(), entity.getId());
//        Assert.assertEquals(newEntity.getFechaInicioEstadia(), entity.getFechaInicioEstadia());
//        Assert.assertEquals(newEntity.getFechaTerminacionEstadia(), entity.getFechaTerminacionEstadia());
//    }
//
//    /**
//     * Prueba para eliminar un Disponibilidad.
//     *
//     * @generated
//     */
//    @Test
//    public void deleteDisponibilidadTest() {
//        DisponibilidadEntity entity = data.get(0);
//        disponibilidadLogic.deleteDisponibilidad(entity.getId());
//        DisponibilidadEntity deleted = em.find(DisponibilidadEntity.class, entity.getId());
//        Assert.assertNull(deleted);
//    }
//
//    /**
//     * Prueba para actualizar un Disponibilidad.
//     *
//     * @generated
//     */
//    @Test
//    public void updateDisponibilidadTest() throws BusinessLogicException {
//        DisponibilidadEntity entity = data.get(0);
//        PodamFactory factory = new PodamFactoryImpl();
//        DisponibilidadEntity pojoEntity = factory.manufacturePojo(DisponibilidadEntity.class);
//        pojoEntity.setId(entity.getId());
//
//        disponibilidadLogic.updateDisponibilidad(pojoEntity);
//
//        DisponibilidadEntity newEntity = em.find(DisponibilidadEntity.class, entity.getId());
//
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(newEntity.getId(), pojoEntity.getId());
//        Assert.assertEquals(newEntity.getFechaInicioEstadia(), pojoEntity.getFechaInicioEstadia());
//        Assert.assertEquals(newEntity.getFechaTerminacionEstadia(), pojoEntity.getFechaTerminacionEstadia());
//
//    }

}
