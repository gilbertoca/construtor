package park.model.auto;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.cayenne.CayenneDataObject;

import park.model.Employee;
import park.model.Parking;
import park.model.Vehicle;

/**
 * Class _Stay was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Stay extends CayenneDataObject {

    public static final String DT_ENTRANCE_PROPERTY = "dtEntrance";
    public static final String DT_OUTGOING_PROPERTY = "dtOutgoing";
    public static final String HR_ENTRANCE_PROPERTY = "hrEntrance";
    public static final String HR_OUTGOING_PROPERTY = "hrOutgoing";
    public static final String ID_PROPERTY = "id";
    public static final String STATUS_PROPERTY = "status";
    public static final String TOTAL_PRICE_PROPERTY = "totalPrice";
    public static final String VERSION_PROPERTY = "version";
    public static final String PARKING_PROPERTY = "parking";
    public static final String TO_EMPLOYEE_PROPERTY = "toEmployee";
    public static final String TO_EMPLOYEE1_PROPERTY = "toEmployee1";
    public static final String TO_VEHICLE_PROPERTY = "toVehicle";

    public static final String ID_PK_COLUMN = "ID";

    public void setDtEntrance(Date dtEntrance) {
        writeProperty("dtEntrance", dtEntrance);
    }
    public Date getDtEntrance() {
        return (Date)readProperty("dtEntrance");
    }

    public void setDtOutgoing(Date dtOutgoing) {
        writeProperty("dtOutgoing", dtOutgoing);
    }
    public Date getDtOutgoing() {
        return (Date)readProperty("dtOutgoing");
    }

    public void setHrEntrance(Date hrEntrance) {
        writeProperty("hrEntrance", hrEntrance);
    }
    public Date getHrEntrance() {
        return (Date)readProperty("hrEntrance");
    }

    public void setHrOutgoing(Date hrOutgoing) {
        writeProperty("hrOutgoing", hrOutgoing);
    }
    public Date getHrOutgoing() {
        return (Date)readProperty("hrOutgoing");
    }

    public void setId(Integer id) {
        writeProperty("id", id);
    }
    public Integer getId() {
        return (Integer)readProperty("id");
    }

    public void setStatus(String status) {
        writeProperty("status", status);
    }
    public String getStatus() {
        return (String)readProperty("status");
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        writeProperty("totalPrice", totalPrice);
    }
    public BigDecimal getTotalPrice() {
        return (BigDecimal)readProperty("totalPrice");
    }

    public void setVersion(Integer version) {
        writeProperty("version", version);
    }
    public Integer getVersion() {
        return (Integer)readProperty("version");
    }

    public void setParking(Parking parking) {
        setToOneTarget("parking", parking, true);
    }

    public Parking getParking() {
        return (Parking)readProperty("parking");
    }


    public void setToEmployee(Employee toEmployee) {
        setToOneTarget("toEmployee", toEmployee, true);
    }

    public Employee getToEmployee() {
        return (Employee)readProperty("toEmployee");
    }


    public void setToEmployee1(Employee toEmployee1) {
        setToOneTarget("toEmployee1", toEmployee1, true);
    }

    public Employee getToEmployee1() {
        return (Employee)readProperty("toEmployee1");
    }


    public void setToVehicle(Vehicle toVehicle) {
        setToOneTarget("toVehicle", toVehicle, true);
    }

    public Vehicle getToVehicle() {
        return (Vehicle)readProperty("toVehicle");
    }


}