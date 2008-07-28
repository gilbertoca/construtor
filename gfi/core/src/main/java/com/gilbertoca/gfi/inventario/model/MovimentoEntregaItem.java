package com.gilbertoca.gfi.inventario.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import org.apache.commons.lang.builder.ToStringStyle;
/**
 * @hibernate.class table="construtor.estoque_movimento_entrega_item"
 */  
public class MovimentoEntregaItem implements Serializable{
    /** identifier field */
    private MovimentoEntregaItemPK comp_id = new MovimentoEntregaItemPK();

    /** 
     * TODO : Utilizar o novo mecanismo que hibernate3 fornece para este
     * tipo de situação, por exemplo, removendo este atributo.
     * Usado para determinar se instancia é transient or detached 
     */
    private int version = -1;

    private Integer movimentoEstoqueCdMovimentoEstoque;
    private Integer movimentoEstoqueLinhaNumero;
    private MovimentoEstoqueItem movimentoEstoqueItem;
    private Float quantidade = new Float(0);    
    private Date dtHrSaida;
    private Date dtHrChegada;  
    /**
     * Usado para determinar se este item foi entregue. 
     */
    private Boolean flEntregaRealizada = new Boolean(false);    
    private MovimentoEntrega movimentoEntrega = new MovimentoEntrega();
    
    /** default constructor */
    public MovimentoEntregaItem() {
    }

    /**
     *  @hibernate.id generator-class="assigned"
     *
     */
    public MovimentoEntregaItemPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(MovimentoEntregaItemPK comp_id) {
        this.comp_id = comp_id;
    }

    /**
     * @return Returns the dtHrSaida.
     * @hibernate.property column = "dt_hr_saida"
     */
    public Date getDtHrSaida() {
        return dtHrSaida;
    }
    /**
     * @param dtHrSaida The dtHrSaida to set.
     */
    public void setDtHrSaida(Date dtHrSaida) {
        this.dtHrSaida = dtHrSaida;
    }

    /**
     * @return Returns the dtHrChegada.
     * @hibernate.property column = "dt_hr_chegada"
     */
    public Date getDtHrChegada() {
        return dtHrChegada;
    }
    /**
     * @param dtHrChegada The dtHrChegada to set.
     */
    public void setDtHrChegada(Date dtHrChegada) {
        this.dtHrChegada = dtHrChegada;
    }
    
    /**
     * @return Returns the movimentoEstoqueItem.
     * @hibernate.many-to-one  insert="false" update="false" outer-join="true"
     * @hibernate.column name="movimento_estoque_cd_movimento_estoque"         
     * @hibernate.column name="movimento_estoque_linha_numero"  
     */
    public MovimentoEstoqueItem getMovimentoEstoqueItem() {
        return movimentoEstoqueItem;
    }

    /**
     * @param movimentoEstoqueItem The movimentoEstoqueItem to set.
     */
    public void setMovimentoEstoqueItem(MovimentoEstoqueItem movimentoEstoqueItem) {
        this.movimentoEstoqueItem = movimentoEstoqueItem;
    }

    /**
     * @hibernate.many-to-one
     *  insert="false" update="false" column="cd_movimento_entrega"
     *  outer-join="true"
     * @return a populated movimentoEntrega object (based on the cdmovimentoEntrega)
     */
    public MovimentoEntrega getMovimentoEntrega() {
        return this.movimentoEntrega;
    }

    public void setMovimentoEntrega(MovimentoEntrega movimentoEntrega) {
        this.movimentoEntrega = movimentoEntrega;
    }


    public void addMovimentoEntrega(MovimentoEntrega movimentoEntrega) {
           movimentoEntrega.getMovimentoEntregaItems().add(this);
           this.movimentoEntrega = movimentoEntrega;
       }     
    /**
     *  @hibernate.property  column="quantidade"
     */
    public Float getQuantidade() {
        return this.quantidade;
    }
    
    /**
     * @param quantidade
     * 
     */
    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
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
     * @hibernate.property  column="fl_entrega_realizada" length="1"
     */
     public Boolean getFlEntregaRealizada() {
         return flEntregaRealizada;
     }

     /**
      * @param flEntregaRealizada The flEntregaRealizada to set.
      */
     public void setFlEntregaRealizada(Boolean flEntregaRealizada) {
         this.flEntregaRealizada = flEntregaRealizada;
     }
    
     /**
      * @hibernate.property column ="movimento_estoque_cd_movimento_estoque"
      *
      */
     public Integer getMovimentoEstoqueCdMovimentoEstoque() {
         return this.movimentoEstoqueCdMovimentoEstoque;
     }

     public void setMovimentoEstoqueCdMovimentoEstoque(Integer movimentoEstoqueCdMovimentoEstoque) {
         this.movimentoEstoqueCdMovimentoEstoque = movimentoEstoqueCdMovimentoEstoque;
     }

     /**
      * @hibernate.property column ="movimento_estoque_linha_numero"
      *
      */
     public Integer getMovimentoEstoqueLinhaNumero() {
         return this.movimentoEstoqueLinhaNumero;
     }

     public void setMovimentoEstoqueLinhaNumero(Integer movimentoEstoqueLinhaNumero) {
         this.movimentoEstoqueLinhaNumero = movimentoEstoqueLinhaNumero;
     }
     
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof MovimentoEntregaItem)) {
            return false;
        }
        MovimentoEntregaItem rhs = (MovimentoEntregaItem) object;
        return new EqualsBuilder().append(this.comp_id, rhs.comp_id)
                .append(this.movimentoEntrega, rhs.movimentoEntrega).append(
                        this.flEntregaRealizada, rhs.flEntregaRealizada)
                .append(this.dtHrSaida, rhs.dtHrSaida).append(
                        this.movimentoEstoqueLinhaNumero,
                        rhs.movimentoEstoqueLinhaNumero).append(
                        this.dtHrChegada, rhs.dtHrChegada).append(
                        this.movimentoEstoqueCdMovimentoEstoque,
                        rhs.movimentoEstoqueCdMovimentoEstoque).append(
                        this.version, rhs.version).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-99469705, -1604895627).append(
                this.comp_id).append(
                this.movimentoEntrega).append(this.flEntregaRealizada).append(
                this.dtHrSaida).append(this.movimentoEstoqueLinhaNumero)
                .append(this.dtHrChegada).append(
                        this.movimentoEstoqueCdMovimentoEstoque).append(
                        this.version).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("comp_id", this.comp_id).append("movimentoEntrega",
                        this.movimentoEntrega).append("flEntregaRealizada",
                        this.flEntregaRealizada).append("dtHrSaida",
                        this.dtHrSaida).append("movimentoEstoqueLinhaNumero",
                        this.movimentoEstoqueLinhaNumero).append("dtHrChegada",
                        this.dtHrChegada).append(
                        "movimentoEstoqueCdMovimentoEstoque",
                        this.movimentoEstoqueCdMovimentoEstoque).append(
                        "version", this.version).toString();
    }
}
