package park.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the vehicle database table.
 * 
 */
@Entity
@Table(name="VEHICLE")
@NamedQueries({
    @NamedQuery(name = Vehicle.FIND_ALL, query = "SELECT v FROM Vehicle v")
})
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_ALL = "findAllVehicle";
	@Id
	@Column(name="LICENSE_PLATE", length=20)
	private String licensePlate;

	@Column(length=20)
	private String color;

	//bi-directional many-to-one association to Stay
	@OneToMany(mappedBy="vehicle")
	private Set<Stay> stays;

	//bi-directional many-to-one association to Customer
    @ManyToOne
	@JoinColumn(name="CD_CUSTOMER", nullable=false)
	private Customer customer;
    @OneToOne
    @JoinColumn(name="CD_PRICE_TABLE")
    private PriceTable priceTable;

    @OneToOne
    @JoinColumn(name="VTYPE")
	private VehicleType vehicleType;

    public Vehicle() {
    }

	/**
	 * @param licensePlate
	 * @param color
	 * @param stays
	 * @param customer
	 * @param priceTable
	 * @param vehicleType
	 */
	public Vehicle(String licensePlate, String color, Customer customer, PriceTable priceTable, VehicleType vehicleType) {
		super();
		this.licensePlate = licensePlate;
		this.color = color;
		this.customer = customer;
		this.priceTable = priceTable;
		this.vehicleType = vehicleType;
	}

	public String getLicensePlate() {
		return this.licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Set<Stay> getStays() {
		return this.stays;
	}

	public void setStays(Set<Stay> stays) {
		this.stays = stays;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public PriceTable getPricetable() {
		return this.priceTable;
	}

	public void setPricetable(PriceTable pricetable) {
		this.priceTable = pricetable;
	}
	
	public VehicleType getVehicletype() {
		return this.vehicleType;
	}

	public void setVehicletype(VehicleType vehicletype) {
		this.vehicleType = vehicletype;
	}
	
}