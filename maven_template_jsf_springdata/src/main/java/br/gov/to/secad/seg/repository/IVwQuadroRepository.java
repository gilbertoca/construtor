
package br.gov.to.secad.seg.repository;

import br.gov.to.secad.seg.domain.VwQuadro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface IVwQuadroRepository extends JpaRepository<VwQuadro, String> {
    
}
