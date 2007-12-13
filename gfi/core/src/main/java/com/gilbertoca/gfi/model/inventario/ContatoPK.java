package com.gilbertoca.gfi.model.inventario;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ContatoPK implements Serializable{
    private Integer cdContato;
    private Integer cdFornecedor;

    /** full constructor */
    public ContatoPK(Integer cdContato, Integer cdFornecedor) {
        this.cdContato = cdContato;
        this.cdFornecedor = cdFornecedor;
    }

    /** default constructor */
    public ContatoPK() {
    }

    /**
     *  @hibernate.property  
     *  column="cd_fornecedor"
     *
     */
    public Integer getCdFornecedor() {
        return this.cdFornecedor;
    }

    public void setCdFornecedor(Integer CdEntidade) {
        this.cdFornecedor = CdEntidade;
    }

    /**
     * @param Integer
     */
    public void setCdContato(Integer cdContato) {
        this.cdContato = cdContato;
    }

    /**
     * @hibernate.property  
     * column="cd_contato"
     * @return Integer
     */
    public Integer getCdContato() {
        return cdContato;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("cdFornecedor",
                this.cdFornecedor).append("cdContato", this.cdContato)
                .toString();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof ContatoPK)) {
            return false;
        }
        ContatoPK rhs = (ContatoPK) object;
        return new EqualsBuilder().append(
                this.cdContato, rhs.cdContato).append(this.cdFornecedor,
                rhs.cdFornecedor).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-512786585, -376248427).append(this.cdContato).append(
                this.cdFornecedor).toHashCode();
    }
}
