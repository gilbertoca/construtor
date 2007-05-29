package com.gilbertoca.model.financeiro;

import junit.framework.TestCase;

public class MovimentoCaixaTest extends TestCase {

	public void testMovimentoCaixaConstrutor() {
		MovimentoCaixa mc = new MovimentoCaixa();
		System.out.println(mc);
		assertEquals("Novo objeto - cdMovimentoCaixa -1 ", mc.getCdMovimentoCaixa().intValue(), -1);
	}
	public void testMovimentoCaixaCriacao() {
		MovimentoCaixa mc = new MovimentoCaixa();
		mc.setCdMovimentoCaixa(new Integer("1"));
		assertEquals("Cod. Identificação do Movimento", mc.getCdMovimentoCaixa(), new Integer("1"));
	}

}
