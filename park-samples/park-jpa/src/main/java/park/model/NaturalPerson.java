package park.model;

import java.io.Serializable;
import java.util.Date;

public class NaturalPerson extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date dtBirth;
    private String legalDocument;
    private Employee employee;
    
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
