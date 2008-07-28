package com.gilbertoca.gfi.geral.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="ger.ger_pessoadependencia"
 *     
*/
public class PessoaDependencia implements Serializable {

    /** identifier field */
    private Integer cdpessoadependencia;

    /** nullable persistent field */
    private Integer cdpessoadependente;

    /** nullable persistent field */
    private Date dtiniciodependencia;

    /** nullable persistent field */
    private Date dtprevistafimdependencia;

    /** nullable persistent field */
    private Date dtfimdependencia;

    /** nullable persistent field */
    private Boolean flpensionista;

    /** nullable persistent field */
    private Boolean fldepfinsprevidenciarios;

    /** persistent field */
    private com.gilbertoca.gfi.geral.model.Pessoa gerPessoa;

    /** persistent field */
    private com.gilbertoca.gfi.geral.model.TpGrauParentesco gerTpgrauparentesco;

    /** persistent field */
    private com.gilbertoca.gfi.geral.model.TpMotivoInicioDependencia gerTpmotivoiniciodependencia;

    /** persistent field */
    private com.gilbertoca.gfi.geral.model.TpDependencia gerTpdependencia;

    /** persistent field */
    private com.gilbertoca.gfi.geral.model.TpMotivoFimDependencia gerTpmotivofimdependencia;

    /** full constructor */
    public PessoaDependencia(Integer cdpessoadependencia, Integer cdpessoadependente, Date dtiniciodependencia, Date dtprevistafimdependencia, Date dtfimdependencia, Boolean flpensionista, Boolean fldepfinsprevidenciarios, com.gilbertoca.gfi.geral.model.Pessoa gerPessoa, com.gilbertoca.gfi.geral.model.TpGrauParentesco gerTpgrauparentesco, com.gilbertoca.gfi.geral.model.TpMotivoInicioDependencia gerTpmotivoiniciodependencia, com.gilbertoca.gfi.geral.model.TpDependencia gerTpdependencia, com.gilbertoca.gfi.geral.model.TpMotivoFimDependencia gerTpmotivofimdependencia) {
        this.cdpessoadependencia = cdpessoadependencia;
        this.cdpessoadependente = cdpessoadependente;
        this.dtiniciodependencia = dtiniciodependencia;
        this.dtprevistafimdependencia = dtprevistafimdependencia;
        this.dtfimdependencia = dtfimdependencia;
        this.flpensionista = flpensionista;
        this.fldepfinsprevidenciarios = fldepfinsprevidenciarios;
        this.gerPessoa = gerPessoa;
        this.gerTpgrauparentesco = gerTpgrauparentesco;
        this.gerTpmotivoiniciodependencia = gerTpmotivoiniciodependencia;
        this.gerTpdependencia = gerTpdependencia;
        this.gerTpmotivofimdependencia = gerTpmotivofimdependencia;
    }

    /** default constructor */
    public PessoaDependencia() {
    }

    /** minimal constructor */
    public PessoaDependencia(Integer cdpessoadependencia, com.gilbertoca.gfi.geral.model.Pessoa gerPessoa, com.gilbertoca.gfi.geral.model.TpGrauParentesco gerTpgrauparentesco, com.gilbertoca.gfi.geral.model.TpMotivoInicioDependencia gerTpmotivoiniciodependencia, com.gilbertoca.gfi.geral.model.TpDependencia gerTpdependencia, com.gilbertoca.gfi.geral.model.TpMotivoFimDependencia gerTpmotivofimdependencia) {
        this.cdpessoadependencia = cdpessoadependencia;
        this.gerPessoa = gerPessoa;
        this.gerTpgrauparentesco = gerTpgrauparentesco;
        this.gerTpmotivoiniciodependencia = gerTpmotivoiniciodependencia;
        this.gerTpdependencia = gerTpdependencia;
        this.gerTpmotivofimdependencia = gerTpmotivofimdependencia;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Integer"
     *             column="cdpessoadependencia"
     *         
     */
    public Integer getCdpessoadependencia() {
        return this.cdpessoadependencia;
    }

    public void setCdpessoadependencia(Integer cdpessoadependencia) {
        this.cdpessoadependencia = cdpessoadependencia;
    }

    /** 
     *            @hibernate.property
     *             column="cdpessoadependente"
     *             length="4"
     *         
     */
    public Integer getCdpessoadependente() {
        return this.cdpessoadependente;
    }

    public void setCdpessoadependente(Integer cdpessoadependente) {
        this.cdpessoadependente = cdpessoadependente;
    }

    /** 
     *            @hibernate.property
     *             column="dtiniciodependencia"
     *             length="4"
     *         
     */
    public Date getDtiniciodependencia() {
        return this.dtiniciodependencia;
    }

    public void setDtiniciodependencia(Date dtiniciodependencia) {
        this.dtiniciodependencia = dtiniciodependencia;
    }

    /** 
     *            @hibernate.property
     *             column="dtprevistafimdependencia"
     *             length="4"
     *         
     */
    public Date getDtprevistafimdependencia() {
        return this.dtprevistafimdependencia;
    }

    public void setDtprevistafimdependencia(Date dtprevistafimdependencia) {
        this.dtprevistafimdependencia = dtprevistafimdependencia;
    }

    /** 
     *            @hibernate.property
     *             column="dtfimdependencia"
     *             length="4"
     *         
     */
    public Date getDtfimdependencia() {
        return this.dtfimdependencia;
    }

    public void setDtfimdependencia(Date dtfimdependencia) {
        this.dtfimdependencia = dtfimdependencia;
    }

    /** 
     *            @hibernate.property
     *             column="flpensionista"
     *             length="1"
     *         
     */
    public Boolean getFlpensionista() {
        return this.flpensionista;
    }

    public void setFlpensionista(Boolean flpensionista) {
        this.flpensionista = flpensionista;
    }

    /** 
     *            @hibernate.property
     *             column="fldepfinsprevidenciarios"
     *             length="1"
     *         
     */
    public Boolean getFldepfinsprevidenciarios() {
        return this.fldepfinsprevidenciarios;
    }

    public void setFldepfinsprevidenciarios(Boolean fldepfinsprevidenciarios) {
        this.fldepfinsprevidenciarios = fldepfinsprevidenciarios;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="cdpessoa"         
     *         
     */
    public com.gilbertoca.gfi.geral.model.Pessoa getGerPessoa() {
        return this.gerPessoa;
    }

    public void setGerPessoa(com.gilbertoca.gfi.geral.model.Pessoa gerPessoa) {
        this.gerPessoa = gerPessoa;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="cdtpgrauparentesco"         
     *         
     */
    public com.gilbertoca.gfi.geral.model.TpGrauParentesco getGerTpgrauparentesco() {
        return this.gerTpgrauparentesco;
    }

    public void setGerTpgrauparentesco(com.gilbertoca.gfi.geral.model.TpGrauParentesco gerTpgrauparentesco) {
        this.gerTpgrauparentesco = gerTpgrauparentesco;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="cdtpmotivoiniciodependencia"         
     *         
     */
    public com.gilbertoca.gfi.geral.model.TpMotivoInicioDependencia getGerTpmotivoiniciodependencia() {
        return this.gerTpmotivoiniciodependencia;
    }

    public void setGerTpmotivoiniciodependencia(com.gilbertoca.gfi.geral.model.TpMotivoInicioDependencia gerTpmotivoiniciodependencia) {
        this.gerTpmotivoiniciodependencia = gerTpmotivoiniciodependencia;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="cdtpdependencia"         
     *         
     */
    public com.gilbertoca.gfi.geral.model.TpDependencia getGerTpdependencia() {
        return this.gerTpdependencia;
    }

    public void setGerTpdependencia(com.gilbertoca.gfi.geral.model.TpDependencia gerTpdependencia) {
        this.gerTpdependencia = gerTpdependencia;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="cdtpmotivofimdependencia"         
     *         
     */
    public com.gilbertoca.gfi.geral.model.TpMotivoFimDependencia getGerTpmotivofimdependencia() {
        return this.gerTpmotivofimdependencia;
    }

    public void setGerTpmotivofimdependencia(com.gilbertoca.gfi.geral.model.TpMotivoFimDependencia gerTpmotivofimdependencia) {
        this.gerTpmotivofimdependencia = gerTpmotivofimdependencia;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("cdpessoadependencia", getCdpessoadependencia())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PessoaDependencia) ) return false;
        PessoaDependencia castOther = (PessoaDependencia) other;
        return new EqualsBuilder()
            .append(this.getCdpessoadependencia(), castOther.getCdpessoadependencia())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCdpessoadependencia())
            .toHashCode();
    }

}
