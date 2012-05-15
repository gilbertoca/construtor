package com.gilbertoca.igreja.service.security;

import com.gilbertoca.igreja.model.security.Accion;
import com.gilbertoca.igreja.model.security.Usuario;

/**
 *
 * @author Mono
 */
public class PermisosService {

    public static final String MENU_SEGURIDAD = "USU";
    
    public boolean usuarioTieneAccion(Usuario usuario, String a) {

        for (Accion accion: usuario.getAcciones()) {
            if (accion.getClave().equals(a)) {
                return true;
            }
        }

        return false;

    }
    public boolean checkMenuSeguridad(Usuario usuario) {
        return usuarioTieneAccion(usuario, MENU_SEGURIDAD);
    }
    
    
}
