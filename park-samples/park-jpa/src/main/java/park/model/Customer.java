package park.model;

import java.io.Serializable;
import java.util.Collection;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer paymentDay;
    private Collection<Vehicle> vehicles;
    private Person person;

    public Customer() { }
    public Integer getPaymentDay() {return paymentDay;}
    public void setPaymentDay(Integer paymentDay) {this.paymentDay = paymentDay;}
    public Collection<Vehicle> getVehicles() {return vehicles;}
    public void setVehicles(Collection<Vehicle> vehicles) {this.vehicles = vehicles;}
    public Person getPerson() { return person;}
    public void setPerson(Person person) {this.person = person;}

}
