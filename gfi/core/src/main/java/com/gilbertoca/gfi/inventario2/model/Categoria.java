package com.gilbertoca.gfi.inventario2.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Categoria implements Serializable {
    private Integer cdCategoria;
    private String nomeCategoria;
    private String descricaoCategoria;
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
    public Categoria() {   }

    /** minimal constructor */
    public Categoria(Integer cdCategoria, Set produtos) {
        this.cdCategoria = cdCategoria;
        this.produtos = produtos;
    }

    public Integer getCdCategoria() {return this.cdCategoria;}
    public void setCdCategoria(Integer cdCategoria) {this.cdCategoria = cdCategoria;}
    public String getNomeCategoria() {return this.nomeCategoria;}
    public void setNomeCategoria(String nomeCategoria) {this.nomeCategoria = nomeCategoria;}
    public String getDescricaoCategoria() {return this.descricaoCategoria;}
    public void setDescricaoCategoria(String descricaoCategoria) {this.descricaoCategoria = descricaoCategoria;}
    public Date getDtCadastro() {return dtCadastro;}
    public void setDtCadastro(Date dtCadastro) {this.dtCadastro = dtCadastro;}
    public Set getProdutos() {return this.produtos;}
    public void setProdutos(Set produtos) {this.produtos = produtos;}
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
