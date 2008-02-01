package com.gilbertoca.gfi.inventario.model;

import com.gilbertoca.gfi.Constants;
import com.gilbertoca.gfi.component.Endereco;
import com.gilbertoca.gfi.venda.model.Venda;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Gilberto
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * 
 * @hibernate.class table = "construtor.estoque_movimento_estoque"
 */
public class MovimentoEstoque implements Serializable{
    private Integer cdMovimentoEstoque;
    /** 
     * Tipo do movimento do estoque: Entrada ou Saida
     */
    private String tipoMovimento = "Entrada";
    private Date dtEmissaoNota;
    /**
     * Data e hora do movimento
     */
    private Date dtHrMovimento = new Date();
    private String numeroNota;
    private String serieNota;
    /**
     * Tipo pagamento no movimento do estoque: Prazo ou Vista
     */    
    private String tipoPagamento = "Vista";
    /**
     * Forma pagamento no movimento do estoque:
     * D-Dinheiro; H-Cheque; P-Cheque-pré-datado;
     * C-Cartao de Credito; U-Dollar; T-Ticket/Vale;
     * B-Boleto Bancario; L-Duplicata ou O-Convenio.
     */    
    private String formaPagamento = "D";
    private Integer cdFuncionario;
    private Integer cdPlanoPagamento;
    //private PlanoPagamento planoPagamento;	
    /**
     * Código da Venda no movimento do estoque. 
     * Desta forma pela venda saberemos o nome/RazãoSocial e o cpf/cnpj do cliente.
     */    
    private Integer cdVenda;
    /**
     * Código do Fornecedor no movimento do estoque. 
     * Desta forma saberemos que é um fornecedor, obtendo assim, 
     * o nome/RazãoSocial e o cpf/cnpj do fornecedor.
     */    
    private Integer cdFornecedor;
    private String nomeRazaoSocial;
    private String cpfCnpj;
    private String rgInscricaoEstadual;
    private Endereco endereco = new Endereco();   
    private String cdNaturezaOperacao = "1.102";
    private NaturezaOperacao naturezaOperacao = new NaturezaOperacao();
    private Double valorSeguro = new Double(0);
    private Double despesasAcessorias = new Double(0);
     
    private Double desconto = new Double(0);
    private Double baseIcms = new Double(0);
    private Double valorIcms = new Double(0);
    private Double baseIcmsSubstituicao = new Double(0);
    private Double valorIcmsSubstituicao = new Double(0);
    private Double totalProdutos = new Double(0);
    /**
     * Determina de que maneira o valor do frete entrará no calculo na nota: 
     *  Quando for 'Entrada' e o atributo fretePorConta=2, ou seja, por conta do Destinatário;
     *  quanto for 'Saida' e o atributo fretePorConta=1, ou seja, por conta do Emitente.
     * Valores que o campo fretePorConta pode assumir:
     * 0-Sem Frete;
     * 1-Emitente;
     * 2-Destinatário;
     */
    private Integer fretePorConta = new Integer(0);
    private Integer cdTransportadora;
    private String nomeTransportadora;    
    private Double valorFrete = new Double(0);
    private Double totalIPI = new Double(0);
    private Double totalNota = new Double(0);
    private Double valorISS = new Double(0);
    /**
     * Usado para determinar se o procedimento de "gerar registros de contas a pagar"
     * será executado. Caso o valor de flGerarRegistroContaPagar for true, iremos
     * gerar no movimento de conta a pagar registro desta compra.
     * // TODO: Implementar um método que realizará o gerar registros de contas a pagar.
     */
    private Boolean flGerarRegistroContaPagar = new Boolean(false);    
    private Set movimentoEstoqueItems = Collections.EMPTY_SET;
    private Boolean flProcessado = new Boolean(false);
    
 
    /**
     * 
     */
    public MovimentoEstoque() {
       
    }
    
    /**
     * @param cdMovimentoEstoque
     * @param tipoMovimento
     * @param dtEmissaoNota
     * @param dtHrMovimento
     * @param numeroNota
     * @param serieNota
     * @param tipoPagamento
     * @param formaPagamento
     * @param cdFuncionario
     * @param cdPlanoPagamento
     * @param cdVenda
     * @param cdFornecedor
     * @param nomeRazaoSocial
     * @param cpfCnpj
     * @param rgInscricaoEstadual
     * @param desconto
     * @param baseIcms
     * @param valorIcms
     * @param baseIcmsSubstituicao
     * @param valorIcmsSubstituicao
     * @param totalProdutos
     * @param valorFrete
     * @param totalIPI
     * @param totalNota
     * @param cdTransportadora
     * @param nomeTransportadora
     * @param valorISS
     */
    public MovimentoEstoque(Integer cdMovimentoEstoque, String tipoMovimento,
            Date dtEmissaoNota, Date dtHrMovimento, String numeroNota,
            String serieNota, String tipoPagamento, String formaPagamento,
            Integer cdFuncionario, Integer cdPlanoPagamento, Integer cdVenda,
            Integer cdFornecedor, String nomeRazaoSocial, String cpfCnpj,
            String rgInscricaoEstadual, Double desconto, Double baseIcms,
            Double valorIcms, Double baseIcmsSubstituicao,
            Double valorIcmsSubstituicao, Double totalProdutos,
            Double valorFrete, Double totalIPI, Double totalNota,
            Integer cdTransportadora, String nomeTransportadora, Double valorISS) {
        super();
        this.cdMovimentoEstoque = cdMovimentoEstoque;
        this.tipoMovimento = tipoMovimento;
        this.dtEmissaoNota = dtEmissaoNota;
        this.dtHrMovimento = dtHrMovimento;
        this.numeroNota = numeroNota;
        this.serieNota = serieNota;
        this.tipoPagamento = tipoPagamento;
        this.formaPagamento = formaPagamento;
        this.cdFuncionario = cdFuncionario;
        this.cdPlanoPagamento = cdPlanoPagamento;
        this.cdVenda = cdVenda;
        this.cdFornecedor = cdFornecedor;
        this.nomeRazaoSocial = nomeRazaoSocial;
        this.cpfCnpj = cpfCnpj;
        this.rgInscricaoEstadual = rgInscricaoEstadual;
        this.desconto = desconto;
        this.baseIcms = baseIcms;
        this.valorIcms = valorIcms;
        this.baseIcmsSubstituicao = baseIcmsSubstituicao;
        this.valorIcmsSubstituicao = valorIcmsSubstituicao;
        this.totalProdutos = totalProdutos;
        this.valorFrete = valorFrete;
        this.totalIPI = totalIPI;
        this.totalNota = totalNota;
        this.cdTransportadora = cdTransportadora;
        this.nomeTransportadora = nomeTransportadora;
        this.valorISS = valorISS;
    }
    
    /**
     * @param venda
     */
    public MovimentoEstoque(Venda venda) {
        this.setTipoMovimento("Saida");
        this.setCdFuncionario(venda.getCdFuncionario());
        //TODO: verificar qual o codigo que corresponde à venda na tabela naturezaOperacao.
        this.setCdNaturezaOperacao(Constants.NATUREZAOPERACAO_VENDA);//Vendas de prod. própr. ou terc. p/ o Est.
        this.setCdPlanoPagamento(venda.getCdPlanoPagamento());
        this.setCdVenda(venda.getCdVenda()); 
        this.setCpfCnpj(venda.getCpfCliente());
        //E necessario usar a data e hora do movimento.
        //this.setDtHrMovimento(venda.getDtHrVenda());
        this.setFormaPagamento(venda.getFormaPagamento());
        this.setTipoPagamento(venda.getTipoPagamento());
        this.setNomeRazaoSocial(venda.getNomeCliente());
        this.setRgInscricaoEstadual(venda.getRgInscricaoEstadual());
        this.setEndereco(venda.getEnderecoEntrega());
        this.setCdTransportadora(venda.getCdTransportadora());
        this.setValorFrete(venda.getValorFrete());
        this.setTotalProdutos(venda.getTotalProdutos());
        this.setDesconto(venda.getTotalDesconto());
        this.setTotalNota(venda.getValorTotal());
    }

    /** 
     * @hibernate.set lazy="true" inverse="true" cascade="none"
     * @hibernate.collection-key column="cd_movimento_estoque"
     * @hibernate.collection-one-to-many class="org.appfuse.model.estoque.MovimentoEstoqueItem"
     *         
     */
    public Set getMovimentoEstoqueItems() {
        return movimentoEstoqueItems;
    }

    /**
     * @param movimentoEstoqueItems The movimentoEstoqueItems to set.
     */
    public void setMovimentoEstoqueItems(Set movimentoEstoqueItems) {
        this.movimentoEstoqueItems = movimentoEstoqueItems;
    }

    /**
     * @return Returns the fretePorConta.
     * @hibernate.property column="frete_por_conta" length = "2"
     */
    public Integer getFretePorConta() {
        return fretePorConta;
    }
    /**
     * @param fretePorConta The fretePorConta to set.
     */
    public void setFretePorConta(Integer fretePorConta) {
        this.fretePorConta = fretePorConta;
    }
    
    /**
     * @return Returns the naturezaOperacao.
     * @hibernate.many-to-one
     *  insert="false" update="false" column="cd_natureza_operacao"
     *  outer-join="true"
     * @return a populated naturezaOperacao object (based on the cd_natureza_operacao)
     */    
    public NaturezaOperacao getNaturezaOperacao() {
        return naturezaOperacao;
    }
    /**
     * @param naturezaOperacao The naturezaOperacao to set.
     */
    public void setNaturezaOperacao(NaturezaOperacao naturezaOperacao) {
        this.naturezaOperacao = naturezaOperacao;
    }
       
    /**
     * @return Returns the baseIcms.
     * @hibernate.property column = "base_icms"
     */
    public Double getBaseIcms() {
        return baseIcms;
    }
    /**
     * @param baseIcms The baseIcms to set.
     */
    public void setBaseIcms(Double baseIcms) {
        this.baseIcms = baseIcms;
    }
    /**
     * @return Returns the baseIcmsSubstituicao.
     * @hibernate.property column = "base_icms_substituicao"
     */
    public Double getBaseIcmsSubstituicao() {
        return baseIcmsSubstituicao;
    }
    /**
     * @param baseIcmsSubstituicao The baseIcmsSubstituicao to set.
     */
    public void setBaseIcmsSubstituicao(Double baseIcmsSubstituicao) {
        this.baseIcmsSubstituicao = baseIcmsSubstituicao;
    }
    
    /**
     * @hibernate.property  column="fl_gerar_registro_conta_pagar" length="1"
     */
     public Boolean getFlGerarRegistroContaPagar() {
         return flGerarRegistroContaPagar;
     }

     /**
      * @param flGerarRegistroContaPagar The flGerarRegistroContaPagar to set.
      */
     public void setFlGerarRegistroContaPagar(Boolean flGerarRegistroContaPagar) {
         this.flGerarRegistroContaPagar = flGerarRegistroContaPagar;
     }
 
    /**
     * @return Returns the cdFornecedor.
     * @hibernate.property column = "cd_fornecedor"
     */
    public Integer getCdFornecedor() {
        return cdFornecedor;
    }
    /**
     * @param cdFornecedor The cdFornecedor to set.
     */
    public void setCdFornecedor(Integer cdFornecedor) {
        this.cdFornecedor = cdFornecedor;
    }
    /**
     * @return Returns the cdFuncionario.
     * @hibernate.property column = "cd_funcionario"
     */
    public Integer getCdFuncionario() {
        return cdFuncionario;
    }
    /**
     * @param cdFuncionario The cdFuncionario to set.
     */
    public void setCdFuncionario(Integer cdFuncionario) {
        this.cdFuncionario = cdFuncionario;
    }
   
    /**
     * @hibernate.id generator-class="sequence" column="cd_movimento_estoque" unsaved-value = "null"
     * @hibernate.generator-param name = "sequence" value = "construtor.estoque_movimento_estoque_sequence"
     * @return Integer
     */    
    public Integer getCdMovimentoEstoque() {
        return cdMovimentoEstoque;
    }
    /**
     * @param cdMovimentoEstoque The cdMovimentoEstoque to set.
     */
    public void setCdMovimentoEstoque(Integer cdMovimentoEstoque) {
        this.cdMovimentoEstoque = cdMovimentoEstoque;
    }
    /**
     * @return Returns the cdPlanoPagamento.
     * @hibernate.property column = "cd_plano_pagamento" 
     */
    public Integer getCdPlanoPagamento() {
        return cdPlanoPagamento;
    }
    /**
     * @param cdPlanoPagamento The cdPlanoPagamento to set.
     */
    public void setCdPlanoPagamento(Integer cdPlanoPagamento) {
        this.cdPlanoPagamento = cdPlanoPagamento;
    }
	
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
     * @return Returns the cdVenda.
     * @hibernate.property column = "cd_venda"
     */
    public Integer getCdVenda() {
        return cdVenda;
    }
    /**
     * @param cdVenda The cdVenda to set.
     */
    public void setCdVenda(Integer cdVenda) {
        this.cdVenda = cdVenda;
    }
    /**
     * @return Returns the cpfCnpj.
     * @hibernate.property column = "cpf_cnpj" length = "14"
     */
    public String getCpfCnpj() {
        return cpfCnpj;
    }
    /**
     * @param cpfCnpj The cpfCnpj to set.
     */
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }
    /**
     * @return Returns the desconto.
     * @hibernate.property column = "desconto"
     */
    public Double getDesconto() {
        return desconto;
    }
    /**
     * @param desconto The desconto to set.
     */
    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
    /**
     * @return Returns the dtEmissaoNota.
     * @hibernate.property column = "dt_emissao_nota"
     */
    public Date getDtEmissaoNota() {
        return dtEmissaoNota;
    }
    /**
     * @param dtEmissaoNota The dtEmissaoNota to set.
     */
    public void setDtEmissaoNota(Date dtEmissaoNota) {
        this.dtEmissaoNota = dtEmissaoNota;
    }
    /**
     * @return Returns the dtHrMovimento.
     * @hibernate.property column = "dt_hr_movimento"
     */
    public Date getDtHrMovimento() {
        return dtHrMovimento;
    }
    /**
     * @param dtHrMovimento The dtHrMovimento to set.
     * @spring.validator type="required"
     */
    public void setDtHrMovimento(Date dtHrMovimento) {
        this.dtHrMovimento = dtHrMovimento;
    }
    /**
     * @return Returns the endereco.
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
     * @return Returns the formaPagamento.
     * @hibernate.property column = "forma_pagamento" length = "1"
     */
    public String getFormaPagamento() {
        return formaPagamento;
    }
    /**
     * @param formaPagamento The formaPagamento to set.
     */
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    /**
     * @return Returns the nomeRazaoSocial.
     * @hibernate.property column = "nome_razao_social" length = "100"
     */
    public String getNomeRazaoSocial() {
        return nomeRazaoSocial;
    }
    /**
     * @param nomeRazaoSocial The nomeRazaoSocial to set.
     */
    public void setNomeRazaoSocial(String nomeRazaoSocial) {
        this.nomeRazaoSocial = nomeRazaoSocial;
    }
    /**
     * @return Returns the nomeTransportadora.
     * @hibernate.property column = "nome_transporadora" length = "100"
     */
    public String getNomeTransportadora() {
        return nomeTransportadora;
    }
    /**
     * @param nomeTransportadora The nomeTransportadora to set.
     */
    public void setNomeTransportadora(String nomeTransportadora) {
        this.nomeTransportadora = nomeTransportadora;
    }
    /**
     * @return Returns the numeroNota.
     * @hibernate.property column = "numero_nota" length = "20"
     */
    public String getNumeroNota() {
        return numeroNota;
    }
    /**
     * @param numeroNota The numeroNota to set.
     */
    public void setNumeroNota(String numeroNota) {
        this.numeroNota = numeroNota;
    }
    /**
     * @return Returns the rgInscricaoEstadual.
     * @hibernate.property column = "rg_inscricao_estadual" length = "14"
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
     * @return Returns the serieNota.
     * @hibernate.property column = "serie_nota" length = "10"
     */
    public String getSerieNota() {
        return serieNota;
    }
    /**
     * @param serieNota The serieNota to set.
     */
    public void setSerieNota(String serieNota) {
        this.serieNota = serieNota;
    }
    /**
     * @return Returns the tipoMovimento.
     * @hibernate.property column = "tipo_movimento" length = "10"
     */
    public String getTipoMovimento() {
        return tipoMovimento;
    }
    /**
     * @param tipoMovimento The tipoMovimento to set.
     */
    public void setTipoMovimento(String tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }
    /**
     * @return Returns the tipoPagamento.
     * @hibernate.property column = "tipo_pagamento" length = "10"
     */
    public String getTipoPagamento() {
        return tipoPagamento;
    }
    /**
     * @param tipoPagamento The tipoPagamento to set.
     */
    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
    /**
     * @return Returns the totalIPI.
     * @hibernate.property column = "total_ipi"
     */
    public Double getTotalIPI() {
        return totalIPI;
    }
    /**
     * @param totalIPI The totalIPI to set.
     */
    public void setTotalIPI(Double totalIPI) {
        this.totalIPI = totalIPI;
    }
    /**
     * @return Returns the totalNota.
     * @hibernate.property column = "total_nota"
     */
    public Double getTotalNota() {
        return totalNota;
    }
    /**
     * @param totalNota The totalNota to set.
     */
    public void setTotalNota(Double totalNota) {
        this.totalNota = totalNota;
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
     * @return Returns the valorIcms.
     * @hibernate.property column = "valor_icms"
     */
    public Double getValorIcms() {
        return valorIcms;
    }
    /**
     * @param valorIcms The valorIcms to set.
     */
    public void setValorIcms(Double valorIcms) {
        this.valorIcms = valorIcms;
    }
    /**
     * @return Returns the valorIcmsSubstituicao.
     * @hibernate.property column = "valor_icms_substituicao"
     */
    public Double getValorIcmsSubstituicao() {
        return valorIcmsSubstituicao;
    }
    /**
     * @param valorIcmsSubstituicao The valorIcmsSubstituicao to set.
     */
    public void setValorIcmsSubstituicao(Double valorIcmsSubstituicao) {
        this.valorIcmsSubstituicao = valorIcmsSubstituicao;
    }
    /**
     * @return Returns the valorISS.
     * @hibernate.property column = "valor_iss"
     */
    public Double getValorISS() {
        return valorISS;
    }
    /**
     * @param valorISS The valorISS to set.
     */
    public void setValorISS(Double valorISS) {
        this.valorISS = valorISS;
    }


    /**
     * @return Returns the cdNaturezaOperacao.
     * @hibernate.property column = "cd_natureza_operacao" length = "15"
     */
    public String getCdNaturezaOperacao() {
        return cdNaturezaOperacao;
    }
    /**
     * @param cdNaturezaOperacao The cdNaturezaOperacao to set.
     */
    public void setCdNaturezaOperacao(String cdNaturezaOperacao) {
        this.cdNaturezaOperacao = cdNaturezaOperacao;
    }
    /**
     * @return Returns the despesasAcessorias.
     * @hibernate.property column = "despesas_acessorias" 
     * 
     */
    public Double getDespesasAcessorias() {
        return despesasAcessorias;
    }
    /**
     * @param despesasAcessorias The despesasAcessorias to set.
     */
    public void setDespesasAcessorias(Double despesasAcessorias) {
        this.despesasAcessorias = despesasAcessorias;
    }
    /**
     * @return Returns the valorSeguro.
     * @hibernate.property column = "valor_seguro" 
     *
     */
    public Double getValorSeguro() {
        return valorSeguro;
    }
    /**
     * @param valorSeguro The valorSeguro to set.
     */
    public void setValorSeguro(Double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
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
     * Faz a totalização da nota fiscal.
     * _valorFrete entrará no cálculo quando:
     *      quando for 'Entrada' e o atributo fretePorConta=2;
     *      quanto for 'Saida' e o atributo fretePorConta=1.
     * @return totalNota.
     */
    public Double getTotalNotaInternal(){
         double _valorFrete = 0;
         double _desconto = (getDesconto() == null) ? 0 : getDesconto().doubleValue();
         double _valorIcmsSubstituicao = (getValorIcmsSubstituicao() == null) ? 0 : getValorIcmsSubstituicao().doubleValue();
         double _totalProdutos = (getTotalProdutos() == null) ? 0 : getTotalProdutos().doubleValue();
         if((getTipoMovimento().equalsIgnoreCase("Entrada") && getFretePorConta().intValue()==2)||
         (getTipoMovimento().equalsIgnoreCase("Saida") && getFretePorConta().intValue()==1)){
             _valorFrete = (getValorFrete() == null) ? 0 : getValorFrete().doubleValue();
         }
         double _totalIPI = (getTotalIPI() == null) ? 0 : getTotalIPI().doubleValue();
         double _DespesasAcessorias = (getDespesasAcessorias() == null) ? 0 : getDespesasAcessorias().doubleValue();
        // double _valorSeguro = (getValorSeguro() == null) ? 0 : getValorSeguro().doubleValue();
         
         return new Double ( _valorIcmsSubstituicao
          + _totalProdutos
          + _valorFrete
          + _totalIPI
          + _DespesasAcessorias
          - _desconto);
    }
    /**
     * Totaliza o movimento de estoque. Realiza um loop sobre todos os itens
     * do movimento e, para cada item chama item.getValorDesconto(); ,
     * para totalizar o campo desconto no movimentoEstoque.
     * @return Double 
     */    
    public Double getTotalValorDescontoInternal(){
        double tempValorDesconto = 0.0;
        Iterator i = getMovimentoEstoqueItems().iterator();
        while (i.hasNext()) {
            MovimentoEstoqueItem movimentoEstoqueItem = (MovimentoEstoqueItem) i.next();
            tempValorDesconto = tempValorDesconto + movimentoEstoqueItem.getValorDesconto().doubleValue();
        }
       return new Double(tempValorDesconto);
    }
    /**
     * Totaliza o movimento de estoque. Realiza um loop sobre todos os itens
     * do movimento e, para cada item chama item.getTotalSemDesconto();
     * para totalizar o campo totalProdutos no movimentoEstoque.
     * @return Double
     */
    public Double getTotalProdutosSemDescontoInternal(){
        double tempTotalSemDesconto = 0.0;
        Iterator i = getMovimentoEstoqueItems().iterator();
        while (i.hasNext()) {
            MovimentoEstoqueItem movimentoEstoqueItem = (MovimentoEstoqueItem) i.next();
            tempTotalSemDesconto = tempTotalSemDesconto + movimentoEstoqueItem.getInternaltotalSemDesconto().doubleValue();
        }
       return new Double(tempTotalSemDesconto);
    }
}