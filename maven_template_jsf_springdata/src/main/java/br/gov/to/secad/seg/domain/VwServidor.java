package br.gov.to.secad.seg.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "vw_servidor", schema = "reca")
@NamedQueries({
    @NamedQuery(name = "VwServidor.findAll", query = "SELECT v FROM VwServidor v"),
    @NamedQuery(name = "VwServidor.findByMatricula", query = "SELECT v FROM VwServidor v WHERE v.matricula = :matricula"),
    @NamedQuery(name = "VwServidor.findAllByLotacao", query = "SELECT v FROM VwServidor v WHERE v.lotacao = :lotacao"),
    @NamedQuery(name = "VwServidor.findAllByOrgaoId", query = "SELECT v FROM VwServidor v WHERE v.orgaoId = :orgaoId"),
    @NamedQuery(name = "VwServidor.findByPessoaId", query = "SELECT v FROM VwServidor v WHERE v.pessoaId = :pessoaId"),
    @NamedQuery(name = "VwServidor.findByNome", query = "SELECT v FROM VwServidor v WHERE v.nome = :nome"),
    @NamedQuery(name = "VwServidor.findOneByCpf", query = "SELECT v FROM VwServidor v WHERE v.cpf = :cpf"),
    @NamedQuery(name = "VwServidor.findOneByOrgaoCpf", query = "SELECT v FROM VwServidor v WHERE v.orgaoId = :orgaoId AND v.cpf = :cpf")})
public class VwServidor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "cpf", length = 11)
    private String cpf;
    @Column(name = "matricula", length = 11)
    private String matricula;
    @Column(name = "lotacao")
    private Integer lotacao;
    @Column(name = "orgao_id")
    private Integer orgaoId;
    @Column(name = "pessoa_id")
    private Integer pessoaId;
    @Column(name = "nome", length = 100)
    private String nome;
    // bi-directional many-to-one association to Cargo
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private VwCargo vwCargo;
    @Column(name = "cargocomissao_id", length = 10)
    private String cargoComissaoId;

    public VwServidor() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getLotacao() {
        return lotacao;
    }

    public void setLotacao(Integer lotacao) {
        this.lotacao = lotacao;
    }

    public Integer getOrgaoId() {
        return orgaoId;
    }

    public void setOrgaoId(Integer orgaoId) {
        this.orgaoId = orgaoId;
    }

    public Integer getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Integer pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public VwCargo getVwCargo() {
        return vwCargo;
    }

    public void setVwCargo(VwCargo vwCargo) {
        this.vwCargo = vwCargo;
    }

    public String getCargoComissaoId() {
        return cargoComissaoId;
    }

    public void setCargoComissaoId(String cargoComissaoId) {
        this.cargoComissaoId = cargoComissaoId;
    }

    @Override
    public String toString() {
        return "VwServidor{" + "cpf=" + cpf + ", matricula=" + matricula + ", lotacao=" + lotacao + ", orgaoId=" + orgaoId + ", pessoaId=" + pessoaId + ", nome=" + nome + ", vwCargo=" + vwCargo.getId() + ", cargoComissaoId=" + cargoComissaoId + '}';
    }

}
