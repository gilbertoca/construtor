package park.model.orm;

import java.io.Serializable;

public class VehicleType implements Serializable {
    private static final long serialVersionUID = 1L;
    private String vehicleType;
    private String manufacturer;
    private String model;

    public VehicleType() {
    }

    public VehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VehicleType(String vehicleType, String manufacturer, String model) {
        this.vehicleType = vehicleType;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vehicleType != null ? vehicleType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VehicleType)) {
            return false;
        }
        VehicleType other = (VehicleType) object;
        if ((this.vehicleType == null && other.vehicleType != null) || (this.vehicleType != null && !this.vehicleType.equals(other.vehicleType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.VehicleType[V_TYPE=" + vehicleType + "]";
    }

}
