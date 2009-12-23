package park.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the stay database table.
 * 
 */
@Entity
@Table(name="STAY")
public class Stay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CD_STAY")
	private Integer cdStay;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_ENTRANCE", nullable=false)
	private Date dtEntrance;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_OUTGOING")
	private Date dtOutgoing;

	@Column(name="HR_ENTRANCE", nullable=false)
	private Time hrEntrance;

	@Column(name="HR_OUTGOING")
	private Time hrOutgoing;

	@Column(precision=131089)
	private BigDecimal price;

	private Integer status;

	//bi-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="CD_EMPLOYEE_ENTRANCE", nullable=false)
	private Employee employee1;

	//bi-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="CD_EMPLOYEE_OUTGOING", nullable=false)
	private Employee employee2;

	//bi-directional many-to-one association to Parking
    @ManyToOne
	@JoinColumn(name="CD_PARKING", nullable=false)
	private Parking parking;

	//bi-directional many-to-one association to Vehicle
    @ManyToOne
	@JoinColumn(name="LICENSE_PLATE", nullable=false)
	private Vehicle vehicle;

    public Stay() {
    }

	public Integer getCdStay() {
		return this.cdStay;
	}

	public void setCdStay(Integer cdStay) {
		this.cdStay = cdStay;
	}

	public Date getDtEntrance() {
		return this.dtEntrance;
	}

	public void setDtEntrance(Date dtEntrance) {
		this.dtEntrance = dtEntrance;
	}

	public Date getDtOutgoing() {
		return this.dtOutgoing;
	}

	public void setDtOutgoing(Date dtOutgoing) {
		this.dtOutgoing = dtOutgoing;
	}

	public Time getHrEntrance() {
		return this.hrEntrance;
	}

	public void setHrEntrance(Time hrEntrance) {
		this.hrEntrance = hrEntrance;
	}

	public Time getHrOutgoing() {
		return this.hrOutgoing;
	}

	public void setHrOutgoing(Time hrOutgoing) {
		this.hrOutgoing = hrOutgoing;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Employee getEmployee1() {
		return this.employee1;
	}

	public void setEmployee1(Employee employee1) {
		this.employee1 = employee1;
	}
	
	public Employee getEmployee2() {
		return this.employee2;
	}

	public void setEmployee2(Employee employee2) {
		this.employee2 = employee2;
	}
	
	public Parking getParking() {
		return this.parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}
	
	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
}