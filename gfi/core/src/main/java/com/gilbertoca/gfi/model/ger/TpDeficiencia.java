package com.gilbertoca.gfi.model.ger;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="ger.ger_tpdeficiencia"
 *     
*/
public class TpDeficiencia implements Serializable {

    /** identifier field */
    private Short cdtpdeficiencia;

    /** persistent field */
    private String dctpdeficiencia;

    /** persistent field */
    private Set gerPessoacaracteristicas;

    /** full constructor */
    public TpDeficiencia(Short cdtpdeficiencia, String dctpdeficiencia, Set gerPessoacaracteristicas) {
        this.cdtpdeficiencia = cdtpdeficiencia;
        this.dctpdeficiencia = dctpdeficiencia;
        this.gerPessoacaracteristicas = gerPessoacaracteristicas;
    }

    /** default constructor */
    public TpDeficiencia() {
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Short"
     *             column="cdtpdeficiencia"
     *         
     */
    public Short getCdtpdeficiencia() {
        return this.cdtpdeficiencia;
    }

    public void setCdtpdeficiencia(Short cdtpdeficiencia) {
        this.cdtpdeficiencia = cdtpdeficiencia;
    }

    /** 
     *            @hibernate.property
     *             column="dctpdeficiencia"
     *             length="20"
     *             not-null="true"
     *         
     */
    public String getDctpdeficiencia() {
        return this.dctpdeficiencia;
    }

    public void setDctpdeficiencia(String dctpdeficiencia) {
        this.dctpdeficiencia = dctpdeficiencia;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="cdtpdeficiencia"
     *            @hibernate.collection-one-to-many
     *             class="com.gilbertoca.gfi.model.ger.PessoaCaracteristica"
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
            .append("cdtpdeficiencia", getCdtpdeficiencia())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TpDeficiencia) ) return false;
        TpDeficiencia castOther = (TpDeficiencia) other;
        return new EqualsBuilder()
            .append(this.getCdtpdeficiencia(), castOther.getCdtpdeficiencia())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCdtpdeficiencia())
            .toHashCode();
    }

}
