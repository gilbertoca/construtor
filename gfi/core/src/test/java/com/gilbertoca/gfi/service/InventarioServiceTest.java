/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.service;

import com.gilbertoca.gfi.model.inventario.Categoria;
import com.gilbertoca.gfi.model.inventario.Produto;
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
            "UPDATE gfi.id_gerador SET id_valor = 0 WHERE id_nome = ?";
            //"UPDATE id_gerador SET id_valor = 0 WHERE id_nome = 'categoria_id_gerador';"+
            //"UPDATE id_gerador SET id_valor = 0 WHERE id_nome = 'produto_id_gerador';";
    public InventarioServiceTest() {
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("gfi-corePU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery(initId).setParameter(1, "item_id_gerador").executeUpdate();
        em.createNativeQuery(initId).setParameter(1, "categoria_id_gerador").executeUpdate();
        em.createNativeQuery(initId).setParameter(1, "produto_id_gerador").executeUpdate();
        em.createNativeQuery("DELETE FROM gfi.item").executeUpdate();
        em.createNativeQuery("DELETE FROM gfi.produto").executeUpdate();
        em.createNativeQuery("DELETE FROM gfi.categoria").executeUpdate();
        em.getTransaction().commit();
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();        
    }

    @Test
    public void createCategoriaTest() {
        log.debug("createCategoriaTest - cria��o de uma inst�ncia da classe Categoria");
        String cNome = "Vestu�rio";
        String cDescricao = "V�rios tipos de roupas.";
        Categoria c = new Categoria(cNome, cDescricao);
        assertNull("cdCategoria antes do m�todo persist:",c.getCdCategoria());
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        log.debug("Objeto p�s grava��o: \n"+c);
        assertNotNull("cdCategoria p�s persist:",c.getCdCategoria());
    }
    @Test
    public void createProdutoTest() {
        log.debug("createProdutoTest - cria��o de uma inst�ncia da classe Produto");
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
    
}
