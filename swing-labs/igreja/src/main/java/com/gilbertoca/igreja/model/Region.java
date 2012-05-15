package com.gilbertoca.igreja.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Administrador
 * @version 1.0
 * @created 31-dic-2010 03:58:52 p.m.
 */
@Entity
@Table(name="region")
public class Region implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="region_gen")
    @SequenceGenerator(name="region_gen",sequenceName="region_gen", allocationSize=1)
    private Long id;

    private String nombre;

    public Region(){

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

    public String toString(){
        return this.getNombre();
    }

}