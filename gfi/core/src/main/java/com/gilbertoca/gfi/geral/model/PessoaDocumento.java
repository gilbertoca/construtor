package com.gilbertoca.gfi.geral.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="ger.ger_pessoadocumento"
 *     
*/
public class PessoaDocumento implements Serializable {

    /** identifier field */
    private Integer cdpessoa;

    /** nullable persistent field */
    private String numerosus;

    /** nullable persistent field */
    private String pispasep;

    /** nullable persistent field */
    private String crnumero;

    /** nullable persistent field */
    private String crcategoria;

    /** nullable persistent field */
    private String crserie;

    /** nullable persistent field */
    private String crvrm;

    /** nullable persistent field */
    private String crvsm;

    /** nullable persistent field */
    private Date cremissao;

    /** nullable persistent field */
    private String almserie;

    /** nullable persistent field */
    private String almregmilitar;

    /** nullable persistent field */
    private String almvrm;

    /** nullable persistent field */
    private String almcsm;

    /** nullable persistent field */
    private Date almemissao;

    /** nullable persistent field */
    private String titelnumero;

    /** nullable persistent field */
    private String titelsecao;

    /** nullable persistent field */
    private String titelzona;

    /** nullable persistent field */
    private Date titelemissao;

    /** nullable persistent field */
    private String ctpsnumero;

    /** nullable persistent field */
    private String ctpsserie;

    /** nullable persistent field */
    private Date ctpsemissao;

    /** nullable persistent field */
    private String ctpsuf;

    /** nullable persistent field */
    private String cnhnumero;

    /** nullable persistent field */
    private String cnhcategoria;

    /** nullable persistent field */
    private Date cnhemissao;

    /** nullable persistent field */
    private Date cnhvalidade;

    /** nullable persistent field */
    private com.gilbertoca.gfi.geral.model.Pessoa gerPessoa;

    /** full constructor */
    public PessoaDocumento(Integer cdpessoa, String numerosus, String pispasep, String crnumero, String crcategoria, String crserie, String crvrm, String crvsm, Date cremissao, String almserie, String almregmilitar, String almvrm, String almcsm, Date almemissao, String titelnumero, String titelsecao, String titelzona, Date titelemissao, String ctpsnumero, String ctpsserie, Date ctpsemissao, String ctpsuf, String cnhnumero, String cnhcategoria, Date cnhemissao, Date cnhvalidade, com.gilbertoca.gfi.geral.model.Pessoa gerPessoa) {
        this.cdpessoa = cdpessoa;
        this.numerosus = numerosus;
        this.pispasep = pispasep;
        this.crnumero = crnumero;
        this.crcategoria = crcategoria;
        this.crserie = crserie;
        this.crvrm = crvrm;
        this.crvsm = crvsm;
        this.cremissao = cremissao;
        this.almserie = almserie;
        this.almregmilitar = almregmilitar;
        this.almvrm = almvrm;
        this.almcsm = almcsm;
        this.almemissao = almemissao;
        this.titelnumero = titelnumero;
        this.titelsecao = titelsecao;
        this.titelzona = titelzona;
        this.titelemissao = titelemissao;
        this.ctpsnumero = ctpsnumero;
        this.ctpsserie = ctpsserie;
        this.ctpsemissao = ctpsemissao;
        this.ctpsuf = ctpsuf;
        this.cnhnumero = cnhnumero;
        this.cnhcategoria = cnhcategoria;
        this.cnhemissao = cnhemissao;
        this.cnhvalidade = cnhvalidade;
        this.gerPessoa = gerPessoa;
    }

    /** default constructor */
    public PessoaDocumento() {
    }

    /** minimal constructor */
    public PessoaDocumento(Integer cdpessoa) {
        this.cdpessoa = cdpessoa;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Integer"
     *             column="cdpessoa"
     *         
     */
    public Integer getCdpessoa() {
        return this.cdpessoa;
    }

    public void setCdpessoa(Integer cdpessoa) {
        this.cdpessoa = cdpessoa;
    }

    /** 
     *            @hibernate.property
     *             column="numerosus"
     *             length="30"
     *         
     */
    public String getNumerosus() {
        return this.numerosus;
    }

    public void setNumerosus(String numerosus) {
        this.numerosus = numerosus;
    }

    /** 
     *            @hibernate.property
     *             column="pispasep"
     *             length="11"
     *         
     */
    public String getPispasep() {
        return this.pispasep;
    }

    public void setPispasep(String pispasep) {
        this.pispasep = pispasep;
    }

    /** 
     *            @hibernate.property
     *             column="crnumero"
     *             length="30"
     *         
     */
    public String getCrnumero() {
        return this.crnumero;
    }

    public void setCrnumero(String crnumero) {
        this.crnumero = crnumero;
    }

    /** 
     *            @hibernate.property
     *             column="crcategoria"
     *             length="15"
     *         
     */
    public String getCrcategoria() {
        return this.crcategoria;
    }

    public void setCrcategoria(String crcategoria) {
        this.crcategoria = crcategoria;
    }

    /** 
     *            @hibernate.property
     *             column="crserie"
     *             length="15"
     *         
     */
    public String getCrserie() {
        return this.crserie;
    }

    public void setCrserie(String crserie) {
        this.crserie = crserie;
    }

    /** 
     *            @hibernate.property
     *             column="crvrm"
     *             length="15"
     *         
     */
    public String getCrvrm() {
        return this.crvrm;
    }

    public void setCrvrm(String crvrm) {
        this.crvrm = crvrm;
    }

    /** 
     *            @hibernate.property
     *             column="crvsm"
     *             length="15"
     *         
     */
    public String getCrvsm() {
        return this.crvsm;
    }

    public void setCrvsm(String crvsm) {
        this.crvsm = crvsm;
    }

    /** 
     *            @hibernate.property
     *             column="cremissao"
     *             length="4"
     *         
     */
    public Date getCremissao() {
        return this.cremissao;
    }

    public void setCremissao(Date cremissao) {
        this.cremissao = cremissao;
    }

    /** 
     *            @hibernate.property
     *             column="almserie"
     *             length="15"
     *         
     */
    public String getAlmserie() {
        return this.almserie;
    }

    public void setAlmserie(String almserie) {
        this.almserie = almserie;
    }

    /** 
     *            @hibernate.property
     *             column="almregmilitar"
     *             length="15"
     *         
     */
    public String getAlmregmilitar() {
        return this.almregmilitar;
    }

    public void setAlmregmilitar(String almregmilitar) {
        this.almregmilitar = almregmilitar;
    }

    /** 
     *            @hibernate.property
     *             column="almvrm"
     *             length="15"
     *         
     */
    public String getAlmvrm() {
        return this.almvrm;
    }

    public void setAlmvrm(String almvrm) {
        this.almvrm = almvrm;
    }

    /** 
     *            @hibernate.property
     *             column="almcsm"
     *             length="15"
     *         
     */
    public String getAlmcsm() {
        return this.almcsm;
    }

    public void setAlmcsm(String almcsm) {
        this.almcsm = almcsm;
    }

    /** 
     *            @hibernate.property
     *             column="almemissao"
     *             length="4"
     *         
     */
    public Date getAlmemissao() {
        return this.almemissao;
    }

    public void setAlmemissao(Date almemissao) {
        this.almemissao = almemissao;
    }

    /** 
     *            @hibernate.property
     *             column="titelnumero"
     *             length="12"
     *         
     */
    public String getTitelnumero() {
        return this.titelnumero;
    }

    public void setTitelnumero(String titelnumero) {
        this.titelnumero = titelnumero;
    }

    /** 
     *            @hibernate.property
     *             column="titelsecao"
     *             length="5"
     *         
     */
    public String getTitelsecao() {
        return this.titelsecao;
    }

    public void setTitelsecao(String titelsecao) {
        this.titelsecao = titelsecao;
    }

    /** 
     *            @hibernate.property
     *             column="titelzona"
     *             length="5"
     *         
     */
    public String getTitelzona() {
        return this.titelzona;
    }

    public void setTitelzona(String titelzona) {
        this.titelzona = titelzona;
    }

    /** 
     *            @hibernate.property
     *             column="titelemissao"
     *             length="4"
     *         
     */
    public Date getTitelemissao() {
        return this.titelemissao;
    }

    public void setTitelemissao(Date titelemissao) {
        this.titelemissao = titelemissao;
    }

    /** 
     *            @hibernate.property
     *             column="ctpsnumero"
     *             length="10"
     *         
     */
    public String getCtpsnumero() {
        return this.ctpsnumero;
    }

    public void setCtpsnumero(String ctpsnumero) {
        this.ctpsnumero = ctpsnumero;
    }

    /** 
     *            @hibernate.property
     *             column="ctpsserie"
     *             length="5"
     *         
     */
    public String getCtpsserie() {
        return this.ctpsserie;
    }

    public void setCtpsserie(String ctpsserie) {
        this.ctpsserie = ctpsserie;
    }

    /** 
     *            @hibernate.property
     *             column="ctpsemissao"
     *             length="4"
     *         
     */
    public Date getCtpsemissao() {
        return this.ctpsemissao;
    }

    public void setCtpsemissao(Date ctpsemissao) {
        this.ctpsemissao = ctpsemissao;
    }

    /** 
     *            @hibernate.property
     *             column="ctpsuf"
     *             length="2"
     *         
     */
    public String getCtpsuf() {
        return this.ctpsuf;
    }

    public void setCtpsuf(String ctpsuf) {
        this.ctpsuf = ctpsuf;
    }

    /** 
     *            @hibernate.property
     *             column="cnhnumero"
     *             length="20"
     *         
     */
    public String getCnhnumero() {
        return this.cnhnumero;
    }

    public void setCnhnumero(String cnhnumero) {
        this.cnhnumero = cnhnumero;
    }

    /** 
     *            @hibernate.property
     *             column="cnhcategoria"
     *             length="6"
     *         
     */
    public String getCnhcategoria() {
        return this.cnhcategoria;
    }

    public void setCnhcategoria(String cnhcategoria) {
        this.cnhcategoria = cnhcategoria;
    }

    /** 
     *            @hibernate.property
     *             column="cnhemissao"
     *             length="4"
     *         
     */
    public Date getCnhemissao() {
        return this.cnhemissao;
    }

    public void setCnhemissao(Date cnhemissao) {
        this.cnhemissao = cnhemissao;
    }

    /** 
     *            @hibernate.property
     *             column="cnhvalidade"
     *             length="4"
     *         
     */
    public Date getCnhvalidade() {
        return this.cnhvalidade;
    }

    public void setCnhvalidade(Date cnhvalidade) {
        this.cnhvalidade = cnhvalidade;
    }

    /** 
     *            @hibernate.one-to-one
     *             class="com.gilbertoca.gfi.ger.model.Pessoa"
     *             outer-join="auto"
     *             constrained="true"
     *         
     */
    public com.gilbertoca.gfi.geral.model.Pessoa getGerPessoa() {
        return this.gerPessoa;
    }

    public void setGerPessoa(com.gilbertoca.gfi.geral.model.Pessoa gerPessoa) {
        this.gerPessoa = gerPessoa;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("cdpessoa", getCdpessoa())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof PessoaDocumento) ) return false;
        PessoaDocumento castOther = (PessoaDocumento) other;
        return new EqualsBuilder()
            .append(this.getCdpessoa(), castOther.getCdpessoa())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCdpessoa())
            .toHashCode();
    }

}
