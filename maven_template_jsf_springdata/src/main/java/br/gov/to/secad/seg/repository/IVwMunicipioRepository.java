
package br.gov.to.secad.seg.repository;

import br.gov.to.secad.seg.domain.VwMunicipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface IVwMunicipioRepository extends JpaRepository<VwMunicipio, Long> {
    
}
