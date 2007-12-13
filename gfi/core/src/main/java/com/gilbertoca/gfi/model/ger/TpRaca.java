package com.gilbertoca.gfi.model.ger;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="ger.ger_tpraca"
 *     
*/
public class TpRaca implements Serializable {

    /** identifier field */
    private Short cdtpraca;

    /** persistent field */
    private String dctpraca;

    /** persistent field */
    private Set gerPessoacaracteristicas;

    /** full constructor */
    public TpRaca(Short cdtpraca, String dctpraca, Set gerPessoacaracteristicas) {
        this.cdtpraca = cdtpraca;
        this.dctpraca = dctpraca;
        this.gerPessoacaracteristicas = gerPessoacaracteristicas;
    }

    /** default constructor */
    public TpRaca() {
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Short"
     *             column="cdtpraca"
     *         
     */
    public Short getCdtpraca() {
        return this.cdtpraca;
    }

    public void setCdtpraca(Short cdtpraca) {
        this.cdtpraca = cdtpraca;
    }

    /** 
     *            @hibernate.property
     *             column="dctpraca"
     *             length="20"
     *             not-null="true"
     *         
     */
    public String getDctpraca() {
        return this.dctpraca;
    }

    public void setDctpraca(String dctpraca) {
        this.dctpraca = dctpraca;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="cdtpraca"
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
            .append("cdtpraca", getCdtpraca())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TpRaca) ) return false;
        TpRaca castOther = (TpRaca) other;
        return new EqualsBuilder()
            .append(this.getCdtpraca(), castOther.getCdtpraca())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCdtpraca())
            .toHashCode();
    }

}
