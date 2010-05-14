package park.service.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author gilberto
 */
public class JpaHelper {

    private static final ThreadLocal<EntityManager> THREAD_LOCAL = new ThreadLocal<EntityManager>();
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("parkPU");

    private JpaHelper() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }

    public static EntityManager getEntityManager() {
        EntityManager em = THREAD_LOCAL.get();
        if (em == null || !em.isOpen()) {
            // If EM isn't open, create a new one
            em = getEntityManagerFactory().createEntityManager();
            setEntityManager(em);
            return em;
        } else {
            return em;
        }
    }

    public static void setEntityManager(EntityManager em) {
        THREAD_LOCAL.set(em);
    }
}
