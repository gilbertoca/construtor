package com.gilbertoca.igreja.service;

import com.gilbertoca.igreja.model.generales.Version;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;

public class BaseService {

    private static final Logger logger = Logger.getLogger(BaseService.class);

    public static BaseService get() {
        return new BaseService();
    }

    protected EntityManager getEntityManager() {
        return ServiceFactory.em;
    }


    public String getVersion(){

        String hql = "SELECT v FROM Version v";
        Version version = null;
        try {
            EntityManager em = getEntityManager();
             version = (Version) em.createQuery(hql).getResultList().get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.valueOf(version.getNumero());
    }

    public <T> boolean save(T obj) throws Exception {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(obj);
            getEntityManager().getTransaction().commit();
        } catch (Exception ex) {
            getEntityManager().getTransaction().rollback();
            throw ex;
        }

        return true;
    }

    public <T> T update(T obj) throws Exception {
        try {
            getEntityManager().getTransaction().begin();
            obj = getEntityManager().merge(obj);
            getEntityManager().getTransaction().commit();
        } catch (Exception ex) {
            getEntityManager().getTransaction().rollback();
            throw ex;
        }
        return obj;
    }

    public <T> void remove(T obj) throws Exception {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().remove(obj);
            getEntityManager().getTransaction().commit();
        } catch (Exception ex) {
            getEntityManager().getTransaction().rollback();
            throw ex;
        }
    }

    public <T> T find(Class<T> clazz, Long id) {
        return getEntityManager().find(clazz, id);
    }

    public <Q> List<Q> getAll(Class<Q> clazz) {
        String hql = "select o from " + clazz.getSimpleName() + " o "; //"where o.borrado = false";
        return (List<Q>) getEntityManager().createQuery(hql).getResultList();
    }

    public static Logger getLogger() {
        return logger;
    }

    public int executeNativeSQL(String SQL) {
        getEntityManager().getTransaction().begin();
        int result = getEntityManager().createNativeQuery(SQL).executeUpdate();
        getEntityManager().getTransaction().commit();
        return result;
    }

    public <Q> List<Q> executeQuery(Class<Q> clazz, String hql) {
        return (List<Q>) getEntityManager().createQuery(hql).getResultList();
    }

    public void refreshEntityManager(){
        ServiceFactory.actualizarEntityManager();
    }
}
