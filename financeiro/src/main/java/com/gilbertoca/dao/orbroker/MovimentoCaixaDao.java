package com.gilbertoca.dao.orbroker;

import java.util.Collection;
import java.util.Date;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springmodules.orm.orbroker.support.BrokerDaoSupport;

import com.gilbertoca.dao.IMovimentoCaixaDao;
import com.gilbertoca.model.financeiro.MovimentoCaixa;

public class MovimentoCaixaDao extends BrokerDaoSupport implements IMovimentoCaixaDao {
	public Collection findMovimentoCaixa(Date dtMovimentoCaixaIni, Date dtMovimentoCaixaFim) {
		/* Usando Orbroker API 
		Query qry = getBrokerTemplate().getBroker().startQuery();
		qry.setParameter("dtMovimentoCaixaIni", dtMovimentoCaixaIni);
		qry.setParameter("dtMovimentoCaixaFim", dtMovimentoCaixaFim);
		List movimentos;
		try {
			movimentos = qry.selectMany("getMovimentoCaixasByDtMovimentoCaixaRange");
		} finally {
			qry.close();
		}
		return movimentos; 
		*/
		/* Usando SpringModules */
		return getBrokerTemplate().selectMany("getMovimentoCaixasByDtMovimentoCaixaRange", 
	    		new String[] {"dtMovimentoCaixaIni", "dtMovimentoCaixaFim"},
	    		new Object[] { dtMovimentoCaixaIni, dtMovimentoCaixaFim });
	}

	public MovimentoCaixa getMovimentoCaixa(Integer cdMovimentoCaixa) {
		MovimentoCaixa mc = (MovimentoCaixa) getBrokerTemplate().selectOne("getMovimentoCaixaByCdMovimentoCaixa", "cdMovimentoCaixa", cdMovimentoCaixa);
	    if (mc == null) {
	      throw new ObjectRetrievalFailureException(MovimentoCaixa.class, cdMovimentoCaixa);
	    }
		return mc;
	}

	public MovimentoCaixa getMovimentoCaixaAberto() {
		throw new UnsupportedOperationException("Method not implemented");
	}

	public Collection getMovimentoCaixas() {
		/* Usando Orbroker API 
		Query qry = getBrokerTemplate().getBroker().startQuery();
		qry.setParameter("dtMovimentoCaixaIni", dtMovimentoCaixaIni);
		qry.setParameter("dtMovimentoCaixaFim", dtMovimentoCaixaFim);
		List movimentos;
		try {
			movimentos = qry.selectMany("getMovimentosCaixaByDtMovimentoCaixaRange");
		} finally {
			qry.close();
		}
		return movimentos;
		*/ 
		return getBrokerTemplate().selectMany("getMovimentoCaixas");
	}

	public boolean isThereAnyMovimentoCaixaAberto() {
		// TODO Auto-generated method stub
		return false;
	}

	public void deleteMovimentoCaixa(Integer cdMovimentoCaixa) {
		getBrokerTemplate().execute("deleteMovimentoCaixaByCdMovimentoCaixa", "cdMovimentoCaixa", cdMovimentoCaixa);		
	}

	public void deleteMovimentoCaixa(MovimentoCaixa movimentoCaixa) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	public void insertMovimentoCaixa(MovimentoCaixa movimentoCaixa) {
		getBrokerTemplate().execute("insertMovimentoCaixa", "movimentoCaixa", movimentoCaixa);
	}

	public void updateMovimentoCaixa(MovimentoCaixa movimentoCaixa) {
		getBrokerTemplate().execute("updateMovimentoCaixa", "movimentoCaixa", movimentoCaixa);	
	}    
}