package com.gilbertoca.gfi.service;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.gilbertoca.gfi.inventario2.model.Categoria;
import com.gilbertoca.gfi.inventario2.model.UnidadeMedida;

public class BaseServiceTest {
	private BaseService<UnidadeMedida, String> uS = new BaseService<UnidadeMedida, String>(UnidadeMedida.class);
	private BaseService<Categoria, Integer> cS = new BaseService<Categoria, Integer>(Categoria.class);

	@Test
	public void testGetAll() {
		Collection<UnidadeMedida> result = uS.getAll();
		System.out.println(result);
		assertFalse(result.isEmpty());
	}

	@Test
	public void testFind() {
		String cdUnidadeMedida = "MT";
		UnidadeMedida uM = new UnidadeMedida(cdUnidadeMedida);
		uS.find(uM);
		System.out.println(uM);
		assertTrue("O valor do campo cdUnidadeMedida", cdUnidadeMedida
				.equals(uM.getCdUnidadeMedida()));
	}

	@Test
	public void testInsertUnidadeMedida() {
    	int size = cS.getAll().size();		
		UnidadeMedida entity = new UnidadeMedida("MT3", "Metros");
		uS.insert(entity);
		Collection<UnidadeMedida> result = uS.getAll();
		System.out.println(result);
        assertTrue(result.size() > size);
	}
    @Test
    public void testInsertCategoria() {
    	int size = cS.getAll().size();
        Categoria entity = new Categoria(4, "MT3", "Metros");
        System.out.println(entity);
        cS.insert(entity);
        Collection<Categoria> result = cS.getAll();
        System.out.println(result);
        assertTrue(result.size() > size);
    }
	

}
