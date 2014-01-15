package br.to.gov.secad.service;

import br.to.gov.secad.model.Pessoa;
import br.to.gov.secad.repository.IPessoaRepository;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PessoaService implements IPessoaService {

    @Inject
    private IPessoaRepository repository;

    @Override
    public Pessoa buscaPorCPF(String cpf) {
        return repository.findOne(cpf);
    }

}
