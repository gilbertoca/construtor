/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package br.gov.to.secad.seg.repository;

import br.gov.to.secad.seg.domain.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gilberto
 */
@Transactional(readOnly = true)
public interface IAuditRepository extends JpaRepository<Audit, Integer> {
    
}
