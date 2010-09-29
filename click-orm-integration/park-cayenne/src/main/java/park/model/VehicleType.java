package park.model;

import java.io.Serializable;

public class VehicleType implements Serializable {
    private static final long serialVersionUID = 1L;
    private String vType;
    private String manufacturer;
    private String model;

    public VehicleType() {
    }

    public VehicleType(String vType) {
        this.vType = vType;
    }

    public VehicleType(String vType, String manufacturer, String model) {
        this.vType = vType;
        this.manufacturer = manufacturer;
        this.model = model;
    }


    public String getVtype() {
        return vType;
    }

    public void setVtype(String vType) {
        this.vType = vType;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vType != null ? vType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VehicleType)) {
            return false;
        }
        VehicleType other = (VehicleType) object;
        if ((this.vType == null && other.vType != null) || (this.vType != null && !this.vType.equals(other.vType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.VehicleType[V_TYPE=" + vType + "]";
    }

}
