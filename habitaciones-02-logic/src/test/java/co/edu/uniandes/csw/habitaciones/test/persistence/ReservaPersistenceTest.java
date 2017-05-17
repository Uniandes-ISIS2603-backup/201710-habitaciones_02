/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.persistence;

import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.persistence.AnfitrionPersistence;
import co.edu.uniandes.csw.habitaciones.persistence.ReservaPersistence;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author dg.guarin20
 */
@RunWith(Arquillian.class)
public class ReservaPersistenceTest {
    
    public ReservaPersistenceTest()
            {
                
            }
    @Inject
    private ReservaPersistence reservaPersistence;
   @PersistenceContext (unitName = "habitacionesPU")
    private EntityManager em;
    private final PodamFactory factory = new PodamFactoryImpl();
     private List<ReservaEntity> data = new ArrayList<ReservaEntity>();
    
      @Inject
    UserTransaction utx;
     
    @Deployment 
    public static JavaArchive createDeployment()
    {
                return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
  @Before
    public void setUp(){
        try{
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{
                utx.rollback();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
        private void clearData(){
        em.createQuery("delete from ReservaEntity").executeUpdate();
        
    }
    
    private void insertData(){
        for (int i = 0; i < 3; i++) {
            ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test 
    public void CreateReserva()
    {
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity result = reservaPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        ReservaEntity entity = em.find(ReservaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getPrecio(), entity.getPrecio());
        Assert.assertEquals(newEntity.getCancelado(), entity.getCancelado());
        
    }
        @Test
    public void getReservasTest(){
        List<ReservaEntity> list = reservaPersistence.findAll();
        Assert.assertEquals(list.size(), data.size());
        for (ReservaEntity reservaEntity : list) {
            boolean encontrado = false;
            for (ReservaEntity reservaEntity1 : data) {
                if(reservaEntity.getId()
                        .equals(reservaEntity1.getId())){
                    encontrado = true;
                }   
            }
            Assert.assertTrue(encontrado);
        }
    }
            @Test
    public void findAllByViajeroTest(){
        ReservaEntity entity = data.get(0);
        List<ReservaEntity> list = reservaPersistence.findAllByViajero(entity.getViajero().getIdUsuario());
        if(list==null)
        {
            
            Assert.assertNull(list);
        }
        else{
        Assert.assertEquals(list.size(), data.size());
        for (ReservaEntity reservaEntity : list) {
            boolean encontrado = false;
            for (ReservaEntity reservaEntity1 : data) {
                if(reservaEntity!= null && entity!= null){
                if(reservaEntity.getViajero().getIdUsuario()
                        .equals(entity.getViajero().getIdUsuario())){
                    encontrado = true;
                }   }
            }
            Assert.assertTrue(encontrado);
        }}
    }
        public void findReservaFromViajeroAndHabitacionTest(){
        ReservaEntity entity = data.get(0);
        ReservaEntity reservaEntity = reservaPersistence.findReservaFromViajeroAndHabitacion(entity.getViajero().getIdUsuario(),entity.getHabitacion().getId());
     
            boolean encontrado = false;
            
                if(reservaEntity.getViajero().getIdUsuario()
                        .equals(entity.getViajero().getIdUsuario())){
                        if(reservaEntity.getHabitacion().getId()
                        .equals(entity.getHabitacion().getId())){
                    encontrado = true;}
                   
            }
            Assert.assertTrue(encontrado);
        
    }
    
    @Test 
    public void getReservaTest(){
        ReservaEntity entity = data.get(0);
        ReservaEntity e2 = reservaPersistence.find(entity.getId());
        Assert.assertNotNull(e2);
        Assert.assertEquals(entity.getPrecio(),e2.getPrecio());
  
    }
    

    
    
    @Test
    public void deleteReservaTest(){
        ReservaEntity entity = data.get(0);
        reservaPersistence.delete(entity.getId());
        ReservaEntity e2 = reservaPersistence.find(entity.getId());
        Assert.assertNull(e2);
        
    }
    
    @Test
    
    public void updateReservaTest(){
        ReservaEntity entity = data.get(0);
        ReservaEntity update = factory.manufacturePojo(ReservaEntity.class);
        update.setId(entity.getId());
        ReservaEntity updated = reservaPersistence.update(update);
        Assert.assertNotNull(updated);
        Assert.assertEquals(updated.getCancelado(), update.getCancelado());
        Assert.assertEquals(updated.getFechaInicio(), update.getFechaInicio());
        Assert.assertEquals(updated.getFechaTerminacion(),update.getFechaTerminacion());
        Assert.assertEquals(updated.getPrecio(),update.getPrecio());
    }
    
    
}
