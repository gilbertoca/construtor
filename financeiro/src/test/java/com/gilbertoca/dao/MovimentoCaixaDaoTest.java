package com.gilbertoca.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import com.gilbertoca.model.financeiro.MovimentoCaixa;

public class MovimentoCaixaDaoTest extends BaseDaoTestCase{
	private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static Date DTMOVIMENTOCAIXAINI = null;
	private static Date DTMOVIMENTOCAIXAFIM = null;
	private static final Integer MOVIMENTOCAIXAID = new Integer("1");
	private MovimentoCaixa movimentoCaixa = null;
	private IMovimentoCaixaDao dao = null;

	static{
		try {
			DTMOVIMENTOCAIXAINI = formatter.parse("2007-03-01 00:00:0");
			DTMOVIMENTOCAIXAFIM = formatter.parse("2007-03-02 00:00:0");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void setDao(IMovimentoCaixaDao dao) {
		this.dao = dao;
	}

	public void testFindMovimentosCaixa() {
		/*
		 * Existem dois Movimentos de Caixa no momento do teste no intervalo de data abaixo:
		 * dtMovimentoCaixaIni=
		 * dtMovimentoCaixaFim=
		 */
		Collection movimentoCaixas = dao.findMovimentoCaixa(DTMOVIMENTOCAIXAINI, DTMOVIMENTOCAIXAFIM);
		assertEquals(2, movimentoCaixas.size());
		movimentoCaixas = dao.findMovimentoCaixa(DTMOVIMENTOCAIXAINI, null);
		assertEquals(0, movimentoCaixas.size());
	}

	    public void testGetMovimentoCaixa() throws Exception {
	        movimentoCaixa = dao.getMovimentoCaixa(MOVIMENTOCAIXAID);
	        log.debug(movimentoCaixa);
	        assertNotNull(movimentoCaixa);
	    }

	    public void testGetMovimentoCaixas() throws Exception {
	        Collection results = dao.getMovimentoCaixas();
	        assertTrue(results.size() > 0);
	    }

	  public void testAddMovimentoCaixa() throws Exception {
			MovimentoCaixa mc = new MovimentoCaixa();
	        // set required fields
			mc.setCdMovimentoCaixa(new Integer("3"));

			dao.insertMovimentoCaixa(mc);
	        Collection results = dao.getMovimentoCaixas();
	        log.debug("Quantos existems: "+results.size());
	        assertTrue(results.size() > 2);
	        // (optional) verify set fields are same after save
			
			try {
					//Unique index or primary key violation (the register one already exists)
			        dao.insertMovimentoCaixa(mc);

		            fail("insertMovimentoCaixa didn't throw DataIntegrityViolationException");
		        } catch (Exception e) {
		            assertNotNull(e);
		            log.debug("expected exception: " + e.getMessage());
		        }
			
	    }

	    public void testUpdateMovimentoCaixa() throws Exception {
	        movimentoCaixa = dao.getMovimentoCaixa(MOVIMENTOCAIXAID);
	        boolean processado = movimentoCaixa.getFlProcessado();
	        log.debug("valor atual:"+processado);
	        // update required string fields
	        movimentoCaixa.setFlProcessado(!processado);
	        dao.updateMovimentoCaixa(movimentoCaixa);
	        MovimentoCaixa movimentoCaixaUpdated = dao.getMovimentoCaixa(MOVIMENTOCAIXAID);
	        log.debug("valor alterado:"+movimentoCaixaUpdated.getFlProcessado());
	        assertEquals(movimentoCaixaUpdated.getFlProcessado(), !processado);
	    }

	    public void testDeleteMovimentoCaixa() throws Exception {
	    	Integer removeId = new Integer(2);
	        dao.deleteMovimentoCaixa(removeId);
	        try {
	        	dao.getMovimentoCaixa(removeId);
	        	fail("movimentoCaixa found in database");
	        } catch (Exception e) {
	        	assertNotNull(e.getMessage());
	        }
	    }
}
