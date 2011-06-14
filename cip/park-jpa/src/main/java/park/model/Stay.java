/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package park.model;

import java.io.Serializable;
import java.util.Date;

public class Stay implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
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
    private StayStatus status = StayStatus.ENTRANCE;
    private Double totalPrice;
    private Employee employeeEntrance;
    private Employee employeeOutgoing;
    private Parking parking;
    private Vehicle vehicle;

    public Stay() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public StayStatus getStatus() {
        return status;
    }

    public void setStatus(StayStatus status) {
        this.status = status;
    }
    public Double calcTotal(){
        return null;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Employee getEmployeeEntrance() {
        return employeeEntrance;
    }

    public void setEmployeeEntrance(Employee employeeEntrance) {
        this.employeeEntrance = employeeEntrance;
    }

    public Employee getEmployeeOutgoing() {
        return employeeOutgoing;
    }

    public void setEmployeeOutgoing(Employee employeeOutgoing) {
        this.employeeOutgoing = employeeOutgoing;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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
        if (!(object instanceof Stay)) {
            return false;
        }
        Stay other = (Stay) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "park.model.Stay[id=" + id + "]";
    }

}
