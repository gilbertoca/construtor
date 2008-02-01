package com.gilbertoca.gfi.inventario2.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

 
/** @author Hibernate CodeGenerator */
public class MovimentoEntregaItemPK implements Serializable {
    /** identifier field */
    private Integer cdMovimentoEntrega;

    /** identifier field */
    private Integer linhaNumero;

    /** full constructor */
    public MovimentoEntregaItemPK(Integer linhaNumero, Integer cdMovimentoEntrega) {
        this.cdMovimentoEntrega = cdMovimentoEntrega;
        this.linhaNumero = linhaNumero;
    }

    /** full constructor */
    public MovimentoEntregaItemPK(String linhaNumero, String cdMovimentoEntrega) {
        this.cdMovimentoEntrega = new Integer(cdMovimentoEntrega);
        this.linhaNumero = new Integer(linhaNumero);
    }

    /** default constructor */
    public MovimentoEntregaItemPK() {
    }

    /**
     * @hibernate.property column="cd_movimento_entrega"
     *
     */
    public Integer getCdMovimentoEntrega() {
        return this.cdMovimentoEntrega;
    }

    public void setCdMovimentoEntrega(Integer cdMovimentoEntrega) {
        this.cdMovimentoEntrega = cdMovimentoEntrega;
    }

    /**
     * @hibernate.property column="linha_numero"
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
                                                           .append(this.cdMovimentoEntrega)
                                                           .toHashCode();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof MovimentoEntregaItemPK)) {
            return false;
        }
        MovimentoEntregaItemPK rhs = (MovimentoEntregaItemPK) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(
                this.linhaNumero, rhs.linhaNumero).append(this.cdMovimentoEntrega,
                rhs.cdMovimentoEntrega).isEquals();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("linhaNumero", this.linhaNumero).append("cdMovimentoEntrega",
                        this.cdMovimentoEntrega).toString();
    }
}
