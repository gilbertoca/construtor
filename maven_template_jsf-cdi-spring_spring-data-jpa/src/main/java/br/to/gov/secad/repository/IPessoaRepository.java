
package br.to.gov.secad.repository;

import br.to.gov.secad.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gilberto.andrade
 */
public interface IPessoaRepository extends JpaRepository<Pessoa, String> {
    
}
