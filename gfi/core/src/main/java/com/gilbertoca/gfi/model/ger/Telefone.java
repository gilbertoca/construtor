package com.gilbertoca.gfi.ger.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="ger.ger_telefone"
 *     
*/
public class Telefone implements Serializable {

    /** identifier field */
    private com.gilbertoca.gfi.ger.model.TelefonePK comp_id;

    /** nullable persistent field */
    private String tptelefone;

    /** full constructor */
    public Telefone(com.gilbertoca.gfi.ger.model.TelefonePK comp_id, String tptelefone) {
        this.comp_id = comp_id;
        this.tptelefone = tptelefone;
    }

    /** default constructor */
    public Telefone() {
    }

    /** minimal constructor */
    public Telefone(com.gilbertoca.gfi.ger.model.TelefonePK comp_id) {
        this.comp_id = comp_id;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *         
     */
    public com.gilbertoca.gfi.ger.model.TelefonePK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.gilbertoca.gfi.ger.model.TelefonePK comp_id) {
        this.comp_id = comp_id;
    }

    /** 
     *            @hibernate.property
     *             column="tptelefone"
     *             length="30"
     *         
     */
    public String getTptelefone() {
        return this.tptelefone;
    }

    public void setTptelefone(String tptelefone) {
        this.tptelefone = tptelefone;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Telefone) ) return false;
        Telefone castOther = (Telefone) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
