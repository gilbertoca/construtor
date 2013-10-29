package br.gov.to.secad.seg.service;

import br.gov.to.secad.seg.domain.EventType;
import br.gov.to.secad.seg.domain.Grupo;
import br.gov.to.secad.seg.domain.Usuario;
import br.gov.to.secad.seg.repository.IGrupoRepository;
import br.gov.to.secad.seg.repository.IUsuarioRepository;
import br.gov.to.secad.seg.domain.VwServidor;
import br.gov.to.secad.seg.repository.IVwOrgaoRepository;
import br.gov.to.secad.seg.repository.IVwServidorRepository;
import java.util.List;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe agrupamento de serviços. Aqui agruparemos os serviços referentes às
 * seguintes classes de domínio: Usuario, Grupo e Permissoes. <p/>
 * <p/>
 * @author gilberto
 */
@Service
public class GroupOneSEGService {

    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IGrupoRepository grupoRepository;
    @Autowired
    private IVwServidorRepository vwServidorRepository;
    @Autowired
    private IVwOrgaoRepository vwOrgaoRepository;
    //@Autowired
    //private IAuditoriaService auditoriaService;
    

    public GroupOneSEGService() {
    }

    //Usuario
    public Usuario saveUsuario(Usuario q) {
        Usuario _p = null;
        EventType _eventType = (q.getId() == null ? EventType.CREATED : EventType.UPDATED);
        _p = usuarioRepository.save(q);
        //auditoriaService.audit(_p, _p.getId(), "Usuario", _eventType);
        return _p;
    }
    public void deleteUsuario(Usuario p) {
        //auditoriaService.audit(p, p.getId(), "Usuario", EventType.DELETED);
        usuarioRepository.delete(p);        
    }
    public List<Usuario> findAllUsuario() {return usuarioRepository.findAll();}
    public Usuario findOneUsuario(Integer id) {return usuarioRepository.findOne(id);}
    public long size() {return usuarioRepository.count();}
    public Usuario findByLogin(String login) {return usuarioRepository.findOneByLogin(login);}
    public List<Usuario> findAllByOrgao(Integer orgaoId) {
		return usuarioRepository.findAllByOrgao(orgaoId);
    }
    /**
     * Método para verificar login e comparar a senha ciptografada
     */
    public Usuario logIn(String login, String senha) {
        Usuario result = findByLogin(login);
        boolean passwordEqual = false;
        if (result != null) {
            BasicTextEncryptor passwordDecryptor = new BasicTextEncryptor();
            passwordDecryptor.setPassword("Secad*1;Sugep*2;Dmasi*3.");
            String decryptedPassword = passwordDecryptor.decrypt(result.getSenha());
            passwordEqual = decryptedPassword.equals(senha);
        }
        return (result == null || (!passwordEqual)) ? Usuario.GUEST_USER : result;
    }

    //Grupo
    public Grupo saveGrupo(Grupo p) {return grupoRepository.save(p);}
    public void deleteGrupo(Grupo p) {grupoRepository.delete(p);}
    public List<Grupo> findAllGrupo() {return grupoRepository.findAll();}
    public Grupo findOneGrupo(Integer id) {return grupoRepository.findOne(id);}
    
    //VwServidor|VwOrgao 
    //TODO: deveriamos remover esta dependencia de Usuario?
    public VwServidor findServidorByCpf(String cpf){
        return vwServidorRepository.findOneByCpf(cpf);
    }
    public VwServidor findServidorByOrgaoByCpf(Integer orgaoId, String cpf){
        return vwServidorRepository.findOneByOrgaoCpf(orgaoId, cpf);
    }
}
