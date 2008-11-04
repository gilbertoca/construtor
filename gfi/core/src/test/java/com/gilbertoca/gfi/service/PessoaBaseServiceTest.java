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
	assertTrue(cS.find(c));
	System.out.println(c);
    }

    @Test
    public void testFindLikePessoa() {
	Collection<Pessoa> result = cS.findLike("apelido", "%apelido%");
	System.out.println(result);
	assertTrue(result.size() > 2);
    }

    @Test
    public void testInsertPessoa() throws Exception {
	int size = cS.getAll().size();
	System.out.println("@@@@@@@@@@  Quantidade antes da inserção:"+size);	

	Integer cdPessoa = 4;
	Pessoa c = new Pessoa();
	c.setCdPessoa(cdPessoa);
	assertFalse(cS.find(c));
	assertNull(c.getNome());

	Date dtNascimento = new Date("03/02/1974");
	Pessoa entity = new Pessoa(4, "M", "Gilberto", "Mom","Gil", dtNascimento);
	cS.insert(entity);
	c = null;
	c = new Pessoa();
	c.setCdPessoa(cdPessoa);
	assertTrue(cS.find(c));
	assertTrue(c.getApelido().equalsIgnoreCase("Gil"));
    }
    @Test
    public void testUpdatePessoa() {
	Integer cdPessoa = 1;
	String apelido = "Beto";
	Pessoa c = new Pessoa();
	c.setCdPessoa(cdPessoa);
	assertTrue(cS.find(c));
	System.out.println(c);
	c.setApelido(apelido);
	c.setEmail("emailll");
	c.setNomeMae("Francisca");
	c.setNomePai("Americo");
	cS.update(c);
	c = null;
	c = new Pessoa();
	c.setCdPessoa(cdPessoa);
	assertTrue(cS.find(c));
	assertTrue(c.getNomePai().equalsIgnoreCase("Americo"));
    }
    @Test
    public void testDeletePessoa() {
	Integer cdPessoa = 2;
	Pessoa c = new Pessoa();
	c.setCdPessoa(cdPessoa);
	cS.delete(c);
	System.out.println(c);
	assertFalse(cS.find(c));
    }
    

}
