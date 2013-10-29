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
@Table(name = "vw_quadro", schema = "reca")
@NamedQueries({
    @NamedQuery(name = "VwQuadro.findAll", query = "SELECT v FROM VwQuadro v"),
    @NamedQuery(name = "VwQuadro.findAllValidos", query = "SELECT v FROM VwQuadro v WHERE v.id in ('1','2','3','4','5','6','7','8','9','10','11','20')"),
    @NamedQuery(name = "VwQuadro.findById", query = "SELECT v FROM VwQuadro v WHERE v.id = :id"),
    @NamedQuery(name = "VwQuadro.findByDescricao", query = "SELECT v FROM VwQuadro v WHERE v.descricao = :descricao")})
public class VwQuadro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", length = 10)
    private String id;
    @Column(name = "descricao", length = 60)
    private String descricao;

    public VwQuadro() {
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

    @Override
    public String toString() {
        return "VwQuadro{" + "id=" + id + ", descricao=" + descricao + '}';
    }

}
