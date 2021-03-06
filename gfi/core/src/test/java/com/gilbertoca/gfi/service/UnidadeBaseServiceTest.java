package com.gilbertoca.gfi.service;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.gilbertoca.gfi.Constants;
import com.gilbertoca.gfi.inventario.model.UnidadeMedida;

public class UnidadeBaseServiceTest {
    private BaseService<UnidadeMedida, String> uS = new BaseService<UnidadeMedida, String>(
	    UnidadeMedida.class, Constants.ORBROKER_INVENTARIO);

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
    assertTrue(uM.isFlNovo());
    }

    @Test
    public void testInsertUnidadeMedida() throws InsertException {
	int size = uS.getAll().size();
	UnidadeMedida entity = new UnidadeMedida("MT3", "Metros");
    entity.setFlNovo(true);
	uS.insert(entity);
	Collection<UnidadeMedida> result = uS.getAll();
	System.out.println(result);
	assertTrue(result.size() > size);
    }

    @Test
    public void testFindLikeUnidadeMedida() {
	Collection<UnidadeMedida> result = uS.findLike("cd_unidade_medida",
		"%MT%");
	System.out.println(result);
	assertTrue(result.size() == 2);
    }
}
