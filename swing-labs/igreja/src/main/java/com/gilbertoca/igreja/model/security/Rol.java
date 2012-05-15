/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gilbertoca.igreja.model.security;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Mono
 */
@Entity
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator="rol_gen")
    @SequenceGenerator(name="rol_seq",sequenceName="rol_seq", allocationSize=1)
    private Long id;

    private String nombre;
    
    private String descripcion;

    @ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    private List<Accion> acciones;

    /*Acciones*/
    public static final String  COD_CREAR = "CRMO_ROL" ;
    public static final String  COD_MODIFICAR = "CRMO_ROL" ;
    public static final String  COD_ELIMINAR = "ELIM_ROL" ;
    public static final String  COD_CONFIGURAR = "CONF_ROL" ;


    public List<Accion> getAcciones() {
        return acciones;
    }

   
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass().equals(Rol.class)) {
            return ((Rol)obj).getId().equals(this.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        hash = 43 * hash + (this.getNombre() != null ? this.getNombre().hashCode() : 0);
        hash = 43 * hash + (this.getDescripcion() != null ? this.getDescripcion().hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return getNombre();
    }

    /**
     * @return the version
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version the version to set
    public void setVersion(Integer version) {
        this.version = version;
    }
*/
    /**
     * @param acciones the acciones to set
     */
    public void setAcciones(List<Accion> acciones) {
        this.acciones = acciones;
    }

    public Boolean tieneAccion(Accion a) {
        return this.tieneAccion(a.getClave());
    }
    
    public Boolean tieneAccion(String codigoAccion) {
        Boolean resul = false;
        
        for (Accion a : this.getAcciones() ){
            resul = resul || a.getClave().equals(codigoAccion);
        }

        return resul;

    }

    public void agregarAccion(Accion a) {
        this.getAcciones().add(a);
    }

    public void quitarAccion(Accion a) {
        this.getAcciones().remove(a);
    }
    
}
