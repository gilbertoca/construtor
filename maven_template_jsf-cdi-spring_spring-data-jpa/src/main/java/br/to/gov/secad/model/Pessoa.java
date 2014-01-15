
package br.to.gov.secad.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author gilberto.andrade
 */
@Entity
@Table(name = "ger_pessoa", schema = "ger")
public class Pessoa implements Serializable{
    
    @Id
    private String cpf;
    private String nome;
    private String nomeMae;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "cpf=" + cpf + ", nome=" + nome + ", nomeMae=" + nomeMae + '}';
    }
}
