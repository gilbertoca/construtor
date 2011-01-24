package helpdesk.model.auto;

import java.util.Date;

import org.apache.cayenne.CayenneDataObject;

import helpdesk.model.EquipmentType;

/**
 * Class _Equipment was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Equipment extends CayenneDataObject {

    public static final String DT_ACQUIRED_PROPERTY = "dtAcquired";
    public static final String DT_DISPOSED_PROPERTY = "dtDisposed";
    public static final String EQUIPMENT_CODE_PROPERTY = "equipmentCode";
    public static final String MANUFACTURER_NAME_PROPERTY = "manufacturerName";
    public static final String OTHER_DETAILS_PROPERTY = "otherDetails";
    public static final String EQUIPMENT_PROPERTY = "equipment";

    public static final String EQUIPMENT_ID_PK_COLUMN = "equipment_id";
    public static final String EQUIPMENT_NAME_PK_COLUMN = "equipment_name";

    public void setDtAcquired(Date dtAcquired) {
        writeProperty("dtAcquired", dtAcquired);
    }
    public Date getDtAcquired() {
        return (Date)readProperty("dtAcquired");
    }

    public void setDtDisposed(Date dtDisposed) {
        writeProperty("dtDisposed", dtDisposed);
    }
    public Date getDtDisposed() {
        return (Date)readProperty("dtDisposed");
    }

    public void setEquipmentCode(String equipmentCode) {
        writeProperty("equipmentCode", equipmentCode);
    }
    public String getEquipmentCode() {
        return (String)readProperty("equipmentCode");
    }

    public void setManufacturerName(String manufacturerName) {
        writeProperty("manufacturerName", manufacturerName);
    }
    public String getManufacturerName() {
        return (String)readProperty("manufacturerName");
    }

    public void setOtherDetails(String otherDetails) {
        writeProperty("otherDetails", otherDetails);
    }
    public String getOtherDetails() {
        return (String)readProperty("otherDetails");
    }

    public void setEquipment(EquipmentType equipment) {
        setToOneTarget("equipment", equipment, true);
    }

    public EquipmentType getEquipment() {
        return (EquipmentType)readProperty("equipment");
    }


}
