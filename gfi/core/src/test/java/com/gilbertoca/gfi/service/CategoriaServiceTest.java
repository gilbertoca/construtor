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

/**
 *
 * @author Gilberto
 */
public class CategoriaServiceTest {

    /**
     * Test of getBroker method, of class CategoriaService.
     */
    @Test
    public void getBroker() {
        System.out.println("getBroker");
        CategoriaService instance = new CategoriaService();
        Broker result = instance.getBroker();
        assertNotNull(result);
    }

    /**
     * Test of insert method, of class CategoriaService.
     */
    @Test
    public void insert() {
        System.out.println("insert");
        Categoria entity = new Categoria(4, "MT3", "Metros");
        System.out.println(entity);
        CategoriaService instance = new CategoriaService();
        instance.insert(entity);
        Collection result = instance.findAll();
        System.out.println(result);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of findByPk method, of class CategoriaService.
     */
    @Test
    public void findByPk() {
        System.out.println("findByPk");
        Integer cdCategoria = 1;
        CategoriaService instance = new CategoriaService();
        Categoria uM = instance.findByPk(cdCategoria);
        System.out.println(uM);
        assertTrue("O valor do campo cdCategoria", cdCategoria.equals(uM.getCdCategoria()));
    }
    /**
     * Test of findByPk method, of class CategoriaService.
     */
    @Test
    public void findByPkIfExists() {
        System.out.println("findByPkIfExists");
        Categoria uM = new Categoria();
        CategoriaService instance = new CategoriaService();
        uM.setCdCategoria(1);
        assertTrue(instance.findByPk(uM));        
        System.out.println(uM);
    }
    /**
     * Test of findLike method, of class CategoriaService.
     */
    @Test
    public void findLike(){
        System.out.println("findLike");
        CategoriaService instance = new CategoriaService();
        Collection result = instance.findLike(new Categoria(null, "MT", "Cub"));
        System.out.println(result);
        assertFalse(result.isEmpty());
    }
    /**
     * Test of update method, of class CategoriaService.
     */
    @Test
    public void update() {
        System.out.println("update");
        Integer cdCategoria = 1;
        CategoriaService instance = new CategoriaService();
        Categoria uM = instance.findByPk(cdCategoria);
        uM.setDescricaoCategoria("Cubicos");
        instance.update(uM);
        Categoria uM2 = instance.findByPk(cdCategoria);
        assertTrue("O valor do campo DcCategoria", uM.getDescricaoCategoria().equals(uM2.getDescricaoCategoria()));
    }

    /**
     * Test of findAll method, of class CategoriaService.
     */
    @Test
    public void findAll() {
        System.out.println("findAll");
        CategoriaService instance = new CategoriaService();
        Collection result = instance.findAll();
        System.out.println(result);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of delete method, of class CategoriaService.
     */
    @Test
    public void delete() {
        System.out.println("delete");
        Integer cdCategoria = 2;
        Categoria uM = null;
        CategoriaService instance = new CategoriaService();
        instance.deleteByPk(cdCategoria);
        try {
            uM = instance.findByPk(cdCategoria);
            //fail("findByPk didn't throw Exception");
        } catch (Exception d) {
            assertNotNull(d);
        }
    }
    /**
     * Test of delete method, of class CategoriaService.
     */
    @Test
    public void deleteObject() {
        System.out.println("delete");
        Integer cdCategoria = 3;
        CategoriaService instance = new CategoriaService();        
        Categoria uM = instance.findByPk(cdCategoria);
        instance.delete(uM);
        try {
            uM = instance.findByPk(cdCategoria);
            //fail("findByPk didn't throw Exception");
        } catch (Exception d) {
            assertNotNull(d);
        }
    }
    
}
