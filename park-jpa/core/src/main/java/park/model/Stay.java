/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package park.model;

import java.io.Serializable;
import java.util.Date;

public class Stay implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idStay;
    private Date dtEntrance;
    private Date dtOutgoing;
    private Date hrEntrance;
    private Date hrOutgoing;
    /**
     * Can be:
     * E - Entrance;
     * O - Outgoing;
     * C - Cancelled.
     */
    private char status;
    private Double price;
    private Employee idEmployeeEntrance;
    private Employee idEmployeeOutgoing;
    private Parking idParking;
    private Vehicle licensePlate;

    public Stay() {
    }

    public Stay(Integer idStay) {
        this.idStay = idStay;
    }

    public Stay(Integer idStay, Date dtEntrance, Date hrEntrance) {
        this.idStay = idStay;
        this.dtEntrance = dtEntrance;
        this.hrEntrance = hrEntrance;
    }

    public Integer getIdStay() {
        return idStay;
    }

    public void setIdStay(Integer idStay) {
        this.idStay = idStay;
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

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Employee getIdEmployeeEntrance() {
        return idEmployeeEntrance;
    }

    public void setIdEmployeeEntrance(Employee idEmployeeEntrance) {
        this.idEmployeeEntrance = idEmployeeEntrance;
    }

    public Employee getIdEmployeeOutgoing() {
        return idEmployeeOutgoing;
    }

    public void setIdEmployeeOutgoing(Employee idEmployeeOutgoing) {
        this.idEmployeeOutgoing = idEmployeeOutgoing;
    }

    public Parking getIdParking() {
        return idParking;
    }

    public void setIdParking(Parking idParking) {
        this.idParking = idParking;
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
        hash += (idStay != null ? idStay.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stay)) {
            return false;
        }
        Stay other = (Stay) object;
        if ((this.idStay == null && other.idStay != null) || (this.idStay != null && !this.idStay.equals(other.idStay))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.Stay[idStay=" + idStay + "]";
    }

}
