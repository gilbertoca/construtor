/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gilbertoca.gfi.service;

import com.gilbertoca.gfi.model.inventario2.UnidadeMedida;
import java.util.Collection;
import net.sourceforge.orbroker.Broker;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gilberto
 */
public class InventarioServiceTest {

    public InventarioServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getBroker method, of class InventarioService.
     */
    @Test
    public void getBroker() {
        System.out.println("getBroker");
        InventarioService instance = new InventarioService();
        Broker result = instance.getBroker();
        assertNotNull(result);
    }

    /**
     * Test of insert method, of class InventarioService.
     */
    @Test
    public void insert() {
        System.out.println("insert");
        UnidadeMedida entity = new UnidadeMedida("MT3", "Metros");
        InventarioService instance = new InventarioService();
        instance.insert(entity);
        Collection result = instance.findAll();
        assertFalse(result.isEmpty());
    }

    /**
     * Test of update method, of class InventarioService.
     */
//    @Test
//    public void update() {
//        System.out.println("update");
//        Object entity = null;
//        InventarioService instance = new InventarioService();
//        instance.update(entity);
//    }

    /**
     * Test of findAll method, of class InventarioService.
     */
    @Test
    public void findAll() {
        System.out.println("findAll");
        InventarioService instance = new InventarioService();
        Collection result = instance.findAll();
        System.out.println(result);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of delete method, of class InventarioService.
     */
    @Test
    public void delete() {
        System.out.println("delete");
        Object entity = null;
        InventarioService instance = new InventarioService();
        instance.delete(entity);
    }

}