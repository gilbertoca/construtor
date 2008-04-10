/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.service;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import net.sourceforge.orbroker.Broker;

import org.junit.Test;

import com.gilbertoca.gfi.inventario.service.CategoriaService;
import com.gilbertoca.gfi.inventario2.model.Categoria;
import com.gilbertoca.gfi.service.ResourceLocator;

/**
 *
 * @author Gilberto
 */
public class ServiceLocatorTest {

    /**
     * Test of insert method, of class CategoriaService.
     */
    @Test
    public void insertCategoria() {
        System.out.println("insertCategoria");
        Categoria entity = new Categoria(4, "MT3", "Metros");
        System.out.println(entity);
        Service instance = new Service<Categoria, Integer>();
        instance.insert(entity);
        Collection result = instance.findAll();
        System.out.println(result);
        assertFalse(result.isEmpty());
    }

}
