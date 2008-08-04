package com.gilbertoca.gfi.service;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;

import com.gilbertoca.gfi.Constants;
import com.gilbertoca.gfi.geral.model.Pessoa;

public class PessoaBaseServiceTest {
    private BaseService<Pessoa, Integer> cS = new BaseService<Pessoa, Integer>(
	    Pessoa.class, Constants.ORBROKER_GERAL);

    @Test
    public void testGetAll() {
	Collection<Pessoa> result = cS.getAll();
	System.out.println(result);
	assertFalse(result.isEmpty());
    }

    @Test
    public void testFind() {
	Integer cdPessoa = 1;
	Pessoa c = new Pessoa();
	c.setCdPessoa(cdPessoa);
	cS.find(c);
	System.out.println(c);
	assertTrue("O valor do campo cdPessoa", cdPessoa.equals(c
		.getCdPessoa()));
    }

    @Test
    public void testFindLikePessoa() {
	Collection<Pessoa> result = cS.findLike("apelido", "%apelido%");
	System.out.println(result);
	assertTrue(result.size() > 2);
    }

    @Test
    public void testInsertPessoa() {
	int size = cS.getAll().size();
	//JDBC time escape format: YYYY-MM-DD
	//java.sql.Date.valueOf("1974-03-22")
	Pessoa entity = new Pessoa(4, "M", "Gilberto", "Mom","Beto", java.sql.Date.valueOf("1974-03-22"));
	System.out.println(entity);
	cS.insert(entity);
	Collection<Pessoa> result = cS.getAll();
	System.out.println(result);
	assertTrue(result.size() > size);
    }
    @Test
    public void testUpdatePessoa() {
	Integer cdPessoa = 1;
	String apelido = "Beto";
	Pessoa c = new Pessoa();
	c.setCdPessoa(cdPessoa);
	cS.find(c);
	System.out.println(c);
	c.setApelido(apelido);
	cS.update(c);
	assertTrue("O valor do campo apelido", apelido.equals(c
		.getApelido()));
    }

}
