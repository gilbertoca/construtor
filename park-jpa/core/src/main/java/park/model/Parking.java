package park.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the parking database table.
 * 
 */
@Entity
@Table(name="PARKING")
public class Parking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CD_PARKING")
	private Integer cdParking;

	@Column(nullable=false, length=100)
	private String address;

	@Column(name="PARKING_SPACES")
	private Integer parkingSpaces;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="parking")
	private Set<Employee> employees;

	//bi-directional many-to-one association to Stay
	@OneToMany(mappedBy="parking")
	private Set<Stay> stays;

    public Parking() {
    }

	public Integer getCdParking() {
		return this.cdParking;
	}

	public void setCdParking(Integer cdParking) {
		this.cdParking = cdParking;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getParkingSpaces() {
		return this.parkingSpaces;
	}

	public void setParkingSpaces(Integer parkingSpaces) {
		this.parkingSpaces = parkingSpaces;
	}

	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	public Set<Stay> getStays() {
		return this.stays;
	}

	public void setStays(Set<Stay> stays) {
		this.stays = stays;
	}
	
}