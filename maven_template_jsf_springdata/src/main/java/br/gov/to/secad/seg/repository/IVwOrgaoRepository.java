
package br.gov.to.secad.seg.repository;

import br.gov.to.secad.seg.domain.VwOrgao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface IVwOrgaoRepository extends JpaRepository<VwOrgao, Integer> {
    /**
     *  Corresponde à consulta nomeada VwOrgao.findAllNotInQuantitativo
     * @return list lista com entidades VwOrgao não presente em Quantitativo
     */
    //List<VwOrgao> findAllNotInQuantitativo(@Param("processoId") Integer processoId);
    
}
