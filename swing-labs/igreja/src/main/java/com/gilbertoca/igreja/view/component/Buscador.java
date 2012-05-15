package com.gilbertoca.igreja.view.component;

/**
 *
 * @author Administrador
 */
public interface Buscador {

    /**
     *  Setea el objeto encontrado
     *
     * @param variable String por el cual se identifica el objeto en el buscador
     * @param objeto El objeto seleccionado
     */
    public void setObjeto (String variable, Object objeto);
}
