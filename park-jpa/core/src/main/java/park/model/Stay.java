/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package park.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "stay")
@NamedQueries({
    @NamedQuery(name = "Stay.findAll", query = "SELECT s FROM Stay s")})
public class Stay implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cd_stay")
    private Integer cdStay;
    @Basic(optional = false)
    @Column(name = "dt_entrance")
    @Temporal(TemporalType.DATE)
    private Date dtEntrance;
    @Column(name = "dt_outgoing")
    @Temporal(TemporalType.DATE)
    private Date dtOutgoing;
    @Basic(optional = false)
    @Column(name = "hr_entrance")
    @Temporal(TemporalType.TIME)
    private Date hrEntrance;
    @Column(name = "hr_outgoing")
    @Temporal(TemporalType.TIME)
    private Date hrOutgoing;
    @Column(name = "status")
    private Short status;
    @Column(name = "price")
    private BigInteger price;
    @JoinColumn(name = "cd_employee_entrance", referencedColumnName = "cd_employee")
    @ManyToOne(optional = false)
    private Employee cdEmployeeEntrance;
    @JoinColumn(name = "cd_employee_outgoing", referencedColumnName = "cd_employee")
    @ManyToOne(optional = false)
    private Employee cdEmployeeOutgoing;
    @JoinColumn(name = "cd_parking", referencedColumnName = "cd_parking")
    @ManyToOne(optional = false)
    private Parking cdParking;
    @JoinColumn(name = "license_plate", referencedColumnName = "license_plate")
    @ManyToOne(optional = false)
    private Vehicle licensePlate;

    public Stay() {
    }

    public Stay(Integer cdStay) {
        this.cdStay = cdStay;
    }

    public Stay(Integer cdStay, Date dtEntrance, Date hrEntrance) {
        this.cdStay = cdStay;
        this.dtEntrance = dtEntrance;
        this.hrEntrance = hrEntrance;
    }

    public Integer getCdStay() {
        return cdStay;
    }

    public void setCdStay(Integer cdStay) {
        this.cdStay = cdStay;
    }

    public Date getDtEntrance() {
        return dtEntrance;
    }

    public void setDtEntrance(Date dtEntrance) {
        this.dtEntrance = dtEntrance;
    }

    public Date getDtOutgoing() {
        return dtOutgoing;
    }

    public void setDtOutgoing(Date dtOutgoing) {
        this.dtOutgoing = dtOutgoing;
    }

    public Date getHrEntrance() {
        return hrEntrance;
    }

    public void setHrEntrance(Date hrEntrance) {
        this.hrEntrance = hrEntrance;
    }

    public Date getHrOutgoing() {
        return hrOutgoing;
    }

    public void setHrOutgoing(Date hrOutgoing) {
        this.hrOutgoing = hrOutgoing;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public Employee getCdEmployeeEntrance() {
        return cdEmployeeEntrance;
    }

    public void setCdEmployeeEntrance(Employee cdEmployeeEntrance) {
        this.cdEmployeeEntrance = cdEmployeeEntrance;
    }

    public Employee getCdEmployeeOutgoing() {
        return cdEmployeeOutgoing;
    }

    public void setCdEmployeeOutgoing(Employee cdEmployeeOutgoing) {
        this.cdEmployeeOutgoing = cdEmployeeOutgoing;
    }

    public Parking getCdParking() {
        return cdParking;
    }

    public void setCdParking(Parking cdParking) {
        this.cdParking = cdParking;
    }

    public Vehicle getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(Vehicle licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdStay != null ? cdStay.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stay)) {
            return false;
        }
        Stay other = (Stay) object;
        if ((this.cdStay == null && other.cdStay != null) || (this.cdStay != null && !this.cdStay.equals(other.cdStay))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.Stay[cdStay=" + cdStay + "]";
    }

}
