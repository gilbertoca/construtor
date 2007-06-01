package com.gilbertoca.dao;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.orbroker.*;
import junit.framework.Test;
import junit.framework.TestSuite;
import com.gilbertoca.model.financeiro.MovimentoCaixa;
 
/**
 * Unit test for simple App.
 */
public class MovimentoCaixaOrbrokerTest extends BaseDaoTestCase{
	private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static Date DTMOVIMENTOCAIXAINI = null;
	private static Date DTMOVIMENTOCAIXAFIM = null;
	private static final Integer MOVIMENTOCAIXAID = new Integer("1");
	static{
		try {
			DTMOVIMENTOCAIXAINI = formatter.parse("2007-03-01 00:00:0");
			DTMOVIMENTOCAIXAFIM = formatter.parse("2007-03-02 00:00:0");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(MovimentoCaixaOrbrokerTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testFindMovimentosCaixa() {
//		 Retrieve resource
		InputStream is = getClass().getResourceAsStream( "/financeiro-broker.xml" );
		Broker broker = new Broker(is, getJdbcTemplate().getDataSource());
		/* Usando Orbroker API*/ 
		Query qry = broker.startQuery();
		qry.setParameter("dtMovimentoCaixaIni", DTMOVIMENTOCAIXAINI);
		qry.setParameter("dtMovimentoCaixaFim", DTMOVIMENTOCAIXAFIM);
		List movimentos;
		try {
			movimentos = qry.selectMany("getMovimentoCaixasByDtMovimentoCaixaRange");
		}
                finally {
			qry.close();
		}
        assertFalse("Can't be empty", movimentos.isEmpty());
	}
	/**
	 * 
	 */
	public void testGetMovimentoCaixa() {
//		 Retrieve resource
		InputStream is = getClass().getResourceAsStream( "/financeiro-broker.xml" );
		Broker broker = new Broker(is, getJdbcTemplate().getDataSource());
		/* Usando Orbroker API*/ 
		Query qry = broker.startQuery();
		qry.setParameter("cdMovimentoCaixa", new Integer("1"));
		MovimentoCaixa  mc;
		try {
			mc = (MovimentoCaixa) qry.selectOne("getMovimentoCaixaByCdMovimentoCaixa");
		} finally {
			qry.close();
		}
		System.out.println(mc);		
	}	
}
