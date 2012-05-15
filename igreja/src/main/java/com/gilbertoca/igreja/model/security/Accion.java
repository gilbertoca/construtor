/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gilbertoca.igreja.model.security;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

/**
 *
 * @author Mono
 */
@Entity
public class Accion implements Serializable{

     @TableGenerator
            ( allocationSize=1,
              name="accion_gen",
              table="generador_accion",
              valueColumnName="id_accion",
              pkColumnValue="pk_accion",
              pkColumnName = "key_accion"
              )
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator="accion_gen")
    private Long id;

    @Version
    private Integer version;

    private String clave;
    private String nombre;

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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
    public String toString() {
        return getNombre();
    }
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass().equals(Accion.class)) {
            return ((Accion)obj).getId().equals(this.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.getClave() != null ? this.getClave().hashCode() : 0);
        hash = 71 * hash + (this.getNombre() != null ? this.getNombre().hashCode() : 0);
        return hash;
    }

    /**
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    
    
}
