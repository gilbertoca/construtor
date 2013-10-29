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
@Table(name = "vw_municipio", schema = "reca")
@NamedQueries({
    @NamedQuery(name = "VwMunicipio.findAll", query = "SELECT v FROM VwMunicipio v ORDER BY v.descricao"),
    @NamedQuery(name = "VwMunicipio.findById", query = "SELECT v FROM VwMunicipio v WHERE v.id = :id"),
    @NamedQuery(name = "VwMunicipio.findByDescricao", query = "SELECT v FROM VwMunicipio v WHERE v.descricao = :descricao"),
    @NamedQuery(name = "VwMunicipio.findByCep", query = "SELECT v FROM VwMunicipio v WHERE v.cep = :cep"),
    @NamedQuery(name = "VwMunicipio.findByUf", query = "SELECT v FROM VwMunicipio v WHERE v.uf = :uf")})
public class VwMunicipio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", columnDefinition="INT4")
    private Long id;
    @Column(name = "descricao", length = 60)
    private String descricao;
    @Column(name = "cep", length = 8)
    private String cep;
    @Column(name = "uf", length = 2)
    private String uf;

    public VwMunicipio() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "VwMunicipio{" + "id=" + id + ", descricao=" + descricao + ", cep=" + cep + ", uf=" + uf + '}';
    }

}
