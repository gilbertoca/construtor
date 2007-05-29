package com.gilbertoca.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * Base class para execução de testes DAO.
 * @author gilbertoca
 */
public abstract class BaseDaoTestCase extends AbstractTransactionalDataSourceSpringContextTests {
    protected final Log log = LogFactory.getLog(getClass());

    protected String[] getConfigLocations() {
        //setAutowireMode(AUTOWIRE_BY_NAME);
        return new String[] {
                "classpath*:/applicationContext-resources.xml",
                "classpath*:/applicationContext-dao.xml"
            };
    }
}