package com.gilbertoca.gfi.model.venda;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class OrcamentoItemPK implements Serializable {
    /** identifier field */
    private Integer cdOrcamento;

    /** identifier field */
    private Integer linhaNumero;

    /** full constructor */
    public OrcamentoItemPK(Integer cdOrcamento, Integer linhaNumero) {
        this.cdOrcamento = cdOrcamento;
        this.linhaNumero = linhaNumero;
    }

    /** full constructor */
    public OrcamentoItemPK(String cdOrcamento, String linhaNumero) {
        this.cdOrcamento = new Integer(cdOrcamento);
        this.linhaNumero = new Integer(linhaNumero);
    }

    /** default constructor */
    public OrcamentoItemPK() {
    }

    /**
     * @hibernate.property column="cd_orcamento"
     *
     * */
    public Integer getCdOrcamento() {
        return this.cdOrcamento;
    }

    public void setCdOrcamento(Integer cdOrcamento) {
        this.cdOrcamento = cdOrcamento;
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
                                                           .append(this.cdOrcamento)
                                                           .toHashCode();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof OrcamentoItemPK)) {
            return false;
        }
        OrcamentoItemPK rhs = (OrcamentoItemPK) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(
                this.linhaNumero, rhs.linhaNumero).append(this.cdOrcamento,
                rhs.cdOrcamento).isEquals();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("linhaNumero", this.linhaNumero).append("cdOrcamento",
                        this.cdOrcamento).toString();
    }
}
