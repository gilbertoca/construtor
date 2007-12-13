package com.gilbertoca.gfi.model.ger;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="ger.ger_pessoacaracteristica"
 *     
*/
public class PessoaCaracteristica implements Serializable {

    /** identifier field */
    private Integer cdpessoa;

    /** nullable persistent field */
    private Boolean fldoadorsangue;

    /** nullable persistent field */
    private Integer cdnaturalidade;

    /** nullable persistent field */
    private com.gilbertoca.gfi.model.ger.Pessoa gerPessoa;

    /** persistent field */
    private com.gilbertoca.gfi.model.ger.TpSanguineo gerTpsanguineo;

    /** persistent field */
    private com.gilbertoca.gfi.model.ger.EstadoCivil gerEstadocivil;

    /** persistent field */
    private com.gilbertoca.gfi.model.ger.TpNacionalidade gerTpnacionalidade;

    /** persistent field */
    private com.gilbertoca.gfi.model.ger.TpReligiao gerTpreligiao;

    /** persistent field */
    private com.gilbertoca.gfi.model.ger.TpDeficiencia gerTpdeficiencia;

    /** persistent field */
    private com.gilbertoca.gfi.model.ger.Escolaridade gerEscolaridade;

    /** persistent field */
    private com.gilbertoca.gfi.model.ger.TpRaca gerTpraca;

    /** full constructor */
    public PessoaCaracteristica(Integer cdpessoa, Boolean fldoadorsangue, Integer cdnaturalidade, com.gilbertoca.gfi.model.ger.Pessoa gerPessoa, com.gilbertoca.gfi.model.ger.TpSanguineo gerTpsanguineo, com.gilbertoca.gfi.model.ger.EstadoCivil gerEstadocivil, com.gilbertoca.gfi.model.ger.TpNacionalidade gerTpnacionalidade, com.gilbertoca.gfi.model.ger.TpReligiao gerTpreligiao, com.gilbertoca.gfi.model.ger.TpDeficiencia gerTpdeficiencia, com.gilbertoca.gfi.model.ger.Escolaridade gerEscolaridade, com.gilbertoca.gfi.model.ger.TpRaca gerTpraca) {
        this.cdpessoa = cdpessoa;
        this.fldoadorsangue = fldoadorsangue;
        this.cdnaturalidade = cdnaturalidade;
        this.gerPessoa = gerPessoa;
        this.gerTpsanguineo = gerTpsanguineo;
        this.gerEstadocivil = gerEstadocivil;
        this.gerTpnacionalidade = gerTpnacionalidade;
        this.gerTpreligiao = gerTpreligiao;
        this.gerTpdeficiencia = gerTpdeficiencia;
        this.gerEscolaridade = gerEscolaridade;
        this.gerTpraca = gerTpraca;
    }

    /** default constructor */
    public PessoaCaracteristica() {
    }

    /** minimal constructor */
    public PessoaCaracteristica(Integer cdpessoa, com.gilbertoca.gfi.model.ger.TpSanguineo gerTpsanguineo, com.gilbertoca.gfi.model.ger.EstadoCivil gerEstadocivil, com.gilbertoca.gfi.model.ger.TpNacionalidade gerTpnacionalidade, com.gilbertoca.gfi.model.ger.TpReligiao gerTpreligiao, com.gilbertoca.gfi.model.ger.TpDeficiencia gerTpdeficiencia, com.gilbertoca.gfi.model.ger.Escolaridade gerEscolaridade, com.gilbertoca.gfi.model.ger.TpRaca gerTpraca) {
        this.cdpessoa = cdpessoa;
        this.gerTpsanguineo = gerTpsanguineo;
        this.gerEstadocivil = gerEstadocivil;
        this.gerTpnacionalidade = gerTpnacionalidade;
        this.gerTpreligiao = gerTpreligiao;
        this.gerTpdeficiencia = gerTpdeficiencia;
        this.gerEscolaridade = gerEscolaridade;
        this.gerTpraca = gerTpraca;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Integer"
     *             column="cdpessoa"
     *         
     */
    public Integer getCdpessoa() {
        return this.cdpessoa;
    }

    public void setCdpessoa(Integer cdpessoa) {
        this.cdpessoa = cdpessoa;
    }

    /** 
     *            @hibernate.property
     *             column="fldoadorsangue"
     *             length="1"
     *         
     */
    public Boolean getFldoadorsangue() {
        return this.fldoadorsangue;
    }

    public void setFldoadorsangue(Boolean fldoadorsangue) {
        this.fldoadorsangue = fldoadorsangue;
    }

    /** 
     *            @hibernate.property
     *             column="cdnaturalidade"
     *             length="4"
     *         
     */
    public Integer getCdnaturalidade() {
        return this.cdnaturalidade;
    }

    public void setCdnaturalidade(Integer cdnaturalidade) {
        this.cdnaturalidade = cdnaturalidade;
    }

    /** 
     *            @hibernate.one-to-one
     *             class="com.gilbertoca.gfi.model.ger.Pessoa"
     *             outer-join="auto"
     *             constrained="true"
     *         
     */
    public com.gilbertoca.gfi.model.ger.Pessoa getGerPessoa() {
        return this.gerPessoa;
    }

    public void setGerPessoa(com.gilbertoca.gfi.model.ger.Pessoa gerPessoa) {
        this.gerPessoa = gerPessoa;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="cdtpsanguineo"         
     *         
     */
    public com.gilbertoca.gfi.model.ger.TpSanguineo getGerTpsanguineo() {
        return this.gerTpsanguineo;
    }

    public void setGerTpsanguineo(com.gilbertoca.gfi.model.ger.TpSanguineo gerTpsanguineo) {
        this.gerTpsanguineo = gerTpsanguineo;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="cdestadocivil"         
     *         
     */
    public com.gilbertoca.gfi.model.ger.EstadoCivil getGerEstadocivil() {
        return this.gerEstadocivil;
    }

    public void setGerEstadocivil(com.gilbertoca.gfi.model.ger.EstadoCivil gerEstadocivil) {
        this.gerEstadocivil = gerEstadocivil;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="cdtpnacionalidade"         
     *         
     */
    public com.gilbertoca.gfi.model.ger.TpNacionalidade getGerTpnacionalidade() {
        return this.gerTpnacionalidade;
    }

    public void setGerTpnacionalidade(com.gilbertoca.gfi.model.ger.TpNacionalidade gerTpnacionalidade) {
        this.gerTpnacionalidade = gerTpnacionalidade;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="cdtpreligiao"         
     *         
     */
    public com.gilbertoca.gfi.model.ger.TpReligiao getGerTpreligiao() {
        return this.gerTpreligiao;
    }

    public void setGerTpreligiao(com.gilbertoca.gfi.model.ger.TpReligiao gerTpreligiao) {
        this.gerTpreligiao = gerTpreligiao;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="cdtpdeficiencia"         
     *         
     */
    public com.gilbertoca.gfi.model.ger.TpDeficiencia getGerTpdeficiencia() {
        return this.gerTpdeficiencia;
    }

    public void setGerTpdeficiencia(com.gilbertoca.gfi.model.ger.TpDeficiencia gerTpdeficiencia) {
        this.gerTpdeficiencia = gerTpdeficiencia;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="cdescolaridade"         
     *         
     */
    public com.gilbertoca.gfi.model.ger.Escolaridade getGerEscolaridade() {
        return this.gerEscolaridade;
    }

    public void setGerEscolaridade(com.gilbertoca.gfi.model.ger.Escolaridade gerEscolaridade) {
        this.gerEscolaridade = gerEscolaridade;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="cdtpraca"         
     *         
     */
    public com.gilbertoca.gfi.model.ger.TpRaca getGerTpraca() {
        return this.gerTpraca;
    }

    public void setGerTpraca(com.gilbertoca.gfi.model.ger.TpRaca gerTpraca) {
        this.gerTpraca = gerTpraca;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("cdpessoa", getCdpessoa())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PessoaCaracteristica) ) return false;
        PessoaCaracteristica castOther = (PessoaCaracteristica) other;
        return new EqualsBuilder()
            .append(this.getCdpessoa(), castOther.getCdpessoa())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCdpessoa())
            .toHashCode();
    }

}
