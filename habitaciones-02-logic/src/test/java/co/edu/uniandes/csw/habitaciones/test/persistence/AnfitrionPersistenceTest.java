/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.persistence;

import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import co.edu.uniandes.csw.habitaciones.persistence.AnfitrionPersistence;
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
 * @author df.sanabria761
 */
@RunWith (Arquillian.class)
public class AnfitrionPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AnfitrionEntity.class.getPackage())
                .addPackage(AnfitrionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject 
    private AnfitrionPersistence anfitrionPersistence;
    
    @PersistenceContext (unitName = "habitacionesPU")
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private final PodamFactory factory = new PodamFactoryImpl();
    private List<AnfitrionEntity> data = new ArrayList<AnfitrionEntity>();
 
    
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
        em.createQuery("delete from AnfitrionEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }
    
    private void insertData(){
        for (int i = 0; i < 3; i++) {
            AnfitrionEntity entity = factory.manufacturePojo(AnfitrionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void testCreateAnfitrion() {
        AnfitrionEntity entity = factory.manufacturePojo(AnfitrionEntity.class);
        
        AnfitrionEntity result = anfitrionPersistence.create(entity);
        Assert.assertNotNull(result);
        AnfitrionEntity e2 = em.find(AnfitrionEntity.class, result.getIdUsuario());
        Assert.assertNotNull(e2);
        Assert.assertEquals(entity.getIdUsuario(), e2.getIdUsuario());        
        Assert.assertEquals(entity.getNombre(), e2.getNombre());
        Assert.assertEquals(entity.getContrasena(), e2.getContrasena());
        Assert.assertEquals(entity.getCorreoElectronico(), e2.getCorreoElectronico());
        Assert.assertEquals(entity.getNumeroDocumento(),e2.getNumeroDocumento());
    }
    
    @Test
    public void testGetAnfitriones(){
        List<AnfitrionEntity> list = anfitrionPersistence.findAll();
        Assert.assertEquals(list.size(), data.size());
        for (AnfitrionEntity anfitrionEntity : list) {
            boolean encontrado = false;
            for (AnfitrionEntity anfitrionEntity1 : data) {
                if(anfitrionEntity.getIdUsuario()
                        .equals(anfitrionEntity1.getIdUsuario())){
                    encontrado = true;
                }
                
            }
            Assert.assertTrue(encontrado);
        }
    }
    
    @Test 
    public void testGetAnfitrion(){
        AnfitrionEntity entity = data.get(0);
        AnfitrionEntity e2 = anfitrionPersistence.find(entity.getIdUsuario());
        Assert.assertNotNull(e2);
        Assert.assertEquals(entity.getNombre(),e2.getNombre());
  
    }
    
    @Test 
    public void testGetAnfitrionByIDNull(){
       boolean e2 = true;
       e2 = anfitrionPersistence.findByDocumento("");
       Assert.assertFalse(e2);
    }
    
    @Test
    public void testGetAnfitrionByIDTrue(){
        boolean existe = false;
        AnfitrionEntity entity = data.get(0);
        existe = anfitrionPersistence.findByDocumento(entity.getNumeroDocumento());
        Assert.assertTrue(existe);
    }
    @Test
    public void testDeleteAnfitrion(){
        AnfitrionEntity entity = data.get(0);
        anfitrionPersistence.delete(entity.getIdUsuario());
        AnfitrionEntity e2 = anfitrionPersistence.find(entity.getIdUsuario());
        Assert.assertNull(e2);
        
    }
    
    @Test
    
    public void testUpdateAnfitrion(){
        AnfitrionEntity entity = data.get(0);
        AnfitrionEntity update = factory.manufacturePojo(AnfitrionEntity.class);
        update.setIdUsuario(entity.getIdUsuario());
        AnfitrionEntity updated = anfitrionPersistence.update(update);
        Assert.assertNotNull(updated);
        Assert.assertEquals(updated.getNombre(), update.getNombre());
        Assert.assertEquals(updated.getDireccion(), update.getDireccion());
        Assert.assertEquals(updated.getContrasena(),update.getContrasena());
        Assert.assertEquals(updated.getCorreoElectronico(),update.getCorreoElectronico());
    }
}
