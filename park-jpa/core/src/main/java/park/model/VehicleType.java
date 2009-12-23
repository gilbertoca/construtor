package park.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vehicletype database table.
 * 
 */
@Entity
@Table(name="VEHICLE_TYPE")
public class VehicleType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="VTYPE", length=20)
	private String vtype;

	@Column(length=50)
	private String manufacturer;

	@Column(length=20)
	private String model;

    public VehicleType() {
    }

	public String getVtype() {
		return this.vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}	
}