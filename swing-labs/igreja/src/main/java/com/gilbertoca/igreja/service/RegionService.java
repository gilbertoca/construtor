package com.gilbertoca.igreja.service;

import com.gilbertoca.igreja.model.Region;
import com.gilbertoca.igreja.service.base.BaseService;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class RegionService extends BaseService {


   public static RegionService get() {
        return new RegionService();
    }

    public List<Region> getRegiones(String valor){
         String hql = "select r from Region r where lower(r.nombre) like :valor order by r.nombre ";
        return getEntityManager().createQuery(hql).setParameter("valor", valor.toLowerCase()+"%").getResultList();
    }

    public List<Region> getAll(){
         String hql = "select r from Region r order by r.nombre ";
        return getEntityManager().createQuery(hql).getResultList();
    }
}
