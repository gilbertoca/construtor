package park.model;

import java.io.Serializable;

public enum PersonType {
    NATURAL_PERSON,
    LEGAL_ENTITY
};

public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private long idPerson;
    private String address;
    private String name;
    private PersonType pType;
    private NaturalPerson naturalPerson;
    private Customer customer;
    private LegalEntity legalEntity;

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

    public NaturalPerson getNaturalPerson() {
        return naturalPerson;
    }

    public void setNaturalPerson(NaturalPerson naturalPerson) {
        this.naturalPerson = naturalPerson;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LegalEntity getLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(LegalEntity legalEntity) {
        this.legalEntity = legalEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerson != null ? idPerson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.idPerson == null && other.idPerson != null) || (this.idPerson != null && !this.idPerson.equals(other.idPerson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.Person[idPerson=" + idPerson + "]";
    }

}
