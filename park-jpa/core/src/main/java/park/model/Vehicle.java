package park.model;

import java.io.Serializable;
import java.util.Collection;

public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    private String licensePlate;
    private String color;
    private Collection<Stay> stayCollection;
    private Customer idCustomer;
    private PriceTable idPriceTable;
    private VehicleType vType;

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

    public Collection<Stay> getStayCollection() {
        return stayCollection;
    }

    public void setStayCollection(Collection<Stay> stayCollection) {
        this.stayCollection = stayCollection;
    }

    public Customer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Customer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public PriceTable getIdPriceTable() {
        return idPriceTable;
    }

    public void setIdPriceTable(PriceTable idPriceTable) {
        this.idPriceTable = idPriceTable;
    }

    public VehicleType getVType() {
        return vType;
    }

    public void setVType(VehicleType vType) {
        this.vType = vType;
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
