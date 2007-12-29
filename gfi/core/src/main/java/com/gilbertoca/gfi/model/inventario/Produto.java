package com.gilbertoca.gfi.model.inventario;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {
    @Id
    @TableGenerator(
        name="produto_id_gerador",
        table="id_gerador",        
        pkColumnName="id_nome",        
        valueColumnName="id_valor",
        allocationSize=1
    )
    @GeneratedValue(strategy=GenerationType.TABLE, generator="produto_id_gerador")
    @Column(name = "cd_produto")
    private Integer cdProduto;
    
    @Column(name = "nome_produto", length = 80, nullable = false)
    private String nomeProduto;
    
    @Column(name = "descricao_produto", length = 255)
    private String descricaoProduto;
    
    @Column(name = "cd_categoria")
    private Integer cdCategoria;
    
    @ManyToOne
    @JoinColumn(name = "cd_categoria", insertable = false, updatable = false)
    private Categoria categoria;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_cadastro", nullable = false)
    private Date dtCadastro = new Date();
    
    @Version
    private Integer version;
    
    @OneToMany(targetEntity=Item.class, cascade=CascadeType.ALL, mappedBy="produto")
    private Set<Item> items = new HashSet<Item>();

    public Produto(String nomeProduto, String descricaoProduto, Integer cdCategoria) {
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.cdCategoria = cdCategoria;
    }

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

    public Integer getCdProduto() {
        return this.cdProduto;
    }

    public void setCdProduto(Integer cdProduto) {
        this.cdProduto = cdProduto;
    }

    public String getNomeProduto() {
        return this.nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public String getDescricaoProduto() {
        return this.descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public Integer getCdCategoria() {
        return cdCategoria;
    }

    public void setCdCategoria(Integer cdCategoria) {
        this.cdCategoria = cdCategoria;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<Item> getItems() {
        return this.items;
    }

    public void setItems(Set<Item> items) {
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
                rhs.descricaoProduto).append(this.cdCategoria, rhs.cdCategoria).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1008668341, 1343637879).append(this.nomeProduto).append(
                this.cdProduto).append(this.items).append(this.dtCadastro).append(this.descricaoProduto).append(this.cdCategoria).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("descricaoProduto", this.descricaoProduto)
                .append("cdCategoria", this.cdCategoria)
                //O atributo categoria pode receber um valor nullo.
                //.append("categoria", this.categoria.toString())                
                .append("nomeProduto",this.nomeProduto)
                .append("cdProduto", this.cdProduto)
                .append("dtCadastro", this.dtCadastro).toString();
    }
}
