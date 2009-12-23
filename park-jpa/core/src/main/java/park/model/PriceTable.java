package park.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the pricetable database table.
 * 
 */
@Entity
@Table(name = "PRICE_TABLE")
@NamedQueries({
    @NamedQuery(name = PriceTable.FIND_ALL, query = "SELECT pt FROM PriceTable pt")
})
public class PriceTable implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "findAllPriceTable";
    @Id
    @Column(name = "CD_PRICE_TABLE" )
    @GeneratedValue
    private Integer cdPriceTable;
    @Column(nullable = false, length = 50)
    private String item;
    @Column(nullable = false, precision = 12, scale = 2)
    private Double price;

    public PriceTable() {
    }

    public PriceTable(String item, Double price) {
        this.item = item;
        this.price = price;
    }

    public Integer getCdPriceTable() {
        return this.cdPriceTable;
    }

    public void setCdPriceTable(Integer cdPriceTable) {
        this.cdPriceTable = cdPriceTable;
    }

    public String getItem() {
        return this.item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(double d) {
        this.price = d;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cdPriceTable == null) ? 0 : cdPriceTable.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriceTable other = (PriceTable) obj;
		if (cdPriceTable == null) {
			if (other.cdPriceTable != null)
				return false;
		} else if (!cdPriceTable.equals(other.cdPriceTable))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PriceTable [");
		if (getCdPriceTable() != null)
			builder.append("getCdPriceTable()=").append(getCdPriceTable())
					.append(", ");
		if (getItem() != null)
			builder.append("getItem()=").append(getItem()).append(", ");
		if (getPrice() != null)
			builder.append("getPrice()=").append(getPrice()).append(", ");
		builder.append("hashCode()=").append(hashCode()).append("]");
		return builder.toString();
	}
    
}
