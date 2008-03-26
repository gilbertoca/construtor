package com.gilbertoca.gfi.inventario2.model;

import com.gilbertoca.gfi.component.ContaBanco;
import com.gilbertoca.gfi.component.Endereco;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 *        @hibernate.class
 *         table="construtor.estoque_fornecedor"
 *
*/
public class Fornecedor implements Serializable {
    private Integer cdFornecedor;
    private String nomeRazaoSocial;
    private String cpfCnpj;
    private String rgInscricaoEstadual;    
    private Boolean flPessoaFisica = new Boolean(true);//pessoa f�sica ou não
    private Date dtCadastro = new Date();
    private Date dtFundacao;
    private Endereco endereco = new Endereco();   
    private ContaBanco conta = new ContaBanco();
    private String telefone;
    private String celular;
    private String fax;
    private String email;
    private String homePage;
    private String observacoes;
    private Set contatos = Collections.EMPTY_SET;
    private Set itens = Collections.EMPTY_SET;

	/**
	 * @param cdFornecedor
	 * @param flPessoaFisica
	 * @param nomeFantasia
	 * @param dtCadastro
	 * @param dtFundacao
	 * @param cnpj
	 * @param inscricaoEstadual
	 * @param razaoSocial
	 * @param endereco
	 * @param conta
	 * @param telefone
	 * @param fax
	 * @param homePage
	 * @param observacoes
	 * @param anotacoes
	 * @param items
	 * @param contatos
	 */
	public Fornecedor(Integer cdFornecedor, Boolean flPessoaFisica,
			String nomeRazaoSocial, Date dtCadastro, Date dtFundacao, String cpfCnpj,
			String rgInscricaoEstadual, String razaoSocial, Endereco endereco,
			String telefone, String fax, String homePage,
			String observacoes, String anotacoes, Set contatos) {
		super();
		this.cdFornecedor = cdFornecedor;
		this.flPessoaFisica = flPessoaFisica;
		this.nomeRazaoSocial = nomeRazaoSocial;
		this.dtCadastro = dtCadastro;
		this.dtFundacao = dtFundacao;
		this.cpfCnpj = cpfCnpj;
		this.rgInscricaoEstadual = rgInscricaoEstadual;
		this.endereco = endereco;
		this.telefone = telefone;
		this.fax = fax;
		this.homePage = homePage;
		this.observacoes = observacoes;
		this.contatos = contatos;
	}
    /** default constructor */
    public Fornecedor() {
    }
    /**
     * @param string
     */
    public Fornecedor(String nomeRazaoSocial) {
        this.nomeRazaoSocial = nomeRazaoSocial;
    }
    /**
     * @return Returns the itens.
     * This inverse relation causes exceptions :-( drk
     * hibernate.set table="construtor.estoque_item_fornecedor" cascade="save-update"
     *                lazy="false" inverse="true"
     * hibernate.collection-key column="cd_fornecedor"
     * hibernate.collection-many-to-many class="org.appfuse.model.estoque.Item"
     *                                    column="cd_item"
     */
    public Set getItens() {
        return itens;
    }
    /**
     * @param itens The itens to set.
     */
    public void setItens(Set itens) {
        this.itens = itens;
    }

   
    /**
     * @return Returns the rgInscricaoEstadual.
     * @hibernate.property column="rg_inscricao_estadual" length = "14"
     */
    public String getRgInscricaoEstadual() {
        return rgInscricaoEstadual;
    }
    /**
     * @param rgInscricaoEstadual The rgInscricaoEstadual to set.
     * @spring.validator type="required"
     */
    public void setRgInscricaoEstadual(String rgInscricaoEstadual) {
        this.rgInscricaoEstadual = rgInscricaoEstadual;
    }

    /**
     * @hibernate.property column="cpf_cnpj" length="14" 
     * @return Returns the cpfCnpj.
     */
    public String getCpfCnpj() {
        return cpfCnpj;
    }

    /**
     * @param cpfCnpj The cpfCnpj to set.
     * @spring.validator type="required"
     *
     * @spring.validator type="minlength" msgkey="errors.cpfcnpjlength"
     * @spring.validator-args arg1resource="${var:minlength}"
     * @spring.validator-var name="minlength" value="11"
     * 
     * @spring.validator type="maxlength" msgkey="errors.cpfcnpjlength"
     * @spring.validator-args arg2resource="${var:maxlength}" 
     * @spring.validator-var name="maxlength" value="14"
     * 
     * @spring.validator type="mask" msgkey="errors.cpfcnpj"
     * @spring.validator-var name="mask" value="^\d{11,14}\d*$"
     */
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    /**
     * @hibernate.property column="nome_Razao_Social" length="100"  not-null="true"
     *
     */
    public String getNomeRazaoSocial() {
        return this.nomeRazaoSocial;
    }

    /**
     * @param nome
     * @spring.validator type="required"
     */
    public void setNomeRazaoSocial(String nome) {
        this.nomeRazaoSocial = nome;
    }
    
    /**
     * @return Returns the celular.
     * @hibernate.property column="celular" length = "16"
     */
    public String getCelular() {
        return celular;
    }
    /**
     * @param celular The celular to set.
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }
    /**
     *            @hibernate.property
     *             column="email"
     *             length="80"
     *
     */
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @hibernate.property column="dt_cadastro" type = "date" not-null="true"
     */
    public Date getDtCadastro() {
        return dtCadastro;
    }

    /**
     * @param dtCadastro The dtCadastro to set.
     */
    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    /**
     * @hibernate.property column="dt_fundacao" 
     */
    public Date getDtFundacao() {
        return dtFundacao;
    }

    /**
     * @param dtFundacao The dtFundacao to set.
     */
    public void setDtFundacao(Date dtFundacao) {
        this.dtFundacao = dtFundacao;
    }

    /**
     *  @hibernate.property column="observacoes" length="255"
     *
     * */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * @param observacoes The observacoes to set.
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    /**
     * @hibernate.id generator-class="sequence" column="cd_fornecedor" unsaved-value = "null"
     * @hibernate.generator-param name = "sequence" value = "construtor.estoque_fornecedor_sequence"
     * @return Integer
     */
    public Integer getCdFornecedor() {
        return this.cdFornecedor;
    }

    public void setCdFornecedor(Integer cdFornecedor) {
        this.cdFornecedor = cdFornecedor;
    }

    /**
     *            @hibernate.property
     *             column="telefone"
     *             length="30"
     *
     */
    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     *            @hibernate.property
     *             column="fax"
     *             length="30"
     *
     */
    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     *            @hibernate.property
     *             column="home_Page"
     *             length="100"
     *
     */
    public String getHomePage() {
        return this.homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    /** 
     * @hibernate.set lazy="true" inverse="true" cascade="none"
     * @hibernate.collection-key column="cd_fornecedor"
     * @hibernate.collection-one-to-many class="org.appfuse.model.estoque.Contato"
     *         
     */
    public Set getContatos() {
        return contatos;
    }

    /**
     * @return Returns the conta.
     * @hibernate.component
     */
    public ContaBanco getConta() {
        return conta;
    }
    /**
     * @param conta The conta to set.
     */
    public void setConta(ContaBanco conta) {
        this.conta = conta;
    }
    /**
     * @param contatos The contatos to set.
     */
    public void setContatos(Set contatos) {
        this.contatos = contatos;
    }

    /**
     * @hibernate.component
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param endereco The endereco to set.
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
	/**
	 * @hibernate.property  column="flPessoaFisica" length="1"
	 * @return Returns the flPessoaFisica.
	 */
	public Boolean getFlPessoaFisica() {
		return flPessoaFisica;
	}
	/**
	 * @param flPessoaFisica The flPessoaFisica to set.
	 */
	public void setFlPessoaFisica(Boolean flPessoaFisica) {
		this.flPessoaFisica = flPessoaFisica;
	}
    
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor rhs = (Fornecedor) object;
        return new EqualsBuilder().append(this.cpfCnpj, rhs.cpfCnpj).append(
                this.dtFundacao, rhs.dtFundacao).append(this.flPessoaFisica,
                rhs.flPessoaFisica).append(this.celular, rhs.celular).append(
                this.dtCadastro, rhs.dtCadastro).append(
                this.rgInscricaoEstadual, rhs.rgInscricaoEstadual).append(
                this.fax, rhs.fax).append(this.nomeRazaoSocial,
                rhs.nomeRazaoSocial).append(this.homePage, rhs.homePage)
                .append(this.contatos, rhs.contatos).append(this.conta,
                        rhs.conta).append(this.observacoes, rhs.observacoes)
                .append(this.cdFornecedor, rhs.cdFornecedor).append(
                        this.telefone, rhs.telefone).append(this.email,
                        rhs.email).append(this.itens, rhs.itens).append(
                        this.endereco, rhs.endereco).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1112158765, 464474429).append(this.cpfCnpj)
                .append(this.dtFundacao).append(this.flPessoaFisica).append(
                        this.celular).append(this.dtCadastro).append(
                        this.rgInscricaoEstadual).append(this.fax).append(
                        this.nomeRazaoSocial).append(this.homePage).append(
                        this.contatos).append(this.conta).append(
                        this.observacoes).append(this.cdFornecedor).append(
                        this.telefone).append(this.email).append(this.itens)
                .append(this.endereco).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("flPessoaFisica", this.flPessoaFisica).append("conta",
                        this.conta).append("cdFornecedor", this.cdFornecedor)
                .append("homePage", this.homePage).append("cpfCnpj",
                        this.cpfCnpj).append("fax", this.fax).append(
                        "dtFundacao", this.dtFundacao).append("itens",
                        this.itens).append("telefone", this.telefone).append(
                        "celular", this.celular).append("endereco",
                        this.endereco).append("nomeRazaoSocial",
                        this.nomeRazaoSocial).append("observacoes",
                        this.observacoes)
                .append("email", this.email).append("contatos", this.contatos)
                .append("dtCadastro", this.dtCadastro).append(
                        "rgInscricaoEstadual", this.rgInscricaoEstadual)
                .toString();
    }
}
