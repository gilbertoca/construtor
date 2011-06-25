package park.model.auto;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;

import park.model.Person;
import park.model.Vehicle;

/**
 * Class _Customer was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Customer extends CayenneDataObject {

    public static final String ID_PROPERTY = "id";
    public static final String PAYMENT_DAY_PROPERTY = "paymentDay";
    public static final String TO_PERSON_PROPERTY = "toPerson";
    public static final String VEHICLES_PROPERTY = "vehicles";

    public static final String ID_PK_COLUMN = "ID";

    public void setId(Long id) {
        writeProperty("id", id);
    }
    public Long getId() {
        return (Long)readProperty("id");
    }

    public void setPaymentDay(Integer paymentDay) {
        writeProperty("paymentDay", paymentDay);
    }
    public Integer getPaymentDay() {
        return (Integer)readProperty("paymentDay");
    }

    public void setToPerson(Person toPerson) {
        setToOneTarget("toPerson", toPerson, true);
    }

    public Person getToPerson() {
        return (Person)readProperty("toPerson");
    }


    public void addToVehicles(Vehicle obj) {
        addToManyTarget("vehicles", obj, true);
    }
    public void removeFromVehicles(Vehicle obj) {
        removeToManyTarget("vehicles", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<Vehicle> getVehicles() {
        return (List<Vehicle>)readProperty("vehicles");
    }


}