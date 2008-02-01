package com.gilbertoca.gfi.ger.model;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="ger.ger_tpmotivoiniciodependencia"
 *     
*/
public class TpMotivoInicioDependencia implements Serializable {

    /** identifier field */
    private Short cdtpmotivoiniciodependencia;

    /** nullable persistent field */
    private String dcmotivoiniciodependencia;

    /** persistent field */
    private Set gerPessoadependencias;

    /** full constructor */
    public TpMotivoInicioDependencia(Short cdtpmotivoiniciodependencia, String dcmotivoiniciodependencia, Set gerPessoadependencias) {
        this.cdtpmotivoiniciodependencia = cdtpmotivoiniciodependencia;
        this.dcmotivoiniciodependencia = dcmotivoiniciodependencia;
        this.gerPessoadependencias = gerPessoadependencias;
    }

    /** default constructor */
    public TpMotivoInicioDependencia() {
    }

    /** minimal constructor */
    public TpMotivoInicioDependencia(Short cdtpmotivoiniciodependencia, Set gerPessoadependencias) {
        this.cdtpmotivoiniciodependencia = cdtpmotivoiniciodependencia;
        this.gerPessoadependencias = gerPessoadependencias;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Short"
     *             column="cdtpmotivoiniciodependencia"
     *         
     */
    public Short getCdtpmotivoiniciodependencia() {
        return this.cdtpmotivoiniciodependencia;
    }

    public void setCdtpmotivoiniciodependencia(Short cdtpmotivoiniciodependencia) {
        this.cdtpmotivoiniciodependencia = cdtpmotivoiniciodependencia;
    }

    /** 
     *            @hibernate.property
     *             column="dcmotivoiniciodependencia"
     *             length="50"
     *         
     */
    public String getDcmotivoiniciodependencia() {
        return this.dcmotivoiniciodependencia;
    }

    public void setDcmotivoiniciodependencia(String dcmotivoiniciodependencia) {
        this.dcmotivoiniciodependencia = dcmotivoiniciodependencia;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="cdtpmotivoiniciodependencia"
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
            .append("cdtpmotivoiniciodependencia", getCdtpmotivoiniciodependencia())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TpMotivoInicioDependencia) ) return false;
        TpMotivoInicioDependencia castOther = (TpMotivoInicioDependencia) other;
        return new EqualsBuilder()
            .append(this.getCdtpmotivoiniciodependencia(), castOther.getCdtpmotivoiniciodependencia())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCdtpmotivoiniciodependencia())
            .toHashCode();
    }

}
