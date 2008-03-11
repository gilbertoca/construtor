package com.gilbertoca.gfi.venda.model;

import com.gilbertoca.gfi.component.Endereco;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 *        @hibernate.class
 *         table="construtor.venda_cliente"
 *	
*/
public class Cliente implements Serializable{
    private Integer cdCliente;
    private String nomeRazaoSocial;
    private Endereco endereco = new Endereco();
    private String telefone;
    private String celular;
    private String fax;
    private String email;
    private String nomeContato;
    private String contatoCargo;

    private Boolean flPessoaFisica = new Boolean(true);//pessoa f�sica ou não
    private String sexo = "M";//Sendo M ou F
    private String estadoCivil = "SOLTEIRO";//Sendo:OUTROS,SOLTEIRO,CASADO,VI�VO,SEPARADO,DIVORCIADO,UNI�O EST�VEL;

    private String cpfCnpj;
    private String rgInscricaoEstadual;
    private Date dtCadastro = new Date();
    private Date dtNascimento;
    private String naturalidade; //Cidade-UF
    private String nomeEmprego; //ou escola, se estudante
    private String profissao;
    private String empregoTelefone;
    private Date dtAdmissaoEmpregoAtual;
    private double rendaMensal = 0.0;
    private String nomePai;
    private String nomeMae;
    private String logradouroFiliacao;
    private String cepFiliacao;
    private double limiteCredito = 0.0;
    private boolean flCreditoBloqueado = false;
    private String nomeReferenciaPessoal;
    private String telefoneReferenciaPessoal;
    //private Funcionario vendedor = new Funcionario();
    private Integer cdFuncionario; //vendedor preferido
    /*
     * campos que dever�o ser calculados:
     *  dtPrimeiraCompra
     *  dtUltimaCompra
     *  valorPrimeiraCompra
     * 	valorMediaCompra
     *  valorNumeroCompra
     *  numeroCompra
     * 
     * poderiamos tamb�m colocar:
     * 	corPele
     *  corCabelo
     *  altura
     * 	peso
     */
    
    private Set pedidos = Collections.EMPTY_SET;
    private Set orcamentos = Collections.EMPTY_SET;
    private Set compras = Collections.EMPTY_SET;

    
    /**
     * @return Returns the vendedor.
     * @hibernate.many-to-one
     *  insert="false" update="false" cascade="none" column="cd_funcionario"
     *  outer-join="true"
     * @return a populated funcionario object (based on the cdFuncionario)
     */
    /*public Funcionario getVendedor() {
        return vendedor;
    }
    /**
     * @param vendedor The vendedor to set.
     */
    /*public void setVendedor(Funcionario vendedor) {
        this.vendedor = vendedor;
    }*/
    /**
     * @return Returns the nomeReferenciaPessoal.
     * @hibernate.property column="nome_referencia_pessoal" length = "60"
     */
    public String getNomeReferenciaPessoal() {
        return nomeReferenciaPessoal;
    }
    /**
     * @param nomeReferenciaPessoal The nomeReferenciaPessoal to set.
     */
    public void setNomeReferenciaPessoal(String nomeReferenciaPessoal) {
        this.nomeReferenciaPessoal = nomeReferenciaPessoal;
    }
    /**
     * @return Returns the telefoneReferenciaPessoal.
     * @hibernate.property column="telefone_referencia_pessoal" length = "30"
     */
    public String getTelefoneReferenciaPessoal() {
        return telefoneReferenciaPessoal;
    }
    /**
     * @param telefoneReferenciaPessoal The telefoneReferenciaPessoal to set.
     */
    public void setTelefoneReferenciaPessoal(String telefoneReferenciaPessoal) {
        this.telefoneReferenciaPessoal = telefoneReferenciaPessoal;
    }
    /**
     * @return Returns the cdFuncionario.
     * @hibernate.property column="cd_funcionario"
     */
    public Integer getCdFuncionario() {
        return cdFuncionario;
    }
    /**
     * @param cdFuncionario The cdFuncionario to set.
     * 	@spring.validator type="required" 
     */
    public void setCdFuncionario(Integer cdFuncionario) {
        this.cdFuncionario = cdFuncionario;
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
     * @return Returns the cepFiliacao.
     * @hibernate.property column="cep_filiacao" length = "8"
     */
    public String getCepFiliacao() {
        return cepFiliacao;
    }
    /**
     * @param cepFiliacao The cepFiliacao to set.
     */
    public void setCepFiliacao(String cepFiliacao) {
        this.cepFiliacao = cepFiliacao;
    }
    /**
     * @return Returns the compras.
     * @hibernate.set lazy="true" inverse = "true" order-by="cd_cliente" cascade = "save-update"
     * @hibernate.collection-key column="cd_cliente" 
     * @hibernate.collection-one-to-many class = "org.appfuse.model.venda.Venda"
     * 
     */
    public Set getCompras() {
        return compras;
    }
    /**
     * @param compras The compras to set.
     */
    public void setCompras(Set compras) {
        this.compras = compras;
    }
    /**
     * @return Returns the contatoCargo.
     * @hibernate.property column="contato_cargo" length = "40"
     */
    public String getContatoCargo() {
        return contatoCargo;
    }
    /**
     * @param contatoCargo The contatoCargo to set.
     */
    public void setContatoCargo(String contatoCargo) {
        this.contatoCargo = contatoCargo;
    }
    /**
     * @return Returns the dtAdmissaoEmpregoAtual.
     * @hibernate.property column="dt_admissao_emprego_atual" 
     */
    public Date getDtAdmissaoEmpregoAtual() {
        return dtAdmissaoEmpregoAtual;
    }
    /**
     * @param dtAdmissaoEmpregoAtual The dtAdmissaoEmpregoAtual to set.
     */
    public void setDtAdmissaoEmpregoAtual(Date dtAdmissaoEmpregoAtual) {
        this.dtAdmissaoEmpregoAtual = dtAdmissaoEmpregoAtual;
    }
    /**
     * @return Returns the dtNascimento.
     * @hibernate.property column="dt_nascimento"
     */
    public Date getDtNascimento() {
        return dtNascimento;
    }
    /**
     * @param dtNascimento The dtNascimento to set.
     */
    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }
    /**
     * @return Returns the empregoTelefone.
     * @hibernate.property column="emprego_telefone" length = "30"
     */
    public String getEmpregoTelefone() {
        return empregoTelefone;
    }
    /**
     * @param empregoTelefone The empregoTelefone to set.
     */
    public void setEmpregoTelefone(String empregoTelefone) {
        this.empregoTelefone = empregoTelefone;
    }
    /**
     * @return Returns the estadoCivil.
     * @hibernate.property column="estado_civil" length = "20"
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }
    /**
     * @param estadoCivil The estadoCivil to set.
     * @spring.validator type="required"
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    /**
     * @return Returns the flCreditoBloqueado.
     * @hibernate.property column="fl_credito_bloqueado" length = "1"
     */
    public boolean getFlCreditoBloqueado() {
        return flCreditoBloqueado;
    }
    /**
     * @param flCreditoBloqueado The flCreditoBloqueado to set.
     */
    public void setFlCreditoBloqueado(boolean flCreditoBloqueado) {
        this.flCreditoBloqueado = flCreditoBloqueado;
    }
    /**
     * @return Returns the limiteCredito.
     * @hibernate.property column="limite_credito"
     */
    public double getLimiteCredito() {
        return limiteCredito;
    }
    /**
     * @param limiteCredito The limiteCredito to set.
     */
    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }
    /**
     * @return Returns the logradouroFiliacao.
     * @hibernate.property column="logradouro_filiacao" length = "100"
     */
    public String getLogradouroFiliacao() {
        return logradouroFiliacao;
    }
    /**
     * @param logradouroFiliacao The logradouroFiliacao to set.
     */
    public void setLogradouroFiliacao(String logradouroFiliacao) {
        this.logradouroFiliacao = logradouroFiliacao;
    }
    /**
     * @return Returns the naturalidade.
     * @hibernate.property column="naturalidade" length = "100"
     */
    public String getNaturalidade() {
        return naturalidade;
    }
    /**
     * @param naturalidade The naturalidade to set.
     */
    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }
    /**
     * @return Returns the nomeContato.
     * @hibernate.property column="nome_contato" length = "60"
     */
    public String getNomeContato() {
        return nomeContato;
    }
    /**
     * @param nomeContato The nomeContato to set.
     */
    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }
    /**
     * @return Returns the nomeEmprego.
     * @hibernate.property column="nome_emprego" length = "100"
     */
    public String getNomeEmprego() {
        return nomeEmprego;
    }
    /**
     * @param nomeEmprego The nomeEmprego to set.
     */
    public void setNomeEmprego(String nomeEmprego) {
        this.nomeEmprego = nomeEmprego;
    }
    /**
     * @return Returns the nomeMae.
     * @hibernate.property column="nome_mae" length = "60"
     */
    public String getNomeMae() {
        return nomeMae;
    }
    /**
     * @param nomeMae The nomeMae to set.
     * @spring.validator type="required"
     */
    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }
    /**
     * @return Returns the nomePai.
     * @hibernate.property column="nome_pai" length = "60"
     */
    public String getNomePai() {
        return nomePai;
    }
    /**
     * @param nomePai The nomePai to set.
     */
    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }
    /**
     * @return Returns the orcamentos.
     * @hibernate.set lazy="true" inverse = "true" order-by="cd_cliente" cascade = "save-update"
     * @hibernate.collection-key column="cd_cliente" 
     * @hibernate.collection-one-to-many class = "org.appfuse.model.venda.Orcamento"
     *
     */
    public Set getOrcamentos() {
        return orcamentos;
    }
    /**
     * @param orcamentos The orcamentos to set.
     */
    public void setOrcamentos(Set orcamentos) {
        this.orcamentos = orcamentos;
    }
    /**
     * @return Returns the profissao.
     * @hibernate.property column="profissao" length = "40"
     */
    public String getProfissao() {
        return profissao;
    }
    /**
     * @param profissao The profissao to set.
     */
    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }
    /**
     * @return Returns the rendaMensal.
     * @hibernate.property column="renda_mensal"
     */
    public double getRendaMensal() {
        return rendaMensal;
    }
    /**
     * @param rendaMensal The rendaMensal to set.
     */
    public void setRendaMensal(double rendaMensal) {
        this.rendaMensal = rendaMensal;
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
    /** default constructor */
    public Cliente() {
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
     * @hibernate.property column="dt_cadastro" type = "date" not-null = "true" 
     * @return Returns the dtCadastro.
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
     * @hibernate.property column="sexo" length="1" 
     * @return Returns the sexo.
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo The sexo to set.
     * @spring.validator type="required"
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @hibernate.id generator-class="sequence" column="cd_cliente" unsaved-value = "null"
     * @hibernate.generator-param name = "sequence" value = "construtor.venda_cliente_sequence"
     * @return Integer
     */
    public Integer getCdCliente() {
        return this.cdCliente;
    }

    public void setCdCliente(Integer cdCliente) {
        this.cdCliente = cdCliente;
    }

    /**
     *            @hibernate.property
     *             column="email"
     *             length="80"
     *             not-null="true"
     *
     */
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *            @hibernate.property
     *             column="nome_Razao_Social"
     *             length="100"
     *             not-null="true"
     *
     */
    public String getNomeRazaoSocial() {
        return this.nomeRazaoSocial;
    }
    /**
     * @param NomeRazaoSocial The NomeRazaoSocial to set.
     * @spring.validator type="required"
     */
    public void setNomeRazaoSocial(String nome) {
        this.nomeRazaoSocial = nome;
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
     * @hibernate.property  column="fl_pessoa_fisica" length="1"
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
     * @hibernate.set lazy="true" inverse = "true" order-by="cd_cliente" cascade = "save-update"
     * @hibernate.collection-key column="cd_cliente" 
     * @hibernate.collection-one-to-many class = "org.appfuse.model.venda.Pedido"
     *
     */
    public Set getPedidos() {
        return this.pedidos;
    }

    public void setPedidos(Set pedidos) {
        this.pedidos = pedidos;
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
    public String toString() {
        return new ToStringBuilder(this).append("cdCliente", getCdCliente())
                                        .toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof Cliente)) {
            return false;
        }

        Cliente castOther = (Cliente) other;

        return new EqualsBuilder().append(this.getCdCliente(),
            castOther.getCdCliente()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getCdCliente()).toHashCode();
    }
}
