
package br.to.gov.secad.service;

import br.to.gov.secad.model.Pessoa;

/**
 *
 * @author gilberto.andrade
 */
public interface IPessoaService {

    public Pessoa buscaPorCPF(String cpf);
    
}
