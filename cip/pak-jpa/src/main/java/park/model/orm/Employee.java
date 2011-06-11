package park.model.orm;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id; //references NATURAL_PERSON.PERSON_ID
    private Date dtAdmission;
    private Collection<Stay> staysOutgoing;
    private Collection<Stay> staysEntrance;
    private NaturalPerson naturalPerson;  //references NATURAL_PERSON.PERSON_ID
    private Parking parking;

    public Employee() {
    }

    public Employee(Long id) {
        this.id = id;
    }

    public Employee(Long id, Date dtAdmission) {
        this.id = id;
        this.dtAdmission = dtAdmission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDtAdmission() {
        return dtAdmission;
    }

    public void setDtAdmission(Date dtAdmission) {
        this.dtAdmission = dtAdmission;
    }

    public Collection<Stay> getStaysOutgoing() {
        return staysOutgoing;
    }

    public void setStaysOutgoing(Collection<Stay> staysOutgoing) {
        this.staysOutgoing = staysOutgoing;
    }

    public Collection<Stay> getStaysEntrance() {
        return staysEntrance;
    }

    public void setStaysEntrance(Collection<Stay> staysEntrance) {
        this.staysEntrance = staysEntrance;
    }

    public NaturalPerson getNaturalPerson() {
        return naturalPerson;
    }

    public void setNaturalPerson(NaturalPerson naturalPerson) {
        this.naturalPerson = naturalPerson;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.Employee[id=" + id + "]";
    }

}
