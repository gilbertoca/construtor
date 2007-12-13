package com.gilbertoca.gfi.component;

import com.gilbertoca.gfi.Constants;
import java.io.Serializable;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Endereco implements Serializable {
	private String ufeSg = Constants.FAIXAUF_UFESG;
	private String localidade = Constants.LOCALIDADE;
	private String tipoLogradouro = Constants.TIPOLOGR_TIPOLOGRADOURO;
	private String numero = "N/I";
	private String logNome = "N/I";
	private String logComplemento = "N/I";	              			 
	private String baiNome = "N/I";
	private String cep = "N/I";

	/**
	 * 
	 */
	public Endereco() {
	}
	
	/**
	 * @param loc
	 * @param ufeSg
	 * @param localidade
	 * @param numero
	 * @param tipo
	 * @param tipologradouro
	 * @param logNome
	 * @param logComplemento
	 * @param baiNome
	 * @param cep
	 */
	public Endereco(String ufeSg, String loc, String numero, String tipo, String logNome,
			String logComplemento, String baiNome, String cep) {
		this.ufeSg=ufeSg;
		this.localidade=loc;
		this.tipoLogradouro=tipo;
		this.numero = numero;
		this.logNome = logNome;
		this.logComplemento = logComplemento;
		this.baiNome = baiNome;
		this.cep = cep;
	}
	/**
     * @hibernate.property not-null = "false"
     * @hibernate.column name="log_numero" length="10"
     *         
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * @param numero The numero to set.
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
    
    /** 
     * @hibernate.property  not-null = "false"
     * @hibernate.column name="log_nome" length="125"
     */
    public String getLogNome() {
        return this.logNome;
    }

    public void setLogNome(String logNome) {
        this.logNome = logNome;
    }

    /** 
     * @hibernate.property not-null = "false"
     * @hibernate.column name="log_complemento" length="100"
     *         
     */
    public String getLogComplemento() {
        return this.logComplemento;
    }

    public void setLogComplemento(String logComplemento) {
        this.logComplemento = logComplemento;
    }

    /** 
     * @hibernate.property not-null = "false"
     * @hibernate.column name="bai_nome" length="72"
     */
    public String getBaiNome() {
        return this.baiNome;
    }

    public void setBaiNome(String baiNome) {
        this.baiNome = baiNome;
    }

    /** 
     * @hibernate.property not-null = "false"
     * @hibernate.column name="cep" length="8"
     *         
     */
    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
	
    
	/**
     * @hibernate.property not-null = "false"
     * @hibernate.column name="loc_nu_sequencial" 
	 */
	public String getLocalidade() {
		return localidade;
	}
	/**
	 * @param localidade The localidade to set.
	 */
	public void setLocalidade(String locNuSequencial) {
		this.localidade = locNuSequencial;
	}
	/**
     * @hibernate.property not-null = "false"
     * @hibernate.column name="tipo_logradouro" length="72"
	 */
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}
	/**
	 * @param tipoLogradouro The tipoLogradouro to set.
	 */
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
    /** 
     * @hibernate.property not-null = "false"
     * @hibernate.column name="ufe_sg" length="2"
     */
	public String getUfeSg() {
		return ufeSg; 
	}
	/**
	 * @param ufeSg The ufeSg to set.
	 */
	public void setUfeSg(String ufeSg) {
		this.ufeSg = ufeSg;
	}

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco rhs = (Endereco) object;
        return new EqualsBuilder().append(this.localidade,
                rhs.localidade).append(this.baiNome, rhs.baiNome).append(
                this.cep, rhs.cep).append(this.logNome, rhs.logNome).append(
                this.numero, rhs.numero).append(this.logComplemento,
                rhs.logComplemento).append(this.ufeSg, rhs.ufeSg).append(
                this.tipoLogradouro, rhs.tipoLogradouro).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1419301265, -1559857937).append(
                this.localidade).append(this.baiNome).append(this.cep)
                .append(this.logNome).append(this.numero).append(
                        this.logComplemento).append(this.ufeSg).append(
                        this.tipoLogradouro).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("ufeSg", this.ufeSg).append("logComplemento",
                        this.logComplemento).append("locNuSequencial",
                        this.localidade).append("logNome", this.logNome)
                .append("cep", this.cep)
                .append("tipoLogradouro", this.tipoLogradouro).append(
                        "numero", this.numero).append("baiNome",
                        this.baiNome).toString();
    }
}
