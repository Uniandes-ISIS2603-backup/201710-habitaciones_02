/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.logic;

import co.edu.uniandes.csw.habitaciones.ejbs.ReservaLogic;
import co.edu.uniandes.csw.habitaciones.ejbs.ViajeroLogic;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViajeroEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.ViajeroPersistence;
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
public class ReservasLogicTest {
    @PersistenceContext
    private EntityManager em;
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private UserTransaction utx;
    
    @Inject
    private ReservaLogic logic;

    private List<ReservaEntity> data = new ArrayList<ReservaEntity>();    
    
        @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViajeroEntity.class.getPackage())
                .addPackage(ViajeroLogic.class.getPackage())
                .addPackage(ViajeroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
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
        em.createQuery("delete from ReservaEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        
        for (int i = 0; i < 3; i++) {
            ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    @Test 
    public void CreateReserva()
    {
        try{
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity result = logic.createReserva(newEntity);
        
        Assert.assertNotNull(result);
        ReservaEntity entity = em.find(ReservaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getPrecio(), entity.getPrecio());
        Assert.assertEquals(newEntity.getCancelado(), entity.getCancelado());
        }
        catch (BusinessLogicException e) 
                {
                    Assert.fail("el metodo no deberia generar una excepcion");
                }
        
    }
           @Test
    public void getReservasTest(){
        List<ReservaEntity> list = logic.findReservas();
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
        List<ReservaEntity> list = logic.findReservasByViajero(entity.getViajero().getIdUsuario());
        Assert.assertEquals(list.size(), data.size());
        for (ReservaEntity reservaEntity : list) {
            boolean encontrado = false;
            for (ReservaEntity reservaEntity1 : data) {
                if(reservaEntity.getViajero().getIdUsuario()
                        .equals(entity.getViajero().getIdUsuario())){
                    encontrado = true;
                }   
            }
            Assert.assertTrue(encontrado);
        }
    }/**
        public void findReservaFromViajeroAndHabitacionTest(){
        ReservaEntity entity = data.get(0);
        ReservaEntity reservaEntity = logic.(entity.getViajero().getIdUsuario(),entity.getHabitacion().getId());
     
            boolean encontrado = false;
            
                if(reservaEntity.getViajero().getIdUsuario()
                        .equals(entity.getViajero().getIdUsuario())){
                        if(reservaEntity.getHabitacion().getId()
                        .equals(entity.getHabitacion().getId())){
                    encontrado = true;}
                   
            }
            Assert.assertTrue(encontrado);
        
    }
    **/
    @Test 
    public void getReservaTest(){
        ReservaEntity entity = data.get(0);
        ReservaEntity e2 = logic.findReserva(entity.getId());
        Assert.assertNotNull(e2);
        Assert.assertEquals(entity.getPrecio(),e2.getPrecio());
  
    }
    

    
    
    @Test
    public void deleteReservaTest(){
        ReservaEntity entity = data.get(0);
        logic.delete(entity.getId());
        ReservaEntity e2 = logic.findReserva(entity.getId());
        Assert.assertNull(e2);
        
    }
    
    @Test
    
    public void updateReservaTest() {
        try{
        ReservaEntity entity = data.get(0);
        ReservaEntity update = factory.manufacturePojo(ReservaEntity.class);
        update.setId(entity.getId());
        ReservaEntity updated = logic.updateReserva(update);
        Assert.assertNotNull(updated);
        Assert.assertEquals(updated.getCancelado(), update.getCancelado());
        Assert.assertEquals(updated.getFechaInicio(), update.getFechaInicio());
        Assert.assertEquals(updated.getFechaTerminacion(),update.getFechaTerminacion());
        Assert.assertEquals(updated.getPrecio(),update.getPrecio());
    }
       catch(BusinessLogicException e) 
                {
                    Assert.fail("el metodo no deberia generar una excepcion");
                }
    }
}
