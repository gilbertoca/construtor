package com.gilbertoca.gfi.service;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.gilbertoca.gfi.inventario2.model.UnidadeMedida;

public class BaseServiceTest {

	@Test
	public void testGetAll() {
		BaseService<UnidadeMedida, String> uS = new BaseService<UnidadeMedida, String>(UnidadeMedida.class);
        Collection<UnidadeMedida> result = uS.getAll();
        System.out.println(result);
        assertFalse(result.isEmpty());

	}


}
