package com.gilbertoca.igreja.service;

import com.gilbertoca.igreja.model.Localidad;
import com.gilbertoca.igreja.service.base.BaseService;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class LocalidadService extends BaseService {

     public static LocalidadService get() {
        LocalidadService ls =  new LocalidadService();
        ls.refreshEntityManager();
        return ls;
    }

     public List<Localidad> getLocalidades(String valor){
        String hql = "select l from Localidad l where lower(l.nombre) like :valor  order by l.nombre ";
        return getEntityManager().createQuery(hql).setParameter("valor", valor.toLowerCase()+"%").getResultList();
    }

   public List<Localidad> getAll(){
         String hql = "select l from Localidad l order by l.nombre ";
        return getEntityManager().createQuery(hql).getResultList();
    }

}
