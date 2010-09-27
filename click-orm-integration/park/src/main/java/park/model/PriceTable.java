package park.model;

import java.io.Serializable;

public class PriceTable implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String item;
    private Double price;

    public PriceTable() {
    }

    public PriceTable(String item, Double price) {
        this.item = item;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PriceTable)) {
            return false;
        }
        PriceTable other = (PriceTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.PriceTable[id=" + id + "]";
    }

}
