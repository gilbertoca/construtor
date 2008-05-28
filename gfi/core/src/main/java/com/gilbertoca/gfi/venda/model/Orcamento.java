package com.gilbertoca.gfi.venda.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Gilberto
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * 
 * @hibernate.class table = "construtor.venda_orcamento"
 */
public class Orcamento implements Serializable{
    private Integer cdCliente;
    private Cliente cliente;    
    private Integer cdFuncionario;
    private Integer cdOrcamento;
    private Integer cdPlanoPagamento;
    private Date dtOrcamento;
    private Date dtValidadeOrcamento;
    /**
     * Tipo pagamento na venda: Prazo ou Vista
     */
    private String tipoPagamento = "Vista";
    /**
     * Forma pagamento na venda:
     * D-Dinheiro; H-Cheque; P-Cheque-pr�-datado;
     * C-Cartao de Credito; U-Dollar; T-Ticket/Vale;
     * B-Boleto Bancario; L-Duplicata ou O-Convenio.
     */
    private String formaPagamento = "D";	
    private String status;
    private String NomeCliente;
    private String observacao;
    private String tipoCliente;//Sendo:F=fornecedor C=cliente
    private Double valorDesconto;
    private Double valorEntrada;
    private Double valorParcela;
    private Double valorTotal;
    private Double valorTotalPrazo;
    private Double valorTotalProduto;
    private Boolean flProcessado = new Boolean(false);
    private Set orcamentoItems = Collections.EMPTY_SET;


    /**
     * 
     */
    public Orcamento() {

    }

    /**
     * @hibernate.property column="cd_cliente"
     * @return Returns the cdCliente.
     */
    public Integer getCdCliente() {
        return cdCliente;
    }

    /**
     * @hibernate.property  column="fl_processado" length="1"
     */
     public Boolean getFlProcessado() {
         return flProcessado;
     }

    /**
     * @param b
     */
    public void setFlProcessado(Boolean flProcessado) {
        this.flProcessado  = flProcessado;
    }    
    /**
     * @hibernate.property column="cd_funcionario"
     * @return Returns the cdFuncionario.
     */
    public Integer getCdFuncionario() {
        return cdFuncionario;
    }
    /**
     * @hibernate.id generator-class="sequence" column="cd_orcamento" unsaved-value = "null"
     * @hibernate.generator-param name = "sequence" value = "construtor.venda_orcamento_sequence"
     * @return Integer
     */
    public Integer getCdOrcamento() {
        return cdOrcamento;
    }
    
    /**
     * @hibernate.many-to-one insert="false" update="false" 
     * cascade="none" column="cd_cliente" outer-join="true"
     * @return a populated cliente object (based on the cdCliente)
     */
    public Cliente getCliente() { 
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }    
	
    /**
     * @hibernate.property column="forma_pagamento" length = "1"
     * @return Returns the formaPagamento.
     */
    public String getFormaPagamento() {
        return formaPagamento;
    }
	
    public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	

	/**
     * @hibernate.property column="cd_plano_pagamento"
     * @return Returns the cdPlanoPagamento.
     */
    public Integer getCdPlanoPagamento() {
        return cdPlanoPagamento;
    }
    /**
     * @hibernate.property column="dt_orcamento"
     * @return Returns the dtOrcamento.
     */
    public Date getDtOrcamento() {
        return dtOrcamento;
    }
    /**
     * @hibernate.property column="dt_validade_orcamento"
     * @return Returns the dtValidadeOrcamento.
     */
    public Date getDtValidadeOrcamento() {
        return dtValidadeOrcamento;
    }
    /**
     * @hibernate.property column="tipo_pagamento" length = "10"
     * @return Returns the tipoPagamento.
     */
    public String getTipoPagamento() {
        return tipoPagamento;
    }
    /**
     * @hibernate.property column="status" length = "1"
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }
    /**
     * @hibernate.property column="nome_cliente" length = "60"
     * @return Returns the nomeCliente.
     */
    public String getNomeCliente() {
        return NomeCliente;
    }
    /**
     * @hibernate.property column="observacao" length = "255"
     * @return Returns the observacao.
     */
    public String getObservacao() {
        return observacao;
    }
    /**
     * @hibernate.property column="tipo_cliente" length = "1"
     * @return Returns the tipoCliente.
     */
    public String getTipoCliente() {
        return tipoCliente;
    }
    /**
     * @hibernate.property column="valor_desconto"
     * @return Returns the valorDesconto.
     */
    public Double getValorDesconto() {
        return valorDesconto;
    }
    /**
     * @hibernate.property column="valor_entrada"
     * @return Returns the valorEntrada.
     */
    public Double getValorEntrada() {
        return valorEntrada;
    }
    /**
     * @hibernate.property column="valor_parcela"
     * @return Returns the valorParcela.
     */
    public Double getValorParcela() {
        return valorParcela;
    }
    /**
     * @hibernate.property column="valor_total"
     * @return Returns the valorTotal.
     */
    public Double getValorTotal() {
        return valorTotal;
    }
    /**
     * @hibernate.property column="valor_total_prazo"
     * @return Returns the valorTotalPrazo.
     */
    public Double getValorTotalPrazo() {
        return valorTotalPrazo;
    }
    /**
     * @hibernate.property column="valor_total_produto"
     * @return Returns the valorTotalProduto.
     */
    public Double getValorTotalProduto() {
        return valorTotalProduto;
    }

    /**
     * @param cdCliente The cdCliente to set.
     */
    public void setCdCliente(Integer cdCliente) {
        this.cdCliente = cdCliente;
    }
    /**
     * @param cdFuncionario The cdFuncionario to set.
     */
    public void setCdFuncionario(Integer cdFuncionario) {
        this.cdFuncionario = cdFuncionario;
    }
    /**
     * @param cdOrcamento The cdOrcamento to set.
     */
    public void setCdOrcamento(Integer cdOrcamento) {
        this.cdOrcamento = cdOrcamento;
    }
    /**
     * @param cdPlanoPagamento The cdPlanoPagamento to set.
     */
    public void setCdPlanoPagamento(Integer cdPlanoPagamento) {
        this.cdPlanoPagamento = cdPlanoPagamento;
    }
    /**
     * @param dtOrcamento The dtOrcamento to set.
     */
    public void setDtOrcamento(Date dtOrcamento) {
        this.dtOrcamento = dtOrcamento;
    }
    /**
     * @param dtValidadeOrcamento The dtValidadeOrcamento to set.
     */
    public void setDtValidadeOrcamento(Date dtValidadeOrcamento) {
        this.dtValidadeOrcamento = dtValidadeOrcamento;
    }
    /**
     * @param tipoPagamento The tipoPagamento to set.
     */
    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
    /**
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @param nomeCliente The nomeCliente to set.
     */
    public void setNomeCliente(String nomeCliente) {
        NomeCliente = nomeCliente;
    }
    /**
     * @param observacao The observacao to set.
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    /**
     * @param tipoCliente The tipoCliente to set.
     */
    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    /**
     * @param valorDesconto The valorDesconto to set.
     */
    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }
    /**
     * @param valorEntrada The valorEntrada to set.
     */
    public void setValorEntrada(Double valorEntrada) {
        this.valorEntrada = valorEntrada;
    }
    /**
     * @param valorParcela The valorParcela to set.
     */
    public void setValorParcela(Double valorParcela) {
        this.valorParcela = valorParcela;
    }
    /**
     * @param valorTotal The valorTotal to set.
     */
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    /**
     * @param valorTotalPrazo The valorTotalPrazo to set.
     */
    public void setValorTotalPrazo(Double valorTotalPrazo) {
        this.valorTotalPrazo = valorTotalPrazo;
    }
    /**
     * @param valorTotalProduto The valorTotalProduto to set.
     */
    public void setValorTotalProduto(Double valorTotalProduto) {
        this.valorTotalProduto = valorTotalProduto;
    }
    
    /**
     * @hibernate.set lazy="true" inverse = "true" order-by="linha_numero" cascade = "none"
     * @hibernate.collection-key column="cd_orcamento" 
     * @hibernate.collection-one-to-many class = "org.appfuse.model.venda.OrcamentoItem"
     */
    public Set getOrcamentoItems() {
	return this.orcamentoItems;
    }

    public void setOrcamentoItems(Set orcamentoItems) {
        this.orcamentoItems = orcamentoItems;
    }

    /**
     * Faz a totalização da nota fiscal.
     * @return totalNota.
     */
    public Double getTotalInternal(){
         double _totalDesconto = (getValorDesconto() == null) ? 0 : getValorDesconto().doubleValue();
         double _totalProdutos = (getValorTotalProduto() == null) ? 0 : getValorTotalProduto().doubleValue();
         
         return new Double (_totalProdutos - _totalDesconto);
    }
    /**
     * Totaliza o movimento de estoque. Realiza um loop sobre todos os itens
     * do movimento e, para cada item chama item.getSubTotal(); e item.getValorDesconto();,
     * para totalizar os campos totalProdutos e desconto na orcamento.
     * @return Double 
     */    
    public Double getTotalProdutosInternal(){
        double tempTotal = 0.0;
        Iterator i = getOrcamentoItems().iterator();
        setValorTotalProduto(new Double(0.0));
        while (i.hasNext()) {
            OrcamentoItem orcamentoItem = (OrcamentoItem) i.next();
            tempTotal = tempTotal + orcamentoItem.getTotal().doubleValue();
        }
       return new Double(tempTotal); 
    }    
    
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Orcamento)) {
            return false;
        }
        Orcamento rhs = (Orcamento) object;
        return new EqualsBuilder().append(
                this.valorTotalProduto, rhs.valorTotalProduto).append(
                this.valorTotalPrazo, rhs.valorTotalPrazo).append(
                this.valorEntrada, rhs.valorEntrada).append(this.valorParcela,
                rhs.valorParcela).append(this.NomeCliente, rhs.NomeCliente)
                .append(this.observacao, rhs.observacao).append(
                        this.dtValidadeOrcamento, rhs.dtValidadeOrcamento)
                .append(this.tipoPagamento, rhs.tipoPagamento).append(
                        this.status, rhs.status).append(this.tipoCliente,
                        rhs.tipoCliente).append(this.cdOrcamento,
                        rhs.cdOrcamento).append(this.valorDesconto,
                        rhs.valorDesconto).append(this.cdFuncionario,
                        rhs.cdFuncionario)
                .append(this.cdCliente, rhs.cdCliente).append(this.dtOrcamento,
                        rhs.dtOrcamento)
                .append(this.valorTotal, rhs.valorTotal).append(
                        this.cdPlanoPagamento, rhs.cdPlanoPagamento).isEquals();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("dtValidadeOrcamento",
                this.dtValidadeOrcamento).append("valorTotalPrazo",
                this.valorTotalPrazo).append("valorTotalProduto",
                this.valorTotalProduto).append("valorParcela",
                this.valorParcela).append("tipoCliente", this.tipoCliente)
                .append("valorDesconto", this.valorDesconto).append(
                        "observacao", this.observacao).append("status",
                        this.status).append("nomeCliente",
                        this.getNomeCliente()).append("valorTotal",
                        this.valorTotal).append("cdCliente", this.cdCliente)
                .append("dtOrcamento", this.dtOrcamento).append("cdOrcamento",
                        this.cdOrcamento).append("cdPlanoPagamento",
                        this.cdPlanoPagamento).append("cdFuncionario",
                        this.cdFuncionario).append("valorEntrada",
                        this.valorEntrada).append("tipoPagamento",
                        this.tipoPagamento).toString();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1413895399, 1315420679).append(this.valorTotalProduto).append(
                this.valorTotalPrazo).append(this.valorEntrada).append(
                this.valorParcela).append(this.NomeCliente).append(
                this.observacao).append(this.dtValidadeOrcamento).append(
                this.tipoPagamento).append(this.status).append(
                this.tipoCliente).append(this.cdOrcamento).append(
                this.valorDesconto).append(this.cdFuncionario).append(
                this.cdCliente).append(this.dtOrcamento)
                .append(this.valorTotal).append(this.cdPlanoPagamento)
                .toHashCode();
    }
}
