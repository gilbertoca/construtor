package br.gov.to.secad.seg.domain;

import br.gov.to.secad.seg.domain.Grupo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the permissoes database table.
 * 
 */

@Entity
@Table(schema = "reca", name = "Permissoes")
@NamedQueries({
        @NamedQuery(name = "Permissoes.findAll", query = "SELECT p FROM Permissoes p")
        //@NamedQuery(name = "Permissoes.findByGrupoId", query = "SELECT p FROM Permissoes p where p.grupos.id= :grupoId")
})
public class Permissoes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="permissoes_id_seq")
        @SequenceGenerator(schema="reca", name="permissoes_id_seq", sequenceName="permissoes_id_seq", allocationSize=1)
	private Integer id;

	private String token;

	private String url;

	//bi-directional many-to-many association to Grupo
	@ManyToMany(mappedBy="permissoes")
	private List<Grupo> grupos;

    public Permissoes() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Grupo> getGrupos() {
		return this.grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
}