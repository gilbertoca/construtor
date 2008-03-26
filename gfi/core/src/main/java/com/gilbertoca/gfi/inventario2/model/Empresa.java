package com.gilbertoca.gfi.inventario2.model;

import com.gilbertoca.gfi.component.Endereco;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Set;


/** 
 *        @hibernate.class
 *         table="construtor.empresa_empresa"
 *
*/
public class Empresa implements Serializable {
    private Integer cdEmpresa;
    private String nomeFantasia;
    private String responsavel;
    private Date dtCadastro;
    private Date dtFundacao;
    private String cnpj;
    private String inscricaoEstadual;
    private String razaoSocial;
    private Endereco endereco = new Endereco();
    private String telefone;
    private String celular;
    private String fax;
    private String email;
    private Boolean flOptanteSimples = new Boolean(false);
    private Set items  = Collections.EMPTY_SET;
    private Set contasBancaria  = Collections.EMPTY_SET;

    /**
     * @param cdEmpresa
     * @param nomeFantasia
     * @param responsavel
     * @param dtCadastro
     * @param dtFundacao
     * @param cnpj
     * @param inscricaoEstadual
     * @param razaoSocial
     * @param endereco
     * @param conta
     * @param telefone
     * @param fax
     * @param flOptanteSimples
     * @param items
     */
    public Empresa(Integer cdEmpresa, String nomeFantasia, String responsavel,
        Date dtCadastro, Date dtFundacao, String cnpj,
        String inscricaoEstadual, String razaoSocial, Endereco endereco,
        Set contasBancaira, String telefone, String fax,
        Boolean optanteSimples,Set items) {
        super();
        this.cdEmpresa = cdEmpresa;
        this.nomeFantasia = nomeFantasia;
        this.responsavel = responsavel;
        this.dtCadastro = dtCadastro;
        this.dtFundacao = dtFundacao;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.razaoSocial = razaoSocial;
        this.endereco = endereco;
        this.contasBancaria = contasBancaira;
        this.telefone = telefone;
        this.fax = fax;
        this.flOptanteSimples = optanteSimples;
        this.items = items;
    }

    /** default constructor */
    public Empresa() {
    }

    /** minimal constructor */
    public Empresa(Integer cdEmpresa, Set items) {
        this.cdEmpresa = cdEmpresa;
        this.items = items;
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
    * @hibernate.property column="responsavel" length="100"
     * @return Returns the responsavel.
     */
    public String getResponsavel() {
        return responsavel;
    }

    /**
     * @param responsavel The responsavel to set.
     */
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    /**
     * @hibernate.property column="cnpj" length="14"
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj The cnpj to set.
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
     * @hibernate.property column="dt_fundacao" type = "date"
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
     * @hibernate.id generator-class="sequence" column="cd_empresa" unsaved-value = "null"
     * @hibernate.generator-param name = "sequence" value = "construtor.empresa_empresa_sequence"
     * @return Integer
     */
    public Integer getCdEmpresa() {
        return this.cdEmpresa;
    }

    public void setCdEmpresa(Integer cdEmpresa) {
        this.cdEmpresa = cdEmpresa;
    }

    /**
     *            @hibernate.property
     *             column="Nome_Fantasia"
     *             length="60"
     *
     */
    public String getNomeFantasia() {
        return this.nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    /**
     * @hibernate.property   column="Inscricao_Estadual" length="20"
     */
    public String getInscricaoEstadual() {
        return this.inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    /**
     *            @hibernate.property
     *             column="Razao_Social"
     *             length="60"
     *
     */
    public String getRazaoSocial() {
        return this.razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
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
     * @hibernate.property column="fax" length="30"
     *
     */
    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @hibernate.set role="items" table="construtor.item"
     *  cascade="none" inverse="true"
     * @hibernate.collection-key column="cd_empresa"
     * @hibernate.collection-one-to-many class="org.appfuse.model.estoque.Item"
     *
     */
    public Set getItems() {
        return this.items;
    }

    public void setItems(Set items) {
        this.items = items;
    }

    /**
     * @hibernate.set role="items" table="construtor.financeiro_conta_bancaria"
     *  cascade="none" inverse="true"
     * @hibernate.collection-key column="cd_empresa"
     * @hibernate.collection-one-to-many class="org.appfuse.model.financeiro.ContaBancaria"
     *
     */
    public Set getContasBancaria() {
        return this.contasBancaria;
    }

    public void setContasBancaria(Set contasBancaria) {
        this.contasBancaria = contasBancaria;
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
    * @hibernate.property column="fl_optante_simples" length="1"
     * @return Returns the flOptanteSimples.
     */
    public Boolean getFlOptanteSimples() {
        return flOptanteSimples;
    }

    /**
     * @param flOptanteSimples The flOptanteSimples to set.
     */
    public void setFlOptanteSimples(Boolean optanteSimples) {
        this.flOptanteSimples = optanteSimples;
    }
}
