package com.gilbertoca.gfi.inventario.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

@Entity  
@Table(name="unidade_medida")  
public class UnidadeMedida implements Serializable {
    @Id
    @Column(name="cd_unidade_medida", length=4)
    private String cdUnidadeMedida;
    @Column(name="descricao_unidade", length=30) 
    private String descricaoUnidade;
    

    /** 
     * Usado para determinar se instancia é transient or detached 
     */
    @Version
    private Integer version;
    
    public UnidadeMedida() {   }

    public UnidadeMedida(String cdUnidadeMedida, String descricaoUnidade) {
        this.cdUnidadeMedida = cdUnidadeMedida;
        this.descricaoUnidade = descricaoUnidade;
    }

    public java.lang.String getDescricaoUnidade() { return descricaoUnidade; }
    public java.lang.String getCdUnidadeMedida() { return cdUnidadeMedida; }
    public void setDescricaoUnidade(String descricaoUnidade) { this.descricaoUnidade = descricaoUnidade; }
    public void setCdUnidadeMedida(String cdUnidadeMedida) {this.cdUnidadeMedida = cdUnidadeMedida;}
    public int getVersion() {return version; }
    public void setVersion(int version) { this.version = version; }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("cdUnidadeMedida", this.cdUnidadeMedida)
                .append("descricaoUnidade", this.descricaoUnidade)
                .append("version", this.version).toString();
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
        if (!(object instanceof UnidadeMedida)) {
            return false;
        }
        UnidadeMedida rhs = (UnidadeMedida) object;
        return new EqualsBuilder().append(
                this.cdUnidadeMedida, rhs.cdUnidadeMedida).append(this.descricaoUnidade,
                rhs.descricaoUnidade).isEquals();
    }
}
