package park.model.annotation;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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

@Entity
@Table(name="employee")
@NamedQueries({
    @NamedQuery(name="Employee.findAll",query="SELECT l FROM Employee l"),
    @NamedQuery(name="Employee.deleteById",query="DELETE FROM Employee l WHERE l.id = :id")
})
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="person_id")
    private Long id; //references NATURAL_PERSON.PERSON_ID
    @Temporal(TemporalType.DATE)
    @Column(name="dt_admission")
    private Date dtAdmission;
    @OneToMany(targetEntity=Stay.class, mappedBy="employeeOutgoing")
    private Collection<Stay> staysOutgoing;
    @OneToMany(targetEntity=Stay.class, mappedBy="employeeEntrance")
    private Collection<Stay> staysEntrance;
    @OneToOne(targetEntity=NaturalPerson.class)
    @JoinColumn(name="person_id", referencedColumnName="person_id")
    private NaturalPerson naturalPerson;  //references NATURAL_PERSON.PERSON_ID
    @ManyToOne
    @JoinColumn(name="parking_id", referencedColumnName="id")
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
