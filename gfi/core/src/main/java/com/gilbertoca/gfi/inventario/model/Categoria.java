package com.gilbertoca.gfi.inventario.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer cdCategoria;
    private String nomeCategoria;
    private String descricaoCategoria;
    private Timestamp dtCadastro = new Timestamp(new Date().getTime());
    private int version = -1;
    private Set<Produto> produtos;

    /** default constructor */
    public Categoria() {   }
    
	public Categoria(Integer cdCategoria) {
		this.cdCategoria = cdCategoria;
	}

	public Categoria(Integer cdCategoria, String nomeCategoria, String descricaoCategoria) {
		this.cdCategoria = cdCategoria;
		this.nomeCategoria = nomeCategoria;
		this.descricaoCategoria = descricaoCategoria;
	}


	public Integer getCdCategoria() {return this.cdCategoria;}
    public void setCdCategoria(Integer cdCategoria) {this.cdCategoria = cdCategoria;}
    public String getNomeCategoria() {return this.nomeCategoria;}
    public void setNomeCategoria(String nomeCategoria) {this.nomeCategoria = nomeCategoria;}
    public String getDescricaoCategoria() {return this.descricaoCategoria;}
    public void setDescricaoCategoria(String descricaoCategoria) {this.descricaoCategoria = descricaoCategoria;}
    public Timestamp getDtCadastro() {return dtCadastro;}
    public void setDtCadastro(Timestamp dtCadastro) {this.dtCadastro = dtCadastro;}
    public int getVersion() {return this.version;}
    public void setVersion(int version) {this.version = version;}
    public Set<Produto> getProdutos() {return this.produtos;}
    public void setProdutos(Set<Produto> produtos) {this.produtos = produtos;}
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
            if (!(object instanceof Categoria)) {
                    return false;
            }
            Categoria rhs = (Categoria) object;
            return new EqualsBuilder().appendSuper(super.equals(object))
            		.append(this.cdCategoria, rhs.cdCategoria)
            		.append(this.nomeCategoria, rhs.nomeCategoria)
            		.append(this.descricaoCategoria, rhs.descricaoCategoria)
            		.append(this.dtCadastro, rhs.dtCadastro)
            		.append(this.version, rhs.version).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
            return new HashCodeBuilder(-1968403919, 1474371617).appendSuper(super.hashCode())
            .append(this.cdCategoria)
            .append(this.nomeCategoria)
            .append(this.descricaoCategoria)
            .append(this.version)
            .append(this.dtCadastro).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
            return new ToStringBuilder(this)
            .append("cdCategoria", this.cdCategoria)
            .append("nomeCategoria", this.nomeCategoria)
            .append("descricaoCategoria", this.descricaoCategoria)
            .append("dtCadastro", this.dtCadastro)
            .append("Versão", this.version).toString();
    }
}
