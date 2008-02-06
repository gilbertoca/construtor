package com.gilbertoca.gfi.ger.model;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="ger.ger_tpgrauparentesco"
 *     
*/
public class TpGrauParentesco implements Serializable {

    /** identifier field */
    private Short cdtpgrauparentesco;

    /** nullable persistent field */
    private String dctpgrauparentesco;

    /** persistent field */
    private Set gerPessoadependencias;

    /** full constructor */
    public TpGrauParentesco(Short cdtpgrauparentesco, String dctpgrauparentesco, Set gerPessoadependencias) {
        this.cdtpgrauparentesco = cdtpgrauparentesco;
        this.dctpgrauparentesco = dctpgrauparentesco;
        this.gerPessoadependencias = gerPessoadependencias;
    }

    /** default constructor */
    public TpGrauParentesco() {
    }

    /** minimal constructor */
    public TpGrauParentesco(Short cdtpgrauparentesco, Set gerPessoadependencias) {
        this.cdtpgrauparentesco = cdtpgrauparentesco;
        this.gerPessoadependencias = gerPessoadependencias;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Short"
     *             column="cdtpgrauparentesco"
     *         
     */
    public Short getCdtpgrauparentesco() {
        return this.cdtpgrauparentesco;
    }

    public void setCdtpgrauparentesco(Short cdtpgrauparentesco) {
        this.cdtpgrauparentesco = cdtpgrauparentesco;
    }

    /** 
     *            @hibernate.property
     *             column="dctpgrauparentesco"
     *             length="50"
     *         
     */
    public String getDctpgrauparentesco() {
        return this.dctpgrauparentesco;
    }

    public void setDctpgrauparentesco(String dctpgrauparentesco) {
        this.dctpgrauparentesco = dctpgrauparentesco;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="cdtpgrauparentesco"
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
            .append("cdtpgrauparentesco", getCdtpgrauparentesco())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TpGrauParentesco) ) return false;
        TpGrauParentesco castOther = (TpGrauParentesco) other;
        return new EqualsBuilder()
            .append(this.getCdtpgrauparentesco(), castOther.getCdtpgrauparentesco())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCdtpgrauparentesco())
            .toHashCode();
    }

}
