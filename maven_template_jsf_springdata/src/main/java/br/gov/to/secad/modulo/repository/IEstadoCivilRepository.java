package br.gov.to.secad.modulo.repository;

import br.gov.to.secad.modulo.domain.EstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
/**
 * Interface representando um repositório do tipo { @link JpaRepository}.
 * <p/>
 * @author Gilberto
 */
@Transactional(readOnly = true)
public interface IEstadoCivilRepository extends JpaRepository<EstadoCivil, Integer> {
}
