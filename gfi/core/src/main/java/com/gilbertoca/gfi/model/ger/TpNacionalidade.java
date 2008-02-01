package com.gilbertoca.gfi.ger.model;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="ger.ger_tpnacionalidade"
 *     
*/
public class TpNacionalidade implements Serializable {

    /** identifier field */
    private Short cdtpnacionalidade;

    /** persistent field */
    private String dctpnacionalidade;

    /** persistent field */
    private Set gerPessoacaracteristicas;

    /** full constructor */
    public TpNacionalidade(Short cdtpnacionalidade, String dctpnacionalidade, Set gerPessoacaracteristicas) {
        this.cdtpnacionalidade = cdtpnacionalidade;
        this.dctpnacionalidade = dctpnacionalidade;
        this.gerPessoacaracteristicas = gerPessoacaracteristicas;
    }

    /** default constructor */
    public TpNacionalidade() {
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Short"
     *             column="cdtpnacionalidade"
     *         
     */
    public Short getCdtpnacionalidade() {
        return this.cdtpnacionalidade;
    }

    public void setCdtpnacionalidade(Short cdtpnacionalidade) {
        this.cdtpnacionalidade = cdtpnacionalidade;
    }

    /** 
     *            @hibernate.property
     *             column="dctpnacionalidade"
     *             length="100"
     *             not-null="true"
     *         
     */
    public String getDctpnacionalidade() {
        return this.dctpnacionalidade;
    }

    public void setDctpnacionalidade(String dctpnacionalidade) {
        this.dctpnacionalidade = dctpnacionalidade;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="cdtpnacionalidade"
     *            @hibernate.collection-one-to-many
     *             class="com.gilbertoca.gfi.ger.model.PessoaCaracteristica"
     *         
     */
    public Set getGerPessoacaracteristicas() {
        return this.gerPessoacaracteristicas;
    }

    public void setGerPessoacaracteristicas(Set gerPessoacaracteristicas) {
        this.gerPessoacaracteristicas = gerPessoacaracteristicas;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("cdtpnacionalidade", getCdtpnacionalidade())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TpNacionalidade) ) return false;
        TpNacionalidade castOther = (TpNacionalidade) other;
        return new EqualsBuilder()
            .append(this.getCdtpnacionalidade(), castOther.getCdtpnacionalidade())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCdtpnacionalidade())
            .toHashCode();
    }

}
