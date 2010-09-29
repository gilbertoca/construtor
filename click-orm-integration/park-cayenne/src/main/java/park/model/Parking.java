package park.model;

import java.io.Serializable;
import java.util.Collection;

public class Parking implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String address;
    private Integer parkingSpace;//meters
    private Collection<Stay> stays;
    private Collection<Employee> employees;

    public Parking() {
    }

    public Parking(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(Integer parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public Collection<Stay> getStays() {
        return stays;
    }

    public void setStays(Collection<Stay> stays) {
        this.stays = stays;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
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
        if (!(object instanceof Parking)) {
            return false;
        }
        Parking other = (Parking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.Parking[id=" + id + "]";
    }

}
