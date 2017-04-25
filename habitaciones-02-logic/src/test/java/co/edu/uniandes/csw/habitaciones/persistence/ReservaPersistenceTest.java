/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.persistence;

import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dg.guarin20
 */
public class ReservaPersistenceTest {
    
    public ReservaPersistenceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of find method, of class ReservaPersistence.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        Long id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ReservaPersistence instance = (ReservaPersistence)container.getContext().lookup("java:global/classes/ReservaPersistence");
        ReservaEntity expResult = null;
        ReservaEntity result = instance.find(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class ReservaPersistence.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ReservaPersistence instance = (ReservaPersistence)container.getContext().lookup("java:global/classes/ReservaPersistence");
        List<ReservaEntity> expResult = null;
        List<ReservaEntity> result = instance.findAll();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllByViajero method, of class ReservaPersistence.
     */
    @Test
    public void testFindAllByViajero() throws Exception {
        System.out.println("findAllByViajero");
        Long idViajero = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ReservaPersistence instance = (ReservaPersistence)container.getContext().lookup("java:global/classes/ReservaPersistence");
        List<ReservaEntity> expResult = null;
        List<ReservaEntity> result = instance.findAllByViajero(idViajero);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class ReservaPersistence.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        ReservaEntity entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ReservaPersistence instance = (ReservaPersistence)container.getContext().lookup("java:global/classes/ReservaPersistence");
        ReservaEntity expResult = null;
        ReservaEntity result = instance.create(entity);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class ReservaPersistence.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        ReservaEntity entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ReservaPersistence instance = (ReservaPersistence)container.getContext().lookup("java:global/classes/ReservaPersistence");
        ReservaEntity expResult = null;
        ReservaEntity result = instance.update(entity);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class ReservaPersistence.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Long id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ReservaPersistence instance = (ReservaPersistence)container.getContext().lookup("java:global/classes/ReservaPersistence");
        instance.delete(id);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findReservaFromViajeroAndHabitacion method, of class ReservaPersistence.
     */
    @Test
    public void testFindReservaFromViajeroAndHabitacion() throws Exception {
        System.out.println("findReservaFromViajeroAndHabitacion");
        Long idViajero = null;
        Long idHabitacion = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ReservaPersistence instance = (ReservaPersistence)container.getContext().lookup("java:global/classes/ReservaPersistence");
        ReservaEntity expResult = null;
        ReservaEntity result = instance.findReservaFromViajeroAndHabitacion(idViajero, idHabitacion);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
