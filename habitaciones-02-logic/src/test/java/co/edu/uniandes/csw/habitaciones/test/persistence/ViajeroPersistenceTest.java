/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.persistence;

import co.edu.uniandes.csw.habitaciones.entities.ViajeroEntity;
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
public class ViajeroPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViajeroEntity.class.getPackage())
                .addPackage(ViajeroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private ViajeroPersistence persistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private final PodamFactory factory = new PodamFactoryImpl();
    
    private List<ViajeroEntity> data = new ArrayList<ViajeroEntity>();
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
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
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createViajero()
    {
        ViajeroEntity entity = factory.manufacturePojo(ViajeroEntity.class);

        ViajeroEntity result = persistence.create(entity);
        
        Assert.assertNotNull(result);
        ViajeroEntity entityBusq = em.find(ViajeroEntity.class, result.getIdUsuario());
        Assert.assertNotNull(entityBusq);
        Assert.assertEquals(entity.getNombre(), entityBusq.getNombre());
        Assert.assertEquals(entity.getContrasena(), entityBusq.getContrasena());
        Assert.assertEquals(entity.getCorreoElectronico(), entityBusq.getCorreoElectronico());
        Assert.assertEquals(entity.getDireccion(), entityBusq.getDireccion());
        Assert.assertEquals(entity.getImagen(), entityBusq.getImagen());
        Assert.assertEquals(entity.getNumeroDocumento(), entityBusq.getNumeroDocumento());
        Assert.assertEquals(entity.getTelefono(), entityBusq.getTelefono());
        Assert.assertEquals(entity.getTipoDocumento(), entityBusq.getTipoDocumento());
        
    }
    
    @Test
    public void findViajeros()
    {
        List<ViajeroEntity> lista = persistence.findAll();
        Assert.assertEquals(data.size(), lista.size());
        for(ViajeroEntity entityUno: lista)
        {
            boolean encontrado = false;
            for(ViajeroEntity entityDos: data)
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
    public void findViajero()
    {
        ViajeroEntity entity = data.get(0);
        ViajeroEntity entityBusq = persistence.find(entity.getIdUsuario());
        
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
    public void deleteViajero()
    {
        ViajeroEntity entity = data.get(0);
        persistence.delete(entity.getIdUsuario());
     
        ViajeroEntity entityBusq = persistence.find(entity.getIdUsuario());
        Assert.assertNull(entityBusq);
    }
    
    @Test
    public void updateViajero()
    {
        ViajeroEntity entity = data.get(0);
        ViajeroEntity entityUp = factory.manufacturePojo(ViajeroEntity.class);
        
        entityUp.setIdUsuario(entity.getIdUsuario());
        persistence.update(entityUp);
        
        ViajeroEntity entityBusq = persistence.find(entity.getIdUsuario());
        
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
