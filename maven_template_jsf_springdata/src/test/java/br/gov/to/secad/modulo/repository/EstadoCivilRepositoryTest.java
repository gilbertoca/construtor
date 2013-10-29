package br.gov.to.secad.modulo.repository;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import br.gov.to.secad.modulo.domain.EstadoCivil;

/**
 * Teste de integração mostrando o uso da classe { @link IEstadoCivilRepository}.
 * <p/>
 * @author Gilberto
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-jpa.xml")
@Transactional
public class EstadoCivilRepositoryTest {

    @Autowired
    IEstadoCivilRepository repository;
    EstadoCivil estadoCivil;

    @Before
    public void setUp() {
        //Atenção: NÃO HÁ NECESSIDADE DE CRIAR UM OBJETO POIS O MESMO JÁ
        //EXISTE E NÃO PODE SER MODIFICADO
        //estadoCivil = new EstadoCivil();
    }

    @Test
    public void saveFinOne() {
        assertNotNull("Não deve retornar um objeto NULO ", repository.findOne(1));
    }

    @Test
    public void saveFindAll() throws Exception {
        List<EstadoCivil> estadoCivils = repository.findAll();
        assertTrue("É verdadeiro quando for 7 itens", estadoCivils.size() == 7);
    }
}