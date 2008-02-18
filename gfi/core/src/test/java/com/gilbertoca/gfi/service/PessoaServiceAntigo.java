/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilbertoca.gfi.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gilberto
 */
public class PessoaServiceAntigo {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    private EntityManagerFactory emf = null;
    private EntityManager em = null;

    public PessoaServiceAntigo() {
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
