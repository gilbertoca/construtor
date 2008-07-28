package com.gilbertoca.gfi.service;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.gilbertoca.gfi.inventario.model.Categoria;

public class CategoriaBaseServiceTest {
    private BaseService<Categoria, Integer> cS = new BaseService<Categoria, Integer>(
	    Categoria.class);

    @Test
    public void testGetAll() {
	Collection<Categoria> result = cS.getAll();
	System.out.println(result);
	assertFalse(result.isEmpty());
    }

    @Test
    public void testFind() {
	Integer cdCategoria = 1;
	Categoria c = new Categoria(cdCategoria);
	cS.find(c);
	System.out.println(c);
	assertTrue("O valor do campo cdCategoria", cdCategoria.equals(c
		.getCdCategoria()));
    }

    @Test
    public void testFindLikeCategoria() {
	Collection<Categoria> result = cS.findLike("nome_categoria", "%pa%");
	System.out.println(result);
	assertTrue(result.size() == 1);
    }

    @Test
    public void testInsertCategoria() {
	int size = cS.getAll().size();
	Categoria entity = new Categoria(4, "Vestuário", "Vestuário");
	System.out.println(entity);
	cS.insert(entity);
	Collection<Categoria> result = cS.getAll();
	System.out.println(result);
	assertTrue(result.size() > size);
    }

}
