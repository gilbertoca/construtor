/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.service;

import com.gilbertoca.gfi.model.inventario2.UnidadeMedida;
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
        UnidadeMedida entity = new UnidadeMedida("MT3", "Metros");
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
        UnidadeMedida uM = instance.findByPk(cdUnidadeMedida);
        System.out.println(uM);
        assertTrue("O valor do campo cdUnidadeMedida", cdUnidadeMedida.equals(uM.getCdUnidadeMedida()));
    }

    /**
     * Test of update method, of class UnidadeMedidaService.
     */
    @Test
    public void update() {
        System.out.println("update");
        String cdUnidadeMedida = "MT";
        UnidadeMedidaService instance = new UnidadeMedidaService();
        UnidadeMedida uM = instance.findByPk(cdUnidadeMedida);
        uM.setDescricaoUnidade("Cubicos");
        instance.update(uM);
        UnidadeMedida uM2 = instance.findByPk(cdUnidadeMedida);
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
        UnidadeMedida uM = null;
        UnidadeMedidaService instance = new UnidadeMedidaService();
        instance.deleteByPk(cdUnidadeMedida);
        try {
            uM = instance.findByPk(cdUnidadeMedida);
            fail("findByPk didn't throw Exception");
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
        String cdUnidadeMedida = "KG";
        UnidadeMedidaService instance = new UnidadeMedidaService();        
        UnidadeMedida uM = instance.findByPk(cdUnidadeMedida);
        instance.delete(uM);
        try {
            uM = instance.findByPk(cdUnidadeMedida);
            fail("findByPk didn't throw Exception");
        } catch (Exception d) {
            assertNotNull(d);
        }
    }
    
}
