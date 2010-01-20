package park.model;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private long idPerson;
    private String address;
    private String name;
    private PersonType pType;
    public Person() {
    }

    public Person(long idPerson) {
        this.idPerson = idPerson;
    }

    public Person(long idPerson, String name, PersonType pType) {
        this.idPerson = idPerson;
        this.name = name;
        this.pType = pType;
    }

    public long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(long idPerson) {
        this.idPerson = idPerson;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonType getPType() {
        return pType;
    }

    public void setPType(PersonType pType) {
        this.pType = pType;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idPerson ^ (idPerson >>> 32));
		result = prime * result + ((pType == null) ? 0 : pType.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (idPerson != other.idPerson)
			return false;
		if (pType == null) {
			if (other.pType != null)
				return false;
		} else if (!pType.equals(other.pType))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [address=");
		builder.append(address);
		builder.append(", idPerson=");
		builder.append(idPerson);
		builder.append(", name=");
		builder.append(name);
		builder.append(", pType=");
		builder.append(pType);
		builder.append("]");
		return builder.toString();
	}
    
}
