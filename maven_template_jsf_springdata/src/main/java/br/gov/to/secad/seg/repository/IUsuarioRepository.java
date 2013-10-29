
package br.gov.to.secad.seg.repository;

import br.gov.to.secad.seg.domain.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    /**
     *  Corresponde à consulta NATIVA nomeada Usuario.findAllByOrgao filtradas por cargo
     * @return list lista com entidade Usuario  
     */
    List<Usuario> findAllByOrgao(@Param("orgaoId") Integer id);
    /**
     * Corresponde à consulta nomeada Quantitativo.findOneByLogin
     * <p/>
     * @param String login
     * @return Usuario o Usuario
     */
    Usuario findOneByLogin(@Param("login") String login);
    
    /*
    VwServidor findServidorByCpf(String cpf);
    VwServidor findServidorByOrgaoByCpf(int orgao,String cpf);
    */
}
