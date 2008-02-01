package com.gilbertoca.gfi.component;

import java.io.Serializable;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
 * @author Gilberto
 */
public class ContaBanco implements Serializable {
    private String banco;
    private String agencia;
    private String numeroConta;
    /**
     * @param string
     */
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    /**
     * @hibernate.property column="agencia" length="10"
     */
    public String getAgencia() {
        return agencia;
    }

    /**
     * @hibernate.property column="banco" length="20"
     */
    public String getBanco() {
        return banco;
    }

    /**
     * @param banco
     *            The banco to set.
     */
    public void setBanco(String banco) {
        this.banco = banco;
    }

    /**
     * @hibernate.property column="numero_conta" length="30"
     */
    public String getNumeroConta() {
        return numeroConta;
    }

    /**
     * @param numeroConta
     *            The numeroConta to set.
     */
    public void setNumeroConta(String numero) {
        this.numeroConta = numero;
    }
    
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof ContaBanco)) {
            return false;
        }
        ContaBanco rhs = (ContaBanco) object;
        return new EqualsBuilder().append(this.numeroConta, rhs.numeroConta).append(
                this.agencia, rhs.agencia).append(this.banco, rhs.banco)
                .isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-509314773, 1856906407).append(this.numeroConta)
                .append(this.agencia).append(this.banco).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("numeroConta", this.numeroConta).append("banco", this.banco)
                .append("agencia", this.agencia).toString();
    }
}
