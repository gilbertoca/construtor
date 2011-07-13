package park.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * Sometimes can appear clients that are not in or can't wait to be registered in our database, for 
 * these cases we will create and use a "UNKNOWN CLIENT", but internally we will register the
 * license plate and the model of the client's vehicle. So, the "UNKNOWN CLIENT" can
 * have several vehicles linked to him.
 * The "UNKNOWN CLIENT" client will be initialized the first time when setting up the webapp(park.init.sql)
 * @author gilberto
 */
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
