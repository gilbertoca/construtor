package com.gilbertoca.igreja.service;

import com.gilbertoca.igreja.model.Localidad;
import com.gilbertoca.igreja.model.Region;
import com.gilbertoca.igreja.model.security.Usuario;
import com.gilbertoca.igreja.model.user.Vendedor;
import com.gilbertoca.igreja.service.base.BaseService;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.apache.log4j.Logger;

public class UsuarioService extends BaseService {

    private static final Logger LOGGER = Logger.getLogger(UsuarioService.class);

    
    public static UsuarioService get() {
        return new UsuarioService();
    }
   
    public List<Usuario> buscar() {
        String hql = "select a from Usuario a where a.borrado = 0 order by a.apellido";
        return getEntityManager().createQuery(hql).getResultList();
    }

    public List<Usuario> buscar(String apellido) {
        String hql = "select a from Usuario a where a.borrado = 0 and "  +
                "(a.apellido like '%" + apellido + "%')";
        return getEntityManager().createQuery(hql).getResultList();
      
    }

    /*
    public List<Usuario> buscar(long dniUsuario) {
      //  String hql = "select a from Usuario a where a.borrado = 0 and CAST(a.dni) AS var_char(15) ='" + String.valueOf(dniUsuario) + "'";
        String hql = "select a from Usuario a where a.borrado = 0 and a.dni='" + dniUsuario + "'";
        return getEntityManager().createQuery(hql).getResultList();
   }
     * */
/*
    public Usuario getUsuarioPorDni(long dniUsuario) {
        Usuario Usuario = null;
        String hql = "select a from Usuario a where a.borrado = 0 and a.dni =" + dniUsuario;
        List<Usuario> Usuarios = getEntityManager().createQuery(hql).getResultList();
        if (Usuarios.size() == 1) {
            Usuario = Usuarios.get(0);
        }
        return Usuario;
    }
*/
    public Iterable<Localidad> getAllLocalidades() {
        List<Localidad> localidades = getEntityManager().createQuery("select l from Localidad l order by l.nombre").getResultList();
        if (localidades == null) {
            return new ArrayList<Localidad>();
        } else {
            return localidades;
        }
    }

    public Iterable<Region> getAllRegiones() {
        List<Region> regiones = getEntityManager().createQuery("select r from Region r order by r.nombre").getResultList();
        if (regiones == null) {
            return new ArrayList<Region>();
        } else {
            return regiones;
        }
    }
/*
    public Medico getMedicoPorDNI(String dniMedico) {
        Medico medico = null;
        String hql = "select m from Medico m where m.informacionPersonal.dni =" + dniMedico;
        List<Medico> medicos = getEntityManager().createQuery(hql).getResultList();
        if (medicos.size() == 1) {
            medico = medicos.get(0);
        }
        return medico;
    }
*/


/*
    public Ortopedista getOrtopedistaPorDNI(String dniOrtopedista) {
        Ortopedista ortopedista = null;
        String hql = "select o from Ortopedista o where o.informacionPersonal.dni =" + dniOrtopedista;
        List<Ortopedista> ortopedistas = getEntityManager().createQuery(hql).getResultList();
        if (ortopedistas.size() == 1) {
            ortopedista = ortopedistas.get(0);
        }
        return ortopedista;
    }
*/
    public Integer getSiguienteNroAfiliado() {
        String hql = "SELECT nextval('cliente_seq')";
        Integer r = 0;

        try{
            List l =  getEntityManager()
                        .createNativeQuery(hql)
                        .setMaxResults(1)
                        .getResultList();
            r=Integer.parseInt(((Vector)l.get(0)).get(0).toString());
        }catch (javax.persistence.NoResultException e){
        }

        return r;
    }

     public Integer getSiguienteNroVendedor() {
        String hql = "SELECT nextval('vendedor_seq')";
        Integer r = 0;

        try{
            List l =  getEntityManager()
                        .createNativeQuery(hql)
                        .setMaxResults(1)
                        .getResultList();
            r=Integer.parseInt(((Vector)l.get(0)).get(0).toString());
        }catch (javax.persistence.NoResultException e){
        }

        return r;
    }
}
