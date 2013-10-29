package br.gov.to.secad.seg.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "vw_orgao", schema = "reca")
@NamedQueries({
    @NamedQuery(name = "VwOrgao.findAll", query = "SELECT v FROM VwOrgao v"),
    @NamedQuery(name = "VwOrgao.findById", query = "SELECT v FROM VwOrgao v WHERE v.id = :id"),
    @NamedQuery(name = "VwOrgao.findByCnpj", query = "SELECT v FROM VwOrgao v WHERE v.cnpj = :cnpj"),
    @NamedQuery(name = "VwOrgao.findByDescricao", query = "SELECT v FROM VwOrgao v WHERE v.descricao = :descricao"),
    @NamedQuery(name = "VwOrgao.findByMunicipioId", query = "SELECT v FROM VwOrgao v WHERE v.municipioId = :municipioId")})

public class VwOrgao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "cnpj", length = 20)
    private String cnpj;
    @Column(name = "descricao", length = 100)
    private String descricao;
    @Column(name = "municipio_id")
    private Integer municipioId;

    public VwOrgao() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(Integer municipioId) {
        this.municipioId = municipioId;
    }

    @Override
    public String toString() {
        return "VwOrgao{" + "id=" + id + ", cnpj=" + cnpj + ", descricao=" + descricao + ", municipioId=" + municipioId + '}';
    }

}
