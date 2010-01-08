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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "vehicle_type")
@NamedQueries({
    @NamedQuery(name = "VehicleType.findAll", query = "SELECT v FROM VehicleType v")})
public class VehicleType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "v_type")
    private String vType;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "model")
    private String model;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vType")
    private Collection<Vehicle> vehicleCollection;

    public VehicleType() {
    }

    public VehicleType(String vType) {
        this.vType = vType;
    }

    public String getVType() {
        return vType;
    }

    public void setVType(String vType) {
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

    public Collection<Vehicle> getVehicleCollection() {
        return vehicleCollection;
    }

    public void setVehicleCollection(Collection<Vehicle> vehicleCollection) {
        this.vehicleCollection = vehicleCollection;
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
        return "park.model.VehicleType[vType=" + vType + "]";
    }

}
