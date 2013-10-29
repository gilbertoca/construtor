package br.gov.to.secad.seg.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * The persistent class for the grupo database table.
 * 
 */
@Entity
@Table(schema = "reca", name = "Grupo")
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT u FROM Grupo u"),
    @NamedQuery(name = "Grupo.findById", query = "SELECT u FROM Grupo u WHERE u.id = :id")
})
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="grupo_id_seq")
    @SequenceGenerator(schema="reca", name="grupo_id_seq", sequenceName="grupo_id_seq", allocationSize=1)
    private Integer id;
    private String descricao;
    //bi-directional many-to-many association to Permissoe
    @ManyToMany
    @JoinTable(schema = "reca",name = "grupo_permissoes", joinColumns = {
        @JoinColumn(name = "grupo_id")
    }, inverseJoinColumns = {
        @JoinColumn(name = "permissoes_id")
    })
    private List<Permissoes> permissoes = new ArrayList<Permissoes>();
    //bi-directional many-to-many association to Usuario
    @ManyToMany(mappedBy = "grupos", fetch=FetchType.EAGER,
    cascade={CascadeType.MERGE,CascadeType.PERSIST,  CascadeType.REFRESH})
    private List<Usuario> usuarios;

    public Grupo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Permissoes> getPermissoes() {
    	//return new ArrayList<Permissoes>(permissoes);
    	return permissoes;
    }

    public void setPermissoes(List<Permissoes> permissoes) {
        this.permissoes = permissoes;
    }

    public List<Usuario> getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
