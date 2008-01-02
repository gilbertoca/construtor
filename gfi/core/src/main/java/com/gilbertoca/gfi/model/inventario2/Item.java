package com.gilbertoca.gfi.model.inventario2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * 
 * Para exemplificar, vamos admitir o seguinte exemplo de política de estoques:
 *      Estoque de Matéria-prima X
 *      Estoque máximo: 4 unidades
 *      Estoque mínimo: 1 unidade
 *      Consumo médio mensal: 1 unidade
 *  Pede-se:
 *  1. Qual é o ponto de pedido, admitindo-se que o prazo médio de entrega seja
 *  igual a um mês?
 *  2. Qual é a quantidade a ser comprada?
 *  Solução:
 *  1- Ponto de Pedido, quando o estoque atingir o nível de 2 unidades
 *  2- Quantidade a ser comprada deverá se igual a 3 unidades, porque, quando
 *  ocorrer o recebimento do fornecedor, admitindo-se o prazo médio de entrega
 *  de 1 mês, o estoque estará no nível mínimo de 1 unidade que, adicionando as
 *  três unidades novas adquiridas, atingirá o nível máximo de 4 unidades.
 *  
 *  @hibernate.class table="construtor.estoque_item"
 *
*/
public class Item implements Serializable {
    /** identifier field */
    private Integer cdItem;
    private Date dtCadastro = new Date();
    private Double precoVenda = new Double(0.0);
    private Double precoCusto = new Double(0.0);
    private Float percentualDescontoMaximo = new Float(0.0); 
    /** persistent field */
    private String nomeItem;
    /**
     *  Campo utilizado para calculo do preço de venda
     *  <code>Formula: $precoVenda = $precoCusto + %percentualMargemLucro + $adicinalPrecoFixo</code>
     */
    private Float percentualMargemLucro = new Float(0.0); 
    private Double adicinalPrecoFixo = new Double(0.0);
    
    private String cdLocalizacaoSecao;
    private LocalizacaoSecao localizacaoSecao = new LocalizacaoSecao();
    private String cdUnidadeMedidaVenda;
    private UnidadeMedida unidadeMedidaVenda = new UnidadeMedida();
    private Float qtdPorUnidade = new Float(0.0);
    private String cdUnidadeMedidaCompra;
    private UnidadeMedida unidadeMedidaCompra = new UnidadeMedida();
    private Float estoqueAtual = new Float(0.0);
    private Float estoqueMinimo = new Float(0.0);
    private Float nivelReposicao = new Float(0.0);
    private Boolean flDescontinuado = new Boolean(false);
    private Date dtDescontinuado;
    private Integer cdEmpresa = new Integer(1);
    //private Empresa empresa = new Empresa();
    private Integer cdProduto;
    private Produto produto = new Produto();
    /**
     * Valores que o campo tipoICMS pode assumir:
     * I-Isento, T-Tributado, S-Substituição Tributária e N-Não Incidência
     * // TODO: Substituir o tipoICMS por um tipo específico do hibernate (enumeration)
     */
    private String tipoICMS = "I";
    private Float aliquotaICM = new Float(0.0);
    /**
     * Valores que o campo tipoNotaFiscal pode assumir:
     * 0-Padrão(00/40/1041);20-Com redução base de cálculo;
     * 30-Isenta/Não tributada, ICMS por subs. tributária;
     * 50-Suspensão;51-Diferimento;
     * 60-ICMS cobrado ant. por subs. tributária;
     * 70-Redução base cálc., ICMS por subs. tributária;
     * 90-Outros
     * // TODO: Substituir o tipoNotaFiscal por um tipo específico do hibernate (enumeration)
     */
    private Integer tipoNotaFiscal = new Integer(30);
    /**
     * Valores que o campo origemMercadoriaNotaFiscal pode assumir:
     * 0-Nacional;1-Estrangeira/Importação Direta;
     * 2-Estrangeira/Adquirida Mercado Interno
     */
    private Integer origemMercadoriaNotaFiscal = new Integer(0);
    private Boolean flBaseTributariaIPI = new Boolean(false);
    private Float aliquotaIPI = new Float(0.0);
    private String observacao;    
    protected Set fornecedores = new HashSet();
    
    /* Campos que podem ser implementados futuramente:
     * private Float pesoBruto;
     * private Float pesoLiquido;
     * private Float dimensaoLargura;
     * private Float dimensaoAltura;
     * private Float dimensaoProfundidade;
     * private Integer temperaturaMaxima;
     * private Integer temperaturaMinima;
     * private Integer temperaturaIdeal;
     * private String cor;
     * private Boolean custoEmDollar;
     * private Boolean vendaEmDollar;
     * private Boolean perecivel;
     */

    /** full constructor */
    public Item(Integer cdItem, Double precoVenda, Double precoCusto,
        String unidade, Float estoqueAtual, Float estoqueMinimo,
        Float nivelDeReposicao, Boolean descontinuado, Produto produto) {
        this.cdItem = cdItem;
        this.precoVenda = precoVenda;
        this.precoCusto = precoCusto;
        this.cdUnidadeMedidaCompra = unidade;
        this.estoqueAtual = estoqueAtual;
        this.estoqueMinimo = estoqueMinimo;
        this.nivelReposicao = nivelDeReposicao;
        this.flDescontinuado = descontinuado;
        this.produto = produto;
    }

    /** default constructor */
    public Item() {
    }

    /**
     * @hibernate.id generator-class="sequence" column="cd_item" unsaved-value = "null"
     * @hibernate.generator-param name = "sequence" value = "construtor.estoque_item_sequence"
     * @return Integer
     */
    public Integer getCdItem() {
        return this.cdItem;
    }

    public void setCdItem(Integer cdItem) {
        this.cdItem = cdItem;
    }

    /** 
     * @hibernate.property  column="nome_item" 
     * length="80" not-null="true"
     */
    public String getNomeItem() {
        return this.nomeItem;
    }

    /**
     * @param nomeItem
     * @spring.validator type="required"
     */
    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }
    
    /**
     * @hibernate.property column="dt_cadastro" type = "date" not-null="true"
     */
    public Date getDtCadastro() {
        return dtCadastro;
    }

    /**
     * @param dtCadastro The dtCadastro to set.
     */
    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }
    /**
     * @hibernate.property column="dt_descontinuado"
     */
    public Date getDtDescontinuado() {
        return dtDescontinuado;
    }

    /**
     * @param dtDescontinuado The dtDescontinuado to set.
     */
    public void setDtDescontinuado(Date dtDescontinuado) {
        this.dtDescontinuado = dtDescontinuado;
    }
    
    /**
     * @hibernate.property column="preco_venda" length="10"
     */
    public Double getPrecoVenda() {
        return this.precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    /**
     * @return Returns the adicinalPrecoFixo.
     * @hibernate.property column="adicional_preco_fixo"
     */
    public Double getAdicinalPrecoFixo() {
        return adicinalPrecoFixo;
    }
    /**
     * @param adicinalPrecoFixo The adicinalPrecoFixo to set.
     */
    public void setAdicinalPrecoFixo(Double adicinalPrecoFixo) {
        this.adicinalPrecoFixo = adicinalPrecoFixo;
    }
    /**
     * @return Returns the aliquotaICM.
     * @hibernate.property column="aliquota_icm"
     */
    public Float getAliquotaICM() {
        return aliquotaICM;
    }
    /**
     * @param aliquotaICM The aliquotaICM to set.
     */
    public void setAliquotaICM(Float aliquotaICM) {
        this.aliquotaICM = aliquotaICM;
    }
    /**
     * @return Returns the aliquotaIPI.
     * @hibernate.property column="aliquota_ipi"
     */
    public Float getAliquotaIPI() {
        return aliquotaIPI;
    }
    /**
     * @param aliquotaIPI The aliquotaIPI to set.
     */
    public void setAliquotaIPI(Float aliquotaIPI) {
        this.aliquotaIPI = aliquotaIPI;
    }
    /**
     * @return Returns the flBaseTributariaIPI.
     * @hibernate.property column="fl_base_tributaria_ipi" length = "1"
     */
    public Boolean getFlBaseTributariaIPI() {
        return flBaseTributariaIPI;
    }
    /**
     * @param flBaseTributariaIPI The flBaseTributariaIPI to set.
     */
    public void setFlBaseTributariaIPI(Boolean baseTributariaIPI) {
        this.flBaseTributariaIPI = baseTributariaIPI;
    }
    /**
     * @return Returns the cdLocalizacaoSecao.
     * @hibernate.property column="cd_localizacao_secao"
     */
    public String getCdLocalizacaoSecao() {
        return cdLocalizacaoSecao;
    }
    /**
     * @param cdLocalizacaoSecao The cdLocalizacaoSecao to set.
     * @spring.validator type="required"
     */
    public void setCdLocalizacaoSecao(String cdLocalizacaoSecao) {
        this.cdLocalizacaoSecao = cdLocalizacaoSecao;
    }
    /**
     * @return Returns the cdUnidadeMedidaCompra.
     * @hibernate.property column="cd_unidade_medida_compra" length = "4"
     */
    public String getCdUnidadeMedidaCompra() {
        return cdUnidadeMedidaCompra;
    }
    /**
     * @param cdUnidadeMedidaCompra The cdUnidadeMedidaCompra to set.
     * @spring.validator type="required"
     */
    public void setCdUnidadeMedidaCompra(String cdUnidadeMedidaCompra) {
        this.cdUnidadeMedidaCompra = cdUnidadeMedidaCompra;
    }
    /**
     * @return Returns the cdUnidadeMedidaVenda.
     * @hibernate.property column="cd_unidade_medida_venda" length = "4"
     */
    public String getCdUnidadeMedidaVenda() {
        return cdUnidadeMedidaVenda;
    }
    /**
     * @param cdUnidadeMedidaVenda The cdUnidadeMedidaVenda to set.
     * @spring.validator type="required"
     */
    public void setCdUnidadeMedidaVenda(String cdUnidadeMedidaVenda) {
        this.cdUnidadeMedidaVenda = cdUnidadeMedidaVenda;
    }
    /**
     * @return Returns the localizacaoSecao.
     * 
     */
    public LocalizacaoSecao getLocalizacaoSecao() {
        return localizacaoSecao;
    }
    /**
     * @param localizacaoSecao The localizacaoSecao to set.
     */
    public void setLocalizacaoSecao(LocalizacaoSecao localizacaoSecao) {
        this.localizacaoSecao = localizacaoSecao;
    }
    /**
     * @return Returns the observacao.
     * @hibernate.property column="observacao" length = "255"
     */
    public String getObservacao() {
        return observacao;
    }
    /**
     * @param observacao The observacao to set.
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    /**
     * @return Returns the origemMercadoriaNotaFiscal.
     * @hibernate.property column="origem_mercadoria_nota_fiscal" length = "2"
     */
    public Integer getOrigemMercadoriaNotaFiscal() {
        return origemMercadoriaNotaFiscal;
    }
    /**
     * @param origemMercadoriaNotaFiscal The origemMercadoriaNotaFiscal to set.
     */
    public void setOrigemMercadoriaNotaFiscal(Integer origemMercadoriaNotaFiscal) {
        this.origemMercadoriaNotaFiscal = origemMercadoriaNotaFiscal;
    }
    /**
     * @return Returns the percentualDescontoMaximo.
     * @hibernate.property column="percentual_desconto_maximo"
     */
    public Float getPercentualDescontoMaximo() {
        return percentualDescontoMaximo;
    }
    /**
     * @param percentualDescontoMaximo The percentualDescontoMaximo to set.
     */
    public void setPercentualDescontoMaximo(Float percentualDescontoMaximo) {
        this.percentualDescontoMaximo = percentualDescontoMaximo;
    }
    /**
     * @return Returns the percentualMargemLucro.
     * @hibernate.property column="percentual_margem_lucro"
     */
    public Float getPercentualMargemLucro() {
        return percentualMargemLucro;
    }
    /**
     * @param percentualMargemLucro The percentualMargemLucro to set.
     */
    public void setPercentualMargemLucro(Float percentualMargemLucro) {
        this.percentualMargemLucro = percentualMargemLucro;
    }
    /**
     * @return Returns the tipoICMS.
     * @hibernate.property column="tipo_icms" length = "1"
     */
    public String getTipoICMS() {
        return tipoICMS;
    }
    /**
     * @param tipoICMS The tipoICMS to set.
     */
    public void setTipoICMS(String tipoICMS) {
        this.tipoICMS = tipoICMS;
    }
    /**
     * @return Returns the tipoNotaFiscal.
     * @hibernate.property column="tipo_nota_fiscal" length = "2"
     */
    public Integer getTipoNotaFiscal() {
        return tipoNotaFiscal;
    }
    /**
     * @param tipoNotaFiscal The tipoNotaFiscal to set.
     */
    public void setTipoNotaFiscal(Integer tipoNotaFiscal) {
        this.tipoNotaFiscal = tipoNotaFiscal;
    }
    /**
     * @return Returns the unidadeMedidaCompra.
     */
    public UnidadeMedida getUnidadeMedidaCompra() {
        return unidadeMedidaCompra;
    }
    /**
     * @param unidadeMedidaCompra The unidadeMedidaCompra to set.
     */
    public void setUnidadeMedidaCompra(UnidadeMedida unidadeMedidaCompra) {
        this.unidadeMedidaCompra = unidadeMedidaCompra;
    }
    /**
     * @return Returns the unidadeMedidaVenda.
     */
    public UnidadeMedida getUnidadeMedidaVenda() {
        return unidadeMedidaVenda;
    }
    /**
     * @param unidadeMedidaVenda The unidadeMedidaVenda to set.
     */
    public void setUnidadeMedidaVenda(UnidadeMedida unidadeMedidaVenda) {
        this.unidadeMedidaVenda = unidadeMedidaVenda;
    }
    /**
     * @hibernate.property column="preco_custo" length="10"
     * @return Returns the precoCusto.
     */
    public Double getPrecoCusto() {
        return precoCusto;
    }

    /**
     * @param precoCusto The precoCusto to set.
     * @spring.validator type="required"
     */
    public void setPrecoCusto(Double precoCusto) {
        this.precoCusto = precoCusto;
    }

    /**
     * @hibernate.property column="qtd_por_unidade" length="2"
     */
    public Float getQtdPorUnidade() {
        return this.qtdPorUnidade;
    }

    public void setQtdPorUnidade(Float qtdPorUnidade) {
        this.qtdPorUnidade = qtdPorUnidade;
    }

    /**
     * @hibernate.property column="estoque_atual" length="2"
     *
     */
    public Float getEstoqueAtual() {
        return this.estoqueAtual;
    }

    public void setEstoqueAtual(Float estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    /**
     * @hibernate.property column="estoque_minimo" length="2"
     */
    public Float getEstoqueMinimo() {
        return this.estoqueMinimo;
    }

    public void setEstoqueMinimo(Float estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    /**
     * @hibernate.property column="nivel_reposicao" length="2"
     *
     */
    public Float getNivelReposicao() {
        return this.nivelReposicao;
    }

    /**
    * @hibernate.property  column="fl_descontinuado" length="1"
    */
    public Boolean getFlDescontinuado() {
        return flDescontinuado;
    }

    /**
     * @param flDescontinuado The flDescontinuado to set.
     */
    public void setFlDescontinuado(Boolean descontinuado) {
        this.flDescontinuado = descontinuado;
    }

    public void setNivelReposicao(Float nivelDeReposicao) {
        this.nivelReposicao = nivelDeReposicao;
    }

    /**
     * @return Returns the cdEmpresa.
     * @hibernate.property column="cd_empresa"
     */
    public Integer getCdEmpresa() {
        return cdEmpresa;
    }

    /**
     * @param cdEmpresa The cdEmpresa to set.
     */
    public void setCdEmpresa(Integer cdEmpresa) {
        this.cdEmpresa = cdEmpresa;
    }

    /**
     * @hibernate.many-to-one
     *  insert="false" update="false" cascade="none" column="cd_empresa"
     *  outer-join="true"
     * @return a populated empresa object (based on the cdEmpresa)
     */
//    public Empresa getEmpresa() {
//        return this.empresa;
//    }
//
//    public void setEmpresa(Empresa empresa) {
//        this.empresa = empresa;
//    }

    /**
     * @return Returns the cdProduto.
     * @hibernate.property column="cd_produto"
     *
     */
    public Integer getCdProduto() {
        return cdProduto;
    }

    /**
     * @param cdProduto The cdProduto to set.
     * @spring.validator type="required"
     */
    public void setCdProduto(Integer cdProduto) {
        this.cdProduto = cdProduto;
    }

    /**
     * @hibernate.many-to-one
     *  insert="false" update="false" cascade="none" column="cd_produto"
     *  outer-join="true"
     * @return a populated produto object (based on the cdProduto)
     */
    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * Returns the item's fornecedores.
     * @return Set
     *
     * @hibernate.set table="construtor.estoque_item_fornecedor" cascade="save-update" lazy="false"
     * @hibernate.collection-key column="cd_item"
     * @hibernate.collection-many-to-many class="org.appfuse.model.estoque.Fornecedor"
     *                                    column="cd_fornecedor"
     */
    public Set getFornecedores() {
        return fornecedores;
    }

//    /**
//     * Adds a fornecedor for the user
//     *
//     * @param fornecedor
//     */
//    public void addFornecedor(Fornecedor fornecedor) {
//        getFornecedores().add(fornecedor);
//    }
//
//    /**
//     * Sets the fornecedores.
//     * @param fornecedores The fornecedores to set
//     */
//    protected void setFornecedores(Set fornecedores) {
//        this.fornecedores = fornecedores;
//    }
//
//
//    /**
//     * Convert item fornecedores to LabelValue objects for convenience.  
//     */
//    public List getFornecedorList() {
//        List itemFornecedores = new ArrayList();
//        if (log.isDebugEnabled()) {
//            log.debug("entering 'getFornecedorList' method... neste caso fornecedores :"+this.fornecedores);
//        }
//
//        if (this.fornecedores != null) {
//            for (Iterator it = fornecedores.iterator(); it.hasNext();) {
//                Fornecedor fornecedor = (Fornecedor) it.next();
//
//                // convert the item's fornecedores to LabelValue Objects
//                if (fornecedor.getCdFornecedor()==null){
//                    itemFornecedores.add(new LabelValue(fornecedor.getNomeRazaoSocial(),
//                            fornecedor.getNomeRazaoSocial()));                    
//                }else{
//                itemFornecedores.add(new LabelValue(fornecedor.getNomeRazaoSocial(),
//                                             fornecedor.getCdFornecedor().toString()));
//                }
//            }
//        }
//        if (log.isDebugEnabled()) {
//            log.debug("entering 'getFornecedorList' method... neste caso fornecedores :"+itemFornecedores);
//        }
//
//        return itemFornecedores;
//    }

    
    /**
     * Realiza o aumento da quantidade do item em estoque (estoqueAtual)
     * @param quantidade
     */
    public void increase(Float quantidade) {
        this.setEstoqueAtual(new Float(this.getEstoqueAtual().floatValue()+ quantidade.floatValue()));        
    }
    /**
     * Realiza a diminuição da quantidade do item em estoque (estoqueAtual)
     * @param quantidade
     */
    public void decrease(Float quantidade) {
        this.setEstoqueAtual(new Float(this.getEstoqueAtual().floatValue()- quantidade.floatValue()));        
    }

    /**
     * Realiza o calculo do preço de venda, baseado nos seguintes parametros:
     * Compra de dois itens no valor de custo de 22,00 e um lucro de 5%
     * $valorLucro = (precoCusto/100) * %percentualMargemLucro
     * $precoVenda = valorLucro + precoCusto + $adicinalPrecoFixo 
     */
    public void calculcarPrecoVenvda() {
        double preco = 0, valorLucro = 0,precoCusto = 0, adicinalPrecoFixo = 0; 
        double percentualMargemLucro = 0;
        precoCusto = (getPrecoCusto() == null) ? 0 : getPrecoCusto().doubleValue();
        adicinalPrecoFixo = (getAdicinalPrecoFixo() == null) ? 0 : getAdicinalPrecoFixo().doubleValue();
        percentualMargemLucro = (getPercentualMargemLucro() == null) ? 0 : getPercentualMargemLucro().doubleValue();
        
        valorLucro = (precoCusto/100) * percentualMargemLucro;
        preco = valorLucro + precoCusto + adicinalPrecoFixo; 

        setPrecoVenda(new Double(preco));       
    }    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(final Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Item))
            return false;
        Item castOther = (Item) other;
        return new EqualsBuilder().append(cdItem, castOther.cdItem).append(
                dtCadastro, castOther.dtCadastro).append(precoVenda,
                castOther.precoVenda).append(precoCusto, castOther.precoCusto)
                .append(percentualDescontoMaximo,
                        castOther.percentualDescontoMaximo).append(
                        percentualMargemLucro, castOther.percentualMargemLucro)
                .append(adicinalPrecoFixo, castOther.adicinalPrecoFixo).append(
                        cdLocalizacaoSecao, castOther.cdLocalizacaoSecao)
                .append(localizacaoSecao, castOther.localizacaoSecao).append(
                        cdUnidadeMedidaVenda, castOther.cdUnidadeMedidaVenda)
                .append(qtdPorUnidade, castOther.qtdPorUnidade).append(
                        cdUnidadeMedidaCompra, castOther.cdUnidadeMedidaCompra)
                .append(estoqueAtual, castOther.estoqueAtual).append(
                        estoqueMinimo, castOther.estoqueMinimo).append(
                        nivelReposicao, castOther.nivelReposicao).append(
                        flDescontinuado, castOther.flDescontinuado).append(
                        dtDescontinuado, castOther.dtDescontinuado).append(
                        cdEmpresa, castOther.cdEmpresa).append(cdProduto,
                        castOther.cdProduto).append(tipoICMS,
                        castOther.tipoICMS).append(aliquotaICM,
                        castOther.aliquotaICM).append(tipoNotaFiscal,
                        castOther.tipoNotaFiscal).append(
                        origemMercadoriaNotaFiscal,
                        castOther.origemMercadoriaNotaFiscal).append(
                        flBaseTributariaIPI, castOther.flBaseTributariaIPI)
                .append(aliquotaIPI, castOther.aliquotaIPI).append(observacao,
                        castOther.observacao).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder().append(cdItem).append(dtCadastro).append(
                precoVenda).append(precoCusto).append(percentualDescontoMaximo)
                .append(percentualMargemLucro).append(adicinalPrecoFixo)
                .append(cdLocalizacaoSecao).append(localizacaoSecao).append(
                        cdUnidadeMedidaVenda).append(qtdPorUnidade).append(
                        cdUnidadeMedidaCompra).append(estoqueAtual).append(
                        estoqueMinimo).append(nivelReposicao).append(
                        flDescontinuado).append(dtDescontinuado).append(
                        cdEmpresa).append(cdProduto).append(tipoICMS).append(
                        aliquotaICM).append(tipoNotaFiscal).append(
                        origemMercadoriaNotaFiscal).append(flBaseTributariaIPI)
                .append(aliquotaIPI).append(observacao).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("cdItem", cdItem).append("dtCadastro", dtCadastro)
                .append("precoVenda", precoVenda).append("precoCusto",
                        precoCusto).append("percentualDescontoMaximo",
                        percentualDescontoMaximo).append(
                        "percentualMargemLucro", percentualMargemLucro).append(
                        "adicinalPrecoFixo", adicinalPrecoFixo).append(
                        "cdLocalizacaoSecao", cdLocalizacaoSecao).append(
                        "localizacaoSecao", localizacaoSecao).append(
                        "cdUnidadeMedidaVenda", cdUnidadeMedidaVenda).append(
                        "qtdPorUnidade", qtdPorUnidade).append(
                        "cdUnidadeMedidaCompra", cdUnidadeMedidaCompra).append(
                        "estoqueAtual", estoqueAtual).append("estoqueMinimo",
                        estoqueMinimo).append("nivelReposicao", nivelReposicao)
                .append("flDescontinuado", flDescontinuado).append(
                        "dtDescontinuado", dtDescontinuado).append("cdEmpresa",
                        cdEmpresa).append("cdProduto", cdProduto).append(
                        "tipoICMS", tipoICMS)
                .append("aliquotaICM", aliquotaICM).append("tipoNotaFiscal",
                        tipoNotaFiscal).append("origemMercadoriaNotaFiscal",
                        origemMercadoriaNotaFiscal).append(
                        "flBaseTributariaIPI", flBaseTributariaIPI).append(
                        "aliquotaIPI", aliquotaIPI).append("observacao",
                        observacao).toString();
    }

}