/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.service;

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
public class PessoaServiceTest {

    protected final Log log = LogFactory.getLog(getClass());
    private EntityManagerFactory emf = null;
    private EntityManager em = null;

    public PessoaServiceTest() {
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("metadataPU");
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();        
    }

    @Test
    public void hello() {}
}
