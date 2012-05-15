package com.gilbertoca.igreja.service;

import com.gilbertoca.igreja.model.Parametro;
import com.gilbertoca.igreja.service.base.BaseService;
import java.util.List;
import org.apache.log4j.Logger;

public class ParametroService extends BaseService {

    private static final Logger LOGGER = Logger.getLogger(ParametroService.class);

    public static ParametroService get() {
        return new ParametroService();
    }
    
    public Parametro getParametro() {
        Parametro p = null;
        String hql = "select p from Parametro p ";
        List<Parametro> ps = getEntityManager().createQuery(hql).getResultList();
        if (ps.size() == 1) {
            p = ps.get(0);
        }
        return p;
    }
   
}
