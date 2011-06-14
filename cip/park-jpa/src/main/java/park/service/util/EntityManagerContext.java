package park.service.util;


import javax.persistence.*;

public class EntityManagerContext {

    /** The EntityManageractory. */
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;
    private static final String DEFAULT_PERSISTENCE_UNIT = "PU";

    static {
        try {
            String persistenceUnit = System.getProperty("cip.orm.jpa.persistenceUnit");
            if (persistenceUnit == null) {
                persistenceUnit = DEFAULT_PERSISTENCE_UNIT;
            }
            ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(persistenceUnit);
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Get the EntityManager for the current Thread, creating one if neccessary.
     *
     * @return the EntityManager from the current Thread.
     */
    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }
}
