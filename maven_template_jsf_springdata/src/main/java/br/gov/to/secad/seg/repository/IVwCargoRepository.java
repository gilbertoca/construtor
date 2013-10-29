package br.gov.to.secad.seg.repository;

import br.gov.to.secad.seg.domain.VwCargo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface IVwCargoRepository extends JpaRepository<VwCargo, String> {

    /**
     *  Corresponde à consulta nomeada VwCargo.findAllInQuadroList filtrada
     *  por uma lista de quadro 
     * @return list lista com entidade VwCargo 
     */
    List<VwCargo> findAllInQuadroList(@Param("quadroList") List<String> listaQuadros);
    
    /**
     *  Corresponde à consulta nomeada VwCargo.findAllInQuadro
     * @return list lista com entidade VwCargo filtradas por quadro  
     */
    List<VwCargo> findAllInQuadro();
}
