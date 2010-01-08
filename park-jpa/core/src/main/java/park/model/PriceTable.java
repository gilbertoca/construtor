/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package park.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "price_table")
@NamedQueries({
    @NamedQuery(name = "PriceTable.findAll", query = "SELECT p FROM PriceTable p")})
public class PriceTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cd_price_table")
    private Integer cdPriceTable;
    @Basic(optional = false)
    @Column(name = "item")
    private String item;
    @Basic(optional = false)
    @Column(name = "price")
    private BigInteger price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdPriceTable")
    private Collection<Vehicle> vehicleCollection;

    public PriceTable() {
    }

    public PriceTable(Integer cdPriceTable) {
        this.cdPriceTable = cdPriceTable;
    }

    public PriceTable(Integer cdPriceTable, String item, BigInteger price) {
        this.cdPriceTable = cdPriceTable;
        this.item = item;
        this.price = price;
    }

    public Integer getCdPriceTable() {
        return cdPriceTable;
    }

    public void setCdPriceTable(Integer cdPriceTable) {
        this.cdPriceTable = cdPriceTable;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
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
        hash += (cdPriceTable != null ? cdPriceTable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PriceTable)) {
            return false;
        }
        PriceTable other = (PriceTable) object;
        if ((this.cdPriceTable == null && other.cdPriceTable != null) || (this.cdPriceTable != null && !this.cdPriceTable.equals(other.cdPriceTable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.PriceTable[cdPriceTable=" + cdPriceTable + "]";
    }

}
