package com.gilbertoca.gfi.ger.model;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="ger.ger_escolaridade"
 *     
*/
public class Escolaridade implements Serializable {

    /** identifier field */
    private Short cdescolaridade;

    /** nullable persistent field */
    private String dcescolaridade;

    /** persistent field */
    private Set gerPessoacaracteristicas;

    /** full constructor */
    public Escolaridade(Short cdescolaridade, String dcescolaridade, Set gerPessoacaracteristicas) {
        this.cdescolaridade = cdescolaridade;
        this.dcescolaridade = dcescolaridade;
        this.gerPessoacaracteristicas = gerPessoacaracteristicas;
    }

    /** default constructor */
    public Escolaridade() {
    }

    /** minimal constructor */
    public Escolaridade(Short cdescolaridade, Set gerPessoacaracteristicas) {
        this.cdescolaridade = cdescolaridade;
        this.gerPessoacaracteristicas = gerPessoacaracteristicas;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Short"
     *             column="cdescolaridade"
     *         
     */
    public Short getCdescolaridade() {
        return this.cdescolaridade;
    }

    public void setCdescolaridade(Short cdescolaridade) {
        this.cdescolaridade = cdescolaridade;
    }

    /** 
     *            @hibernate.property
     *             column="dcescolaridade"
     *             length="40"
     *         
     */
    public String getDcescolaridade() {
        return this.dcescolaridade;
    }

    public void setDcescolaridade(String dcescolaridade) {
        this.dcescolaridade = dcescolaridade;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="cdescolaridade"
     *            @hibernate.collection-one-to-many
     *             class="com.gilbertoca.gfi.ger.model.PessoaCaracteristica"
     *         
     */
    public Set getGerPessoacaracteristicas() {
        return this.gerPessoacaracteristicas;
    }

    public void setGerPessoacaracteristicas(Set gerPessoacaracteristicas) {
        this.gerPessoacaracteristicas = gerPessoacaracteristicas;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("cdescolaridade", getCdescolaridade())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Escolaridade) ) return false;
        Escolaridade castOther = (Escolaridade) other;
        return new EqualsBuilder()
            .append(this.getCdescolaridade(), castOther.getCdescolaridade())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCdescolaridade())
            .toHashCode();
    }

}
