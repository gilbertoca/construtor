package park.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idEmployee;
    private Date dtAdmission;
    private Collection<Stay> stayCollection;
    private Collection<Stay> stayCollection1;
    private NaturalPerson idPerson;
    private Parking idParking;

    public Employee() {
    }

    public Employee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Employee(Integer idEmployee, Date dtAdmission) {
        this.idEmployee = idEmployee;
        this.dtAdmission = dtAdmission;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Date getDtAdmission() {
        return dtAdmission;
    }

    public void setDtAdmission(Date dtAdmission) {
        this.dtAdmission = dtAdmission;
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

    public NaturalPerson getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(NaturalPerson idPerson) {
        this.idPerson = idPerson;
    }

    public Parking getIdParking() {
        return idParking;
    }

    public void setIdParking(Parking idParking) {
        this.idParking = idParking;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmployee != null ? idEmployee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.idEmployee == null && other.idEmployee != null) || (this.idEmployee != null && !this.idEmployee.equals(other.idEmployee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.Employee[idEmployee=" + idEmployee + "]";
    }

}
