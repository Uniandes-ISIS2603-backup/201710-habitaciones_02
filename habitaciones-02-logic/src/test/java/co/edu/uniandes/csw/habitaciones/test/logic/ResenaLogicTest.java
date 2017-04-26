/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.logic;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import co.edu.uniandes.csw.habitaciones.ejbs.ResenaLogic;
import co.edu.uniandes.csw.habitaciones.entities.ResenaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s.cortes
 */
@RunWith(Arquillian.class)
public class ResenaLogicTest {
    
    /**
     * 
     */
    private PodamFactory factory = new PodamFactoryImpl();
    
    /**
     * 
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * 
     */
    @Inject
    private ResenaLogic resenaLogic;
    
    /**
     * 
     */
    @Inject
    private UserTransaction utx;
    
    /**
     * 
     */
    private List<ResenaEntity> data = new ArrayList<ResenaEntity>();
    
    /**
     * 
     */
    
    
    public ResenaLogicTest() {
    }

    @Test
    public void testSomeMethod() {
    }
    
}
