package park.model;

import java.io.Serializable;
import java.util.Collection;

public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    private String licensePlate;
    private String color;
    private Customer customer;
    private PriceTable priceTable;
    private VehicleType vType;
    private Collection<Stay> stays;

    public Vehicle() {
    }

    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PriceTable getPriceTable() {
        return priceTable;
    }

    public void setPriceTable(PriceTable priceTable) {
        this.priceTable = priceTable;
    }

    public VehicleType getVtype() {
        return vType;
    }

    public void setVtype(VehicleType vType) {
        this.vType = vType;
    }

    public Collection<Stay> getStays() {
        return stays;
    }

    public void setStays(Collection<Stay> stays) {
        this.stays = stays;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (licensePlate != null ? licensePlate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehicle)) {
            return false;
        }
        Vehicle other = (Vehicle) object;
        if ((this.licensePlate == null && other.licensePlate != null) || (this.licensePlate != null && !this.licensePlate.equals(other.licensePlate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.Vehicle[licensePlate=" + licensePlate + "]";
    }

}
