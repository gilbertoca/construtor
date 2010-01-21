package park.model;

import java.io.Serializable;
import java.util.Collection;

public class PriceTable implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idPriceTable;
    private String item;
    private Double price;
    private Collection<Vehicle> vehicleCollection;

    public PriceTable() {
    }

    public PriceTable(String item, Double price) {
        this.item = item;
        this.price = price;
    }

    public Integer getIdPriceTable() {
        return idPriceTable;
    }

    public void setIdPriceTable(Integer idPriceTable) {
        this.idPriceTable = idPriceTable;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
        hash += (idPriceTable != null ? idPriceTable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PriceTable)) {
            return false;
        }
        PriceTable other = (PriceTable) object;
        if ((this.idPriceTable == null && other.idPriceTable != null) || (this.idPriceTable != null && !this.idPriceTable.equals(other.idPriceTable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.PriceTable[idPriceTable=" + idPriceTable + "]";
    }

}
