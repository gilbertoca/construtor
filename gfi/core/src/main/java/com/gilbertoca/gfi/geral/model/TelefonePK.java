package com.gilbertoca.gfi.geral.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class TelefonePK implements Serializable {

    /** identifier field */
    private Integer cdpessoa;

    /** identifier field */
    private String telefone;

    /** full constructor */
    public TelefonePK(Integer cdpessoa, String telefone) {
        this.cdpessoa = cdpessoa;
        this.telefone = telefone;
    }

    /** default constructor */
    public TelefonePK() {
    }

    /** 
     *                @hibernate.property
     *                 column="cdpessoa"
     *                 length="4"
     *             
     */
    public Integer getCdpessoa() {
        return this.cdpessoa;
    }

    public void setCdpessoa(Integer cdpessoa) {
        this.cdpessoa = cdpessoa;
    }

    /** 
     *                @hibernate.property
     *                 column="telefone"
     *                 length="30"
     *             
     */
    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("cdpessoa", getCdpessoa())
            .append("telefone", getTelefone())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof TelefonePK) ) return false;
        TelefonePK castOther = (TelefonePK) other;
        return new EqualsBuilder()
            .append(this.getCdpessoa(), castOther.getCdpessoa())
            .append(this.getTelefone(), castOther.getTelefone())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCdpessoa())
            .append(getTelefone())
            .toHashCode();
    }

}
