package park.model.auto;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;

import park.model.Vehicle;

/**
 * Class _VehicleType was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _VehicleType extends CayenneDataObject {

    public static final String MANUFACTURER_PROPERTY = "manufacturer";
    public static final String MODEL_PROPERTY = "model";
    public static final String VEHICLE_TYPE_PROPERTY = "vehicleType";
    public static final String VEHICLES_PROPERTY = "vehicles";

    public static final String V_TYPE_PK_COLUMN = "V_TYPE";

    public void setManufacturer(String manufacturer) {
        writeProperty(MANUFACTURER_PROPERTY, manufacturer);
    }
    public String getManufacturer() {
        return (String)readProperty(MANUFACTURER_PROPERTY);
    }

    public void setModel(String model) {
        writeProperty(MODEL_PROPERTY, model);
    }
    public String getModel() {
        return (String)readProperty(MODEL_PROPERTY);
    }

    public void setVehicleType(String vehicleType) {
        writeProperty(VEHICLE_TYPE_PROPERTY, vehicleType);
    }
    public String getVehicleType() {
        return (String)readProperty(VEHICLE_TYPE_PROPERTY);
    }

    public void addToVehicles(Vehicle obj) {
        addToManyTarget(VEHICLES_PROPERTY, obj, true);
    }
    public void removeFromVehicles(Vehicle obj) {
        removeToManyTarget(VEHICLES_PROPERTY, obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<Vehicle> getVehicles() {
        return (List<Vehicle>)readProperty(VEHICLES_PROPERTY);
    }


}
