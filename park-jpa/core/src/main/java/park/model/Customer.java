package park.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;

/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name = "CUSTOMER")
@NamedQueries({
    @NamedQuery(name = Customer.FIND_ALL, query = "SELECT c FROM Customer c")})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "findAllCustomer";
    @Id
    @Column(name = "CD_CUSTOMER" )
    @GeneratedValue
    private Integer cdCustomer;

    @OneToOne
    @JoinColumn(name = "CD_PERSON")
    private Person person;
    
    @Column(name = "PAYMENT_DAY")
    private Integer paymentDay;
    // bi-directional many-to-one association to Vehicle
    @OneToMany(mappedBy = "customer")
    private Set<Vehicle> vehicles;

    public Customer() {
    }

    /**
     * @param cdCustomer
     * @param paymentDay
     */
    public Customer( Integer CdCustomer, Integer paymentDay) {
        this.cdCustomer = CdCustomer;
        this.paymentDay = paymentDay;
    }

    public Integer getCdPerson() {
        return this.cdCustomer;
    }

    /**
     * @param person
     *            the person to set
     */
    public void setPerson(Person person) {
        this.person = person;


    }

    /**
     * @return the person
     */
    public Person getPerson() {
        return person;


    }

    public Integer getPaymentDay() {
        return this.paymentDay;


    }

    public void setPaymentDay(Integer paymentDay) {
        this.paymentDay = paymentDay;


    }

    public Set<Vehicle> getVehicles() {
        return this.vehicles;


    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;


    }

    public String getName() {
        return getPerson().getName();

    }
}
