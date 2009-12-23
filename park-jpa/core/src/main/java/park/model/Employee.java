package park.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@Table(name="EMPLOYEE")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CD_EMPLOYEE")
	private Integer cdEmployee;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_ADMISSION", nullable=false)
	private Date dtAdmission;

	@Column(length=20)
	private String password;

	@Column(length=20)
	private String username;

	//bi-directional many-to-one association to Parking
        @ManyToOne
	@JoinColumn(name="CD_PARKING", nullable=false)
	private Parking parking;

	//bi-directional many-to-one association to Stay
	@OneToMany(mappedBy="employee1")
	private Set<Stay> stays1;

	//bi-directional many-to-one association to Stay
	@OneToMany(mappedBy="employee2")
	private Set<Stay> stays2;

    public Employee() {
    }

	public Integer getCdEmployee() {
		return this.cdEmployee;
	}

	public void setCdEmployee(Integer cdEmployee) {
		this.cdEmployee = cdEmployee;
	}

	public Date getDtAdmission() {
		return this.dtAdmission;
	}

	public void setDtAdmission(Date dtAdmission) {
		this.dtAdmission = dtAdmission;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Parking getParking() {
		return this.parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}
	
	public Set<Stay> getStays1() {
		return this.stays1;
	}

	public void setStays1(Set<Stay> stays1) {
		this.stays1 = stays1;
	}
	
	public Set<Stay> getStays2() {
		return this.stays2;
	}

	public void setStays2(Set<Stay> stays2) {
		this.stays2 = stays2;
	}
	
}