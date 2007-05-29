package com.gilbertoca.model.financeiro;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class MovimentoCaixaLancamentoPK implements Serializable {

    /** identifier field */
    private Integer cdMovimentoCaixaLancamento;

    /** identifier field */
    private Integer cdMovimentoCaixa;

    /** full constructor */
    public MovimentoCaixaLancamentoPK(Integer cdMovimentoCaixaLancamento, Integer cdMovimentoCaixa) {
        this.cdMovimentoCaixaLancamento = cdMovimentoCaixaLancamento;
        this.cdMovimentoCaixa = cdMovimentoCaixa;
    }

    /** default constructor */
    public MovimentoCaixaLancamentoPK() {
    }

    /** full constructor */
    public MovimentoCaixaLancamentoPK(String cdMovimentoCaixaLancamento, String cdMovimentoCaixa) {
        this.cdMovimentoCaixaLancamento = new Integer(cdMovimentoCaixaLancamento);
        this.cdMovimentoCaixa = new Integer(cdMovimentoCaixa);
    }
    
    /** 
     *                @hibernate.property
     *                 column="cd_movimento_caixa_lancamento"
     *             
     */
    public Integer getCdMovimentoCaixaLancamento() {
        return this.cdMovimentoCaixaLancamento;
    }

    public void setCdMovimentoCaixaLancamento(Integer cdMovimentoCaixaLancamento) {
        this.cdMovimentoCaixaLancamento = cdMovimentoCaixaLancamento;
    }

    /** 
     *                @hibernate.property
     *                 column="cd_movimento_caixa"
     *             
     */
    public Integer getCdMovimentoCaixa() {
        return this.cdMovimentoCaixa;
    }

    public void setCdMovimentoCaixa(Integer cdMovimentoCaixa) {
        this.cdMovimentoCaixa = cdMovimentoCaixa;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("cdMovimentoCaixaLancamento", getCdMovimentoCaixaLancamento())
            .append("cdMovimentoCaixa", getCdMovimentoCaixa())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof MovimentoCaixaLancamentoPK) ) return false;
        MovimentoCaixaLancamentoPK castOther = (MovimentoCaixaLancamentoPK) other;
        return new EqualsBuilder()
            .append(this.getCdMovimentoCaixaLancamento(), castOther.getCdMovimentoCaixaLancamento())
            .append(this.getCdMovimentoCaixa(), castOther.getCdMovimentoCaixa())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCdMovimentoCaixaLancamento())
            .append(getCdMovimentoCaixa())
            .toHashCode();
    }

}
