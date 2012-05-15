/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gilbertoca.igreja.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Gisela Marin
 */
@Entity
@Table(name = "parametro")
public class Parametro implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator="parametro_seq")
    @SequenceGenerator(name="parametro_seq",sequenceName="parametro_seq", allocationSize=1)
    private Long id;

    private boolean borrado;

    /*Acciones*/
    public static final String  COD_CREAR = "CRMO_PAR" ;
    public static final String  COD_MODIFICAR = "CRMO_PAR" ;
    public static final String  COD_ELIMINAR = "ELIM_PAR" ;

    public Parametro(){
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
