package com.gilbertoca.model.financeiro;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *     
*/
public class CaixaConta implements Serializable {

    private Integer cdCaixaConta;
    private String nomeCaixaConta;
    private String marcador;
    private String tipoConta;
    private Boolean flPermiteLancamentos = new Boolean(false);

    /** full constructor */
    public CaixaConta(Integer cdCaixaConta, String nomeCaixaConta, String marcador, String tipoConta, Boolean flPermiteLancamentos) {
        this.cdCaixaConta = cdCaixaConta;
        this.nomeCaixaConta = nomeCaixaConta;
        this.marcador = marcador;
        this.tipoConta = tipoConta;
        this.flPermiteLancamentos = flPermiteLancamentos;
    }

    /** default constructor */
    public CaixaConta() {
    }

    /** minimal constructor */
    public CaixaConta(Integer cdCaixaConta) {
        this.cdCaixaConta = cdCaixaConta;
    }

    public Integer getCdCaixaConta() {
        return this.cdCaixaConta;
    }

    public void setCdCaixaConta(Integer cdCaixaConta) {
        this.cdCaixaConta = cdCaixaConta;
    }

    public String getNomeCaixaConta() {
        return this.nomeCaixaConta;
    }

    public void setNomeCaixaConta(String nomeCaixaConta) {
        this.nomeCaixaConta = nomeCaixaConta;
    }

    public String getMarcador() {
        return this.marcador;
    }

    public void setMarcador(String marcador) {
        this.marcador = marcador;
    }

    public String getTipoConta() {
        return this.tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Boolean getFlPermiteLancamentos() {
        return this.flPermiteLancamentos;
    }

    public void setFlPermiteLancamentos(Boolean flPermiteLancamentos) {
        this.flPermiteLancamentos = flPermiteLancamentos;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("cdCaixaConta", getCdCaixaConta())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof CaixaConta) ) return false;
        CaixaConta castOther = (CaixaConta) other;
        return new EqualsBuilder()
            .append(this.getCdCaixaConta(), castOther.getCdCaixaConta())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCdCaixaConta())
            .toHashCode();
    }

}