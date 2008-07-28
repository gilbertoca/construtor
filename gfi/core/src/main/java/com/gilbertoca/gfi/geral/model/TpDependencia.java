package com.gilbertoca.gfi.geral.model;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="ger.ger_tpdependencia"
 *     
*/
public class TpDependencia implements Serializable {

    /** identifier field */
    private Short cdtpdependencia;

    /** nullable persistent field */
    private String dctpdependencia;

    /** persistent field */
    private Set gerPessoadependencias;

    /** full constructor */
    public TpDependencia(Short cdtpdependencia, String dctpdependencia, Set gerPessoadependencias) {
        this.cdtpdependencia = cdtpdependencia;
        this.dctpdependencia = dctpdependencia;
        this.gerPessoadependencias = gerPessoadependencias;
    }

    /** default constructor */
    public TpDependencia() {
    }

    /** minimal constructor */
    public TpDependencia(Short cdtpdependencia, Set gerPessoadependencias) {
        this.cdtpdependencia = cdtpdependencia;
        this.gerPessoadependencias = gerPessoadependencias;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Short"
     *             column="cdtpdependencia"
     *         
     */
    public Short getCdtpdependencia() {
        return this.cdtpdependencia;
    }

    public void setCdtpdependencia(Short cdtpdependencia) {
        this.cdtpdependencia = cdtpdependencia;
    }

    /** 
     *            @hibernate.property
     *             column="dctpdependencia"
     *             length="50"
     *         
     */
    public String getDctpdependencia() {
        return this.dctpdependencia;
    }

    public void setDctpdependencia(String dctpdependencia) {
        this.dctpdependencia = dctpdependencia;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="cdtpdependencia"
     *            @hibernate.collection-one-to-many
     *             class="com.gilbertoca.gfi.ger.model.PessoaDependencia"
     *         
     */
    public Set getGerPessoadependencias() {
        return this.gerPessoadependencias;
    }

    public void setGerPessoadependencias(Set gerPessoadependencias) {
        this.gerPessoadependencias = gerPessoadependencias;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("cdtpdependencia", getCdtpdependencia())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TpDependencia) ) return false;
        TpDependencia castOther = (TpDependencia) other;
        return new EqualsBuilder()
            .append(this.getCdtpdependencia(), castOther.getCdtpdependencia())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCdtpdependencia())
            .toHashCode();
    }

}
