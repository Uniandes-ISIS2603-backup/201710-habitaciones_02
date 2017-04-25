/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.ejbs;

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
public class ReservaLogicTest {
    
    public ReservaLogicTest() {
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
     * Test of findReservas method, of class ReservaLogic.
     */
    @Test
    public void testFindReservas() throws Exception {
        System.out.println("findReservas");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ReservaLogic instance = (ReservaLogic)container.getContext().lookup("java:global/classes/ReservaLogic");
        List<ReservaEntity> expResult = null;
        List<ReservaEntity> result = instance.findReservas();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findReservasByViajero method, of class ReservaLogic.
     */
    @Test
    public void testFindReservasByViajero() throws Exception {
        System.out.println("findReservasByViajero");
        Long idViajero = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ReservaLogic instance = (ReservaLogic)container.getContext().lookup("java:global/classes/ReservaLogic");
        List<ReservaEntity> expResult = null;
        List<ReservaEntity> result = instance.findReservasByViajero(idViajero);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findReserva method, of class ReservaLogic.
     */
    @Test
    public void testFindReserva() throws Exception {
        System.out.println("findReserva");
        Long id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ReservaLogic instance = (ReservaLogic)container.getContext().lookup("java:global/classes/ReservaLogic");
        ReservaEntity expResult = null;
        ReservaEntity result = instance.findReserva(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createReserva method, of class ReservaLogic.
     */
    @Test
    public void testCreateReserva() throws Exception {
        System.out.println("createReserva");
        ReservaEntity entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ReservaLogic instance = (ReservaLogic)container.getContext().lookup("java:global/classes/ReservaLogic");
        ReservaEntity expResult = null;
        ReservaEntity result = instance.createReserva(entity);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateReserva method, of class ReservaLogic.
     */
    @Test
    public void testUpdateReserva() throws Exception {
        System.out.println("updateReserva");
        ReservaEntity entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ReservaLogic instance = (ReservaLogic)container.getContext().lookup("java:global/classes/ReservaLogic");
        ReservaEntity expResult = null;
        ReservaEntity result = instance.updateReserva(entity);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class ReservaLogic.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Long id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ReservaLogic instance = (ReservaLogic)container.getContext().lookup("java:global/classes/ReservaLogic");
        instance.delete(id);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
