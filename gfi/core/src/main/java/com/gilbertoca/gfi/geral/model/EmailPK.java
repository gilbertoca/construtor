package com.gilbertoca.gfi.geral.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class EmailPK implements Serializable {

    /** identifier field */
    private String email;

    /** identifier field */
    private Integer cdpessoa;

    /** full constructor */
    public EmailPK(String email, Integer cdpessoa) {
        this.email = email;
        this.cdpessoa = cdpessoa;
    }

    /** default constructor */
    public EmailPK() {
    }

    /** 
     *                @hibernate.property
     *                 column="email"
     *             
     */
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /** 
     *                @hibernate.property
     *                 column="cdpessoa"
     *             
     */
    public Integer getCdpessoa() {
        return this.cdpessoa;
    }

    public void setCdpessoa(Integer cdpessoa) {
        this.cdpessoa = cdpessoa;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("email", getEmail())
            .append("cdpessoa", getCdpessoa())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof EmailPK) ) return false;
        EmailPK castOther = (EmailPK) other;
        return new EqualsBuilder()
            .append(this.getEmail(), castOther.getEmail())
            .append(this.getCdpessoa(), castOther.getCdpessoa())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getEmail())
            .append(getCdpessoa())
            .toHashCode();
    }

}
