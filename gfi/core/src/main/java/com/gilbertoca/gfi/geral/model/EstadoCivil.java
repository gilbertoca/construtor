package com.gilbertoca.gfi.geral.model;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="ger.ger_estadocivil"
 *     
*/
public class EstadoCivil implements Serializable {

    /** identifier field */
    private Short cdestadocivil;

    /** persistent field */
    private String dcestadocivil;

    /** persistent field */
    private Set gerPessoacaracteristicas;

    /** full constructor */
    public EstadoCivil(Short cdestadocivil, String dcestadocivil, Set gerPessoacaracteristicas) {
        this.cdestadocivil = cdestadocivil;
        this.dcestadocivil = dcestadocivil;
        this.gerPessoacaracteristicas = gerPessoacaracteristicas;
    }

    /** default constructor */
    public EstadoCivil() {
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Short"
     *             column="cdestadocivil"
     *         
     */
    public Short getCdestadocivil() {
        return this.cdestadocivil;
    }

    public void setCdestadocivil(Short cdestadocivil) {
        this.cdestadocivil = cdestadocivil;
    }

    /** 
     *            @hibernate.property
     *             column="dcestadocivil"
     *             length="30"
     *             not-null="true"
     *         
     */
    public String getDcestadocivil() {
        return this.dcestadocivil;
    }

    public void setDcestadocivil(String dcestadocivil) {
        this.dcestadocivil = dcestadocivil;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="cdestadocivil"
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
            .append("cdestadocivil", getCdestadocivil())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof EstadoCivil) ) return false;
        EstadoCivil castOther = (EstadoCivil) other;
        return new EqualsBuilder()
            .append(this.getCdestadocivil(), castOther.getCdestadocivil())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCdestadocivil())
            .toHashCode();
    }

}
