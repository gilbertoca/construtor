package com.gilbertoca.gfi.model.venda;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class VendaItemPK implements Serializable {
    /** identifier field */
    private Integer cdVenda;

    /** identifier field */
    private Integer linhaNumero;

    /** full constructor */
    public VendaItemPK(Integer linhaNumero, Integer cdVenda) {
        this.cdVenda = cdVenda;
        this.linhaNumero = linhaNumero;
    }

    /** full constructor */
    public VendaItemPK(String cdVenda, String linhaNumero) {
        this.cdVenda = new Integer(cdVenda);
        this.linhaNumero = new Integer(linhaNumero);
    }

    /** default constructor */
    public VendaItemPK() {
    }

    /**
     * @hibernate.property column="cd_venda"
     *
     * */
    public Integer getCdVenda() {
        return this.cdVenda;
    }

    public void setCdVenda(Integer cdVenda) {
        this.cdVenda = cdVenda;
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
                                                           .append(this.cdVenda)
                                                           .toHashCode();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof VendaItemPK)) {
            return false;
        }
        VendaItemPK rhs = (VendaItemPK) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(
                this.linhaNumero, rhs.linhaNumero).append(this.cdVenda,
                rhs.cdVenda).isEquals();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("linhaNumero", this.linhaNumero).append("cdVenda",
                        this.cdVenda).toString();
    }
}
