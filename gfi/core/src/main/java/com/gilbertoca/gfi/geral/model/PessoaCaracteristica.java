package com.gilbertoca.gfi.geral.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @hibernate.class table="ger.ger_pessoacaracteristica"
 * 
 */
public class PessoaCaracteristica implements Serializable {

    /** identifier field */
    private Integer cdpessoa;

    /** nullable persistent field */
    private Boolean fldoadorsangue;

    /** nullable persistent field */
    private Pessoa gerPessoa;

    /** persistent field */
    private TpSanguineo gerTpsanguineo;

    /** persistent field */
    private Nacionalidade gerTpnacionalidade;

    /** persistent field */
    private TpReligiao gerTpreligiao;

    /** persistent field */
    private TpDeficiencia gerTpdeficiencia;

    /** persistent field */
    private TpRaca gerTpraca;

    /** default constructor */
    public PessoaCaracteristica() {
    }

    /**
     * @hibernate.id generator-class="assigned" type="java.lang.Integer"
     *               column="cdpessoa"
     * 
     */
    public Integer getCdpessoa() {
	return this.cdpessoa;
    }

    public void setCdpessoa(Integer cdpessoa) {
	this.cdpessoa = cdpessoa;
    }

    /**
     * @hibernate.property column="fldoadorsangue" length="1"
     * 
     */
    public Boolean getFldoadorsangue() {
	return this.fldoadorsangue;
    }

    public void setFldoadorsangue(Boolean fldoadorsangue) {
	this.fldoadorsangue = fldoadorsangue;
    }

    /**
     * @hibernate.one-to-one class="com.gilbertoca.gfi.ger.model.Pessoa"
     *                       outer-join="auto" constrained="true"
     * 
     */
    public Pessoa getGerPessoa() {
	return this.gerPessoa;
    }

    public void setGerPessoa(Pessoa gerPessoa) {
	this.gerPessoa = gerPessoa;
    }

    /**
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="cdtpsanguineo"
     * 
     */
    public TpSanguineo getGerTpsanguineo() {
	return this.gerTpsanguineo;
    }

    public void setGerTpsanguineo(TpSanguineo gerTpsanguineo) {
	this.gerTpsanguineo = gerTpsanguineo;
    }

    /**
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="cdtpnacionalidade"
     * 
     */
    public Nacionalidade getGerTpnacionalidade() {
	return this.gerTpnacionalidade;
    }

    public void setGerTpnacionalidade(Nacionalidade gerTpnacionalidade) {
	this.gerTpnacionalidade = gerTpnacionalidade;
    }

    /**
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="cdtpreligiao"
     * 
     */
    public TpReligiao getGerTpreligiao() {
	return this.gerTpreligiao;
    }

    public void setGerTpreligiao(TpReligiao gerTpreligiao) {
	this.gerTpreligiao = gerTpreligiao;
    }

    /**
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="cdtpdeficiencia"
     * 
     */
    public TpDeficiencia getGerTpdeficiencia() {
	return this.gerTpdeficiencia;
    }

    public void setGerTpdeficiencia(TpDeficiencia gerTpdeficiencia) {
	this.gerTpdeficiencia = gerTpdeficiencia;
    }

    /**
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="cdtpraca"
     * 
     */
    public TpRaca getGerTpraca() {
	return this.gerTpraca;
    }

    public void setGerTpraca(TpRaca gerTpraca) {
	this.gerTpraca = gerTpraca;
    }

    public String toString() {
	return new ToStringBuilder(this).append("cdpessoa", getCdpessoa())
		.toString();
    }

    public boolean equals(Object other) {
	if (!(other instanceof PessoaCaracteristica))
	    return false;
	PessoaCaracteristica castOther = (PessoaCaracteristica) other;
	return new EqualsBuilder().append(this.getCdpessoa(),
		castOther.getCdpessoa()).isEquals();
    }

    public int hashCode() {
	return new HashCodeBuilder().append(getCdpessoa()).toHashCode();
    }

}
