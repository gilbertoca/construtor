package br.gov.to.secad.seg.repository;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import br.gov.to.secad.seg.domain.Usuario;
import java.util.Date;

/**
 * Teste de integração mostrando o uso da classe { @link IUsuarioRepository}.
 * <p/>
 * @author Gilberto
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jpa.xml")
@Transactional
public class UsuarioRepositoryTest {

    @Autowired
    IUsuarioRepository repository;
    Usuario usuario;

    @Before
    public void setUp() {

        usuario = new Usuario();
        usuario.setLogin("usuario1000");
        usuario.setSenha("senha1000");
        usuario.setDtCriacao(new Date());
        usuario.setDtUltimoAcesso(new Date());
        usuario.setEmail("usuario@secad.to.gov.br");
        usuario.setCpf("CPF1000");
        usuario.setUserId("usuario1000");
        usuario.setNome("usuario1000");
    }

    /**
     * Testa a inserção de um usuario e verifica se o mesmo pode ser carregado novamente.
     */
    @Test
    public void saveFinOne() {

        usuario = repository.save(usuario);
        
        System.out.println(usuario);         

        assertEquals(usuario, repository.findOne(usuario.getId()));
    }

    @Test
    public void saveFindAll() throws Exception {

        usuario = repository.save(usuario);

        List<Usuario> usuarios = repository.findAll();

        assertNotNull(usuarios);
        assertTrue(usuarios.contains(usuario));
    }

    /**
     * Testa a inserção de um usuario, logo depois verifica se o mesmo pode ser carregado novamente
     * para efetuar uma modificação
     */
    @Test
    public void saveFindOneSave() throws Exception {
        Usuario u2 = null;
        usuario = repository.save(usuario);

        System.out.println(usuario);         
        u2 = repository.findOne(usuario.getId());
        assertEquals(usuario, u2);
        usuario = new Usuario();
        u2.setSenha("12345678");
        usuario = repository.save(u2);
        System.out.println("apos o metodo save --> "+usuario);                 
        assertEquals(u2.getSenha(), usuario.getSenha());
    }

}