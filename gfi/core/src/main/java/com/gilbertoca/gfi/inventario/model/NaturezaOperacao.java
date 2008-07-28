package com.gilbertoca.gfi.inventario.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/** 
 *        @hibernate.class
 *         table="construtor.estoque_natureza_operacao"
 *     
*/
public class NaturezaOperacao implements Serializable {
    private String cdNaturezaOperacao;
    private String nomeNaturezaOperacao;
    private String descricaoNaturezaOPeracao;
    private Boolean flBaixaEstoque = new Boolean(false);
    private Boolean flEfetuaLancamentos = new Boolean(false);
    private Date dtCadastro = new Date();
    /** 
     * TODO : Utilizar o novo mecanismo que hibernate3 fornece para este
     * tipo de situação, por exemplo, removendo este atributo.
     * Usado para determinar se instancia é transient or detached 
     */
    private int version = -1;

    /** full constructor */
    public NaturezaOperacao(String cdNaturezaOperacao, String nomeNaturezaOperacao, Boolean flBaixaEstoque, Boolean flEfetuaLancamentos) {
        this.cdNaturezaOperacao = cdNaturezaOperacao;
        this.nomeNaturezaOperacao = nomeNaturezaOperacao;
        this.flBaixaEstoque = flBaixaEstoque;
        this.flEfetuaLancamentos = flEfetuaLancamentos;
    }

    /** default constructor */
    public NaturezaOperacao() {
    }

    /** minimal constructor */
    public NaturezaOperacao(String cdNaturezaOperacao, Boolean flEfetuaLancamentos) {
        this.cdNaturezaOperacao = cdNaturezaOperacao;
        this.flEfetuaLancamentos = flEfetuaLancamentos;
    }

    /** 
    * @hibernate.id generator-class="assigned" unsaved-value="null"
    *  type="java.lang.String" column="cd_natureza_operacao" length="15"
    */
    public String getCdNaturezaOperacao() {
        return this.cdNaturezaOperacao;
    }

    public void setCdNaturezaOperacao(String cdNaturezaOperacao) {
        this.cdNaturezaOperacao = cdNaturezaOperacao;
    }

    /** 
     * @hibernate.property  column="nome_natureza_operacao" length="100"
     */
    public String getNomeNaturezaOperacao() {
        return this.nomeNaturezaOperacao;
    }

    public void setNomeNaturezaOperacao(String nomeNaturezaOperacao) {
        this.nomeNaturezaOperacao = nomeNaturezaOperacao;
    }
    /** 
     * @hibernate.property  column="descricao_natureza_operacao" length="100"
     */
    public String getDescricaoNaturezaOperacao() {
        return this.descricaoNaturezaOPeracao;
    }

    public void setDescricaoNaturezaOperacao(String descricaoNaturezaOperacao) {
        this.descricaoNaturezaOPeracao = descricaoNaturezaOperacao;
    }

    /** 
     *  @hibernate.property  column="fl_baixa_estoque" length="1"
     *         
     */
    public Boolean getFlBaixaEstoque() {
        return this.flBaixaEstoque;
    }

    public void setFlBaixaEstoque(Boolean flBaixaEstoque) {
        this.flBaixaEstoque = flBaixaEstoque;
    }

    /**
     * @hibernate.property column="dt_cadastro" type = "date" not-null="true"
     */
    public Date getDtCadastro() {
        return dtCadastro;
    }

    /**
     * @param dtCadastro The dtCadastro to set.
     */
    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }
    
    /** 
     *  @hibernate.property  column="fl_efetua_lancamentos" length="1"
     *         
     */
    public Boolean getFlEfetuaLancamentos() {
        return this.flEfetuaLancamentos;
    }

    public void setFlEfetuaLancamentos(Boolean flEfetuaLancamentos) {
        this.flEfetuaLancamentos = flEfetuaLancamentos;
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
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof NaturezaOperacao)) {
            return false;
        }
        NaturezaOperacao rhs = (NaturezaOperacao) object;
        return new EqualsBuilder().append(this.nomeNaturezaOperacao,
                rhs.nomeNaturezaOperacao).append(this.dtCadastro,
                rhs.dtCadastro).append(this.flEfetuaLancamentos,
                rhs.flEfetuaLancamentos).append(this.flBaixaEstoque,
                rhs.flBaixaEstoque).append(this.cdNaturezaOperacao,
                rhs.cdNaturezaOperacao).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1772832815, 1657213191).append(
                this.nomeNaturezaOperacao).append(this.dtCadastro).append(
                this.flEfetuaLancamentos).append(this.flBaixaEstoque).append(
                this.cdNaturezaOperacao).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("nomeNaturezaOperacao", this.nomeNaturezaOperacao)
                .append("flEfetuaLancamentos", this.flEfetuaLancamentos)
                .append("dtCadastro", this.dtCadastro).append(
                        "cdNaturezaOperacao", this.cdNaturezaOperacao).append(
                        "flBaixaEstoque", this.flBaixaEstoque).toString();
    }
}
