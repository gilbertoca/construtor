package com.googlecode.construtor.modulo.repository;

import com.googlecode.construtor.modulo.domain.EstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
/**
 * Interface representando um repositório do tipo { @link JpaRepository} que expoe CRUDs para a entidade { @link EstadoCivil}.
 * <p/>
 * @author Gilberto
 */
@Transactional(readOnly = true)
public interface IEstadoCivilRepository extends JpaRepository<EstadoCivil, Integer> {
}
