package com.gilbertoca.gfi.inventario2.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

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
    public Produto() { }

    /** minimal constructor */
    public Produto(Integer cdProduto, String nomeProduto, Categoria categoria, Set items) {
        this.cdProduto = cdProduto;
        this.nomeProduto = nomeProduto;
        this.categoria = categoria;
        this.items = items;
    }

    public Integer getCdProduto() {return this.cdProduto;}
    public void setCdProduto(Integer cdProduto) {this.cdProduto = cdProduto;}
    public String getNomeProduto() {return this.nomeProduto;}
    public void setNomeProduto(String nomeProduto) {this.nomeProduto = nomeProduto;}
    public Date getDtCadastro() {return dtCadastro;}
    public void setDtCadastro(Date dtCadastro) {this.dtCadastro = dtCadastro;}
    public String getDescricaoProduto() {return this.descricaoProduto;}
    public void setDescricaoProduto(String descricaoProduto) {this.descricaoProduto = descricaoProduto;}
    public Integer getCdCategoria() {return cdCategoria;}
    public void setCdCategoria(Integer cdCategoria) {this.cdCategoria = cdCategoria;}
    public Categoria getCategoria() {return this.categoria;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}
    public Set getItems() {return this.items;}
    public void setItems(Set items) {this.items = items;}

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
