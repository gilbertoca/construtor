package com.gilbertoca.gfi.geral.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Nacionalidade implements Serializable {
    private static final long serialVersionUID = 1L;
    private Short cdNacionalidade;
    private String dcNacionalidade;

    public Nacionalidade() {
    }

    public Short getCdNacionalidade() {
        return cdNacionalidade;
    }

    public void setCdNacionalidade(Short cdNacionalidade) {
        this.cdNacionalidade = cdNacionalidade;
    }

    public String getDcNacionalidade() {
        return dcNacionalidade;
    }

    public void setDcNacionalidade(String dcNacionalidade) {
        this.dcNacionalidade = dcNacionalidade;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof Nacionalidade))
            return false;
        Nacionalidade castOther = (Nacionalidade) other;
        return new EqualsBuilder().append(cdNacionalidade,
        	castOther.cdNacionalidade).append(dcNacionalidade,
        	castOther.dcNacionalidade).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cdNacionalidade).append(
        	dcNacionalidade).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        	.append("cdNacionalidade", cdNacionalidade).append(
        		"dcNacionalidade", dcNacionalidade).toString();
    }

}
