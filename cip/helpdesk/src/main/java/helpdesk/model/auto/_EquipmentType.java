package helpdesk.model.auto;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;

import helpdesk.model.Equipment;

/**
 * Class _EquipmentType was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _EquipmentType extends CayenneDataObject {

    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String EQUIPMENT_TYPES_PROPERTY = "equipmentTypes";

    public static final String EQUIPMENT_TYPE_ID_PK_COLUMN = "equipment_type_id";

    public void setDescription(String description) {
        writeProperty("description", description);
    }
    public String getDescription() {
        return (String)readProperty("description");
    }

    public void addToEquipmentTypes(Equipment obj) {
        addToManyTarget("equipmentTypes", obj, true);
    }
    public void removeFromEquipmentTypes(Equipment obj) {
        removeToManyTarget("equipmentTypes", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<Equipment> getEquipmentTypes() {
        return (List<Equipment>)readProperty("equipmentTypes");
    }


}
