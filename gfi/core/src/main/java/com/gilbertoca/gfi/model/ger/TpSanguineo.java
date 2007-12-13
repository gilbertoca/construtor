package com.gilbertoca.gfi.model.ger;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="ger.ger_tpsanguineo"
 *     
*/
public class TpSanguineo implements Serializable {

    /** identifier field */
    private Short cdtpsanguineo;

    /** persistent field */
    private String dctpsanguineo;

    /** persistent field */
    private Set gerPessoacaracteristicas;

    /** full constructor */
    public TpSanguineo(Short cdtpsanguineo, String dctpsanguineo, Set gerPessoacaracteristicas) {
        this.cdtpsanguineo = cdtpsanguineo;
        this.dctpsanguineo = dctpsanguineo;
        this.gerPessoacaracteristicas = gerPessoacaracteristicas;
    }

    /** default constructor */
    public TpSanguineo() {
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Short"
     *             column="cdtpsanguineo"
     *         
     */
    public Short getCdtpsanguineo() {
        return this.cdtpsanguineo;
    }

    public void setCdtpsanguineo(Short cdtpsanguineo) {
        this.cdtpsanguineo = cdtpsanguineo;
    }

    /** 
     *            @hibernate.property
     *             column="dctpsanguineo"
     *             length="6"
     *             not-null="true"
     *         
     */
    public String getDctpsanguineo() {
        return this.dctpsanguineo;
    }

    public void setDctpsanguineo(String dctpsanguineo) {
        this.dctpsanguineo = dctpsanguineo;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="cdtpsanguineo"
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
            .append("cdtpsanguineo", getCdtpsanguineo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TpSanguineo) ) return false;
        TpSanguineo castOther = (TpSanguineo) other;
        return new EqualsBuilder()
            .append(this.getCdtpsanguineo(), castOther.getCdtpsanguineo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCdtpsanguineo())
            .toHashCode();
    }

}
