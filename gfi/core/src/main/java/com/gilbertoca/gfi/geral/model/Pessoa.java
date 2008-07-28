package com.gilbertoca.gfi.geral.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer cdPessoa;
    private String rgNumero;
    private String fotoPath;
    private String apelido;
    private String rgOrgaoExp;
    private Date rgEmissao;
    private String cpf;
    private String cnNumero;
    private String cnLv;
    private String cnFls;
    private String cnCidade;
    private String cnSubDistrito;
    private String cnUf;
    private String sexo;
    private String nomePai;
    private String nomeMae;
    private Date dtNascimento;
    private Date dtFalecimento;
    private String nome;
    private Boolean flDependente;
    private String ufeSg;
    private Integer locNuSequencial;
    private String tipoLogradouro;
    private String logNome;
    private String logComplemento;
    private String baiNome;
    private String cep;
    private String email;
    private Timestamp dtCadastro = new Timestamp(new Date().getTime());
    private int version = -1;
    private PessoaDocumento gerPessoaDocumento;
    private PessoaCaracteristica gerPessoaCaracteristica;

    private Set<PessoaDependencia> gerPessoaDependencias;

    /** full constructor */
    public Pessoa(
	    Integer cdpessoa,
	    String rgnumero,
	    String fotopath,
	    String apelido,
	    String rgorgaoexp,
	    Date rgemissao,
	    String cpf,
	    String cnnumero,
	    String cnlv,
	    String cnfls,
	    String cncidade,
	    String cnsubdistrito,
	    String cnuf,
	    String sexo,
	    String nomepai,
	    String nomemae,
	    Date dtnascimento,
	    Date dtfalecimento,
	    String nome,
	    Boolean fldependente,
	    String ufeSg,
	    Integer locNuSequencial,
	    String tipologradouro,
	    String logNome,
	    String logComplemento,
	    String baiNome,
	    String cep,
	    PessoaDocumento gerPessoadocumento,
	    PessoaCaracteristica gerPessoacaracteristica,
	    Set<PessoaDependencia> gerPessoadependencias) {
	this.cdPessoa = cdpessoa;
	this.rgNumero = rgnumero;
	this.fotoPath = fotopath;
	this.apelido = apelido;
	this.rgOrgaoExp = rgorgaoexp;
	this.rgEmissao = rgemissao;
	this.cpf = cpf;
	this.cnNumero = cnnumero;
	this.cnLv = cnlv;
	this.cnFls = cnfls;
	this.cnCidade = cncidade;
	this.cnSubDistrito = cnsubdistrito;
	this.cnUf = cnuf;
	this.sexo = sexo;
	this.nomePai = nomepai;
	this.nomeMae = nomemae;
	this.dtNascimento = dtnascimento;
	this.dtFalecimento = dtfalecimento;
	this.nome = nome;
	this.flDependente = fldependente;
	this.ufeSg = ufeSg;
	this.locNuSequencial = locNuSequencial;
	this.tipoLogradouro = tipologradouro;
	this.logNome = logNome;
	this.logComplemento = logComplemento;
	this.baiNome = baiNome;
	this.cep = cep;
	this.gerPessoaDocumento = gerPessoadocumento;
	this.gerPessoaCaracteristica = gerPessoacaracteristica;
	this.gerPessoaDependencias = gerPessoadependencias;
    }

    /** default constructor */
    public Pessoa() {
    }

    /** minimal constructor */
    public Pessoa(Integer cdpessoa, String sexo, String nome, String nomeMae,
	    String apelido, Date dtNascimento) {
	this.cdPessoa = cdpessoa;
	this.sexo = sexo;
	this.nome = nome;
	this.nomeMae = nomeMae;
	this.apelido = apelido;
	this.dtNascimento = dtNascimento;	
    }

    /**
     * @hibernate.id generator-class="assigned" type="java.lang.Integer"
     *               column="cdPessoa"
     * 
     */
    public Integer getCdPessoa() {
	return this.cdPessoa;
    }

    public void setCdPessoa(Integer cdpessoa) {
	this.cdPessoa = cdpessoa;
    }

    /**
     * @hibernate.property column="rgNumero" length="30"
     * 
     */
    public String getRgNumero() {
	return this.rgNumero;
    }

    public void setRgNumero(String rgnumero) {
	this.rgNumero = rgnumero;
    }

    /**
     * @hibernate.property column="fotoPath" length="255"
     * 
     */
    public String getFotoPath() {
	return this.fotoPath;
    }

    public void setFotoPath(String fotopath) {
	this.fotoPath = fotopath;
    }

    /**
     * @hibernate.property column="apelido" length="50"
     * 
     */
    public String getApelido() {
	return this.apelido;
    }

    public void setApelido(String apelido) {
	this.apelido = apelido;
    }

    /**
     * @hibernate.property column="rgOrgaoExp" length="6"
     * 
     */
    public String getRgOrgaoExp() {
	return this.rgOrgaoExp;
    }

    public void setRgOrgaoExp(String rgorgaoexp) {
	this.rgOrgaoExp = rgorgaoexp;
    }

    /**
     * @hibernate.property column="rgEmissao" length="4"
     * 
     */
    public Date getRgEmissao() {
	return this.rgEmissao;
    }

    public void setRgEmissao(Date rgemissao) {
	this.rgEmissao = rgemissao;
    }

    /**
     * @hibernate.property column="cpf" length="11"
     * 
     */
    public String getCpf() {
	return this.cpf;
    }

    public void setCpf(String cpf) {
	this.cpf = cpf;
    }

    /**
     * @hibernate.property column="cnNumero" length="20"
     * 
     */
    public String getCnNumero() {
	return this.cnNumero;
    }

    /**
     * Sets the cnNumero.
     * 
     * @param cnNumero
     *                O número da certidão de Nascimento/Casamento.
     * @spring.validator type="required"
     */
    public void setCnNumero(String cnnumero) {
	this.cnNumero = cnnumero;
    }

    /**
     * @hibernate.property column="cnLv" length="10"
     * 
     */
    public String getCnLv() {
	return this.cnLv;
    }

    /**
     * Sets the cnLv.
     * 
     * @param cnLv
     *                O número do livro da certidão de Nascimento/Casamento.
     * @spring.validator type="required"
     */
    public void setCnLv(String cnlv) {
	this.cnLv = cnlv;
    }

    /**
     * @hibernate.property column="cnFls" length="10"
     * 
     */
    public String getCnFls() {
	return this.cnFls;
    }

    /**
     * Sets the cnFls.
     * 
     * @param cnFls
     *                O número da folha da certidão de Nascimento/Casamento.
     * @spring.validator type="required"
     */
    public void setCnFls(String cnfls) {
	this.cnFls = cnfls;
    }

    /**
     * @hibernate.property column="cnCidade" length="50"
     * 
     */
    public String getCnCidade() {
	return this.cnCidade;
    }

    /**
     * Sets the cnCidade.
     * 
     * @param cnCidade
     *                O nome da Cidade da certidão de Nascimento/Casamento.
     * @spring.validator type="required"
     */
    public void setCnCidade(String cncidade) {
	this.cnCidade = cncidade;
    }

    /**
     * @hibernate.property column="cnSubDistrito" length="50"
     * 
     */
    public String getCnSubDistrito() {
	return this.cnSubDistrito;
    }

    public void setCnSubDistrito(String cnsubdistrito) {
	this.cnSubDistrito = cnsubdistrito;
    }

    /**
     * @hibernate.property column="cnUf" length="2"
     * 
     */
    public String getCnUf() {
	return this.cnUf;
    }

    /**
     * Sets the cnUf.
     * 
     * @param cnUf
     *                A sigla do Estado da certidão de Nascimento/Casamento.
     * @spring.validator type="required"
     */
    public void setCnUf(String cnuf) {
	this.cnUf = cnuf;
    }

    /**
     * @hibernate.property column="sexo" length="1" not-null="true"
     * 
     */
    public String getSexo() {
	return this.sexo;
    }

    /**
     * Sets the sexo.
     * 
     * @param sexo
     *                O genero.
     * @spring.validator type="required"
     */
    public void setSexo(String sexo) {
	this.sexo = sexo;
    }

    /**
     * @hibernate.property column="nomePai" length="100"
     * 
     */
    public String getNomePai() {
	return this.nomePai;
    }

    public void setNomePai(String nomepai) {
	this.nomePai = nomepai;
    }

    /**
     * @hibernate.property column="nomeMae" length="100"
     * 
     */
    public String getNomeMae() {
	return this.nomeMae;
    }

    /**
     * Sets the nomeMae.
     * 
     * @param nomeMae
     *                O nome da M�e.
     * @spring.validator type="required"
     */
    public void setNomeMae(String nomemae) {
	this.nomeMae = nomemae;
    }

    /**
     * @hibernate.property column="dtNascimento" length="4"
     * 
     */
    public Date getDtNascimento() {
	return this.dtNascimento;
    }

    /**
     * Sets the dtNascimento.
     * 
     * @param dtNascimento
     *                A data de nascimento.
     * @spring.validator type="required"
     */
    public void setDtNascimento(Date dtnascimento) {
	this.dtNascimento = dtnascimento;
    }

    /**
     * @hibernate.property column="dtFalecimento" length="4"
     * 
     */
    public Date getDtFalecimento() {
	return this.dtFalecimento;
    }

    public void setDtFalecimento(Date dtfalecimento) {
	this.dtFalecimento = dtfalecimento;
    }

    /**
     * @hibernate.property column="nome" length="100" not-null="true"
     * 
     */
    public String getNome() {
	return this.nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    /**
     * @hibernate.property column="flDependente" length="1"
     * 
     */
    public Boolean getFlDependente() {
	return this.flDependente;
    }

    public void setFlDependente(Boolean fldependente) {
	this.flDependente = fldependente;
    }

    /**
     * @hibernate.property column="ufe_sg" length="2"
     * 
     */
    public String getUfeSg() {
	return this.ufeSg;
    }

    public void setUfeSg(String ufeSg) {
	this.ufeSg = ufeSg;
    }

    /**
     * @hibernate.property column="loc_nu_sequencial" length="4"
     * 
     */
    public Integer getLocNuSequencial() {
	return this.locNuSequencial;
    }

    public void setLocNuSequencial(Integer locNuSequencial) {
	this.locNuSequencial = locNuSequencial;
    }

    /**
     * @hibernate.property column="tipoLogradouro" length="72"
     * 
     */
    public String getTipoLogradouro() {
	return this.tipoLogradouro;
    }

    public void setTipoLogradouro(String tipologradouro) {
	this.tipoLogradouro = tipologradouro;
    }

    /**
     * @hibernate.property column="log_nome" length="125"
     * 
     */
    public String getLogNome() {
	return this.logNome;
    }

    public void setLogNome(String logNome) {
	this.logNome = logNome;
    }

    /**
     * @hibernate.property column="log_complemento" length="100"
     * 
     */
    public String getLogComplemento() {
	return this.logComplemento;
    }

    public void setLogComplemento(String logComplemento) {
	this.logComplemento = logComplemento;
    }

    /**
     * @hibernate.property column="bai_nome" length="72"
     * 
     */
    public String getBaiNome() {
	return this.baiNome;
    }

    public void setBaiNome(String baiNome) {
	this.baiNome = baiNome;
    }

    /**
     * @hibernate.property column="cep" length="8"
     * 
     */
    public String getCep() {
	return this.cep;
    }

    public void setCep(String cep) {
	this.cep = cep;
    }

    /**
     * @hibernate.property column="email" length="100"
     * 
     */
    public String getEmail() {
	return this.email;
    }

    public void setEmail(String email) {
	this.cep = email;
    }

    public Timestamp getDtCadastro() {
	return dtCadastro;
    }

    public void setDtCadastro(Timestamp dtCadastro) {
	this.dtCadastro = dtCadastro;
    }

    public int getVersion() {
	return this.version;
    }

    public void setVersion(int version) {
	this.version = version;
    }
    
    /**
     * @hibernate.one-to-one outer-join="auto"
     * 
     */
    public PessoaDocumento getGerPessoaDocumento() {
	return this.gerPessoaDocumento;
    }

    public void setGerPessoaDocumento(PessoaDocumento gerPessoadocumento) {
	this.gerPessoaDocumento = gerPessoadocumento;
    }

    /**
     * @hibernate.one-to-one outer-join="auto"
     * 
     */
    public PessoaCaracteristica getGerPessoaCaracteristica() {
	return this.gerPessoaCaracteristica;
    }

    public void setGerPessoaCaracteristica(PessoaCaracteristica gerPessoacaracteristica) {
	this.gerPessoaCaracteristica = gerPessoacaracteristica;
    }

    /**
     * @hibernate.set lazy="true" inverse="true" cascade="none"
     * @hibernate.collection-key column="cdPessoa"
     * @hibernate.collection-one-to-many class="com.gilbertoca.gfi.ger.model.PessoaDependencia"
     * 
     */
    public Set getGerPessoaDependencias() {
	return this.gerPessoaDependencias;
    }

    public void setGerPessoaDependencias(Set gerPessoadependencias) {
	this.gerPessoaDependencias = gerPessoadependencias;
    }

    public String toString() {
	return new ToStringBuilder(this).append("cdPessoa", getCdPessoa())
		.toString();
    }

    public boolean equals(Object other) {
	if (!(other instanceof Pessoa))
	    return false;
	Pessoa castOther = (Pessoa) other;
	return new EqualsBuilder().append(this.getCdPessoa(),
		castOther.getCdPessoa()).isEquals();
    }

    public int hashCode() {
	return new HashCodeBuilder().append(getCdPessoa()).toHashCode();
    }

}
