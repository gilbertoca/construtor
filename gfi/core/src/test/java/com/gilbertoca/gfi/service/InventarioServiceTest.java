/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.service;

import com.gilbertoca.gfi.model.inventario.Categoria;
import com.gilbertoca.gfi.model.inventario.Item;
import com.gilbertoca.gfi.model.inventario.Produto;
import com.gilbertoca.gfi.model.inventario.UnidadeMedida;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gilberto
 */
public class InventarioServiceTest {

    protected final Log log = LogFactory.getLog(getClass());
    private EntityManagerFactory emf = null;
    private EntityManager em = null;
    private static final String initId =
            "UPDATE gfi.id_gerador SET AID = 2 WHERE PK = ?";
    public InventarioServiceTest() {
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("annotatedPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery(initId).setParameter(1, "item_id_gerador").executeUpdate();
        em.createNativeQuery(initId).setParameter(1, "categoria_id_gerador").executeUpdate();
        em.createNativeQuery(initId).setParameter(1, "produto_id_gerador").executeUpdate();
        em.createNativeQuery("DELETE FROM gfi.item").executeUpdate();
        em.createNativeQuery("DELETE FROM gfi.produto").executeUpdate();
        em.createNativeQuery("DELETE FROM gfi.categoria").executeUpdate();
        em.createNativeQuery("DELETE FROM gfi.unidade_medida").executeUpdate();        
        //Necess�rio para o teste do produto
        em.createNativeQuery("INSERT INTO gfi.categoria (cd_categoria, descricao_categoria, dt_cadastro, nome_categoria) "+
                "VALUES (1, 'V�rios tipos de roupas.', '2007-12-17 15:26:08.586', 'Vestu�rio')").executeUpdate();
        //Necess�rio para o teste do item
        em.createNativeQuery("INSERT INTO gfi.produto (cd_produto, cd_categoria, dt_cadastro, nome_produto, VERSION, descricao_produto) "+
                "VALUES (1, 1, '2007-12-17 15:26:10.451', 'Cal�a Jeans', 1, 'Cal�a Jeans estilo sertanejo.') ").executeUpdate();
        em.createNativeQuery("INSERT INTO gfi.unidade_medida (cd_unidade_medida, descricao_unidade, VERSION) "+
                "VALUES ('MT', 'Metros', 1) ").executeUpdate();
        em.getTransaction().commit();
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();        
    }

    @Test
    public void persistRemoveUnidadeMedidaTest() {
        log.debug("\npersistRemoveUnidadeMedidaTest - Cria��o de uma inst�ncia da classe UnidadeMedida\n");
        String uCodigo = "KG";
        String uNome = "Kilogramas";
        UnidadeMedida uM = new UnidadeMedida(uCodigo, uNome);
        //assertEquals("cdVersion antes do m�todo persist:",-1, uM.getVersion());
        log.debug("\nObjeto antes grava��o: \n"+uM);
        em.getTransaction().begin();
        em.persist(uM);
        em.getTransaction().commit();
        log.debug("\nObjeto p�s grava��o: \n"+uM);
        assertNotNull("cdVersion p�s persist:",uM.getVersion());
    }

    @Test
    public void persistRemoveCategoriaTest() {
        log.debug("\npersistRemoveCategoriaTest - Cria��o de uma inst�ncia da classe Categoria\n");
        String cNome = "Sapatos";
        String cDescricao = "V�rios tipos de sapatos.";
        Categoria c = new Categoria(cNome, cDescricao);
        assertNull("cdCategoria antes do m�todo persist:",c.getCdCategoria());
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        log.debug("\nObjeto p�s grava��o: \n"+c);
        assertNotNull("cdCategoria p�s persist:",c.getCdCategoria());
    }
    @Test
    public void persistRemoveProdutoTest() {
        log.debug("\npersistRemoveProdutoTest - Cria��o de uma inst�ncia da classe Produto\n");
        String pNome = "Cal�a Jeans";
        String pDescricao = "Cal�a Jeans estilo sertanejo.";
        Categoria c = em.find(Categoria.class, 1);
        Produto p = new Produto(pNome, pDescricao, c.getCdCategoria());
        assertNull("cdProduto antes do m�todo persist:",p.getCdProduto());
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        log.debug("Objeto p�s grava��o: \n"+p);
        assertNotNull("cdProduto p�s persist:",p.getCdProduto());
        Produto p2 = em.find(Produto.class, 1);
        log.debug("Objeto carregado: \n"+p2);
        assertNotNull(p2.getCategoria());
    }
    @Test
    public void persistRemoveItemTest() {
        log.debug("\npersistRemoveItemTest - Cria��o de uma inst�ncia da classe Item\n");
        String nomeItem = "Cal�a";
        Integer cdItem = null;
        BigDecimal precoVenda = new BigDecimal(0.0F);
        BigDecimal precoCusto = new BigDecimal(0.0F);
        UnidadeMedida uM = em.find(UnidadeMedida.class, "MT");
        Float estoqueAtual = 0.0F;
        Float estoqueMinimo = 0.0F;
        Float nivelDeReposicao = 0.0F;
        Boolean flDescontinuado = false;
        Produto p = em.find(Produto.class, 1);
       
        Item i = new Item(nomeItem,precoVenda, precoCusto, uM.getCdUnidadeMedida(), estoqueAtual, estoqueMinimo, nivelDeReposicao, flDescontinuado, p.getCdProduto());
        assertNull("cdItem antes do m�todo persist:",i.getCdItem());
        i.setProduto(p);
        em.getTransaction().begin();
        em.persist(i);
        em.getTransaction().commit();
        log.debug("Objeto p�s grava��o: \n"+i);
        cdItem = i.getCdItem();
        i = null;
       
        assertNotNull("cdItem p�s persist:",cdItem);
        
        Item i2 = em.find(Item.class, cdItem);
        log.debug("Objeto carregado: \n"+i2);
        assertNotNull("N�o pode ser nulo:",i2.getProduto());
    }
    
}
