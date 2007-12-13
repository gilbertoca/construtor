package com.gilbertoca.gfi.model.venda;

import com.gilbertoca.gfi.model.inventario.Item;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import org.apache.commons.lang.builder.ToStringStyle;
/**
 * @hibernate.class table="construtor.venda_venda_item"
 */  
public class VendaItem implements Serializable {
    /** identifier field */
    private VendaItemPK comp_id = new VendaItemPK();

    /** 
     * TODO : Utilizar o novo mecanismo que hibernate3 fornece para este
     * tipo de situação, por exemplo, removendo este atributo.
     * Usado para determinar se instancia é transient or detached 
     */
    private int version = -1;

    private Double desconto = new Double(0);
    private Float quantidade = new Float(0); 
    private Double precoUnitario = new Double(0);
    private Integer cdItem;
    private Item item = new Item();
    private Venda venda = new Venda();
    /**
     * Usado para simular a chamada de método interno que realiza a totalização do item com desconto.
     * Como não podemos chamar o método getTotalComDesconto() na interface web, então simularemos
     * a chamada de um bean, ou seja, set e get para uma propriedade:
     * 	- Criamos um atributo transiente: totalComDesconto;
     * 	- Criamos os metodos correspondentes set e get:
     *  - No metodo totalComDesconto(), chamados nossa rotinas de calculo, neste caso, getInternalTotalComDesconto()
     */
    private Double totalComDesconto = new Double(0);

    /**
     * Usado para simular a chamada de método interno que realiza a totalização do item sem desconto.
     * Como não podemos chamar o método getTotalSemDesconto() na interface web, então simularemos
     * a chamada de um bean, ou seja, set e get para uma propriedade:
     * 	- Criamos um atributo transiente: totalSemDesconto;
     * 	- Criamos os metodos correspondentes set e get:
     *  - No metodo totalSemDesconto(), chamados nossa rotinas de calculo, neste caso, getInternalTotalSemDesconto()
     */
    private Double totalSemDesconto = new Double(0);

    /**
     * Usado para simular a chamada de método interno que realiza a totalização do item.
     * Como não podemos chamar o método getValorDesconto() na interface web, então simularemos 
     * a chamada de um bean, ou seja, set e get para uma propriedade:
     * 	- Criamos um atributo transiente: valorDesconto;
     * 	- Criamos os metodos correspondentes set e get:
     *  - No metodo getValorDesconto(), chamados nossa rotinas de calculo, neste caso, getInternalValorDesconto()  
     */    
    private Double valorDesconto = new Double(0);
    
    /** full constructor */
    public VendaItem(VendaItemPK comp_id, Float quantidade,
        Double precoPorQuantidade, Item item) {
        this.comp_id = comp_id;
        this.quantidade = quantidade;
        this.precoUnitario = precoPorQuantidade;
        this.item = item;
    } 

    /**
     * @param comp_id
     * @param desconto
     * @param quantidade
     * @param precoUnitario
     * @param cdItem
     */
    public VendaItem(VendaItemPK comp_id, Double desconto, Float quantidade,
            Double precoUnitario, Integer cdItem) {
        super();
        this.comp_id = comp_id;
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.cdItem = cdItem;
    }
    /** default constructor */
    public VendaItem() {
    }

    /**
     *  @hibernate.id generator-class="assigned"
     *
     */
    public VendaItemPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(VendaItemPK comp_id) {
        this.comp_id = comp_id;
    }

    /**
     *  @hibernate.property  column="quantidade"  not-null="true"
     *
     */
    public Float getQuantidade() {
        return this.quantidade;
    }

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
     *  insert="false" update="false" cascade="none" column="cd_item"
     *  outer-join="true"
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
     *  insert="false" update="false" column="cd_venda" outer-join="true"
     * @return a populated venda object (based on the cdVenda)
     */
    public Venda getVenda() {
        return this.venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }


    public void addVenda(Venda venda) {
           venda.getVendaItems().add(this);
           this.venda = venda;
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
        if (!(object instanceof VendaItem)) {
            return false;
        }
        VendaItem rhs = (VendaItem) object;
        return new EqualsBuilder().append(this.cdItem, rhs.cdItem).append(this.precoUnitario,
                rhs.precoUnitario).append(this.quantidade, rhs.quantidade)
                .append(this.desconto, rhs.desconto).append(
                        this.comp_id, rhs.comp_id).append(this.version,
                        rhs.version).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1478035765, 554094549).append(this.cdItem).append(this.precoUnitario).append(
                        this.quantidade).append(this.desconto).append(this.comp_id).append(this.version).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("desconto", this.desconto).append("version", this.version).append("quantidade",
                        this.quantidade).append("comp_id", this.comp_id)
                .append("precoUnitario", this.precoUnitario).append("cdItem",
                        this.cdItem).toString();
    }
}