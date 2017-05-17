/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.logic;

import co.edu.uniandes.csw.habitaciones.ejbs.AnfitrionLogic;
import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.AnfitrionPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AnfitrionLogicTest 
{
    @PersistenceContext
    private EntityManager em;
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private UserTransaction utx;
    
    @Inject
    private AnfitrionLogic logic;
    
    private List<AnfitrionEntity> data = new ArrayList<AnfitrionEntity>();
    
    /**
     * 
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AnfitrionEntity.class.getPackage())
                .addPackage(AnfitrionLogic.class.getPackage())
                .addPackage(AnfitrionPersistence.class.getPackage())
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
        em.createQuery("delete from AnfitrionEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        
        for (int i = 0; i < 3; i++) {
            AnfitrionEntity entity = factory.manufacturePojo(AnfitrionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void testCreateAnfitrionTest()
    {
        try 
        {
            AnfitrionEntity entity = factory.manufacturePojo(AnfitrionEntity.class);
            AnfitrionEntity result = logic.createAnfitrion(entity);

            Assert.assertNotNull(result);
            
            AnfitrionEntity entityBusq = em.find(AnfitrionEntity.class, result.getIdUsuario());
            Assert.assertNotNull(entityBusq);
            Assert.assertEquals(entity.getContrasena(), entityBusq.getContrasena());
            Assert.assertEquals(entity.getCorreoElectronico(), entityBusq.getCorreoElectronico());
            Assert.assertEquals(entity.getCorreoElectronico(), entityBusq.getCorreoElectronico());
            Assert.assertEquals(entity.getDireccion(), entityBusq.getDireccion());
            Assert.assertEquals(entity.getImagen(), entityBusq.getImagen());
            Assert.assertEquals(entity.getNombre(), entityBusq.getNombre());
            Assert.assertEquals(entity.getNumeroDocumento(), entityBusq.getNumeroDocumento());
            Assert.assertEquals(entity.getTelefono(), entityBusq.getTelefono());
            Assert.assertEquals(entity.getTipoDocumento(), entityBusq.getTipoDocumento());
        
        } 
        catch (BusinessLogicException e) 
        {
            Assert.fail("el metodo no deberia generar una excepcion");
        }
        
        try 
        {
            AnfitrionEntity entity = factory.manufacturePojo(AnfitrionEntity.class);
            entity.setNombre(null);
            entity.setContrasena("");
            AnfitrionEntity result = logic.createAnfitrion(entity);
            
            Assert.fail("Deberia haber lanzado una excepcion. La informacion contenia nulls y vacios");
        } 
        catch (BusinessLogicException e) 
        {
            
        }
    }
    
    @Test
    public void testGetAnfitriones()
    {
        List<AnfitrionEntity> lista = logic.getAnfitriones();
        Assert.assertEquals(data.size(), lista.size());
        for(AnfitrionEntity entityUno : lista)
        {
            boolean encontrado = false;
            
            for(AnfitrionEntity entityDos : data)
            {
                if(entityUno.getIdUsuario().equals(entityDos.getIdUsuario()))
                {
                    encontrado = true;
                }
            }
            
            Assert.assertTrue(encontrado);
        }
    }
    
    @Test
    public void testGetAnfitrion()
    {
        AnfitrionEntity entity = data.get(0);
        AnfitrionEntity entityBusq = logic.getAnfitrion(entity.getIdUsuario());
        
        Assert.assertNotNull(entityBusq);
        Assert.assertEquals(entity.getCorreoElectronico(), entityBusq.getCorreoElectronico());
        Assert.assertEquals(entity.getContrasena(), entityBusq.getContrasena());
        Assert.assertEquals(entity.getDireccion(), entityBusq.getDireccion());
        Assert.assertEquals(entity.getImagen(), entityBusq.getImagen());
        Assert.assertEquals(entity.getNombre(), entityBusq.getNombre());
        Assert.assertEquals(entity.getNumeroDocumento(), entityBusq.getNumeroDocumento());
        Assert.assertEquals(entity.getTelefono(), entityBusq.getTelefono());
        Assert.assertEquals(entity.getTipoDocumento(), entityBusq.getTipoDocumento());
       
    }
    
    @Test
    public void testFindAnfitrionLoginUno(){
        
        
        try {
            
           AnfitrionEntity entity = data.get(0);
            AnfitrionEntity entityBusq = logic.getAnfitrionLogin(entity.getCorreoElectronico(), entity.getContrasena());
            
            Assert.assertNotNull(entityBusq);
            Assert.assertEquals(entity.getCorreoElectronico(), entityBusq.getCorreoElectronico());
            Assert.assertEquals(entity.getContrasena(), entityBusq.getContrasena());
            Assert.assertEquals(entity.getDireccion(), entityBusq.getDireccion());
            Assert.assertEquals(entity.getImagen(), entityBusq.getImagen());
            Assert.assertEquals(entity.getNombre(), entityBusq.getNombre());
            Assert.assertEquals(entity.getNumeroDocumento(), entityBusq.getNumeroDocumento());
            Assert.assertEquals(entity.getTelefono(), entityBusq.getTelefono());
            Assert.assertEquals(entity.getTipoDocumento(), entityBusq.getTipoDocumento());
        } 
        catch (BusinessLogicException ex) {
            
            ex.printStackTrace();
           
        }
    }
    
    @Test
    public void testFindAnfitrionLoginDos(){
        
        try {
            
            AnfitrionEntity entity = data.get(0);
            AnfitrionEntity entityBusq = logic.getAnfitrionLogin(entity.getNombre(), entity.getDireccion());
            
            Assert.fail("Debería fallar");
        } 
        catch (BusinessLogicException ex) {
            
        }
    }
    
    
    
    @Test
    public void testDeleteAnfitrion()
    {
        AnfitrionEntity entity = data.get(0);
        logic.deleteAnfitrion(entity.getIdUsuario());
        
        AnfitrionEntity entityBusq = logic.getAnfitrion(entity.getIdUsuario());
        Assert.assertNull(entityBusq);
    }
    
    @Test
    public void testUpdateAnfitrion() 
    {
       
            AnfitrionEntity entity = data.get(0);
            AnfitrionEntity entityUp = factory.manufacturePojo(AnfitrionEntity.class);
            entityUp.setIdUsuario(entity.getIdUsuario());
            
            logic.updateAnfitrion(entityUp);
            
            AnfitrionEntity entityBusq = logic.getAnfitrion(entity.getIdUsuario());
            
            Assert.assertNotNull(entityBusq);
            Assert.assertEquals(entityUp.getNombre(), entityBusq.getNombre());
            Assert.assertEquals(entityUp.getContrasena(), entityBusq.getContrasena());
            Assert.assertEquals(entityUp.getCorreoElectronico(), entityBusq.getCorreoElectronico());
            Assert.assertEquals(entityUp.getDireccion(), entityBusq.getDireccion());
            Assert.assertEquals(entityUp.getImagen(), entityBusq.getImagen());
            Assert.assertEquals(entityUp.getNumeroDocumento(), entityBusq.getNumeroDocumento());
            Assert.assertEquals(entityUp.getTelefono(), entityBusq.getTelefono());
            Assert.assertEquals(entityUp.getTipoDocumento(), entityBusq.getTipoDocumento());


    }
}
