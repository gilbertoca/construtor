package com.gilbertoca.gfi.geral.model;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="ger.ger_tpreligiao"
 *     
*/
public class TpReligiao implements Serializable {

    /** identifier field */
    private Short cdtpreligiao;

    /** persistent field */
    private String dctpreligiao;

    /** persistent field */
    private Set gerPessoacaracteristicas;

    /** full constructor */
    public TpReligiao(Short cdtpreligiao, String dctpreligiao, Set gerPessoacaracteristicas) {
        this.cdtpreligiao = cdtpreligiao;
        this.dctpreligiao = dctpreligiao;
        this.gerPessoacaracteristicas = gerPessoacaracteristicas;
    }

    /** default constructor */
    public TpReligiao() {
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Short"
     *             column="cdtpreligiao"
     *         
     */
    public Short getCdtpreligiao() {
        return this.cdtpreligiao;
    }

    public void setCdtpreligiao(Short cdtpreligiao) {
        this.cdtpreligiao = cdtpreligiao;
    }

    /** 
     *            @hibernate.property
     *             column="dctpreligiao"
     *             length="20"
     *             not-null="true"
     *         
     */
    public String getDctpreligiao() {
        return this.dctpreligiao;
    }

    public void setDctpreligiao(String dctpreligiao) {
        this.dctpreligiao = dctpreligiao;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="cdtpreligiao"
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
            .append("cdtpreligiao", getCdtpreligiao())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TpReligiao) ) return false;
        TpReligiao castOther = (TpReligiao) other;
        return new EqualsBuilder()
            .append(this.getCdtpreligiao(), castOther.getCdtpreligiao())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCdtpreligiao())
            .toHashCode();
    }

}
