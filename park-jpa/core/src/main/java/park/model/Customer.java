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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "customer")
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")})
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cd_customer")
    private Integer cdCustomer;
    @Column(name = "payment_day")
    private Integer paymentDay;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdCustomer")
    private Collection<Vehicle> vehicleCollection;
    @JoinColumn(name = "cd_person", referencedColumnName = "cd_person")
    @OneToOne(optional = false)
    private Person cdPerson;

    public Customer() {
    }

    public Customer(Integer cdCustomer) {
        this.cdCustomer = cdCustomer;
    }

    public Integer getCdCustomer() {
        return cdCustomer;
    }

    public void setCdCustomer(Integer cdCustomer) {
        this.cdCustomer = cdCustomer;
    }

    public Integer getPaymentDay() {
        return paymentDay;
    }

    public void setPaymentDay(Integer paymentDay) {
        this.paymentDay = paymentDay;
    }

    public Collection<Vehicle> getVehicleCollection() {
        return vehicleCollection;
    }

    public void setVehicleCollection(Collection<Vehicle> vehicleCollection) {
        this.vehicleCollection = vehicleCollection;
    }

    public Person getCdPerson() {
        return cdPerson;
    }

    public void setCdPerson(Person cdPerson) {
        this.cdPerson = cdPerson;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdCustomer != null ? cdCustomer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.cdCustomer == null && other.cdCustomer != null) || (this.cdCustomer != null && !this.cdCustomer.equals(other.cdCustomer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.Customer[cdCustomer=" + cdCustomer + "]";
    }

}
