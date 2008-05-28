package com.gilbertoca.gfi.inventario2.model;

import java.io.Serializable;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder; 
import org.apache.commons.lang.builder.ToStringStyle;
/**
 *        @hibernate.class
 *         table="construtor.estoque_localizacao_secao"
 *
*/
public class LocalizacaoSecao implements Serializable{
    private String descricaoLocalizacaoSecao;
    private String cdLocalizacaoSecao;
    /** 
     * TODO : Utilizar o novo mecanismo que hibernate3 fornece para este
     * tipo de situação, por exemplo, removendo este atributo.
     * Usado para determinar se instancia � transient or detached 
     */
    private int version = -1;

    public LocalizacaoSecao() {
    }

    /**
     *            @hibernate.property
     *             column="descricao_localizacao_secao"
     *             length="50"
     *
     */
    public java.lang.String getDescricaoLocalizacaoSecao() {
        return descricaoLocalizacaoSecao;
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
    *            @hibernate.id
    *             generator-class="assigned" unsaved-value="none"
    *             type="java.lang.String"
    *             column="cd_localizacao_secao"
    *             length="10"
    *
    */
    public java.lang.String getCdLocalizacaoSecao() {
        return cdLocalizacaoSecao;
    }

     public void setDescricaoLocalizacaoSecao(java.lang.String value) {
        descricaoLocalizacaoSecao = value;
    }

    public void setCdLocalizacaoSecao(java.lang.String value) {
        cdLocalizacaoSecao = value;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof LocalizacaoSecao)) {
            return false;
        }
        LocalizacaoSecao rhs = (LocalizacaoSecao) object;
        return new EqualsBuilder().append(this.cdLocalizacaoSecao,
                rhs.cdLocalizacaoSecao).append(this.descricaoLocalizacaoSecao,
                rhs.descricaoLocalizacaoSecao).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1028119897, 1854625015).append(
                this.cdLocalizacaoSecao).append(this.descricaoLocalizacaoSecao)
                .toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("cdLocalizacaoSecao", this.cdLocalizacaoSecao)
                .append(
                        "descricaoLocalizacaoSecao",
                        this.descricaoLocalizacaoSecao).toString();
    }
}
