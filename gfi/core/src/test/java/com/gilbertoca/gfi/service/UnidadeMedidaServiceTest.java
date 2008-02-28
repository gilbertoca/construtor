/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.service;

import com.gilbertoca.gfi.inventario.service.UnidadeMedidaService;
import com.gilbertoca.gfi.inventario2.model.Item;

import java.util.Collection;
import net.sourceforge.orbroker.Broker;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gilberto
 */
public class UnidadeMedidaServiceTest {

    /**
     * Test of getBroker method, of class UnidadeMedidaService.
     */
    @Test
    public void getBroker() {
        System.out.println("getBroker");
        UnidadeMedidaService instance = new UnidadeMedidaService();
        Broker result = instance.getBroker();
        assertNotNull(result);
    }

    /**
     * Test of insert method, of class UnidadeMedidaService.
     */
    @Test
    public void insert() {
        System.out.println("insert");
        Item entity = new Item("MT3", "Metros");
        UnidadeMedidaService instance = new UnidadeMedidaService();
        instance.insert(entity);
        Collection result = instance.findAll();
        assertFalse(result.isEmpty());
    }

    /**
     * Test of findByPk method, of class UnidadeMedidaService.
     */
    @Test
    public void findByPk() {
        System.out.println("findByPk");
        String cdUnidadeMedida = "MT";
        UnidadeMedidaService instance = new UnidadeMedidaService();
        Item uM = instance.findByPk(cdUnidadeMedida);
        System.out.println(uM);
        assertTrue("O valor do campo cdUnidadeMedida", cdUnidadeMedida.equals(uM.getCdUnidadeMedida()));
    }
    /**
     * Test of findByPk method, of class UnidadeMedidaService.
     */
    @Test
    public void findByPkIfExists() {
        System.out.println("findByPkIfExists");
        Item uM = new Item();
        UnidadeMedidaService instance = new UnidadeMedidaService();
        uM.setCdUnidadeMedida("MT");
        assertTrue(instance.findByPk(uM));        
        System.out.println(uM);
    }
    /**
     * Test of findLike method, of class UnidadeMedidaService.
     */
    @Test
    public void findLike(){
        System.out.println("findLike");
        UnidadeMedidaService instance = new UnidadeMedidaService();
        Collection result = instance.findLike(new Item("MT", "Cu"));
        System.out.println(result);
        assertFalse(result.isEmpty());
    }
    /**
     * Test of update method, of class UnidadeMedidaService.
     */
    @Test
    public void update() {
        System.out.println("update");
        String cdUnidadeMedida = "MT";
        UnidadeMedidaService instance = new UnidadeMedidaService();
        Item uM = instance.findByPk(cdUnidadeMedida);
        uM.setDescricaoUnidade("Cubicos");
        instance.update(uM);
        Item uM2 = instance.findByPk(cdUnidadeMedida);
        assertTrue("O valor do campo DcUnidadeMedida", uM.getDescricaoUnidade().equals(uM2.getDescricaoUnidade()));
    }

    /**
     * Test of findAll method, of class UnidadeMedidaService.
     */
    @Test
    public void findAll() {
        System.out.println("findAll");
        UnidadeMedidaService instance = new UnidadeMedidaService();
        Collection result = instance.findAll();
        System.out.println(result);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of delete method, of class UnidadeMedidaService.
     */
    @Test
    public void delete() {
        System.out.println("delete");
        String cdUnidadeMedida = "KG";
        Item uM = null;
        UnidadeMedidaService instance = new UnidadeMedidaService();
        instance.deleteByPk(cdUnidadeMedida);
        try {
            uM = instance.findByPk(cdUnidadeMedida);
            //fail("findByPk didn't throw Exception");
        } catch (Exception d) {
            assertNotNull(d);
        }
    }
    /**
     * Test of delete method, of class UnidadeMedidaService.
     */
    @Test
    public void deleteObject() {
        System.out.println("delete");
        String cdUnidadeMedida = "CEM";
        UnidadeMedidaService instance = new UnidadeMedidaService();        
        Item uM = instance.findByPk(cdUnidadeMedida);
        instance.delete(uM);
        try {
            uM = instance.findByPk(cdUnidadeMedida);
            //fail("findByPk didn't throw Exception");
        } catch (Exception d) {
            assertNotNull(d);
        }
    }
    
}
