package br.to.gov.secad.view;

import br.to.gov.secad.service.IPessoaService;
import br.to.gov.secad.model.Pessoa;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author gilberto.andrade
 */
@Named
public class PessoaBean {

    private final Pessoa pessoa = new Pessoa();
    @Inject
    private IPessoaService service;
    
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void consultar() {
        Pessoa p;
        System.out.println("consulta executada para cpf: " + pessoa.getCpf());
        p = service.buscaPorCPF(pessoa.getCpf());
        System.out.println("Pessoa "+p);
    }
}
