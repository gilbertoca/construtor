package com.gilbertoca.gfi.inventario2.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *        @hibernate.class table="construtor.estoque_unidade_medida"
 *
 */
public class UnidadeMedida implements Serializable {

    private String descricaoUnidade;
    private String cdUnidadeMedida;
    /** 
     * TODO : Utilizar o novo mecanismo que hibernate3 fornece para este
     * tipo de situação, por exemplo, removendo este atributo.
     * Usado para determinar se instancia é transient or detached 
     */
    private int version = -1;

    public UnidadeMedida() {
    }

    public UnidadeMedida(String cdUnidadeMedida, String descricaoUnidade) {
        this.cdUnidadeMedida = cdUnidadeMedida;
        this.descricaoUnidade = descricaoUnidade;
    }

    /**
     * @hibernate.property column="descricao_unidade" length="30"
     *
     */
    public java.lang.String getDescricaoUnidade() {
        return descricaoUnidade;
    }

    /**
     * @hibernate.id generator-class="assigned" unsaved-value="null"
     *  type="java.lang.String" column="cd_unidade_medida" length="4"
     *
     */
    public java.lang.String getCdUnidadeMedida() {
        return cdUnidadeMedida;
    }

    public void setDescricaoUnidade(String descricaoUnidade) {
        this.descricaoUnidade = descricaoUnidade;
    }

    public void setCdUnidadeMedida(String cdUnidadeMedida) {
        this.cdUnidadeMedida = cdUnidadeMedida;
    }

    /**
     * @return Returns the updated timestamp.
     * @hibernate.version unsaved-value = "negative" 
     */
    public int getVersion() {
        return version;
    }

    /**
     * @param updated The updated version to set.
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("cdUnidadeMedida", this.cdUnidadeMedida).append("descricaoUnidade", this.descricaoUnidade).toString();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-2100871471, -590516841).append(this.cdUnidadeMedida).append(
                this.descricaoUnidade).toHashCode();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof UnidadeMedida)) {
            return false;
        }
        UnidadeMedida rhs = (UnidadeMedida) object;
        return new EqualsBuilder().append(
                this.cdUnidadeMedida, rhs.cdUnidadeMedida).append(this.descricaoUnidade,
                rhs.descricaoUnidade).isEquals();
    }
}
