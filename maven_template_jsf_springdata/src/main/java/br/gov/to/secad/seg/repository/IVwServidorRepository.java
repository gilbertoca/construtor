
package br.gov.to.secad.seg.repository;

import br.gov.to.secad.seg.domain.VwServidor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface IVwServidorRepository extends JpaRepository<VwServidor, String> {
    /**
     *  Corresponde à consulta nomeada VwServidor.findAllByOrgaoId
     * @return list lista com entidade VwServidor válidas
     */
    List<VwServidor> findAllByOrgaoId(@Param("orgaoId") Integer orgaoId);
    /**
     *  Corresponde à consulta nomeada VwServidor.findOneByCpf
     * @return VwServidor o VwServidor
     */
    VwServidor findOneByCpf(@Param("cpf") String cpf);
    /**
     *  Corresponde à consulta nomeada VwServidor.findOneByOrgaoCpf
     * @return VwServidor o VwServidor
     */
    public VwServidor findOneByOrgaoCpf(Integer orgaoId, String cpf);
    
}
