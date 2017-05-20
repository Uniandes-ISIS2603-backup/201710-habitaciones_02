/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.logic;

import co.edu.uniandes.csw.habitaciones.ejbs.ViajeroLogic;
import co.edu.uniandes.csw.habitaciones.entities.ViajeroEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.ViajeroPersistence;
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
public class ViajeroLogicTest 
{
    @PersistenceContext
    private EntityManager em;
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private UserTransaction utx;
    
    @Inject
    private ViajeroLogic logic;
    
    private List<ViajeroEntity> data = new ArrayList<ViajeroEntity>();
    
    /**
     * 
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViajeroEntity.class.getPackage())
                .addPackage(ViajeroLogic.class.getPackage())
                .addPackage(ViajeroPersistence.class.getPackage())
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
        em.createQuery("delete from ViajeroEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        
        for (int i = 0; i < 3; i++) {
            ViajeroEntity entity = factory.manufacturePojo(ViajeroEntity.class);
            entity.setActivo(true);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void testCreateViajeroTest()
    {
        try 
        {
            ViajeroEntity entity = factory.manufacturePojo(ViajeroEntity.class);
            ViajeroEntity result = logic.createViajero(entity);

            Assert.assertNotNull(result);
            
            ViajeroEntity entityBusq = em.find(ViajeroEntity.class, result.getIdUsuario());
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
            ViajeroEntity entity = factory.manufacturePojo(ViajeroEntity.class);
            entity.setNombre(null);
            entity.setContrasena("");
            ViajeroEntity result = logic.createViajero(entity);
            
            Assert.fail("Deberia haber lanzado una excepcion. La informacion contenia nulls y vacios");
        } 
        catch (BusinessLogicException e) 
        {
            
        }
    }
    
    @Test
    public void testGetViajeros()
    {
        List<ViajeroEntity> lista = logic.getViajeros();
        Assert.assertEquals(data.size(), lista.size());
        for(ViajeroEntity entityUno : lista)
        {
            boolean encontrado = false;
            
            for(ViajeroEntity entityDos : data)
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
    public void testGetViajero()
    {
        ViajeroEntity entity = data.get(0);
        ViajeroEntity entityBusq = logic.getViajero(entity.getIdUsuario());
        
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
    public void testFindViajeroLoginUno(){
        
        
        try {
            
            ViajeroEntity entity = data.get(0);
            ViajeroEntity entityBusq = logic.getViajeroLogin(entity.getCorreoElectronico(), entity.getContrasena());
            
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
            Assert.fail("No debería fallar");
        }
    }
    
    @Test
    public void testFindViajeroLoginDos(){
        
        try {
            
            ViajeroEntity entity = data.get(0);
            ViajeroEntity entityBusq = logic.getViajeroLogin(entity.getNombre(), entity.getDireccion());
            
            Assert.fail("Debería fallar");
        } 
        catch (BusinessLogicException ex) {
            
        }
    }
    
    
    
    @Test
    public void testDeleteViajero()
    {
        ViajeroEntity entity = data.get(0);
        logic.deleteViajero(entity.getIdUsuario());
        
        ViajeroEntity entityBusq = logic.getViajero(entity.getIdUsuario());
        Assert.assertNull(entityBusq);
    }
    
    @Test
    public void testUpdateViajero() 
    {
        try 
        {
            ViajeroEntity entity = data.get(0);
            ViajeroEntity entityUp = factory.manufacturePojo(ViajeroEntity.class);
            entityUp.setIdUsuario(entity.getIdUsuario());
            
            logic.updateViajero(entityUp);
            
            ViajeroEntity entityBusq = logic.getViajero(entity.getIdUsuario());
            
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
        catch (BusinessLogicException e) 
        {
            Assert.fail("No deberia lanzar excepcion");
        }
        
        try 
        {
            ViajeroEntity entity = data.get(0);
            ViajeroEntity entityUp = factory.manufacturePojo(ViajeroEntity.class);
            entityUp.setContrasena("");
            entityUp.setNombre(null);
            entityUp.setIdUsuario(entity.getIdUsuario());
            
            logic.updateViajero(entityUp);
            
            Assert.fail("Deberia lanzar excepcion. "
                    + "Se esta intentando actualizar datos en Null o vacio");
        } 
        catch (BusinessLogicException e) 
        {
            
        }
    }
}
