package com.gilbertoca.gfi.inventario2.model;


import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import org.apache.commons.lang.builder.ToStringStyle;
/**
 * @hibernate.class table="construtor.estoque_movimento_estoque_item"
 */  
public class MovimentoEstoqueItem implements Serializable{
    /** identifier field */
    private MovimentoEstoqueItemPK comp_id = new MovimentoEstoqueItemPK();

    /** 
     * TODO : Utilizar o novo mecanismo que hibernate3 fornece para este
     * tipo de situa��o, por exemplo, removendo este atributo.
     * Usado para determinar se instancia � transient or detached 
     */
    private int version = -1;

    /**
     * Usado para determinar se o procedimento de ajuste de pre�o de custo
     * ser� executado. Caso o valor de flAjustarPrecoCusto for true, atualizaremos
     * o pre�o de custo do item em quest�o.
     * // TODO: Implementar um m�todo que realizar� o ajuste de pre�o custo e por
     * consequencia o pre�o de venda.
     */
    private Boolean flAjustarPrecoCusto = new Boolean(false);
    private Double desconto = new Double(0);
    private Float quantidade = new Float(0); 
    private Double precoUnitario = new Double(0);
    /**
     * Usado para determinar se um item, neste caso de venda (saida no movimento de estoque),
     * j� foi entregue. Ap�s realizada uma venda, a baixa no estoque deste item ser� imediata.
     * Mas não indicar� a sa�da, fisicamente do mesmo.
     */
    private Boolean flEntregaRealizada = new Boolean(false);
    private Integer cdItem;
    private Item item = new Item();
    private MovimentoEstoque movimentoEstoque = new MovimentoEstoque();
    /** 
     * Usado para simular a chamada de m�todo interno que realiza a totaliza��o do item com desconto.
     * Como não podemos chamar o m�todo getTotalComDesconto() na interface web, ent�o simularemos
     * a chamada de um bean, ou seja, set e get para uma propriedade:
     * 	- Criamos um atributo transiente: totalComDesconto;
     * 	- Criamos os metodos correspondentes set e get:
     *  - No metodo totalComDesconto(), chamados nossa rotinas de calculo, neste caso, getInternalTotalComDesconto()
     */    
    private Double totalComDesconto = new Double(0);

    /**
     * Usado para simular a chamada de m�todo interno que realiza a totaliza��o do item sem desconto.
     * Como não podemos chamar o m�todo getTotalSemDesconto() na interface web, ent�o simularemos
     * a chamada de um bean, ou seja, set e get para uma propriedade:
     * 	- Criamos um atributo transiente: totalSemDesconto;
     * 	- Criamos os metodos correspondentes set e get:
     *  - No metodo totalSemDesconto(), chamados nossa rotinas de calculo, neste caso, getInternalTotalSemDesconto()
     */
    private Double totalSemDesconto = new Double(0);

    /**
     * Usado para simular a chamada de m�todo interno que realiza a totaliza��o do item.
     * Como não podemos chamar o m�todo getValorDesconto() na interface web, ent�o simularemos 
     * a chamada de um bean, ou seja, set e get para uma propriedade:
     * 	- Criamos um atributo transiente: valorDesconto;
     * 	- Criamos os metodos correspondentes set e get:
     *  - No metodo getValorDesconto(), chamados nossa rotinas de calculo, neste caso, getInternalValorDesconto()  
     */    
    private Double valorDesconto = new Double(0);
    
    /** full constructor */
    public MovimentoEstoqueItem(MovimentoEstoqueItemPK comp_id, Float quantidade,
        Double precoPorQuantidade) {
        this.comp_id = comp_id;
        this.quantidade = quantidade;
        this.precoUnitario = precoPorQuantidade;
    } 

    /** default constructor */
    public MovimentoEstoqueItem() {
    }

    /**
     *  @hibernate.id generator-class="assigned"
     *
     */
    public MovimentoEstoqueItemPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(MovimentoEstoqueItemPK comp_id) {
        this.comp_id = comp_id;
    }

    /**
     * @hibernate.property  column="fl_ajustar_preco_custo" length="1"
     */
     public Boolean getFlAjustarPrecoCusto() {
         return flAjustarPrecoCusto;
     }

     /**
      * @param flAjustarPrecoCusto The flAjustarPrecoCusto to set.
      */
     public void setFlAjustarPrecoCusto(Boolean flAjustarPrecoCusto) {
         this.flAjustarPrecoCusto = flAjustarPrecoCusto;
     }

     /**
      * @hibernate.property  column="fl_entrega_realizada" length="1"
      */
      public Boolean getFlEntregaRealizada() {
          return flEntregaRealizada;
      }

      /**
       * @param flAjustarPrecoCusto The flEntregaRealizada to set.
       */
      public void setFlEntregaRealizada(Boolean flEntregaRealizada) {
          this.flEntregaRealizada = flEntregaRealizada;
      }
     
    /**
     *  @hibernate.property  column="quantidade"  not-null="true"
     */
    public Float getQuantidade() {
        return this.quantidade;
    }
    
    /**
     * @param quantidade
     * @spring.validator type="required"
     */
    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    /**
     *  @hibernate.property  column="desconto"
     *	@return Returns the desconto.
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
     *            @hibernate.property
     *             column="preco_unitario"
     *             length="10"
     *             not-null="true"
     *
     */
    public Double getPrecoUnitario() {
        return this.precoUnitario;
    }
    /**
     * @param precoUnitario
     * @spring.validator type="required"
     */
    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    /**
     * @return Returns the cdItem.
     * @hibernate.property column="cd_item"
     */
    public Integer getCdItem() {
        return cdItem;
    }

    /**
     * @param cdItem The cdItem to set.
     */
    public void setCdItem(Integer cdItem) {
        this.cdItem = cdItem;
    }

    /**
     * @hibernate.many-to-one
     *  insert="false" update="false" column="cd_item"  outer-join="true"
     * @return a populated item object (based on the cdItem)
     */
    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @hibernate.many-to-one
     *  insert="false" update="false" column="cd_movimento_estoque"  outer-join="true"
     * @return a populated movimentoEstoque object (based on the cdMovimentoEstoque)
     */
    public MovimentoEstoque getMovimentoEstoque() {
        return this.movimentoEstoque;
    }

    public void setMovimentoEstoque(MovimentoEstoque movimentoEstoque) {
        this.movimentoEstoque = movimentoEstoque;
    }


    public void addMovimentoEstoque(MovimentoEstoque movimentoEstoque) {
           movimentoEstoque.getMovimentoEstoqueItems().add(this);
           this.movimentoEstoque = movimentoEstoque;
       }     

    /**
     * @return Returns the updated timestamp.
     * @hibernate.version unsaved-value = "negative"
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
     * Retorna o totalComDesconto para este item, exemplo:
     * Compra de dois itens no valor 22,00 e um desconto de 5%
     * valor = precounitario * quantidade
     * valordescontado = (valor/100)* desconto
     * Total = valor - valorDescontado 
     */
    public Double getInternalTotalComDesconto() {
        double preco, quantidade, desconto; 
        double valor = 0, valorDescontado = 0;
        preco = (getPrecoUnitario() == null) ? 0 : getPrecoUnitario().doubleValue();
        quantidade = (getQuantidade() == null) ? 0 : getQuantidade().doubleValue();
        desconto = (getDesconto() == null) ? 0 : getDesconto().doubleValue();
        
    	valor = (preco * quantidade);
    	valorDescontado = (valor / 100) * desconto;
        return new Double(valor - valorDescontado);
    }

    /**
     * @return Returns the totalComDesconto.
     */
    public Double getTotalComDesconto() {
        return getInternalTotalComDesconto();
    }
    /**
     * @param totalComDesconto The totalComDesconto to set.
     */
    public void setTotalComDesconto(Double totalComDesconto) {
        this.totalComDesconto = totalComDesconto;
    }
    /**
     * Retorna o valor do desconto para este item, exemplo:
     * Compra de dois itens no valor 22,00 e um desconto de 5%
     * valor = precounitario * quantidade
     * valordescontado = (valor/100)* desconto 
     */
    public Double getInternalValorDesconto() {
        double preco, quantidade, desconto; 
        double valor = 0, valorDescontado = 0;
        preco = (getPrecoUnitario() == null) ? 0 : getPrecoUnitario().doubleValue();
        quantidade = (getQuantidade() == null) ? 0 : getQuantidade().doubleValue();
        desconto = (getDesconto() == null) ? 0 : getDesconto().doubleValue();
        
    	valor = (preco * quantidade);
    	valorDescontado = (valor / 100) * desconto;

    	return new Double(valorDescontado);
    }

    /**
     * @return Returns the valorDesconto.
     */
    public Double getValorDesconto() {
        return getInternalValorDesconto();
    }
    /**
     * @param valorDesconto The valorDesconto to set.
     */
    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }
    
    /**
     * Retorna o totalSemDesconto para este item, exemplo:
     * Compra de dois itens no valor 22,00 e um desconto de 5%
     * valor = precounitario * quantidade
     * Total = valor
     */
    public Double getInternaltotalSemDesconto() {
        double preco, quantidade;
        double valor = 0;
        preco = (getPrecoUnitario() == null) ? 0 : getPrecoUnitario().doubleValue();
        quantidade = (getQuantidade() == null) ? 0 : getQuantidade().doubleValue();

    	valor = (preco * quantidade);
        return new Double(valor);
    }

    /**
     * @return Returns the totalSemDesconto.
     */
    public Double getTotalSemDesconto() {
        return getInternaltotalSemDesconto();
    }
    /**
     * @param totalSemDesconto The totalSemDesconto to set.
     */
    public void setTotalSemDesconto(Double totalSemDesconto) {
        this.totalSemDesconto = totalSemDesconto;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof MovimentoEstoqueItem)) {
            return false;
        }
        MovimentoEstoqueItem rhs = (MovimentoEstoqueItem) object;
        return new EqualsBuilder().append(this.cdItem, rhs.cdItem).append(this.precoUnitario,
                rhs.precoUnitario).append(this.quantidade, rhs.quantidade)
                .append(this.desconto, rhs.desconto).append(
                        this.flAjustarPrecoCusto, rhs.flAjustarPrecoCusto)
                .append(this.comp_id, rhs.comp_id).append(this.version,
                        rhs.version).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1478035765, 554094549).append(this.cdItem).append(this.precoUnitario).append(
                        this.quantidade).append(this.desconto).append(this.flAjustarPrecoCusto)
                .append(this.comp_id).append(this.version).toHashCode();
    }
    /**
     * @see java.lang.Object#toString() 
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("desconto", this.desconto).append(
                        "flAjustarPrecoCusto", this.flAjustarPrecoCusto)
                .append("version", this.version).append("quantidade",
                        this.quantidade).append("comp_id", this.comp_id)
                .append("valorDesconto", this.getInternalValorDesconto()).append("precoUnitario", this.precoUnitario).append("cdItem",
                        this.cdItem).toString();
    }
}