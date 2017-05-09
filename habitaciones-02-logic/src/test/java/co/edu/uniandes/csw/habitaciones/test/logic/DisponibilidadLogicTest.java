//package co.edu.uniandes.csw.habitaciones.test.logic;
//
//
//import co.edu.uniandes.csw.habitaciones.ejbs.DisponibilidadLogic;
//import co.edu.uniandes.csw.habitaciones.entities.DisponibilidadEntity;
//import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
//import co.edu.uniandes.csw.habitaciones.persistence.DisponibilidadPersistence;
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
//public class DisponibilidadLogicTest {
//
//    public static final String DEPLOY = "Prueba";
//
//    /**
//     * @generated
//     */
//    @Deployment
//    public static WebArchive createDeployment() {
//        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
//                .addPackage(DisponibilidadEntity.class.getPackage())
//                .addPackage(DisponibilidadLogic.class.getPackage())
//                .addPackage(DisponibilidadPersistence.class.getPackage())
//                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
//                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
//    }
//
//    /**
//     * @generated
//     */
//    @Inject
//    private DisponibilidadLogic disponibilidadLogic;
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
//       Assert.assertNotNull(newEntity);
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
//
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
//       Assert.assertNotNull(newEntity);
//        Assert.assertEquals(newEntity.getId(), pojoEntity.getId());
//        Assert.assertEquals(newEntity.getFechaInicioEstadia(), pojoEntity.getFechaInicioEstadia());
//        Assert.assertEquals(newEntity.getFechaTerminacionEstadia(), pojoEntity.getFechaTerminacionEstadia());
//        
//    }
//
//}
