package com.gilbertoca.gfi.model.inventario2;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/** 
 *        @hibernate.class
 *         table="construtor.estoque_produto"
 *     
*/
public class Produto implements Serializable {
    private Integer cdProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private Integer cdCategoria;
    private Categoria categoria;
    private Date dtCadastro = new Date();
    private Set items;

    /** full constructor */
    public Produto(Integer cdProduto, String nomeProduto, String descricaoProduto, Categoria categoria, Set items) {
        this.cdProduto = cdProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.categoria = categoria;
        this.items = items;
    }

    /** default constructor */
    public Produto() {
    }

    /** minimal constructor */
    public Produto(Integer cdProduto, String nomeProduto, Categoria categoria, Set items) {
        this.cdProduto = cdProduto;
        this.nomeProduto = nomeProduto;
        this.categoria = categoria;
        this.items = items;
    }

    /**
     * @hibernate.id generator-class="sequence" column="cd_produto" unsaved-value = "null"
     * @hibernate.generator-param name = "sequence" value = "construtor.estoque_produto_sequence"
     * @return Integer
     */
    public Integer getCdProduto() {
        return this.cdProduto;
    }

    public void setCdProduto(Integer cdProduto) {
        this.cdProduto = cdProduto;
    }

    /** 
     * @hibernate.property  column="nome_produto" 
     * length="80" not-null="true"
     */
    public String getNomeProduto() {
        return this.nomeProduto;
    }

    /**
     * @param nomeProduto
     * @spring.validator type="required"
     */
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
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
     *            @hibernate.property
     *             column="descricao_produto"
     *             length="255"
     *         
     */
    public String getDescricaoProduto() {
        return this.descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    /**
     * @return Returns the cdCategoria.
     * @hibernate.property column="cd_categoria"
     *
     */
    public Integer getCdCategoria() {
        return cdCategoria;
    }
    /**
     * @param cdCategoria The cdCategoria to set.
     */
    public void setCdCategoria(Integer cdCategoria) {
        this.cdCategoria = cdCategoria;
    }
    /** 
     * @hibernate.many-to-one
     *  insert="false" update="false" cascade="none" column="cd_categoria"
     *  outer-join="true"
     * @return a populated categoria object (based on the cdCategoria)
     */
    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="delete"
     *            @hibernate.collection-key
     *             column="cd_produto"
     *            @hibernate.collection-one-to-many
     *             class="org.appfuse.model.estoque.Item"
     *         
     */
    public Set getItems() {
        return this.items;
    }

    public void setItems(Set items) {
        this.items = items;
    }

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Produto)) {
			return false;
		}
		Produto rhs = (Produto) object;
		return new EqualsBuilder().append(
				this.nomeProduto, rhs.nomeProduto).append(this.cdProduto,
				rhs.cdProduto).append(this.dtCadastro, rhs.dtCadastro).append(this.descricaoProduto,
				rhs.descricaoProduto).append(this.cdCategoria, rhs.cdCategoria)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1008668341, 1343637879).append(this.nomeProduto).append(
				this.cdProduto).append(this.items).append(this.dtCadastro)
				.append(this.descricaoProduto).append(this.cdCategoria).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("descricaoProduto", this.descricaoProduto).append(
						"cdCategoria", this.cdCategoria).append("nomeProduto",
						this.nomeProduto).append("cdProduto",this.cdProduto).append("dtCadastro", this.dtCadastro)
				.toString();
	}
}
