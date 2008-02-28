package com.gilbertoca.gfi.inventario2.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

public class Item implements Serializable {
    private String descricaoUnidade;
    private String cdUnidadeMedida;
    private int version = -1;

    public Item() {  }
    public Item(String cdUnidadeMedida, String descricaoUnidade) {
        this.cdUnidadeMedida = cdUnidadeMedida;
        this.descricaoUnidade = descricaoUnidade;
    }
    public String getDescricaoUnidade() {return this.descricaoUnidade;}
    public String getCdUnidadeMedida() {return this.cdUnidadeMedida;}
    public void setDescricaoUnidade(String descricaoUnidade) {this.descricaoUnidade = descricaoUnidade;}
    public void setCdUnidadeMedida(String cdUnidadeMedida) {this.cdUnidadeMedida = cdUnidadeMedida;}
    public int getVersion() {return this.version;}
    public void setVersion(int version) {this.version = version;}

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("cdUnidadeMedida", this.cdUnidadeMedida).append("descricaoUnidade", this.descricaoUnidade).toString();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-2100871471, -590516841).append(this.cdUnidadeMedida).append(
                this.descricaoUnidade).toHashCode();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Item)) {
            return false;
        }
        Item rhs = (Item) object;
        return new EqualsBuilder().append(
                this.cdUnidadeMedida, rhs.cdUnidadeMedida).append(this.descricaoUnidade,
                rhs.descricaoUnidade).isEquals();
    }
}
