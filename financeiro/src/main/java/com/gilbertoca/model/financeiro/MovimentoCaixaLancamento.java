package com.gilbertoca.model.financeiro;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class MovimentoCaixaLancamento implements Serializable {
    private MovimentoCaixaLancamentoPK comp_id = new MovimentoCaixaLancamentoPK();
    private MovimentoCaixa movimentoCaixa = new MovimentoCaixa();    
    private Date dtHrLancamento = new Date();
    private String documento;
    private Double valor;
    private String historico;
    private Boolean flCancelado = new Boolean(false);
    private String operacao = "C";//Podendo ser C-crédito ou D-débito
    private Integer cdCaixaConta = new Integer(4);
    private CaixaConta caixaConta = new CaixaConta();
    private Integer cdCentroCusto;
    private CentroCusto centroCusto = new CentroCusto();    
    private int version = -1;

    /** full constructor */
    public MovimentoCaixaLancamento(MovimentoCaixaLancamentoPK comp_id, Date dtHrLancamento, String documento, Double valor, String historico, Boolean flCancelado, String operacao, CaixaConta caixaConta) {
        this.comp_id = comp_id;
        this.dtHrLancamento = dtHrLancamento;
        this.documento = documento;
        this.valor = valor;
        this.historico = historico;
        this.flCancelado = flCancelado;
        this.operacao = operacao;
        this.caixaConta = caixaConta;
    }

    /** default constructor */
    public MovimentoCaixaLancamento() {
    }

    /** minimal constructor */
    public MovimentoCaixaLancamento(MovimentoCaixaLancamentoPK comp_id, CaixaConta caixaConta) {
        this.comp_id = comp_id;
        this.caixaConta = caixaConta;
    }

    public MovimentoCaixaLancamentoPK getComp_id() {
        return this.comp_id;
    }

    /**
     * @return Returns the cdCaixaConta.
     */
    public Integer getCdCaixaConta() {
        return cdCaixaConta;
    }
    /**
     * @param cdCaixaConta The cdCaixaConta to set.
     */
    public void setCdCaixaConta(Integer cdCaixaConta) {
        this.cdCaixaConta = cdCaixaConta;
    }
    /**
     * @return Returns the updated timestamp.
     */
    public int getVersion() {
        return version;
    }

    /**
     * @param updated The updated version to set.
     */
    public void setVersion(int version) {
        this.version = version;
    }
    
    /**
     * @return a populated movimentoCaixa object (based on the cdMovimentoCaixa)
     */
    public MovimentoCaixa getMovimentoCaixa() {
        return this.movimentoCaixa;
    }

    public void setMovimentoCaixa(MovimentoCaixa movimentoCaixa) {
        this.movimentoCaixa = movimentoCaixa;
    }


    public void addMovimentoCaixa(MovimentoCaixa movimentoCaixa) {
           movimentoCaixa.getMovimentoCaixaLancamentos().add(this);
           this.movimentoCaixa = movimentoCaixa;
       }     
    
    public void setComp_id(MovimentoCaixaLancamentoPK comp_id) {
        this.comp_id = comp_id;
    }

    /** 
     */
    public Date getDtHrLancamento() {
        return this.dtHrLancamento;
    }
    
    /**
     */
    public void setDtHrLancamento(Date dtHrLancamento) {
        this.dtHrLancamento = dtHrLancamento;
    }

    /** 
     */
    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /** 
     */
    public Double getValor() {
        return this.valor;
    }
    /**
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /** 
     */
    public String getHistorico() {
        return this.historico;
    }
    /**
     */
    public void setHistorico(String historico) {
        this.historico = historico;
    }

    /** 
     */
    public Boolean getFlCancelado() {
        return this.flCancelado;
    }

    public void setFlCancelado(Boolean flCancelado) {
        this.flCancelado = flCancelado;
    }
    /** 
     *         
     */
    public Integer getCdCentroCusto() {
        return this.cdCentroCusto;
    }

    public void setCdCentroCusto(Integer cdCentroCusto) {
        this.cdCentroCusto = cdCentroCusto;
    }
    /**
     * @return a populated CentroCusto object (based on the cdCentroCusto)
     */
    public CentroCusto getCentroCusto() {
        return this.centroCusto;
    }

    public void setCentroCusto(CentroCusto centroCusto) {
        this.centroCusto = centroCusto;
    }

    /** 
     * @return String Retorna C para crédito ou D para débito        
     */
    public String getOperacao() {
        return this.operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    /**
     * @return a populated CaixaConta object (based on the cdCaixaConta)
     */
    public CaixaConta getCaixaConta() {
        return this.caixaConta;
    }

    public void setCaixaConta(CaixaConta caixaConta) {
        this.caixaConta = caixaConta;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof MovimentoCaixaLancamento) ) return false;
        MovimentoCaixaLancamento castOther = (MovimentoCaixaLancamento) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
