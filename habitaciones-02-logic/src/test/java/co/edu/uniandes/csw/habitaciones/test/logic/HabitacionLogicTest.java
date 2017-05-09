//package co.edu.uniandes.csw.habitaciones.test.logic;
//
//
//import co.edu.uniandes.csw.habitaciones.ejbs.HabitacionLogic;
//import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
//import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
//import co.edu.uniandes.csw.habitaciones.persistence.HabitacionPersistence;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.UserTransaction;
//import org.junit.Assert;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import uk.co.jemos.podam.api.PodamFactory;
//import uk.co.jemos.podam.api.PodamFactoryImpl;
//
///**
// * @generated
// */
//@RunWith(Arquillian.class)
//public class HabitacionLogicTest {
//
//    public static final String DEPLOY = "Prueba";
//
//    /**
//     * @generated
//     */
//    @Deployment
//    public static WebArchive createDeployment() {
//        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
//                .addPackage(HabitacionEntity.class.getPackage())
//                .addPackage(HabitacionLogic.class.getPackage())
//                .addPackage(HabitacionPersistence.class.getPackage())
//                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
//                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
//    }
//
//    /**
//     * @generated
//     */
//    @Inject
//    private HabitacionLogic habitacionLogic;
//
//    /**
//     * @generated
//     */
//    @PersistenceContext
//    private EntityManager em;
//
//    /**
//     * @generated
//     */
//    @Inject
//    UserTransaction utx;
//
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
//        em.createQuery("delete from HabitacionEntity").executeUpdate();
//    }
//
//    /**
//     * @generated
//     */
//    private List<HabitacionEntity> data = new ArrayList<HabitacionEntity>();
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
//            HabitacionEntity entity = factory.manufacturePojo(HabitacionEntity.class);
//
//            em.persist(entity);
//            data.add(entity);
//        }
//    }
//
//    /**
//     * Prueba para crear un Habitacion.
//     *
//     * @generated
//     */
//    @Test
//    public void createHabitacionTest() throws BusinessLogicException {
//        PodamFactory factory = new PodamFactoryImpl();
//        
//        HabitacionEntity entity = factory.manufacturePojo(HabitacionEntity.class);
//        HabitacionEntity newEntity = habitacionLogic.createHabitacion(entity);
//        
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(newEntity.getId(), entity.getId());
//        Assert.assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
//        Assert.assertEquals(newEntity.getArea(), entity.getArea());
//        Assert.assertEquals(newEntity.getRutaImagen(), entity.getRutaImagen());
//        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
//    }
//
//    /**
//     * Prueba para consultar la lista de Habitaciones.
//     *
//     * @generated
//     */
//    @Test
//    public void getHabitacionsTest() {
//        List<HabitacionEntity> list = habitacionLogic.getHabitaciones();
//        Assert.assertEquals(data.size(), list.size());
//        for (HabitacionEntity entity : list) {
//            boolean found = false;
//            for (HabitacionEntity storedEntity : data) {
//                if (entity.getId().equals(storedEntity.getId())) {
//                    found = true;
//                }
//            }
//            Assert.assertTrue(found);
//        }
//    }
//
//    /**
//     * Prueba para consultar un Habitacion.
//     *
//     * @generated
//     */
//    @Test
//    public void getHabitacionTest() {
//        HabitacionEntity entity = data.get(0);
//        HabitacionEntity newEntity = habitacionLogic.getHabitacion(entity.getId());
//        
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(newEntity.getId(), entity.getId());
//        Assert.assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
//        Assert.assertEquals(newEntity.getArea(), entity.getArea());
//        Assert.assertEquals(newEntity.getRutaImagen(), entity.getRutaImagen());
//        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
//    }
//
//    /**
//     * Prueba para eliminar un Habitacion.
//     *
//     * @generated
//     */
//    @Test
//    public void deleteHabitacionTest() {
//        HabitacionEntity entity = data.get(0);
//        habitacionLogic.deleteHabitacion(entity.getId());
//        HabitacionEntity deleted = em.find(HabitacionEntity.class, entity.getId());
//        Assert.assertNull(deleted);
//    }
//
//    /**
//     * Prueba para actualizar un Habitacion.
//     *
//     * @generated
//     */
//    @Test
//    public void updateHabitacionTest() throws BusinessLogicException {
//        HabitacionEntity entity = data.get(0);
//        PodamFactory factory = new PodamFactoryImpl();
//        HabitacionEntity pojoEntity = factory.manufacturePojo(HabitacionEntity.class);
//        pojoEntity.setId(entity.getId());
//
//        habitacionLogic.updateHabitacion(pojoEntity);
//
//        HabitacionEntity newEntity = em.find(HabitacionEntity.class, entity.getId());
//
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(newEntity.getId(), pojoEntity.getId());
//        Assert.assertEquals(newEntity.getCapacidad(), pojoEntity.getCapacidad());
//        Assert.assertEquals(newEntity.getArea(), pojoEntity.getArea());
//        Assert.assertEquals(newEntity.getRutaImagen(), pojoEntity.getRutaImagen());
//        Assert.assertEquals(newEntity.getDescripcion(), pojoEntity.getDescripcion());
//        
//    }
//
//}
