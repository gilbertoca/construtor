package park.service.util;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import park.model.LegalEntity;

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

    public static void create(Serializable obj) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist((Object) obj);
        em.getTransaction().commit();
    }

    public static Object retrieve(Class clz, Serializable key) {
        return getEntityManager().find(clz, (Object) key);
    }

    public static Object update(Serializable obj) {
        Serializable objReturn;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            objReturn = (Serializable) em.merge((Object) obj);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return objReturn;
    }

    public static void delete(Serializable obj) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.remove((Object) obj);
        em.getTransaction().commit();
    }
    public static void delete(String namedQuery, String parameter, Object value) {
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery(namedQuery);
        query.setParameter(parameter, value);
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();

    }
    public static List namedQuery(String namedQuery, String parameter, Object value) {
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery(namedQuery);
        query.setParameter(parameter, value);
        return query.getResultList();
    }

}
