package com.gilbertoca.model.financeiro;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="construtor.financeiro_centro_custo"
 *     
*/
public class CentroCusto implements Serializable {

    /** identifier field */
    private Integer cdCentroCusto;

    /** nullable persistent field */
    private String descricaoCentroCusto;

    /** nullable persistent field */
    private String codigoTexto;

    /** full constructor */
    public CentroCusto(Integer cdCentroCusto, String descricaoCentroCusto, String codigoTexto) {
        this.cdCentroCusto = cdCentroCusto;
        this.descricaoCentroCusto = descricaoCentroCusto;
        this.codigoTexto = codigoTexto;
    }

    /** default constructor */
    public CentroCusto() {
    }

    /** minimal constructor */
    public CentroCusto(Integer cdCentroCusto) {
        this.cdCentroCusto = cdCentroCusto;
    }

    /**
     * @hibernate.id generator-class="sequence" column="cd_centro_custo" unsaved-value = "null"
     * @hibernate.generator-param name = "sequence" value = "construtor.financeiro_centro_custo_sequence"
     * @return Integer
     */
    public Integer getCdCentroCusto() {
        return this.cdCentroCusto;
    }

    public void setCdCentroCusto(Integer cdCentroCusto) {
        this.cdCentroCusto = cdCentroCusto;
    }

    /** 
     *            @hibernate.property
     *             column="descricao_centro_custo"
     *             length="100"
     *         
     */
    public String getDescricaoCentroCusto() {
        return this.descricaoCentroCusto;
    }

    public void setDescricaoCentroCusto(String descricaoCentroCusto) {
        this.descricaoCentroCusto = descricaoCentroCusto;
    }

    /** 
     *            @hibernate.property
     *             column="codigo_texto"
     *             length="7"
     *         
     */
    public String getCodigoTexto() {
        return this.codigoTexto;
    }

    public void setCodigoTexto(String codigoTexto) {
        this.codigoTexto = codigoTexto;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("cdCentroCusto", getCdCentroCusto())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof CentroCusto) ) return false;
        CentroCusto castOther = (CentroCusto) other;
        return new EqualsBuilder()
            .append(this.getCdCentroCusto(), castOther.getCdCentroCusto())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCdCentroCusto())
            .toHashCode();
    }

}
