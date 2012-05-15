package com.gilbertoca.igreja.model.util;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

//@Entity
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(length=30,name="DTYPE")
//@Table(name="tiposgenericosimples")
public abstract class TipoGenericoSimple implements Serializable {

//   @TableGenerator
//            ( allocationSize=1,
//              name="tipogenericosimple_gen",
//              table="tipogenericosimple",
//              valueColumnName="id_tipogenericosimple",
//              pkColumnValue="pk_tipogenericosimple",
//              pkColumnName = "key_tipogenericosimple"
//              )
//    @Id
//    @GeneratedValue(strategy=GenerationType.TABLE,generator="tipogenericosimple_gen")
    private Integer id;

    //@Column(length=50,nullable=false)
    private String descripcion;

    //@Column(nullable=false)
    private Boolean borrado;

    public TipoGenericoSimple() {
        borrado = Boolean.FALSE;
    }

    @Override
    public String toString() {
        return this.getDescripcion();
    }



    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

   
    /**
     * @return the borrado
     */
    public Boolean getBorrado() {
        return borrado;
    }

    /**
     * @param borrado the borrado to set
     */
    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   
    
}
