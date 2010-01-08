/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package park.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "parking")
@NamedQueries({
    @NamedQuery(name = "Parking.findAll", query = "SELECT p FROM Parking p")})
public class Parking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cd_parking")
    private Integer cdParking;
    @Column(name = "parking_spaces")
    private Integer parkingSpaces;
    @JoinColumn(name = "cd_person", referencedColumnName = "cd_person")
    @ManyToOne(optional = false)
    private LegalEntity cdPerson;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdParking")
    private Collection<Stay> stayCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdParking")
    private Collection<Employee> employeeCollection;

    public Parking() {
    }

    public Parking(Integer cdParking) {
        this.cdParking = cdParking;
    }

    public Integer getCdParking() {
        return cdParking;
    }

    public void setCdParking(Integer cdParking) {
        this.cdParking = cdParking;
    }

    public Integer getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(Integer parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public LegalEntity getCdPerson() {
        return cdPerson;
    }

    public void setCdPerson(LegalEntity cdPerson) {
        this.cdPerson = cdPerson;
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
        hash += (cdParking != null ? cdParking.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parking)) {
            return false;
        }
        Parking other = (Parking) object;
        if ((this.cdParking == null && other.cdParking != null) || (this.cdParking != null && !this.cdParking.equals(other.cdParking))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.Parking[cdParking=" + cdParking + "]";
    }

}
