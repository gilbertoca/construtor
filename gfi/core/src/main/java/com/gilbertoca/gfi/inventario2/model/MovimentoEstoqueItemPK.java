package com.gilbertoca.gfi.inventario2.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

 
/** @author Hibernate CodeGenerator */
public class MovimentoEstoqueItemPK implements Serializable {
    /** identifier field */
    private Integer cdMovimentoEstoque;

    /** identifier field */
    private Integer linhaNumero;

    /** full constructor */
    public MovimentoEstoqueItemPK(Integer linhaNumero, Integer cdMovimentoEstoque) {
        this.cdMovimentoEstoque = cdMovimentoEstoque;
        this.linhaNumero = linhaNumero;
    }

    /** full constructor */
    public MovimentoEstoqueItemPK(String linhaNumero, String cdMovimentoEstoque) {
        this.cdMovimentoEstoque = new Integer(cdMovimentoEstoque);
        this.linhaNumero = new Integer(linhaNumero);
    }

    /** default constructor */
    public MovimentoEstoqueItemPK() {
    }

    /**
     * @hibernate.property column ="cd_movimento_estoque"
     *
     */
    public Integer getCdMovimentoEstoque() {
        return this.cdMovimentoEstoque;
    }

    public void setCdMovimentoEstoque(Integer cdMovimentoEstoque) {
        this.cdMovimentoEstoque = cdMovimentoEstoque;
    }

    /**
     * @hibernate.property column ="linha_numero"
     *
     */
    public Integer getLinhaNumero() {
        return this.linhaNumero;
    }

    public void setLinhaNumero(Integer linhaNumero) {
        this.linhaNumero = linhaNumero;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1891411617, -1484005095).appendSuper(super.hashCode())
                                                           .append(this.linhaNumero)
                                                           .append(this.cdMovimentoEstoque)
                                                           .toHashCode();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof MovimentoEstoqueItemPK)) {
            return false;
        }
        MovimentoEstoqueItemPK rhs = (MovimentoEstoqueItemPK) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(
                this.linhaNumero, rhs.linhaNumero).append(this.cdMovimentoEstoque,
                rhs.cdMovimentoEstoque).isEquals();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("linhaNumero", this.linhaNumero).append("cdMovimentoEstoque",
                        this.cdMovimentoEstoque).toString();
    }
}
