package park.model.annotation;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer")
@NamedQueries({
    @NamedQuery(name="Customer.findAll",query="SELECT l FROM Customer l"),
    @NamedQuery(name="Customer.deleteById",query="DELETE FROM Customer l WHERE l.id = :id")
})
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="person_id")
    private Long id; //references PERSON.ID
    @Basic
    @Column(name="payment_day")
    private Integer paymentDay;
    @OneToMany(targetEntity=Vehicle.class, mappedBy="customer")
    private Collection<Vehicle> vehicles;
    @OneToOne(optional=false,targetEntity=Person.class)
    @JoinColumn(name="person_id", referencedColumnName="id")
    private Person person; //references PERSON.ID

    public Customer() { }
    public Customer(Long id) {this.id = id;}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Integer getPaymentDay() {return paymentDay;}
    public void setPaymentDay(Integer paymentDay) {this.paymentDay = paymentDay;}
    public Collection<Vehicle> getVehicles() {return vehicles;}
    public void setVehicles(Collection<Vehicle> vehicles) {this.vehicles = vehicles;}
    public Person getPerson() { return person;}
    public void setPerson(Person person) {this.person = person;}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.Customer[id=" + id + "]";
    }

}
