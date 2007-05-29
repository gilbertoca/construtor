package com.gilbertoca.dao;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import junit.framework.Test;
import junit.framework.TestSuite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
/**
 * Unit test for simple App.
 */
public class MovimentoCaixaJdbcTest extends BaseDaoTestCase{
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
            ResultSet rs = null;
            Connection conn = null;
            PreparedStatement pstmt = null;            
	    try{	
                conn = getJdbcTemplate().getDataSource().getConnection();
		
                String SQL = "SELECT cd_movimento_caixa," +
                                              "fl_processado, dt_hr_movimento," +
                                              "dt_hr_abertura,	fl_fechado, entradas," +
                                              "	saidas,	saldo_anterior,	saldo," +
                                              "	cd_funcionario,	cd_caixa FROM " +
                                              "	financeiro.movimento_caixa" +
                                              "WHERE dt_hr_movimento BETWEEN ? AND ?";                
                pstmt = conn.prepareStatement(SQL);
                pstmt.setDate(1, new java.sql.Date(DTMOVIMENTOCAIXAINI.getTime()));
                pstmt.setDate(2, new java.sql.Date(DTMOVIMENTOCAIXAFIM.getTime()));
                rs = pstmt.executeQuery();
                 // extract data from the ResultSet
                while (rs.next()) {
                    int cdMovimentoCaixa = rs.getInt(1);
                    boolean flProcessado = rs.getBoolean(2);
                    Date dtMovimento = rs.getDate(3);
                    Date dtAbertura = rs.getDate(4);
                    System.out.println(cdMovimentoCaixa + "\t" + flProcessado + "\t" + dtMovimento + "\t" + dtAbertura);
                  }
                } catch (Exception e) {
                  e.printStackTrace();
                } finally {
                  try {
                    rs.close();
                    pstmt.close();
                    conn.close();
                  } catch (SQLException e) {
                    e.printStackTrace();
                  }
                }
	}
	/**
	 * 
	 */
	public void testGetMovimentoCaixa() {
            
	}	
}
