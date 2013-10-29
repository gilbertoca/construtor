package br.gov.to.secad.seg.domain;

import java.io.Serializable;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Representa a entidade Usuário mantida pela tabela seg.usuario no banco de
 * dados.
 *
 */
@Entity
@Table(schema = "reca", name = "Usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u ORDER BY u.nome"),
    @NamedQuery(name = "Usuario.findByLoginAndSenha", query = "SELECT u FROM Usuario u where u.login= :login and u.senha = :senha"),
    @NamedQuery(name = "Usuario.findByLogin", query = "SELECT u FROM Usuario u where u.login= :login")
})
@NamedNativeQuery(name = "Usuario.findAllByOrgao", query = "SELECT * FROM reca.usuario u INNER JOIN reca.vw_servidor s ON u.cpf=s.cpf WHERE s.orgao_id= :orgaoId ORDER BY u.nome", resultClass = Usuario.class)
public class Usuario implements Serializable {

    public static Usuario GUEST_USER = new Usuario("Convidado", "");
    private static final long serialVersionUID = 1L;

    public boolean isNew() {
        return getId() == null;
    }

    public boolean hasPersistentIdentity() {
        return !isNew();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq")
    @SequenceGenerator(schema = "reca", name = "usuario_id_seq", sequenceName = "usuario_id_seq", allocationSize = 1)
    private Integer id;
    private String cpf;
    @Column(name = "dt_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCriacao;
    @Column(name = "dt_ultimo_acesso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltimoAcesso;
    private String email;
    private String login;
    private String nome;
    /**
     * Atributo para setar o usuário que está autenticado
     */
    @Transient
    private String userId;
    private String senha;
    //bi-directional many-to-many association to Grupo
    @ManyToMany
    @JoinTable(schema = "reca", name = "usuario_grupo", joinColumns = {
        @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
        @JoinColumn(name = "grupo_id", referencedColumnName = "id")
    })
    private List<Grupo> grupos = new ArrayList<Grupo>();

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario() {
    }

    public boolean hasPermissoes(String permissoes) {
        for (Grupo grupo : grupos) {
            for (Permissoes permissao : grupo.getPermissoes()) {
                if (permissao.getToken().equals(permissoes) || permissao.getToken().equals("all")) {
                    return true;
                }
            }
        }
        return false;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDtCriacao() {
        return this.dtCriacao;
    }

    public void setDtCriacao(Date dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public Date getDtUltimoAcesso() {
        return this.dtUltimoAcesso;
    }

    public void setDtUltimoAcesso(Date dtUltimoAcesso) {
        this.dtUltimoAcesso = dtUltimoAcesso;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Grupo> getGrupos() {
        return this.grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
	 * @param nome the nome to set
	 */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
	 * @return the nome
	 */
    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", cpf=" + cpf + ", dtCriacao=" + dtCriacao + ", dtUltimoAcesso=" + dtUltimoAcesso + ", email=" + email + ", login=" + login + ", nome=" + nome + ", userId=" + userId + ", senha=" + senha + '}';
    }
    
}
