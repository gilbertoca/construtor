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
@Table(name = "vw_cargo", schema = "reca")
@NamedQueries({
    @NamedQuery(name = "VwCargo.findAll", query = "SELECT v FROM VwCargo v"),
    @NamedQuery(name = "VwCargo.findAllInQuadro", query = "SELECT v FROM VwCargo v WHERE v.vwQuadro.id in ('1','2','3','4','5','6','7','8','9','10','11','20') AND v.flativo = true "),
    @NamedQuery(name = "VwCargo.findById", query = "SELECT v FROM VwCargo v WHERE v.id = :id"),
    @NamedQuery(name = "VwCargo.findAllInQuadroList", query = "SELECT v FROM VwCargo v WHERE v.vwQuadro.id in :quadroList  AND v.flativo = true "),
    @NamedQuery(name = "VwCargo.findByDescricao", query = "SELECT v FROM VwCargo v WHERE v.descricao = :descricao"),
    @NamedQuery(name = "VwCargo.findByFlativo", query = "SELECT v FROM VwCargo v WHERE v.flativo = :flativo"),
    @NamedQuery(name = "VwCargo.findByEscolaridade", query = "SELECT v FROM VwCargo v WHERE v.escolaridade = :escolaridade")})
public class VwCargo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", length = 10)
    private String id;
    @Column(name = "descricao", length = 60)
    private String descricao;
    @Column(name = "flativo")
    private Boolean flativo;
    @Column(name = "escolaridade", length = 50)
    private String escolaridade;
    @Column(name = "requisito", length = 255)
    private String requisito;
    @Column(name = "remuneracao", length = 9, precision = 2, columnDefinition="numerica(9,2)")
    private Double remuneracao;
  // bi-directional many-to-one association to QuadroProfissional
    @ManyToOne
    @JoinColumn(name = "quadro_id")
    private VwQuadro vwQuadro;

    public VwCargo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getFlativo() {
        return flativo;
    }

    public void setFlativo(Boolean flativo) {
        this.flativo = flativo;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public Double getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(Double remuneracao) {
        this.remuneracao = remuneracao;
    }

    public String getRequisito() {
        return requisito;
    }

    public void setRequisito(String requisito) {
        this.requisito = requisito;
    }

    public VwQuadro getVwQuadro() {
        return vwQuadro;
    }

    public void setVwQuadro(VwQuadro vwQuadro) {
        this.vwQuadro = vwQuadro;
    }

    @Override
    public String toString() {
        return "VwCargo{" + "id=" + id + ", descricao=" + descricao + ", flativo=" + flativo + ", escolaridade=" + escolaridade + ", requisito=" + requisito + ", remuneracao=" + remuneracao + ", vwQuadro=" + vwQuadro.getId() + '}';
    }
  
}
