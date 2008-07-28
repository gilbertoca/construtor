package com.gilbertoca.gfi.geral.model;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="ger.ger_tpmotivofimdependencia"
 *     
*/
public class TpMotivoFimDependencia implements Serializable {

    /** identifier field */
    private Short cdtpmotivofimdependencia;

    /** nullable persistent field */
    private String dctpmotivofimdependencia;

    /** persistent field */
    private Set gerPessoadependencias;

    /** full constructor */
    public TpMotivoFimDependencia(Short cdtpmotivofimdependencia, String dctpmotivofimdependencia, Set gerPessoadependencias) {
        this.cdtpmotivofimdependencia = cdtpmotivofimdependencia;
        this.dctpmotivofimdependencia = dctpmotivofimdependencia;
        this.gerPessoadependencias = gerPessoadependencias;
    }

    /** default constructor */
    public TpMotivoFimDependencia() {
    }

    /** minimal constructor */
    public TpMotivoFimDependencia(Short cdtpmotivofimdependencia, Set gerPessoadependencias) {
        this.cdtpmotivofimdependencia = cdtpmotivofimdependencia;
        this.gerPessoadependencias = gerPessoadependencias;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Short"
     *             column="cdtpmotivofimdependencia"
     *         
     */
    public Short getCdtpmotivofimdependencia() {
        return this.cdtpmotivofimdependencia;
    }

    public void setCdtpmotivofimdependencia(Short cdtpmotivofimdependencia) {
        this.cdtpmotivofimdependencia = cdtpmotivofimdependencia;
    }

    /** 
     *            @hibernate.property
     *             column="dctpmotivofimdependencia"
     *             length="50"
     *         
     */
    public String getDctpmotivofimdependencia() {
        return this.dctpmotivofimdependencia;
    }

    public void setDctpmotivofimdependencia(String dctpmotivofimdependencia) {
        this.dctpmotivofimdependencia = dctpmotivofimdependencia;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="cdtpmotivofimdependencia"
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
            .append("cdtpmotivofimdependencia", getCdtpmotivofimdependencia())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TpMotivoFimDependencia) ) return false;
        TpMotivoFimDependencia castOther = (TpMotivoFimDependencia) other;
        return new EqualsBuilder()
            .append(this.getCdtpmotivofimdependencia(), castOther.getCdtpmotivofimdependencia())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCdtpmotivofimdependencia())
            .toHashCode();
    }

}
