package com.gilbertoca.gfi.inventario.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

public class UnidadeMedida implements Serializable {
	private static final long serialVersionUID = 1L;
	private String descricaoUnidade;
    private String cdUnidadeMedida;
    private boolean flNovo;

    private int version = -1;

    public UnidadeMedida() {  }
    public UnidadeMedida(String cdUnidadeMedida, String descricaoUnidade) {
        this.cdUnidadeMedida = cdUnidadeMedida;
        this.descricaoUnidade = descricaoUnidade;
    }
    public UnidadeMedida(String cdUnidadeMedida) {
    	this.cdUnidadeMedida = cdUnidadeMedida;
	}
	public String getDescricaoUnidade() {return this.descricaoUnidade;}
    public String getCdUnidadeMedida() {return this.cdUnidadeMedida;}
    public void setDescricaoUnidade(String descricaoUnidade) {this.descricaoUnidade = descricaoUnidade;}
    public void setCdUnidadeMedida(String cdUnidadeMedida) {this.cdUnidadeMedida = cdUnidadeMedida;}
    public boolean isFlNovo() {return flNovo;}
    public void setFlNovo(boolean flNovo) {this.flNovo = flNovo;}

    public int getVersion() {return this.version;}
    public void setVersion(int version) {this.version = version;}

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("Unidade", this.cdUnidadeMedida)
								        .append("Descrição", this.descricaoUnidade)
                                        .append("Novo", this.flNovo)
								        .append("Versão", this.version).toString();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-2100871471, -590516841)
        	.append(this.cdUnidadeMedida)
        	.append(this.descricaoUnidade)
            .append(this.flNovo)
        	.append(this.version).toHashCode();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof UnidadeMedida)) {
            return false;
        }
        UnidadeMedida rhs = (UnidadeMedida) object;
        return new EqualsBuilder().append(this.cdUnidadeMedida, rhs.cdUnidadeMedida)
						        .append(this.descricaoUnidade, rhs.descricaoUnidade)
                                 .append(this.flNovo, rhs.flNovo)
						        .append(this.version, rhs.version).isEquals();
    }
}
