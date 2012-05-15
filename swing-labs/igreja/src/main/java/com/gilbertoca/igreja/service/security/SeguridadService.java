package com.gilbertoca.igreja.service.security;

import com.gilbertoca.igreja.model.security.Accion;
import com.gilbertoca.igreja.model.security.Rol;
import com.gilbertoca.igreja.model.security.Usuario;
import com.gilbertoca.igreja.service.base.BaseService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;

public class SeguridadService extends BaseService {

    public static SeguridadService get() {
        return new SeguridadService();
    }    
    
    public List<Usuario> search(String criterio) {
        String hql = "select u from Usuario u where u.username like '%"+criterio+"%'";
        return getEntityManager().createQuery(hql).getResultList();
    }
    
    public List<Accion> getAllAcciones() {
        return getEntityManager().createQuery("select a from Accion a").getResultList();
    }
    
    public List<Rol> getAllRoles() {
        
        List<Rol> roles = getEntityManager().createQuery("select r from Rol r").getResultList();
        if (roles == null) {
            return new ArrayList<Rol>();
        } else {
            return roles;
        }
    }
    
   
    public Usuario login(String usuario, String clave) {

        String hql = "select u from Usuario u where u.username = :usu and u.password = :pss";
        Usuario usr = null;

        try{
            usr = (Usuario) getEntityManager().createQuery(hql)
                                .setParameter("usu", usuario)
                                .setParameter("pss", clave)
                                .getSingleResult();
        }catch (NoResultException none){
            //El usuario no se encuentra en la BD
        }

        return usr;
    }

    public Usuario getUsuarioPorNombreDeUsuario(String nombreUsr) {
       String hql = "select u from Usuario u where u.username = :usu ";
        Usuario usr = null;

        try{
            usr = (Usuario) getEntityManager().createQuery(hql)
                                .setParameter("usu", nombreUsr)
                                .getSingleResult();
        }catch (NoResultException e){
            //El usuario no se encuentra en la BD
        }

        return usr;
    }

    public List<Usuario> buscar(String text) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}
