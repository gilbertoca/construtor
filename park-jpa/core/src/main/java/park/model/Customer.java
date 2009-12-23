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
@NamedQueries( { @NamedQuery(name = Customer.FIND_ALL, query = "SELECT c FROM Customer c") })
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_ALL = "findAllCustomer";
	@Id
	@Column(name = "CD_CUSTOMER")
	private Integer cdCustomer;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CD_CUSTOMER", insertable = false, updatable = false)
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
	public Customer(Integer cdCustomer, Integer paymentDay) {
		this.cdCustomer = cdCustomer;
		this.paymentDay = paymentDay;
	}

	public Integer getCdCustomer() {
		return this.cdCustomer;
	}

	public void setCdCustomer(Integer cdCustomer) {
		this.cdCustomer = cdCustomer;
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