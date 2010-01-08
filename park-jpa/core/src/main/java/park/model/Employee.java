/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package park.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "employee")
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")})
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cd_employee")
    private Integer cdEmployee;
    @Basic(optional = false)
    @Column(name = "dt_admission")
    @Temporal(TemporalType.DATE)
    private Date dtAdmission;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdEmployeeEntrance")
    private Collection<Stay> stayCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdEmployeeOutgoing")
    private Collection<Stay> stayCollection1;
    @JoinColumn(name = "cd_person", referencedColumnName = "cd_person")
    @OneToOne(optional = false)
    private NaturalPerson cdPerson;
    @JoinColumn(name = "cd_parking", referencedColumnName = "cd_parking")
    @ManyToOne(optional = false)
    private Parking cdParking;

    public Employee() {
    }

    public Employee(Integer cdEmployee) {
        this.cdEmployee = cdEmployee;
    }

    public Employee(Integer cdEmployee, Date dtAdmission) {
        this.cdEmployee = cdEmployee;
        this.dtAdmission = dtAdmission;
    }

    public Integer getCdEmployee() {
        return cdEmployee;
    }

    public void setCdEmployee(Integer cdEmployee) {
        this.cdEmployee = cdEmployee;
    }

    public Date getDtAdmission() {
        return dtAdmission;
    }

    public void setDtAdmission(Date dtAdmission) {
        this.dtAdmission = dtAdmission;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Stay> getStayCollection() {
        return stayCollection;
    }

    public void setStayCollection(Collection<Stay> stayCollection) {
        this.stayCollection = stayCollection;
    }

    public Collection<Stay> getStayCollection1() {
        return stayCollection1;
    }

    public void setStayCollection1(Collection<Stay> stayCollection1) {
        this.stayCollection1 = stayCollection1;
    }

    public NaturalPerson getCdPerson() {
        return cdPerson;
    }

    public void setCdPerson(NaturalPerson cdPerson) {
        this.cdPerson = cdPerson;
    }

    public Parking getCdParking() {
        return cdParking;
    }

    public void setCdParking(Parking cdParking) {
        this.cdParking = cdParking;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdEmployee != null ? cdEmployee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.cdEmployee == null && other.cdEmployee != null) || (this.cdEmployee != null && !this.cdEmployee.equals(other.cdEmployee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.Employee[cdEmployee=" + cdEmployee + "]";
    }

}
