package park.model.annotation;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
@Entity
@Table(name="natural_person")
@DiscriminatorValue("NP")
@PrimaryKeyJoinColumn(name="person_id",referencedColumnName="id")
@NamedQueries({
    @NamedQuery(name="NaturalPerson.findAll",query="SELECT n FROM NaturalPerson n"),
    @NamedQuery(name="NaturalPerson.findByName",query="SELECT n FROM NaturalPerson n WHERE n.name LIKE :name"),@NamedQuery(name="NaturalPerson.deleteById",query="DELETE FROM NaturalPerson n WHERE n.id = :id")
})
public class NaturalPerson extends Person{
    private static final long serialVersionUID = 1L;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="dt_birth")
    private Date dtBirth;
    @Basic
    @Column(name="legal_document", length=20)
    private String legalDocument;
    
    public NaturalPerson() {
    }

    public NaturalPerson(String address, String name, Date dtBirth, String legalDocument) {
        super(address, name);
        this.dtBirth = dtBirth;
        this.legalDocument = legalDocument;
    }


    public Date getDtBirth() {
        return dtBirth;
    }

    public void setDtBirth(Date dtBirth) {
        this.dtBirth = dtBirth;
    }

    public String getLegalDocument() {
        return legalDocument;
    }

    public void setLegalDocument(String legalDocument) {
        this.legalDocument = legalDocument;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dtBirth == null) ? 0 : dtBirth.hashCode());
		result = prime * result
				+ ((legalDocument == null) ? 0 : legalDocument.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		NaturalPerson other = (NaturalPerson) obj;
		if (dtBirth == null) {
			if (other.dtBirth != null)
				return false;
		} else if (!dtBirth.equals(other.dtBirth))
			return false;
		if (legalDocument == null) {
			if (other.legalDocument != null)
				return false;
		} else if (!legalDocument.equals(other.legalDocument))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NaturalPerson [dtBirth=");
		builder.append(dtBirth);
		builder.append(", legalDocument=");
		builder.append(legalDocument);
		builder.append("]");
		return builder.toString();
	}

}
