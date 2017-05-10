//
//package co.edu.uniandes.csw.habitaciones.test.persistence;
//
//import co.edu.uniandes.csw.habitaciones.entities.DisponibilidadEntity;
//import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
//import co.edu.uniandes.csw.habitaciones.persistence.DisponibilidadPersistence;
//import co.edu.uniandes.csw.habitaciones.persistence.HabitacionPersistence;
//import java.util.ArrayList;
//import java.util.List;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.UserTransaction;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.Assert;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import uk.co.jemos.podam.api.PodamFactory;
//import uk.co.jemos.podam.api.PodamFactoryImpl;
//
///**
// *
// * @author s.cortes
// */
//@RunWith(Arquillian.class)
//public class DisponibilidadPersistenceTest {
//    
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addPackage(DisponibilidadEntity.class.getPackage())
//                .addPackage(DisponibilidadPersistence.class.getPackage())
//                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
//                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
//    }
//    
//    @Inject
//    private DisponibilidadPersistence persistence;
//    
//    @Inject
//    private HabitacionPersistence persistenceH;
//    
//    @PersistenceContext
//    private EntityManager em;
//    
//    @Inject
//    UserTransaction utx;
//    
//    private final PodamFactory factory = new PodamFactoryImpl();
//    
//    private List<DisponibilidadEntity> data = new ArrayList<DisponibilidadEntity>();
//    
//    /**
//     * Configuración inicial de la prueba.
//     */
//    @Before
//    public void setUp() {
//        try {
//            utx.begin();
//            em.joinTransaction();
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
//     */
//    private void clearData() {
//        em.createQuery("delete from DisponibilidadEntity").executeUpdate();
//       
//    }
//    
//    /**
//     * Inserta los datos iniciales para el correcto funcionamiento de las
//     * pruebas.
//     *
//     */
//    private void insertData() {
//        
//        for (int i = 0; i < 3; i++) {
//            DisponibilidadEntity entity = factory.manufacturePojo(DisponibilidadEntity.class);
//            em.persist(entity);
//            data.add(entity);
//        }
//    }
//    
//    @Test
//    public void createDisponibilidad()
//    {
//        HabitacionEntity entityH = factory.manufacturePojo(HabitacionEntity.class);
//        DisponibilidadEntity entity = factory.manufacturePojo(DisponibilidadEntity.class);
//
//        HabitacionEntity resultH = persistenceH.create(entityH);
//        DisponibilidadEntity result = persistence.create(entity);
//        
//        Assert.assertNotNull(resultH);
//        Assert.assertNotNull(result);
//        DisponibilidadEntity newEntity = em.find(DisponibilidadEntity.class, result.getId());
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(newEntity.getId(), entity.getId());
//        Assert.assertEquals(newEntity.getFechaInicioEstadia(), entity.getFechaInicioEstadia());
//        Assert.assertEquals(newEntity.getFechaTerminacionEstadia(), entity.getFechaTerminacionEstadia());
//
//        
//    }
//    
////    @Test
////    public void findDisponibilidades()
////    {
////        List<DisponibilidadEntity> lista = persistence.findAll();
////        Assert.assertEquals(data.size(), lista.size());
////        for(DisponibilidadEntity entityUno: lista)
////        {
////            boolean encontrado = false;
////            for(DisponibilidadEntity entityDos: data)
////            {
////                if(entityUno.getId().equals(entityDos.getId()))
////                {
////                    encontrado = true;
////                }
////            }
////            Assert.assertTrue(encontrado);
////        }
////    }
//    
//    @Test
//    public void findDisponibilidad()
//    {
//        DisponibilidadEntity entity = data.get(0);
//        DisponibilidadEntity newEntity = persistence.find(entity.getHabitacion().getId(), entity.getId());
//        
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(newEntity.getId(), entity.getId());
//        Assert.assertEquals(newEntity.getFechaInicioEstadia(), entity.getFechaInicioEstadia());
//        Assert.assertEquals(newEntity.getFechaTerminacionEstadia(), entity.getFechaTerminacionEstadia());
//
//        
//    }
//    
//    @Test
//    public void deleteDisponibilidad()
//    {
//        DisponibilidadEntity entity = data.get(0);
//        persistence.delete(entity.getId());
//     
//        DisponibilidadEntity entityBusq = persistence.find(entity.getHabitacion().getId(), entity.getId());
//        Assert.assertNull(entityBusq);
//    }
//    
//    @Test
//    public void updateDisponibilidad()
//    {
//        DisponibilidadEntity entity = data.get(0);
//        DisponibilidadEntity entityUp = factory.manufacturePojo(DisponibilidadEntity.class);
//        
//        entityUp.setId(entity.getId());
//        persistence.update(entityUp);
//        
//        DisponibilidadEntity newEntity = persistence.find(entity.getHabitacion().getId(), entity.getId());
//        
//        Assert.assertNotNull(newEntity);
//        Assert.assertEquals(newEntity.getId(), entityUp.getId());
//        Assert.assertEquals(newEntity.getFechaInicioEstadia(), entityUp.getFechaInicioEstadia());
//        Assert.assertEquals(newEntity.getFechaTerminacionEstadia(), entityUp.getFechaTerminacionEstadia());
//
//        
//    }
//    
//}
//
//        
//   