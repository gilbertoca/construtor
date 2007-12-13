package com.gilbertoca.gfi.model.ger;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 *        @hibernate.class
 *         table="ger.ger_email"
 *     
*/
public class Email implements Serializable {

    /** identifier field */
    private com.gilbertoca.gfi.model.ger.EmailPK comp_id;

    /** full constructor */
    public Email(com.gilbertoca.gfi.model.ger.EmailPK comp_id) {
        this.comp_id = comp_id;
    }

    /** default constructor */
    public Email() {
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *         
     */
    public com.gilbertoca.gfi.model.ger.EmailPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(com.gilbertoca.gfi.model.ger.EmailPK comp_id) {
        this.comp_id = comp_id;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Email) ) return false;
        Email castOther = (Email) other;
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
