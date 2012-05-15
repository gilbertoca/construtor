package com.gilbertoca.igreja.service.base;

import com.gilbertoca.igreja.service.security.PermisosService;
import com.gilbertoca.igreja.view.Application;
import com.gilbertoca.igreja.view.dialog.ErrorDialog;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;

public abstract class ServiceFactory {

    static final Logger LOG = Logger.getLogger(ServiceFactory.class);

    public static final String SCHEMA = "base";
    private static EntityManagerFactory emf;
    public static EntityManager em;

    public static void initJPA() {
        try {
            emf = Persistence.createEntityManagerFactory("persistence");
            em = emf.createEntityManager();
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
            ErrorDialog.show("Ocurrio un error al intentar conectar con <br> la base de datos.<br>Asegurese que no existe otra instancia<br>de la aplicación ejecutandose.");
            Application.getApplication().exit();

        }

    }

    public static void actualizarEntityManager(){
        emf.close();
        emf = Persistence.createEntityManagerFactory("persistence");
        em = emf.createEntityManager();
      //  em.clear();
    }

    public static PermisosService createPermisosService() {
        return new PermisosService();
    }
}
