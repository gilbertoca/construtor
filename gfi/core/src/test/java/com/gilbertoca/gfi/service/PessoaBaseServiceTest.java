package com.gilbertoca.gfi.service;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;

import com.gilbertoca.gfi.geral.model.Pessoa;

public class PessoaBaseServiceTest {
    private BaseService<Pessoa, Integer> cS = new BaseService<Pessoa, Integer>(
	    Pessoa.class);

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
	Collection<Pessoa> result = cS.findLike("nome", "%ilb%");
	System.out.println(result);
	assertTrue(result.size() == 1);
    }

    @Test
    public void testInsertPessoa() {
	int size = cS.getAll().size();
	Pessoa entity = new Pessoa(4, "M", "Gilberto", "Mom",
		    "Beto", new Date("22/03/1974")); 
	System.out.println(entity);
	cS.insert(entity);
	Collection<Pessoa> result = cS.getAll();
	System.out.println(result);
	assertTrue(result.size() > size);
    }

}
