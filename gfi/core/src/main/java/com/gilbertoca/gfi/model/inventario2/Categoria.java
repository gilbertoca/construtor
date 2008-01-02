package com.gilbertoca.gfi.model.inventario2;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="construtor.estoque_categoria"
 *     
*/
public class Categoria implements Serializable {

    /** identifier field */
    private Integer cdCategoria;

    /** nullable persistent field */
    private String nomeCategoria;

    /** nullable persistent field */
    private String descricaoCategoria;

    /** persistent field */
    private Set produtos;

    private Date dtCadastro = new Date();

    /** full constructor */
    public Categoria(Integer cdCategoria, String nomeCategoria, String descricaoCategoria, Set produtos) {
        this.cdCategoria = cdCategoria;
        this.nomeCategoria = nomeCategoria;
        this.descricaoCategoria = descricaoCategoria;
        this.produtos = produtos;
    }

    /** default constructor */
    public Categoria() {
    }

    /** minimal constructor */
    public Categoria(Integer cdCategoria, Set produtos) {
        this.cdCategoria = cdCategoria;
        this.produtos = produtos;
    }

    /**
     * @hibernate.id generator-class="sequence" column="cd_categoria" unsaved-value = "null"
     * @hibernate.generator-param name = "sequence" value = "construtor.estoque_categoria_sequence"
     * @return Integer
     */
    public Integer getCdCategoria() {
        return this.cdCategoria;
    }

    public void setCdCategoria(Integer cdCategoria) {
        this.cdCategoria = cdCategoria;
    }

    /** 
     *            @hibernate.property
     *             column="nome_categoria"
     *             length="80"
     *         
     */
    public String getNomeCategoria() {
        return this.nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    /** 
     *            @hibernate.property
     *             column="descricao_categoria"
     *             length="255"
     *         
     */
    public String getDescricaoCategoria() {
        return this.descricaoCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
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
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="all-delete-orphan"
     *            @hibernate.collection-key
     *             column="cd_categoria"
     *            @hibernate.collection-one-to-many
     *             class="org.appfuse.model.estoque.Produto"
     *         
     */
    public Set getProdutos() {
        return this.produtos;
    }

    public void setProdutos(Set produtos) {
        this.produtos = produtos;
    }

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Categoria)) {
			return false;
		}
		Categoria rhs = (Categoria) object;
		return new EqualsBuilder().appendSuper(super.equals(object)).append(
				this.produtos, rhs.produtos).append(this.nomeCategoria,
				rhs.nomeCategoria).append(this.descricaoCategoria,
				rhs.descricaoCategoria).append(this.cdCategoria,
				rhs.cdCategoria).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1968403919, 1474371617).appendSuper(
				super.hashCode()).append(this.nomeCategoria).append(this.descricaoCategoria).append(
				this.cdCategoria).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("nomeCategoria",
				this.nomeCategoria).append("descricaoCategoria",
				this.descricaoCategoria).append("cdCategoria",
				this.cdCategoria).toString();
	}
}
