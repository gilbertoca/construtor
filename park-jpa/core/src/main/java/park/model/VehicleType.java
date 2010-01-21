package park.model;

import java.io.Serializable;
import java.util.Collection;

public class VehicleType implements Serializable {
    private static final long serialVersionUID = 1L;
    private String V_TYPE;
    private String manufacturer;
    private String model;
    private Collection<Vehicle> vehicleCollection;

    public VehicleType() {
    }

    public VehicleType(String V_TYPE) {
        this.V_TYPE = V_TYPE;
    }

    public String getV_TYPE() {
        return V_TYPE;
    }

    public void setV_TYPE(String V_TYPE) {
        this.V_TYPE = V_TYPE;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Collection<Vehicle> getVehicleCollection() {
        return vehicleCollection;
    }

    public void setVehicleCollection(Collection<Vehicle> vehicleCollection) {
        this.vehicleCollection = vehicleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (V_TYPE != null ? V_TYPE.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VehicleType)) {
            return false;
        }
        VehicleType other = (VehicleType) object;
        if ((this.V_TYPE == null && other.V_TYPE != null) || (this.V_TYPE != null && !this.V_TYPE.equals(other.V_TYPE))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.VehicleType[V_TYPE=" + V_TYPE + "]";
    }

}
