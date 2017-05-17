/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.logic;

import co.edu.uniandes.csw.habitaciones.ejbs.ResenaLogic;
import co.edu.uniandes.csw.habitaciones.entities.ViajeroEntity;
import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.ResenaEntity;
import co.edu.uniandes.csw.habitaciones.persistence.ResenaPersistence;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.Date;
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
public class ResenaLogicTest {

    private final static int MAX_VIAJEROS = 2;

    private final static int MAX_HABITACIONES = 2;

    private final static int MAX_RESENAS = 4;

    private final static long CALIFICACION_MAYOR = 5;

    private final static long CALIFICACION_MENOR = 3;

    @PersistenceContext
    private EntityManager em;

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private UserTransaction utx;

    @Inject
    private ResenaLogic logic;

    private List<ResenaEntity> data = new ArrayList<ResenaEntity>();

    private List<ViajeroEntity> dataViajero = new ArrayList<ViajeroEntity>();

    private List<HabitacionEntity> dataHabitacion = new ArrayList<HabitacionEntity>();

    private List<ReservaEntity> dataReserva = new ArrayList<ReservaEntity>();

    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ResenaEntity.class.getPackage())
                .addPackage(ResenaLogic.class.getPackage())
                .addPackage(ResenaPersistence.class.getPackage())
                .addPackage(ViajeroEntity.class.getPackage())
                .addPackage(HabitacionEntity.class.getPackage())
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
        em.createQuery("delete from ResenaEntity").executeUpdate();
        em.createQuery("delete from PagoEntity").executeUpdate();
        em.createQuery("delete from DisponibilidadEntity").executeUpdate();
        em.createQuery("delete from ReservaEntity").executeUpdate();
        em.createQuery("delete from HabitacionEntity").executeUpdate();
        em.createQuery("delete from ViajeroEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();

    }

    private void insertData() {

        for (int i = 0; i < MAX_VIAJEROS; i++) {
            ViajeroEntity viajero = factory.manufacturePojo(ViajeroEntity.class);
            dataViajero.add(viajero);
            em.persist(viajero);
        }

        for (int i = 0; i < MAX_HABITACIONES; i++) {
            HabitacionEntity habitacion = factory.manufacturePojo(HabitacionEntity.class);
            dataHabitacion.add(habitacion);
            em.persist(habitacion);
        }

        //Se agregan reservas y resenas a la base de datos y a las listas
        //la mitad de las resenas y reservas tendran viajeros y habitaciones distintas
        //a la otra mitad
        Date fechaActual = new Date();
        for (int i = 0; i < MAX_RESENAS; i++) {
            ReservaEntity reserva = factory.manufacturePojo(ReservaEntity.class);
            ResenaEntity entity = factory.manufacturePojo(ResenaEntity.class);

            //Coloca la fecha de inicio de la reserva en el dia de ayer.
            reserva.setFechaInicio(new Date(fechaActual.getTime() - 86400000));
            
            //la mitad de las resenas son del viajero y la habitacion en la posicion 0
            //la otra mitad son del viajero y la habitacion en la posicion 1
            int j = (i < (MAX_RESENAS / 2)) ? 0 : 1;

            //una mitad tiene califacion de 2
            //la otra mitad tiene calificacion de 4
            entity.setCalificacion((double) ((2 * j) + 2));

            reserva.setHabitacion(dataHabitacion.get(j));
            reserva.setViajero(dataViajero.get(j));
            reserva.setPago(null);
            reserva.setAnfitrion(null);
            reserva.setCancelado(false);
            
            entity.setViajero(dataViajero.get(j));
            entity.setHabitacion(dataHabitacion.get(j));

            em.persist(reserva);
            em.persist(entity);

            dataReserva.add(reserva);
            data.add(entity);

        }
    }
   
    @Test public void createResenaUno() { 
        
        ResenaEntity entity = factory.manufacturePojo(ResenaEntity.class);
        entity.setHabitacion(dataHabitacion.get(0));
        entity.setViajero(dataViajero.get(0)); 
        entity.setCalificacion(3.0);

        try { 
            ResenaEntity result = logic.createResena(entity);
            Assert.assertNotNull(result); 
            ResenaEntity entityBusq =em.find(ResenaEntity.class, result.getId());
            Assert.assertNotNull(entityBusq);
            Assert.assertEquals(entity.getCalificacion(),
            entityBusq.getCalificacion());
            Assert.assertEquals(entity.getComentario(), entityBusq.getComentario());
        }
        catch (BusinessLogicException e){ 
            Assert.fail("No deberia generar excepcion: " +e.getMessage());
            e.printStackTrace(); 
        } 
    }

    @Test public void createResenaDos() { 
        try { 
            ResenaEntity entity = factory.manufacturePojo(ResenaEntity.class);
            entity.setHabitacion(dataHabitacion.get(0));
            entity.setViajero(dataViajero.get(0)); 
            entity.setCalificacion(10.0);

            ResenaEntity result = logic.createResena(entity); 
            Assert.fail("Deberia generar excepcion. La calificacion es mayor " 
                    + "al maximo establecida");
        } 
        catch (BusinessLogicException e)
        {

        }
    }

    @Test public void createResenaTres() 
    { 
        try { 
            ResenaEntity entity =factory.manufacturePojo(ResenaEntity.class); 
            entity.setHabitacion(null);
            entity.setViajero(null); entity.setCalificacion(3.0);

            ResenaEntity result = logic.createResena(entity); 
            Assert.fail("Deberia generar excepcion. El viajeroo y la habitacion " 
                    + "fueron inicializadosen Null");
        }
        catch (BusinessLogicException ex) 
        {

        }
    }


    @Test public void findResenas() { 
        List<ResenaEntity> lista = logic.findResenas(); 
        Assert.assertEquals(data.size(), lista.size());//expected data.size()

        for(ResenaEntity entityUno: lista) { 
            boolean encontrado = false;
            
            for(ResenaEntity entityDos : data) 
            {
                if(entityUno.getId().equals(entityDos.getId())) 
                { encontrado = true; 
                
                } 
            }
            Assert.assertTrue(encontrado); 
        }
    }

    @Test public void findResenasPorViajero() { 
        
        List<ResenaEntity> lista;
        lista = logic.findResenasViajero(dataViajero.get(0).getIdUsuario());
        Assert.assertEquals((data.size()/2), lista.size());

        for(ResenaEntity entityUno: lista) { 
            boolean encontrado = false;
            
            for(ResenaEntity entityDos : data) {
                
                if(entityUno.getId().equals(entityDos.getId())) {
                    
                    if(entityUno.getViajero().getCorreoElectronico().equals(
                        dataViajero.get(0).getCorreoElectronico())) {
                        encontrado = true;
                     } 
                
                } 
           
            }
            Assert.assertTrue(encontrado); 
        } 
    }

    @Test public void findResenasPorHabitacion() { 
        
        List<ResenaEntity> lista;
        lista = logic.findResenasHabitacion(dataHabitacion.get(0).getId());
        Assert.assertEquals((data.size()/2), lista.size());

        for(ResenaEntity entityUno: lista) { 
            
            boolean encontrado = false;
            for(ResenaEntity entityDos : data) {
                
                if(entityUno.getId().equals(entityDos.getId())) {
                    
                    if(entityUno.getHabitacion().getDescripcion().equals(
                        dataHabitacion.get(0).getDescripcion())) 
                    { 
                        encontrado = true; 
                    } 
                } 
            }
            Assert.assertTrue(encontrado); 
        } 
    }

    @Test public void findResenasPorViajeroYRangoUno() {

        try { 
            
            List<ResenaEntity> lista; 
            lista = logic.finResenasViajeroPorRango(dataViajero.get(1).getIdUsuario(),
            CALIFICACION_MENOR,CALIFICACION_MAYOR);

            Assert.assertEquals((data.size()/2), lista.size());

            for(ResenaEntity entityUno: lista) { 
                boolean encontrado = false;
                
                for(ResenaEntity entityDos : data) { 
                    
                    //revisa si las entidades tienen el mismo ID 
                    Boolean uno = entityUno.getId().equals(entityDos.getId());
                    
                    
                    //revisa si las entidades tienen al mismo viajero 
                    Boolean dos =entityUno.getViajero().getCorreoElectronico().equals(
                    dataViajero.get(1).getCorreoElectronico());

                    //revisa si la entidad se encuentra dentro de rango 
                    Boolean tres =(entityUno.getCalificacion() >= CALIFICACION_MENOR) &&
                    (entityUno.getCalificacion() <= CALIFICACION_MAYOR);

                    if(uno && dos && tres) { 
                        encontrado = true; 
                    }

                }
                Assert.assertTrue(encontrado); 
            } 
        } 
        catch (BusinessLogicException e) {
            Assert.fail("No deberia generar excepcion"); 
        }

    }

    @Test public void findResenasPorViajeroYRangoDos() { 
        try {
            logic.finResenasViajeroPorRango(dataViajero.get(0).getIdUsuario(),
            CALIFICACION_MAYOR,CALIFICACION_MENOR);

            Assert.fail("Deberia generar excepcion"); 
        } 
        catch (BusinessLogicException e) { 
            //Deberia generar excepcion 
        } 
    }

    @Test public void findResenasPorHabitacionYRangoUno() { 
        try {
            List<ResenaEntity> lista; lista =
            logic.finResenasHabitacionPorRango(dataHabitacion.get(1).getId(),
            CALIFICACION_MENOR,CALIFICACION_MAYOR);

            Assert.assertEquals((data.size()/2), lista.size());

            for(ResenaEntity entityUno: lista) { 
                
                boolean encontrado = false;
                for(ResenaEntity entityDos : data) { 
                    
                    //revisa si las entidades tienen el mismo ID 
                    Boolean uno = entityUno.getId().equals(entityDos.getId());

                    //revisa si las entidades tienen a la misma habitacion 
                    Boolean dos = entityUno.getHabitacion().getDescripcion().equals(
                    dataHabitacion.get(1).getDescripcion());

                    //revisa si la entidad se encuentra dentro del rango 
                    Boolean tres = (entityUno.getCalificacion() >= CALIFICACION_MENOR) &&
                    (entityUno.getCalificacion() <= CALIFICACION_MAYOR);

                    if(uno && dos && tres) 
                    { 
                        encontrado = true; 
                    }

                }
                Assert.assertTrue(encontrado); 
            } 
        } 
        catch (BusinessLogicException e) {
            
            Assert.fail("No deberia generar excepcion"); 
        } 
    }

    @Test public void findResenasPorHabitacionYRangoDos() {
        
        try {
            
            logic.finResenasHabitacionPorRango(dataHabitacion.get(0).getId(),
            CALIFICACION_MAYOR, CALIFICACION_MENOR);

            Assert.fail("Deberia generar excepcion"); 
        } 
        catch (BusinessLogicException e) { 
                //Deberia generar excepcion 
        } 
    }

    @Test public void findResena() { ResenaEntity entity = data.get(0);
    ResenaEntity entityBusq = logic.findResena(entity.getId());

    Assert.assertNotNull(entityBusq);
    Assert.assertEquals(entity.getCalificacion(),
    entityBusq.getCalificacion());
    Assert.assertEquals(entity.getComentario(), entityBusq.getComentario());
    }

    @Test public void deleteResena() { 
        ResenaEntity entity = data.get(0);
        logic.delete(entity.getId());

        ResenaEntity entityBusq = logic.findResena(entity.getId());
        Assert.assertNull(entityBusq); 
    }

    @Test public void updateResenaUno() {
        try { 
            ResenaEntity entity = data.get(0); ResenaEntity entityUp =
            factory.manufacturePojo(ResenaEntity.class);

            entityUp.setId(entity.getId()); entityUp.setViajero(dataViajero.get(0));
            entityUp.setHabitacion(dataHabitacion.get(0));
            entityUp.setCalificacion(3.0);

            logic.updateResena(entityUp); 
            ResenaEntity entityBusq =logic.findResena(entity.getId());

            Assert.assertNotNull(entityBusq);
            Assert.assertEquals(entityUp.getCalificacion(),
            entityBusq.getCalificacion());
            Assert.assertEquals(entityUp.getComentario(),
            entityBusq.getComentario()); 
        } 
        catch (BusinessLogicException e) {
            
            Assert.fail("No deberia generar excepcion: " + e.getMessage()); 
        }


    }

    @Test public void updateResenaDos() { 
        
        try { 
            
            ResenaEntity entity = data.get(0); ResenaEntity entityUp =
            factory.manufacturePojo(ResenaEntity.class);

            entityUp.setId(entity.getId()); entityUp.setViajero(dataViajero.get(0));
            entityUp.setHabitacion(dataHabitacion.get(0));
            entityUp.setCalificacion(10.0);

            logic.updateResena(entityUp);

            Assert.fail("Deberia generar excepcion"); 
        } 
        catch (BusinessLogicException e) {

        }
    }

    @Test public void updateResenaTres() {
        try { 
            ResenaEntity entity = data.get(0); ResenaEntity entityUp =
            factory.manufacturePojo(ResenaEntity.class);

            entityUp.setId(entity.getId()); entityUp.setViajero(null);
            entityUp.setHabitacion(null); entityUp.setCalificacion(3.0);

            logic.updateResena(entityUp);

            Assert.fail("Deberia generar excepcion"); 
        } 
        catch (BusinessLogicException e) {

        }
    }
     
}
