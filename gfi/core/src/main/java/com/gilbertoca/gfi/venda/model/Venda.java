package com.gilbertoca.gfi.venda.model;

import com.gilbertoca.gfi.component.Endereco;
import com.gilbertoca.gfi.inventario.model.Empresa;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Gilberto
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 *
 * @hibernate.class table = "construtor.venda_venda"
 */
public class Venda implements Serializable{
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private Integer cdCaixa;
    private Integer cdEmpresa = new Integer(1);
    private Empresa empresa = new Empresa();
    private Integer cdCliente;
    private Cliente cliente = new Cliente();
    private Integer cdFuncionario;
    //private Funcionario funcionario = new Funcionario();    
    private Integer cdPlanoPagamento;
    //private PlanoPagamento planoPagamento;
    private Integer cdVenda;
    private Integer cdTransportadora;
    //private Transportadora transportadora = new Transportadora();
    private Double valorFrete = new Double(0.0);
    private String cpfCliente;
    private String rgInscricaoEstadual;
    private String cupomFiscal;
    private Date dtEntrega;
    private Date dtHrVenda = new Date();

    /**
     * Forma pagamento na venda:
     * D-Dinheiro; H-Cheque; P-Cheque-predatado;
     * C-Cartao de Credito; U-Dollar; T-Ticket/Vale;
     * B-Boleto Bancario; L-Duplicata ou O-Convenio.
     */
    private String formaPagamento = "D";
    private String nomeCliente;
    private String telefone = "N/I";
    /**
     * Usado para determinar os tipos de endereço:
     * 0 - Sem endereço (Default sem endereço);
     * 1 - Com endereço;
     * 2 - Com endereço de entrega;
     * 3 - Com ambos.
     */
    private int tipoEndereco = 0;
    private Endereco endereco = new Endereco();
    /**
     * Usado para determinar se existe endereço de entrega.
     * Se verdadeiro, o endereco de entrega é diferente do endereço.
     * Caso constrário o endereço de entrega é o mesmo endereço.
     */
    private Boolean flEnderecoEntrega = new Boolean(false);
    private Endereco enderecoEntrega = new Endereco();

    /**
     * Tipo pagamento na venda: Prazo ou Vista
     */
    private String tipoPagamento = "Vista";
    private Double valorEntrada = new Double(0.0);
    private Double valorInterestadualAICMS = new Double(0.0);
    private Boolean vendaInterestadual = new Boolean(false);
    private Double valorParcela = new Double(0.0);
    private Double valorTotalPrazo = new Double(0.0);

    //totalização
    private Double totalProdutos = new Double(0.0);
    private Double totalDesconto = new Double(0.0);
    private Double valorTotal = new Double(0.0);
    private Double valorRecebido = new Double(0.0);
    private Double valorTroco = new Double(0.0);

    /**
     * Usado para determinar se o procedimento de "gerar registros de contas a receber"
     * será executado. Caso o valor de flGerarRegistroContaReceber for true, iremos
     * gerar no movimento de conta a receber o registro desta venda.
     * // TODO: Implementar um método que realizará o gerar registros de contas a receber.
     */
    private Boolean flGerarRegistroContaReceber = new Boolean(false);

    /**
     * Usado para determinar se o procedimento de "gerar o recibo de entrega dos itens da venda"
     * será executado. Caso o valor de flGerarReciboEntrega for true, iremos
     * gerar no movimento de entrega de itens de estoque desta venda, dizendo para cada item que
     * o mesmo ainda não foi entregue.
     * // TODO: Implementar um método que realizará o gerar registro de entrega dos itens da venda.
     */
    private Boolean flGerarReciboEntrega = new Boolean(true);
    private Boolean flProcessado = new Boolean(false);
    private Set vendaItems = Collections.EMPTY_SET;
    //Precisamos formatar a saida dos numeros
    private NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);


    /**
     *
     */
    public Venda() {
    }

    /**
     * @param orcamento
     */
    public Venda(Orcamento orcamento) {
        this.setCdFuncionario(orcamento.getCdFuncionario());
        this.setCdCliente(orcamento.getCdCliente());
        this.setEnderecoEntrega(orcamento.getCliente().getEndereco());
    }

    
    /**
     * @param cdCaixa
     * @param cdEmpresa
     * @param cdCliente
     * @param cdFuncionario
     * @param cdPlanoPagamento
     * @param cdVenda
     * @param cdTransportadora
     * @param valorFrete
     * @param cpfCliente
     * @param rgInscricaoEstadual
     * @param cupomFiscal
     * @param dtEntrega
     * @param dtHrVenda
     * @param formaPagamento
     * @param nomeCliente
     * @param telefone
     * @param tipoEndereco
     * @param flEnderecoEntrega
     * @param enderecoEntrega
     * @param tipoPagamento
     * @param valorEntrada
     * @param totalProdutos
     * @param totalDesconto
     * @param valorTotal
     * @param valorRecebido
     * @param valorTroco
     * @param flGerarRegistroContaReceber
     * @param flGerarReciboEntrega
     * @param vendaItems
     */
    public Venda(Integer cdCaixa, Integer cdEmpresa, Integer cdCliente,
            Integer cdFuncionario, Integer cdPlanoPagamento, Integer cdVenda,
            Integer cdTransportadora, Double valorFrete, String cpfCliente,
            String rgInscricaoEstadual, String cupomFiscal, Date dtEntrega,
            Date dtHrVenda, String formaPagamento, String nomeCliente,
            String telefone, int tipoEndereco, Boolean flEnderecoEntrega,
            Endereco enderecoEntrega, String tipoPagamento,
            Double valorEntrada, Double totalProdutos, Double totalDesconto,
            Double valorTotal, Double valorRecebido, Double valorTroco,
            Boolean flGerarRegistroContaReceber, Boolean flGerarReciboEntrega,
            Set vendaItems) {
        super();
        this.cdCaixa = cdCaixa;
        this.cdEmpresa = cdEmpresa;
        this.cdCliente = cdCliente;
        this.cdFuncionario = cdFuncionario;
        this.cdPlanoPagamento = cdPlanoPagamento;
        this.cdVenda = cdVenda;
        this.cdTransportadora = cdTransportadora;
        this.valorFrete = valorFrete;
        this.cpfCliente = cpfCliente;
        this.rgInscricaoEstadual = rgInscricaoEstadual;
        this.cupomFiscal = cupomFiscal;
        this.dtEntrega = dtEntrega;
        this.dtHrVenda = dtHrVenda;
        this.formaPagamento = formaPagamento;
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.tipoEndereco = tipoEndereco;
        this.flEnderecoEntrega = flEnderecoEntrega;
        this.enderecoEntrega = enderecoEntrega;
        this.tipoPagamento = tipoPagamento;
        this.valorEntrada = valorEntrada;
        this.totalProdutos = totalProdutos;
        this.totalDesconto = totalDesconto;
        this.valorTotal = valorTotal;
        this.valorRecebido = valorRecebido;
        this.valorTroco = valorTroco;
        this.flGerarRegistroContaReceber = flGerarRegistroContaReceber;
        this.flGerarReciboEntrega = flGerarReciboEntrega;
        this.vendaItems = vendaItems;
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
     * @hibernate.property  column="telefone"   length="30"
     */
    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefoneResidencial) {
        this.telefone = telefoneResidencial;
    }
    
    /**
     * @hibernate.many-to-one insert="false" update="false"
     * cascade="none" column="cd_empresa" outer-join="true"
     * @return a populated empresa object (based on the cdEmpresa)
     */
    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    /**
     * @return Returns the totalProdutos.
     * @hibernate.property column = "total_produtos"
     */
    public Double getTotalProdutos() {
        return totalProdutos;
    }

    /**
     * @param totalProdutos The totalProdutos to set.
     */
    public void setTotalProdutos(Double totalProdutos) {
        this.totalProdutos = totalProdutos;
    }

//    /**
//     * @return Returns the funcionario.
//     * @hibernate.many-to-one
//     *  insert="false" update="false" column="cd_funcionario"
//     *  outer-join="true"
//     * @return a populated localidade object (based on the cd_funcionario)
//     */
//    public Funcionario getFuncionario() {
//        return funcionario;
//    }
//
//    /**
//     * @param funcionario The funcionario to set.
//     */
//    public void setFuncionario(Funcionario funcionario) {
//        this.funcionario = funcionario;
//    }
//
//    /**
//     * @hibernate.many-to-one insert="false" update="false"
//     * cascade="none" column="cd_plano_pagamento" outer-join="true"
//     * @return a populated planoPagamento object (based on the cdPlanoPagamento)
//     */
//    public PlanoPagamento getPlanoPagamento() {
//        return this.planoPagamento;
//    }
//
//    public void setPlanoPagamento(PlanoPagamento PlanoPagamento) {
//        this.planoPagamento = PlanoPagamento;
//    }
//
//    /**
//     * @hibernate.many-to-one insert="false" update="false"
//     * cascade="none" column="cd_transportadora" outer-join="true"
//     * @return a populated transportadora object (based on the cdTransportadora)
//     */
//    public Transportadora getTransportadora() {
//        return this.transportadora;
//    }
//
//    public void setTransportadora(Transportadora transportadora) {
//        this.transportadora = transportadora;
//    }

    /**
     * @hibernate.component
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param endereco The endereco to set.
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * @hibernate.property  column="fl_gerar_registro_conta_receber" length="1"
     */
    public Boolean getFlGerarRegistroContaReceber() {
        return flGerarRegistroContaReceber;
    }

    /**
     * @param flGerarRegistroContaReceber The flGerarRegistroContaReceber to set.
     */
    public void setFlGerarRegistroContaReceber(
        Boolean flGerarRegistroContaReceber) {
        this.flGerarRegistroContaReceber = flGerarRegistroContaReceber;
    }

    /**
     * @hibernate.property  column="fl_endereco_entrega" length="1"
     */
    public Boolean getFlEnderecoEntrega() {
        return flEnderecoEntrega;
    }

    /**
     * @param flEnderecoEntrega The flEnderecoEntrega to set.
     */
    public void setFlEnderecoEntrega(Boolean flEnderecoEntrega) {
        this.flEnderecoEntrega = flEnderecoEntrega;
    }

    /**
     *
     * Usado para determinar se o procedimento de "gerar o recibo de entrega dos itens da venda"
     * será executado. Caso o valor de flGerarReciboEntrega for true, iremos
     * gerar no movimento de entrega de itens de estoque desta venda, dizendo para cada item que
     * o mesmo ainda não foi entregue.
     *
     * @hibernate.property  column="fl_gerar_recibo_entrega" length="1"
     */
    public Boolean getFlGerarReciboEntrega() {
        return flGerarReciboEntrega;
    }

    /**
     * @param flGerarReciboEntrega The flGerarReciboEntrega to set.
     */
    public void setFlGerarReciboEntrega(Boolean flGerarReciboEntrega) {
        this.flGerarReciboEntrega = flGerarReciboEntrega;
    }

    /**
     * @hibernate.component prefix = "entrega_"
     */
    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    /**
     * @param endereco The endereco to set.
     */
    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    /**
     * Usado para determinar os tipos de endereço:
     * 0 - Sem endereço (Default sem endereço);
     * 1 - Com endereço;
     * 2 - Com endereço de entrega;
     * 3 - Com ambos. 
     * @return Returns the tipoEndereco.
     * @hibernate.property column = "tipo_endereco" type = "integer"
     */
    public int getTipoEndereco() {
        return tipoEndereco;
    }

    /**
     * @param tipoEndereco The tipoEndereco to set.
     */
    public void setTipoEndereco(int tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    /**
     * @return Returns the cdTransportadora.
     * @hibernate.property column = "cd_transportadora"
     */
    public Integer getCdTransportadora() {
        return cdTransportadora;
    }

    /**
     * @param cdTransportadora The cdTransportadora to set.
     */
    public void setCdTransportadora(Integer cdTransportadora) {
        this.cdTransportadora = cdTransportadora;
    }

    /**
     * @return Returns the valorFrete.
     * @hibernate.property column = "valor_frete"
     */
    public Double getValorFrete() {
        return valorFrete;
    }

    /**
     * @param valorFrete The valorFrete to set.
     */
    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    /**
     * @return Returns the rgInscricaoEstadual.
     * @hibernate.property column="rg_inscricao_estadual" length = "14"
     */
    public String getRgInscricaoEstadual() {
        return rgInscricaoEstadual;
    }

    /**
     * @param rgInscricaoEstadual The rgInscricaoEstadual to set.
     */
    public void setRgInscricaoEstadual(String rgInscricaoEstadual) {
        this.rgInscricaoEstadual = rgInscricaoEstadual;
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
        this.flProcessado = flProcessado;
    }

    /**
     * @hibernate.property column="cd_caixa"
     * @return Returns the cdCaixa.
     */
    public Integer getCdCaixa() {
        return cdCaixa;
    }

    /**
     * @hibernate.property column="cd_cliente"
     * @return Returns the cdCliente.
     */
    public Integer getCdCliente() {
        return cdCliente;
    }

    /**
     * @param cdEmpresa The cdEmpresa to set.
     */
    public void setCdEmpresa(Integer cdEmpresa) {
        this.cdEmpresa = cdEmpresa;
    }
    /**
     * @hibernate.property column="cd_empresa"
     * @return Returns the cdEmpresa.
     */
    public Integer getCdEmpresa() {
        return cdEmpresa;
    }    
    /**
     * @hibernate.property column="cd_funcionario"
     * @return Returns the cdFuncionario.
     */
    public Integer getCdFuncionario() {
        return cdFuncionario;
    }

    /**
     * @hibernate.property column="cd_plano_pagamento"
     * @return Returns the cdPlanoPagamento.
     */
    public Integer getCdPlanoPagamento() {
        return cdPlanoPagamento;
    }

    /**
     * @hibernate.id generator-class="sequence" column="cd_venda" unsaved-value = "null"
     * @hibernate.generator-param name = "sequence" value = "construtor.venda_venda_sequence"
     * @return Integer
     */
    public Integer getCdVenda() {
        return cdVenda;
    }

    /**
     * @hibernate.property column="cpf_cliente" length = "14"
     * @return Returns the cpfCliente.
     */
    public String getCpfCliente() {
        return cpfCliente;
    }

    /**
     * @hibernate.property column="cupom_fiscal" length = "10"
     * @return Returns the cupomFiscal.
     */
    public String getCupomFiscal() {
        return cupomFiscal;
    }

    /**
     * @hibernate.property column="dt_entrega"
     * @return Returns the dtEntrega.
     */
    public Date getDtEntrega() {
        return dtEntrega;
    }

    /**
     * @hibernate.property column="dt_hr_venda"
     * @return Returns the dtHrVenda.
     */
    public Date getDtHrVenda() {
        return dtHrVenda;
    }

    /**
     * @hibernate.property column="forma_pagamento" length = "1"
     * @return Returns the formaPagamento.
     */
    public String getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * @hibernate.property column="nome_cliente" length = "60"
     * @return Returns the nomeCliente.
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * @hibernate.property column="tipo_pagamento" length = "10"
     * @return Returns the tipoPagamento.
     * Tipo pagamento na venda: Prazo ou Vista
     */
    public String getTipoPagamento() {
        return tipoPagamento;
    }

    /**
     * @hibernate.property column="total_desconto"
     * @return Returns the totalDesconto.
     */
    public Double getTotalDesconto() {
        return totalDesconto;
    }

    /**
     * @hibernate.property column="valor_entrada"
     * @return Returns the valorEntrada.
     */
    public Double getValorEntrada() {
        return valorEntrada;
    }

    /**
     * @hibernate.property column="valor_interestadual_AICMS"
     * @return Returns the valorInterestadualAICMS.
     */
    public Double getValorInterestadualAICMS() {
        return valorInterestadualAICMS;
    }

    /**
     * @hibernate.property column="valor_parcela"
     * @return Returns the valorParcela.
     */
    public Double getValorParcela() {
        return valorParcela;
    }

    /**
     * @hibernate.property column="valor_recebido"
     * @return Returns the valorRecebido.
     */
    public Double getValorRecebido() {
        return valorRecebido;
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
     * @hibernate.property column="valor_troco"
     * @return Returns the valorTroco.
     */
    public Double getValorTroco() {
        return valorTroco;
    }

    /**
     * @hibernate.property column="fl_venda_interestadual" length = "1"
     * @return Returns the vendaInterestadual.
     */
    public Boolean getVendaInterestadual() {
        return vendaInterestadual;
    }

    /**
     * @param cdCaixa The cdCaixa to set.
     */
    public void setCdCaixa(Integer cdCaixa) {
        this.cdCaixa = cdCaixa;
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
     * @param cdPlanoPagamento The cdPlanoPagamento to set.
     */
    public void setCdPlanoPagamento(Integer cdPlanoPagamento) {
        this.cdPlanoPagamento = cdPlanoPagamento;
    }

    /**
     * @param cdVenda The cdVenda to set.
     */
    public void setCdVenda(Integer cdVenda) {
        this.cdVenda = cdVenda;
    }

    /**
     * @param cpfCliente The cpfCliente to set.
     */
    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    /**
     * @param cupomFiscal The cupomFiscal to set.
     */
    public void setCupomFiscal(String cupomFiscal) {
        this.cupomFiscal = cupomFiscal;
    }

    /**
     * @param dtEntrega The dtEntrega to set.
     */
    public void setDtEntrega(Date dtEntrega) {
        this.dtEntrega = dtEntrega;
    }

    /**
     * @param dtHrVenda The dtHrVenda to set.
     * @spring.validator type="required"
     */
    public void setDtHrVenda(Date dtVenda) {
        this.dtHrVenda = dtVenda;
    }

    /**
     * @param formaPagamento The formaPagamento to set.
     */
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    /**
     * @param nomeCliente The nomeCliente to set.
     * @spring.validator type="required" 
     */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    /**
     * @param tipoPagamento The tipoPagamento to set.
     */
    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    /**
     * @param totalDesconto The totalDesconto to set.
     */
    public void setTotalDesconto(Double totalDesconto) {
        this.totalDesconto = totalDesconto;
    }

    /**
     * @param valorEntrada The valorEntrada to set.
     */
    public void setValorEntrada(Double valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    /**
     * @param valorInterestadualAICMS The valorInterestadualAICMS to set.
     */
    public void setValorInterestadualAICMS(Double valorInterestadualAICMS) {
        this.valorInterestadualAICMS = valorInterestadualAICMS;
    }

    /**
     * @param valorParcela The valorParcela to set.
     */
    public void setValorParcela(Double valorParcela) {
        this.valorParcela = valorParcela;
    }

    /**
     * @param valorRecebido The valorRecebido to set.
     */
    public void setValorRecebido(Double valorRecebido) {
        this.valorRecebido = valorRecebido;
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
     * @param valorTroco The valorTroco to set.
     */
    public void setValorTroco(Double valorTroco) {
        this.valorTroco = valorTroco;
    }

    /**
     * @param vendaInterestadual The vendaInterestadual to set.
     */
    public void setVendaInterestadual(Boolean vendaInterestadual) {
        this.vendaInterestadual = vendaInterestadual;
    }

    /**
     * @hibernate.set lazy="true" inverse = "true" order-by="linha_numero" cascade = "none"
     * @hibernate.collection-key column="cd_venda"
     * @hibernate.collection-one-to-many class = "org.appfuse.model.venda.VendaItem"
     */
    public Set getVendaItems() {
        return this.vendaItems;
    }

    public void setVendaItems(Set vendaItems) {
        this.vendaItems = vendaItems;
    }

    /**
     * Faz a totalização da nota fiscal.
     * @return totalNota.
     */
    public Double getTotalInternal() {
        double _totalDesconto = (getTotalDesconto() == null) ? 0
                                                             : getTotalDesconto()
                                                                   .doubleValue();
        double _valorFrete = (getValorFrete() == null) ? 0
                                                       : getValorFrete()
                                                             .doubleValue();
        double _totalProdutos = (getTotalProdutos() == null) ? 0
                                                             : getTotalProdutos()
                                                                   .doubleValue();
        double _valorInterestadualAICMS = (getValorInterestadualAICMS() == null)
            ? 0 : getValorInterestadualAICMS().doubleValue();
        nf.setMaximumFractionDigits(2);
        return new Double(nf.format((_totalProdutos + _valorFrete +
            _valorInterestadualAICMS) - _totalDesconto));
    }

     /**
     * Totaliza o venda. Realiza um loop sobre todos os itens
     * do movimento e, para cada item chama item.getValorDesconto(); ,
     * para totalizar o campo desconto no venda.
     * @return Double
     */
    public Double getTotalValorDescontoInternal(){
        double tempValorDesconto = 0.0;
        Iterator i = getVendaItems().iterator();
        while (i.hasNext()) {
            VendaItem vendaItem = (VendaItem) i.next();
            tempValorDesconto = tempValorDesconto + vendaItem.getValorDesconto().doubleValue();
        }
        if (log.isDebugEnabled()) {
            log.debug("leaving 'getTotalValorDescontoInternal' method... valor(como double).: "+
            (tempValorDesconto));
        }
        nf.setMaximumFractionDigits(2);
       return new Double(nf.format(tempValorDesconto));
    }
    /**
     * Totaliza o venda. Realiza um loop sobre todos os itens
     * do movimento e, para cada item chama item.getTotalSemDesconto();
     * para totalizar o campo totalProdutos no venda.
     * @return Double
     */
    public Double getTotalProdutosSemDescontoInternal(){
        double tempTotalSemDesconto = 0.0;
        Iterator i = getVendaItems().iterator();
        while (i.hasNext()) {
            VendaItem vendaItem = (VendaItem) i.next();
            tempTotalSemDesconto = tempTotalSemDesconto + vendaItem.getInternaltotalSemDesconto().doubleValue();
        }
        if (log.isDebugEnabled()) {
            log.debug("leaving 'getTotalProdutosSemDescontoInternal' method... valor(como double).: "+
            (tempTotalSemDesconto));
        }
       nf.setMaximumFractionDigits(2);
       return new Double(nf.format(tempTotalSemDesconto));
    }
    
    /**
     * Após a totalização da venda, com o valor recebido, iremos totalizar
     * o valor do troco.
     * @return Double.
     */
    public Double getValorTrocoInternal() {
        double _valorTotal = (getValorTotal() == null) ? 0
                                                       : getValorTotal()
                                                             .doubleValue();
        double _valorRecebido = (getValorRecebido() == null) ? 0
                                                             : getValorRecebido()
                                                                   .doubleValue();

        if (_valorRecebido == 0) {
            return new Double(0);
        }
        if (log.isDebugEnabled()) {
            log.debug("leaving 'getValorTrocoInternal' method... valor(como double).: "+
            (_valorRecebido - _valorTotal));
        }
        nf.setMaximumFractionDigits(2);
        return new Double(nf.format(_valorRecebido - _valorTotal));
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Venda)) {
            return false;
        }

        Venda rhs = (Venda) object;

        return new EqualsBuilder().append(this.dtEntrega, rhs.dtEntrega)
                                  .append(this.valorTotalPrazo,
            rhs.valorTotalPrazo).append(this.cdCaixa, rhs.cdCaixa)
                                  .append(this.flGerarRegistroContaReceber,
            rhs.flGerarRegistroContaReceber)
                                  .append(this.valorFrete, rhs.valorFrete)
                                  .append(this.rgInscricaoEstadual,
            rhs.rgInscricaoEstadual).append(this.nomeCliente, rhs.nomeCliente)
                                  .append(this.flGerarReciboEntrega,
            rhs.flGerarReciboEntrega)
                                  .append(this.formaPagamento,
            rhs.formaPagamento).append(this.cpfCliente, rhs.cpfCliente)
                                  .append(this.valorRecebido, rhs.valorRecebido)
                                  .append(this.cupomFiscal, rhs.cupomFiscal)
                                  .append(this.cdFuncionario, rhs.cdFuncionario)
                                  .append(this.endereco, rhs.endereco)
                                  .append(this.tipoPagamento, rhs.tipoPagamento).append(this.valorTotal, rhs.valorTotal)
                                  .append(this.valorEntrada, rhs.valorEntrada)
                                  .append(this.valorParcela, rhs.valorParcela)
                                  .append(this.enderecoEntrega,
            rhs.enderecoEntrega).append(this.valorInterestadualAICMS,
            rhs.valorInterestadualAICMS)
                                  .append(this.totalDesconto, rhs.totalDesconto)
                                  .append(this.totalProdutos, rhs.totalProdutos)
                                  .append(this.flProcessado, rhs.flProcessado)
                                  .append(this.cdTransportadora,
            rhs.cdTransportadora)
                                  .append(this.vendaInterestadual,
            rhs.vendaInterestadual).append(this.cliente, rhs.cliente)
                                  .append(this.cdVenda, rhs.cdVenda)
                                  .append(this.dtHrVenda, rhs.dtHrVenda)
                                  .append(this.cdCliente, rhs.cdCliente)
                                  .append(this.valorTroco, rhs.valorTroco)
                                  .append(this.cdPlanoPagamento,
            rhs.cdPlanoPagamento).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1184996309, -262527143).append(this.dtEntrega)
                                                           .append(this.valorTotalPrazo)
                                                           .append(this.cdCaixa)
                                                           .append(this.flGerarRegistroContaReceber)
                                                           .append(this.valorFrete)
                                                           .append(this.rgInscricaoEstadual)
                                                           .append(this.nomeCliente)
                                                           .append(this.flGerarReciboEntrega)
                                                           .append(this.formaPagamento)
                                                           .append(this.cpfCliente)
                                                           .append(this.valorRecebido)
                                                           .append(this.cupomFiscal)
                                                           .append(this.cdFuncionario)
                                                           .append(this.endereco)
                                                           .append(this.tipoPagamento)
                                                           .append(this.valorTotal)
                                                           .append(this.valorEntrada)
                                                           .append(this.valorParcela)
                                                           .append(this.enderecoEntrega)
                                                           .append(this.valorInterestadualAICMS)
                                                           .append(this.totalDesconto)
                                                           .append(this.totalProdutos)
                                                           .append(this.flProcessado)
                                                           .append(this.cdTransportadora)
                                                           .append(this.vendaInterestadual)
                                                           .append(this.cliente)
                                                           .append(this.cdVenda)
                                                           .append(this.dtHrVenda)
                                                           .append(this.cdCliente)
                                                           .append(this.valorTroco)
                                                           .append(this.cdPlanoPagamento)
                                                           .toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("dtEntrega",
            this.dtEntrega).append("valorTotalPrazo", this.valorTotalPrazo)
                                                                        .append("cdCaixa",
            this.cdCaixa)
                                                                        .append("flGerarRegistroContaReceber",
            this.flGerarRegistroContaReceber)
                                                                        .append("valorFrete",
            this.valorFrete)
                                                                        .append("rgInscricaoEstadual",
            this.rgInscricaoEstadual).append("nomeCliente", this.nomeCliente)
                                                                        .append("flGerarReciboEntrega",
            this.flGerarReciboEntrega)
                                                                        .append("formaPagamento",
            this.formaPagamento).append("cpfCliente", this.cpfCliente)
                                                                        .append("valorRecebido",
            this.valorRecebido).append("cupomFiscal", this.cupomFiscal)
                                                                        .append("cdFuncionario",
            this.cdFuncionario).append("endereco", this.endereco)
                                                                        .append("tipoPagamento",
            this.tipoPagamento).append("valorTotal",
            this.valorTotal).append("valorEntrada", this.valorEntrada)
                                                                        .append("valorParcela",
            this.valorParcela).append("enderecoEntrega", this.enderecoEntrega).append("valorInterestadualAICMS",
            this.valorInterestadualAICMS)
                                                                        .append("totalDesconto",
            this.totalDesconto).append("totalProdutos", this.totalProdutos)
                                                                        .append("flProcessado",
            this.flProcessado).append("cdTransportadora", this.cdTransportadora)
                                                                        .append("vendaInterestadual",
            this.vendaInterestadual).append("cliente", this.cliente)
                                                                        .append("cdVenda",
            this.cdVenda).append("dtHrVenda", this.dtHrVenda)
                                                                        .append("cdCliente",
            this.cdCliente).append("valorTroco", this.valorTroco)
                                                                        .append("cdPlanoPagamento",
            this.cdPlanoPagamento).toString();
    }
}