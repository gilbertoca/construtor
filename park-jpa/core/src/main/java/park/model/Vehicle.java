/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package park.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "vehicle")
@NamedQueries({
    @NamedQuery(name = "Vehicle.findAll", query = "SELECT v FROM Vehicle v")})
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "license_plate")
    private String licensePlate;
    @Column(name = "color")
    private String color;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "licensePlate")
    private Collection<Stay> stayCollection;
    @JoinColumn(name = "cd_customer", referencedColumnName = "cd_customer")
    @ManyToOne(optional = false)
    private Customer cdCustomer;
    @JoinColumn(name = "cd_price_table", referencedColumnName = "cd_price_table")
    @ManyToOne(optional = false)
    private PriceTable cdPriceTable;
    @JoinColumn(name = "v_type", referencedColumnName = "v_type")
    @ManyToOne(optional = false)
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

    public Customer getCdCustomer() {
        return cdCustomer;
    }

    public void setCdCustomer(Customer cdCustomer) {
        this.cdCustomer = cdCustomer;
    }

    public PriceTable getCdPriceTable() {
        return cdPriceTable;
    }

    public void setCdPriceTable(PriceTable cdPriceTable) {
        this.cdPriceTable = cdPriceTable;
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
