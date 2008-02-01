package com.gilbertoca.gfi.inventario.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity  
@Table(name="categoria")  
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="CategoriaGerador")
    @TableGenerator(name="CategoriaGerador", table="ID_GERADOR", pkColumnName="PK",
        valueColumnName="AID", pkColumnValue="categoria_id_gerador", allocationSize=1, initialValue=1)
    @Column(name="cd_categoria", columnDefinition="INTEGER")
    private Integer cdCategoria;

    @Column(name = "nome_categoria", length=80, nullable=false)
    private String nomeCategoria;

    @Column(name = "descricao_categoria")
    private String descricaoCategoria;

    @OneToMany(targetEntity=Produto.class, cascade=CascadeType.ALL, mappedBy="categoria")
    private Set<Produto> produtos = new HashSet<Produto>();
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_cadastro", nullable = false)
    private Date dtCadastro = new Date();

    public Categoria(String nomeCategoria, String descricaoCategoria) {
        this.nomeCategoria = nomeCategoria;
        this.descricaoCategoria = descricaoCategoria;
    }

    /** full constructor */
    public Categoria(Integer cdCategoria, String nomeCategoria, String descricaoCategoria, Set<Produto> produtos) {
        this.cdCategoria = cdCategoria;
        this.nomeCategoria = nomeCategoria;
        this.descricaoCategoria = descricaoCategoria;
        this.produtos = produtos;
    }

    /** default constructor */
    public Categoria() {
    }

    /** minimal constructor */
    public Categoria(Integer cdCategoria, Set<Produto> produtos) {
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

    public void setProdutos(Set<Produto> produtos) {
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
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("nomeCategoria",
				this.nomeCategoria).append("descricaoCategoria",
				this.descricaoCategoria).append("cdCategoria",
				this.cdCategoria).toString();
	}
}
