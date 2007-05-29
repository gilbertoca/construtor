package com.gilbertoca.model.financeiro;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.gilbertoca.model.BaseObject;

/** 
*/
public class MovimentoCaixa extends BaseObject implements Serializable {
    private Integer cdMovimentoCaixa = new Integer("-1");
    private Date dtHrMovimento = new Date();
    private Date dtHrAbertura;
    private Double entradas = new Double(0);
    private Double saidas = new Double(0);
    private Double saldoAnterior = new Double(0);
    private Double saldo = new Double(0);
    private Integer cdFuncionario;
    private Integer cdCaixa;
    private boolean flFechado = true;
    private boolean flProcessado = false;    
    private List movimentoCaixaLancamentos = Collections.EMPTY_LIST;

  
    /**
	 * @param cdMovimentoCaixa
	 * @param dtHrMovimento
	 * @param dtHrAbertura
	 * @param entradas
	 * @param saidas
	 * @param saldoAnterior
	 * @param saldo
	 * @param cdFuncionario
	 * @param cdCaixa
	 * @param flFechado
	 * @param flProcessado
	 */
	public MovimentoCaixa(Integer cdMovimentoCaixa, Date dtHrMovimento, Date dtHrAbertura, Double entradas, Double saidas, Double saldoAnterior, Double saldo, Integer cdFuncionario, Integer cdCaixa, boolean flFechado, boolean flProcessado) {
		this.cdMovimentoCaixa = cdMovimentoCaixa;
		this.dtHrMovimento = dtHrMovimento;
		this.dtHrAbertura = dtHrAbertura;
		this.entradas = entradas;
		this.saidas = saidas;
		this.saldoAnterior = saldoAnterior;
		this.saldo = saldo;
		this.cdFuncionario = cdFuncionario;
		this.cdCaixa = cdCaixa;
		this.flFechado = flFechado;
		this.flProcessado = flProcessado;
	}

	public MovimentoCaixa(Integer cdMovimentoCaixa, Date dtHrMovimento, Double entradas, Double saidas, Double saldoAnterior, Double saldo, Integer cdFuncionario, Integer cdCaixa) {
        this.cdMovimentoCaixa = cdMovimentoCaixa;
        this.dtHrMovimento = dtHrMovimento;
        this.entradas = entradas;
        this.saidas = saidas;
        this.saldoAnterior = saldoAnterior;
        this.saldo = saldo;
        this.cdFuncionario = cdFuncionario;
        this.cdCaixa = cdCaixa;
    }

    /** default constructor */
    public MovimentoCaixa() {
    }

    /** minimal constructor */
    public MovimentoCaixa(Integer cdMovimentoCaixa) {
        this.cdMovimentoCaixa = cdMovimentoCaixa;
    }

    /**
     * @return Integer
     */
    public Integer getCdMovimentoCaixa() {
        return this.cdMovimentoCaixa;
    }

    public void setCdMovimentoCaixa(Integer cdMovimentoCaixa) {
        this.cdMovimentoCaixa = cdMovimentoCaixa;
    }

    /**
     * Usado para determinar se um caixa já foi processado. Um movimento de caxia,
     * so poderão ser processado se estiver aberto, ou seja, o atributo flFechado
     * for igual a false.
	 * @return boolean
     */
     public boolean getFlProcessado() {
         return flProcessado;
     }

    public void setFlProcessado(boolean flProcessado) {
        this.flProcessado  = flProcessado;
    }
    
    public List getMovimentoCaixaLancamentos() {
        return movimentoCaixaLancamentos;
    }

    public void setMovimentoCaixaLancamentos(List movimentoCaixaLancamentos) {
        this.movimentoCaixaLancamentos = movimentoCaixaLancamentos;
    }
    
    public Date getDtHrMovimento() {
        return this.dtHrMovimento;
    }

    public void setDtHrMovimento(Date dtHrMovimento) {
        this.dtHrMovimento = dtHrMovimento;
    }

    public Date getDtHrAbertura() {
        return this.dtHrAbertura;
    }

    public void setDtHrAbertura(Date dtHrAbertura) {
        this.dtHrAbertura = dtHrAbertura;
    }
    
    /**
     * Se o Movimento de Caixa está fechado(true) ou aberto(false).
     * @return boolean 
     */
    public boolean getFlFechado() {
        return this.flFechado;
    }

    public void setFlFechado(boolean flFechado) {
        this.flFechado = flFechado;
    }
    
    public Double getEntradas() {
        return this.entradas;
    }

    public void setEntradas(Double entradas) {
        this.entradas = entradas;
    }

    public Double getSaidas() {
        return this.saidas;
    }

    public void setSaidas(Double saidas) {
        this.saidas = saidas;
    }

    public Double getSaldoAnterior() {
        return this.saldoAnterior;
    }

    public void setSaldoAnterior(Double saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public Double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Integer getCdFuncionario() {
        return this.cdFuncionario;
    }

    public void setCdFuncionario(Integer cdFuncionario) {
        this.cdFuncionario = cdFuncionario;
    }

    public Integer getCdCaixa() {
        return this.cdCaixa;
    }

    public void setCdCaixa(Integer cdCaixa) {
        this.cdCaixa = cdCaixa;
    }
    
    /**
     * Totaliza o movimento de Caixa. Realiza um loop sobre todos os itens
     * do movimento e, para cada item chama movimentoCaixaLancamento.getValor() 
     * para debito e para crédito.
     * Formula: saldo = tempCreditoTotal - tempDebitoTotal
     * @return Double 
     */    
    public Double getSaldoInternal(){
        double tempDebitoTotal = 0.0;
        double tempCreditoTotal = 0.0;
        Iterator i = getMovimentoCaixaLancamentos().iterator();
        while (i.hasNext()) {
            MovimentoCaixaLancamento movimentoCaixaLancamento = (MovimentoCaixaLancamento) i.next();
            //Verificamos se o item não foi cancelado
            if(movimentoCaixaLancamento.getFlCancelado().booleanValue()==false){
                //Totalizando Credito
                if(movimentoCaixaLancamento.getOperacao().equalsIgnoreCase("C")){
                    tempCreditoTotal = tempCreditoTotal + movimentoCaixaLancamento.getValor().doubleValue();
                }    
                else{//Totalizando Debito
                    tempDebitoTotal = tempDebitoTotal + movimentoCaixaLancamento.getValor().doubleValue();
                }
                if (log.isDebugEnabled()) {
                    log.debug("into 'getSaldoInternal' method... operacao.: "
                            +movimentoCaixaLancamento.getOperacao()+
                            "\nValor debito: "+tempDebitoTotal+
                            "\nValor credito: "+tempCreditoTotal);
                }
            }
        }
        setSaidas(new Double(tempDebitoTotal));
        setEntradas(new Double(tempCreditoTotal));
        
       return new Double(tempCreditoTotal - tempDebitoTotal); 
    }
   /**
    * Totaliza as saídas do movimento de Caixa. Realiza um loop sobre todos os itens
    * do movimento e, para cada item chama movimentoCaixaLancamento.getValor() 
    * para debito.
    * @return Double 
    */
    public Double getSaidasInternal(){
        double tempDebitoTotal = 0.0;
        Iterator i = getMovimentoCaixaLancamentos().iterator();
        while (i.hasNext()) {
            MovimentoCaixaLancamento movimentoCaixaLancamento = (MovimentoCaixaLancamento) i.next();
            //Verificamos se o item não foi cancelado
            if(movimentoCaixaLancamento.getFlCancelado().booleanValue()==false){
	            if(movimentoCaixaLancamento.getOperacao().equalsIgnoreCase("D")){
	                tempDebitoTotal = tempDebitoTotal + movimentoCaixaLancamento.getValor().doubleValue();
	            }
            }
        }
       return new Double(tempDebitoTotal); 
    }
    /**
     * Totaliza as entradas do movimento de Caixa. Realiza um loop sobre todos os itens
     * do movimento e, para cada item chama movimentoCaixaLancamento.getValor() 
     * para crédito.
     * @return Double 
     */
     public Double getEntradasInternal(){
         double tempCreditoTotal = 0.0;
         Iterator i = getMovimentoCaixaLancamentos().iterator();
         while (i.hasNext()) {
             MovimentoCaixaLancamento movimentoCaixaLancamento = (MovimentoCaixaLancamento) i.next();
             //Verificamos se o item não foi cancelado
             if(movimentoCaixaLancamento.getFlCancelado().booleanValue()==false){
                 if(movimentoCaixaLancamento.getOperacao().equalsIgnoreCase("C")){
                     tempCreditoTotal = tempCreditoTotal + movimentoCaixaLancamento.getValor().doubleValue();
                 }    
             }
         }
        return new Double(tempCreditoTotal);
     }

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(final Object other) {
		if (this == other)
			return true;
		if (!(other instanceof MovimentoCaixa))
			return false;
		MovimentoCaixa castOther = (MovimentoCaixa) other;
		return new EqualsBuilder().append(cdMovimentoCaixa,
				castOther.cdMovimentoCaixa).append(dtHrMovimento,
				castOther.dtHrMovimento).isEquals();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(cdMovimentoCaixa).append(
				dtHrMovimento).toHashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
	       ToStringBuilder sb = new ToStringBuilder(this,
	                ToStringStyle.MULTI_LINE_STYLE)
	       								.append("cdMovimentoCaixa", cdMovimentoCaixa)
	                					.append("dtHrMovimento", dtHrMovimento)
	                					.append("dtHrAbertura",dtHrAbertura)
	                					.append("entradas", entradas)
	                					.append("saidas", saidas)
	                					.append("saldoAnterior", saldoAnterior)
	                					.append("saldo", saldo)
	                					.append("cdFuncionario", cdFuncionario)
	                					.append("cdCaixa", cdCaixa)
	                					.append("flFechado", flFechado)
	                					.append("flProcessado", flProcessado);
	        if (!getMovimentoCaixaLancamentos().isEmpty()) {
	             sb.append("Lançamentos: ");
	             sb.append(", ");
		         Iterator i = getMovimentoCaixaLancamentos().iterator();
		         while (i.hasNext()) {
		             MovimentoCaixaLancamento movimentoCaixaLancamento = (MovimentoCaixaLancamento) i.next();
		             sb.append(movimentoCaixaLancamento.toString());
		         }    
	        } else {
	            sb.append("Nenhum Lançamento.");
	        }
	        return sb.toString();
	}
     
}