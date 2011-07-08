package park.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date dtAdmission;
    private Collection<Stay> staysOutgoing;
    private Collection<Stay> staysEntrance;
    private NaturalPerson naturalPerson;
    private Parking parking;

    public Employee() {
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

}
