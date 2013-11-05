package br.gov.to.secad.modulo.repository;

import com.googlecode.construtor.modulo.repository.IEstadoCivilRepository;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.googlecode.construtor.modulo.domain.EstadoCivil;
import java.util.Arrays;

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
        estadoCivil = new EstadoCivil(1, "CASADO");
    }

    @Test
    public void saveFinOne() {
        repository.save(estadoCivil);
        assertNotNull("Não deve retornar um objeto NULO ", repository.findOne(1));
    }

    @Test
    public void saveFindAll() throws Exception {
        List<EstadoCivil> scList = Arrays.asList(new EstadoCivil(1, "CASADO"),new EstadoCivil(2, "SOLTEIRO"),new EstadoCivil(3, "DIVORCIADO"));
        repository.save(scList);
        List<EstadoCivil> estadoCivils = repository.findAll();
        assertEquals("É verdadeiro quando for 3 itens", 3, estadoCivils.size());
    }
}