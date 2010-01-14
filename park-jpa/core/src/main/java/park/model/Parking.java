package park.model;

import java.io.Serializable;
import java.util.Collection;

public class Parking implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idParking;
    private Integer parkingSpaces;
    private LegalEntity idPerson;
    private Collection<Stay> stayCollection;
    private Collection<Employee> employeeCollection;

    public Parking() {
    }

    public Parking(Integer idParking) {
        this.idParking = idParking;
    }

    public Integer getIdParking() {
        return idParking;
    }

    public void setIdParking(Integer idParking) {
        this.idParking = idParking;
    }

    public Integer getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(Integer parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public LegalEntity getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(LegalEntity idPerson) {
        this.idPerson = idPerson;
    }

    public Collection<Stay> getStayCollection() {
        return stayCollection;
    }

    public void setStayCollection(Collection<Stay> stayCollection) {
        this.stayCollection = stayCollection;
    }

    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParking != null ? idParking.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parking)) {
            return false;
        }
        Parking other = (Parking) object;
        if ((this.idParking == null && other.idParking != null) || (this.idParking != null && !this.idParking.equals(other.idParking))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.Parking[idParking=" + idParking + "]";
    }

}
